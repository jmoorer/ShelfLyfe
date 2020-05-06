package com.moor.shelflyfe.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.moor.shelflyfe.databinding.ListFragmentBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel


class ListFragment : Fragment() {

    private lateinit var adapter: ListAdapter
    private lateinit var binding: ListFragmentBinding

    private val args: ListFragmentArgs by navArgs()
    private  val listViewModel:ListViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=ListFragmentBinding.inflate(inflater,container,false)
        binding.listItems.apply {
            adapter= this@ListFragment.adapter
            layoutManager= LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        args.title?.let {
            binding.toolbar.title=it
        }
        binding.toolbar.setNavigationOnClickListener {
          findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter= ListAdapter(args.items)
        adapter.listener={
            listViewModel.select(it)
        }
    }

}
