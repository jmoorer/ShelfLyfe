package com.moor.shelflyfe.ui.explore


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.moor.shelflyfe.R
import com.moor.shelflyfe.databinding.ExploreFragmentBinding
import com.moor.shelflyfe.toDisplayCase
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.OnBookClickListner
import com.moor.shelflyfe.ui.booklist.BookListViewModel
import com.moor.shelflyfe.ui.SectionAdapter
import com.moor.shelflyfe.ui.SpacesItemDecoration

import com.moor.shelflyfe.ui.list.ListItem
import com.moor.shelflyfe.ui.list.ListViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel


class ExploreFragment : Fragment(), OnBookClickListner, CategoryAdapter.OnGenreClickListener {


    private lateinit var binding: ExploreFragmentBinding
    private  val viewModel: ExploreViewModel by sharedViewModel()
    private  val listViewModel:ListViewModel by sharedViewModel()
    private  val bookListViewModel:BookListViewModel by sharedViewModel()
    private  var bestSellerList= listOf<ListItem>()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        viewModel.featured.observe(viewLifecycleOwner, Observer {books->
//            binding.viewPager.adapter= FeaturedAdapter(books).apply {
//                listener= this@ExploreFragment
//            }
//        })

        viewModel.sections.observe(viewLifecycleOwner, Observer { sections->
           binding.sections.adapter= SectionAdapter(sections).apply {
               listener= this@ExploreFragment
           }
        })

        viewModel.bestSellerList.observe(viewLifecycleOwner, Observer {  lists->
             lists?.let {
                 this.bestSellerList =  it.map { ListItem(it.listNameEncoded!!,it.displayName!!) }.toMutableList()
            }
        })
        viewModel.trendingCategories.observe(viewLifecycleOwner, Observer { cats->
            binding.categories.apply {
                adapter = cats?.let { CategoryAdapter(it).apply {
                  listener= this@ExploreFragment
                } }
                layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                addItemDecoration(SpacesItemDecoration(8))
            }

        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= ExploreFragmentBinding.inflate(inflater,container,false)

        binding.toolbar.apply {
            inflateMenu(R.menu.explore_menu)
            //setOnMenuItemClickListener(this@ExploreFragment)
        }
//
        binding.sections.apply {

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



    override fun onClick(book: Book) {
        val action=ExploreFragmentDirections.actionExploreFragmentToBookDetailFragment(book.isbn!!)
        findNavController().navigate(action)
    }

    override fun onGenreClick(genre: Genre) {
        findNavController().navigate(R.id.bookListFragment, bundleOf("title" to genre.name) )
    }


}



