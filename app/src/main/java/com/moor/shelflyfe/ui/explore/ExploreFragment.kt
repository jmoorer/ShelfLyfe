package com.moor.shelflyfe.ui.explore


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.moor.shelflyfe.R
import com.moor.shelflyfe.databinding.ExploreFragmentBinding
import com.moor.shelflyfe.ui.Section
import com.moor.shelflyfe.ui.home.SectionAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class ExploreFragment : Fragment(), Toolbar.OnMenuItemClickListener {


    private lateinit var binding: ExploreFragmentBinding
    private  val viewModel: ExploreViewModel by viewModel()
    private val  sections = arrayListOf<Section>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.featured.observe(viewLifecycleOwner, Observer {books->
            binding.viewPager.adapter= FeaturedAdapter(books)
        })

        viewModel.sections.observe(viewLifecycleOwner, Observer { section->
            sections.add(section)
            binding.sections.adapter?.notifyDataSetChanged()
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= ExploreFragmentBinding.inflate(inflater,container,false)
        binding.toolbar.apply {
            inflateMenu(R.menu.explore_menu)
            setOnMenuItemClickListener(this@ExploreFragment)
        }

        binding.sections.apply {
            adapter= SectionAdapter(sections)
            layoutManager= LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                ))
        }




        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_search->{
              findNavController().navigate(R.id.action_exploreFragment_to_searchFragment)
            }
        }
        return false
    }


//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.explore_menu,menu);
//    }

}



