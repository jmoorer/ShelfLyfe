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
import com.moor.shelflyfe.toDisplayCase
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.OnBookClickListner
import com.moor.shelflyfe.ui.booklist.BookListViewModel
import com.moor.shelflyfe.ui.SectionAdapter
import com.moor.shelflyfe.ui.SpacesItemDecoration
import com.moor.shelflyfe.ui.list.ListFragmentDirections
import com.moor.shelflyfe.ui.list.ListItem
import com.moor.shelflyfe.ui.list.ListViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel


class ExploreFragment : Fragment(), Toolbar.OnMenuItemClickListener,
    OnBookClickListner {


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
                adapter = cats?.let { CategoryAdapter(it)  }
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
            setOnMenuItemClickListener(this@ExploreFragment)
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
//        binding.genres.setOnClickListener {
//            val items=GENERES.map { ListItem(it.key,it.key.toDisplayCase()) }.toTypedArray()
//            val action=ExploreFragmentDirections.actionOpenList(items,"Genres")
//            val navController = findNavController();
//            navController.navigate(action)
//            val dest= navController.getBackStackEntry(R.id.listFragment)
//            listViewModel.getSelected().observe(dest, Observer { item->
//               getBooksByGenre(item.key)
//            })
//        }
//        binding.bestSellers.setOnClickListener {
//
//            val action=ExploreFragmentDirections.actionOpenList(bestSellerList.toTypedArray(),"Best Seller Lists")
//            val navController = findNavController();
//            navController.navigate(action)
//            val dest= navController.getBackStackEntry(R.id.listFragment)
//            listViewModel.getSelected().observe(dest, Observer { item->
//                getBooksByBestSellerList(item)
//            })
//        }

        return binding.root
    }

    private fun getBooksByGenre(key: String) {
        bookListViewModel.loadBooksByGenre(key)
        val action=ListFragmentDirections.actionListFragmentToBookListFragment(key.toDisplayCase())
        findNavController().navigate(action)
    }

    private fun getBooksByBestSellerList(item: ListItem){
        bookListViewModel.loadBooksByBestSellerList(item.key)
        val action=ListFragmentDirections.actionListFragmentToBookListFragment(item.value)
        findNavController().navigate(action)
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

    override fun onClick(book: Book) {
        val action=ExploreFragmentDirections.actionExploreFragmentToBookDetailFragment(book)
        findNavController().navigate(action)
    }


}



