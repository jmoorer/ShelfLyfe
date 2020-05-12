package com.moor.shelflyfe.ui.subject

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator

import com.moor.shelflyfe.R
import com.moor.shelflyfe.databinding.SubjectFragmentBinding
import com.moor.shelflyfe.ui.list.ListFragment
import com.moor.shelflyfe.ui.list.ListItem
import org.koin.android.viewmodel.ext.android.viewModel

class SubjectFragment : Fragment() {


    private lateinit  var binding: SubjectFragmentBinding
    private val viewModel: SubjectViewModel by viewModel()

    private  val args:SubjectFragmentArgs by navArgs()
    private  val pages= arrayListOf<Collection>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SubjectFragmentBinding.inflate(inflater,container,false)
        binding.toolbar.apply {
            setNavigationOnClickListener {
              findNavController().popBackStack()
            }
            title=args.subject.value
        }
        binding.pager.adapter= CollectionAdapter(this,pages)
        TabLayoutMediator(binding.tabLayout,binding.pager) { tab, position ->
            tab.text = pages[position].title
        }.attach()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadSubject(args.subject.key).observe(viewLifecycleOwner, Observer { res->
            res.authors?.map { ListItem(it.key,it.name) }?.let { authors->
                pages.add(Collection("Authors",authors))
            }

            res.publishers?.map { ListItem(it.key,it.name) }?.let { authors->
                pages.add(Collection("Publishers",authors))
            }

            binding.pager.adapter?.notifyDataSetChanged()
        })

    }

    data class Collection(val title:String,val items:List<ListItem>)

    class CollectionAdapter(fragment: Fragment, val collections:List<Collection>) : FragmentStateAdapter(fragment) {

        override fun getItemCount()=collections.size

        override fun createFragment(position: Int): Fragment {
            // Return a NEW fragment instance in createFragment(int)
            return ListFragment().apply {
                arguments = Bundle().apply {
                    var items=collections[position].items.toTypedArray()
                    putParcelableArray("items",items)
                }
            }
        }
    }
}
