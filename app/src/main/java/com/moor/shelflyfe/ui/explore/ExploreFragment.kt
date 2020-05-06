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
import com.moor.shelflyfe.ui.booklist.BookListViewModel
import com.moor.shelflyfe.ui.home.SectionAdapter
import com.moor.shelflyfe.ui.list.ListFragmentDirections
import com.moor.shelflyfe.ui.list.ListItem
import com.moor.shelflyfe.ui.list.ListViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel


class ExploreFragment : Fragment(), Toolbar.OnMenuItemClickListener {


    private lateinit var binding: ExploreFragmentBinding
    private  val viewModel: ExploreViewModel by sharedViewModel()
    private  val listViewModel:ListViewModel by sharedViewModel()
    private  val bookListViewModel:BookListViewModel by sharedViewModel()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.featured.observe(viewLifecycleOwner, Observer {books->
            binding.viewPager.adapter= FeaturedAdapter(books)
        })

        viewModel.sections.observe(viewLifecycleOwner, Observer { sections->
            binding.sections.adapter=SectionAdapter(sections)
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

            layoutManager= LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                ))
        }
        binding.genres.setOnClickListener {
            val items=GENERES.map { ListItem(it.key,it.key.toDisplayCase()) }.toTypedArray()
            val action=ExploreFragmentDirections.actionOpenList(items,"Genres")
            val navController = findNavController();
            navController.navigate(action)
            val dest= navController.getBackStackEntry(R.id.listFragment)
            listViewModel.getSelected().observe(dest, Observer { item->
               getBooksByGenre(item.key)
            })
        }
        binding.bestSellers.setOnClickListener {
            val items= listOf(ListItem("AB","AB")).toTypedArray()
            val action=ExploreFragmentDirections.actionOpenList(items,"Best Seller Lists")
            val navController = findNavController();
            navController.navigate(action)
            val dest= navController.getBackStackEntry(R.id.listFragment)
            listViewModel.getSelected().observe(dest, Observer { item->

            })
        }

        return binding.root
    }

    private fun getBooksByGenre(key: String) {
        bookListViewModel.loadBooksByGenre(key)
        val action=ListFragmentDirections.actionListFragmentToBookListFragment(key.toDisplayCase())
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


}



