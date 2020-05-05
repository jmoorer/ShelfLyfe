package com.moor.shelflyfe.ui.explore


import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.moor.shelflyfe.R

import com.moor.shelflyfe.databinding.ExploreFragmentBinding
import com.moor.shelflyfe.ui.home.Category
import com.moor.shelflyfe.ui.home.FeaturedAdapter
import com.moor.shelflyfe.ui.home.HomeViewModel
import com.moor.shelflyfe.ui.home.SpacesItemDecoration
import org.koin.android.viewmodel.ext.android.viewModel

class ExploreFragment : Fragment() {


    private lateinit var binding: ExploreFragmentBinding
    private  val viewModel: ExploreViewModel by viewModel()
    val hvm:HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= ExploreFragmentBinding.inflate(inflater,container,false)
        binding.categories.apply {
            adapter= CategoryAdapter(
                listOf(
                    Category("Fiction"),
                    Category("Non fiction"),
                    Category("Science"),
                    Category("Fiction"),
                    Category("Non fiction"),
                    Category("Science"),
                    Category("Fiction"),
                    Category("Non fiction"),
                    Category("Science")

                )
            )
            layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            addItemDecoration(SpacesItemDecoration(8))
        }
//        hvm.getFeatured().observe(viewLifecycleOwner, Observer {books->
//            binding.viewPager.adapter= FeaturedAdapter(books)
//        })
       // binding.toolbar.inflateMenu(R.menu.explore_menu)

        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.explore_menu,menu);
//    }

}
