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


class ListFragment : Fragment(), ListAdapter.OnClickListener {

    private lateinit var adapter: ListAdapter
    private lateinit var binding: ListFragmentBinding


    private  val listViewModel:ListViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val items= arguments?.getParcelableArray("items") as Array<ListItem>

        adapter= ListAdapter(items)

        adapter.listener=this
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

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onClick(listItem: ListItem) {
        listViewModel.select(listItem)
    }

}
