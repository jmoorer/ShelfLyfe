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
import com.google.android.material.tabs.TabLayoutMediator
import com.moor.shelflyfe.R
import com.moor.shelflyfe.creatLoadingDialog
import com.moor.shelflyfe.databinding.ExploreFragmentBinding
import com.moor.shelflyfe.db.Trending
import com.moor.shelflyfe.toDisplayCase
import com.moor.shelflyfe.ui.*
import com.moor.shelflyfe.ui.booklist.BookListViewModel
import com.moor.shelflyfe.ui.list.ListAdapter

import com.moor.shelflyfe.ui.list.ListItem
import com.moor.shelflyfe.ui.list.ListViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel


class ExploreFragment : Fragment(), OnBookClickListner, CategoryAdapter.OnGenreClickListener,
    OnItemClickListener<Trending> {


    private lateinit var binding: ExploreFragmentBinding
    private  val viewModel: ExploreViewModel by sharedViewModel()
    private  val bookListViewModel:BookListViewModel by sharedViewModel()



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.featured.observe(viewLifecycleOwner, Observer {books->
            binding.apply {
                viewPager.adapter= FeaturedAdapter(books.take(5)).apply {
                    listener= this@ExploreFragment
                    registerAdapterDataObserver(indicator.adapterDataObserver)
                }
                indicator.setViewPager(viewPager)
            }
        })

        viewModel.sections.observe(viewLifecycleOwner, Observer { sections->
           binding.sectionLoader.visibility= View.GONE
           binding.sections.adapter= SectionAdapter(sections).apply {
               listener= this@ExploreFragment

           }
            binding.sections.layoutManager= LinearLayoutManager(context)
        })

        viewModel.bestSellerList.observe(viewLifecycleOwner, Observer {  lists->
             lists?.let {
                 val bestSellerList =  it.map { l-> ListItem(l.listNameEncoded!!,l.displayName!!) }.toTypedArray()
                 binding.sellerList.apply {
                     adapter= ListAdapter(bestSellerList).apply {
                         listener= object:ListAdapter.OnClickListener{
                             override fun onClick(listItem: ListItem) {
                                 findNavController().navigate(R.id.bookListFragment, bundleOf("title" to listItem.value))
                                 bookListViewModel.loadBooksByBestSellerList(listItem.key)
                             }
                         }

                     }
                     layoutManager= LinearLayoutManager(context)
                     addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
                 }
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
           // inflateMenu(R.menu.explore_menu)
            //setOnMenuItemClickListener(this@ExploreFragment)
        }
        binding.sectionLoader.visibility= View.VISIBLE

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
        bookListViewModel.loadBooksByGenre(genre.name.toLowerCase().split("&").first())
    }

    override fun onClick(book: Trending) {
        val action=ExploreFragmentDirections.actionExploreFragmentToBookDetailFragment(book.isbn13)
        findNavController().navigate(action)
    }


}



