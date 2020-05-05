package com.moor.shelflyfe.ui.explore


import android.graphics.Rect
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mancj.materialsearchbar.MaterialSearchBar

import com.moor.shelflyfe.databinding.ExploreFragmentBinding
import com.moor.shelflyfe.ui.Book
import org.koin.android.viewmodel.ext.android.viewModel

class ExploreFragment : Fragment(), MaterialSearchBar.OnSearchActionListener {


    private lateinit var binding: ExploreFragmentBinding
    private  val viewModel: ExploreViewModel by viewModel()
    private  val results= arrayListOf<Book>()
    private  val genreAdapter= GenreAdapter(GENERES)
    private   val searchAdapter=SearchAdapter(results)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.searchResults().observe(viewLifecycleOwner, Observer { books->
            this.results.clear()
            this.results.addAll(books)
            searchAdapter.notifyDataSetChanged()
        })
//
//        viewModel.featured.observe(viewLifecycleOwner, Observer {books->
//            binding.viewPager.adapter=
//                FeaturedAdapter(books)
//        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= ExploreFragmentBinding.inflate(inflater,container,false)
        binding.genres.apply {
            adapter= genreAdapter
            layoutManager= LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        }

        binding.results.apply {
            adapter= searchAdapter
            layoutManager= LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        }
        binding.searchBar.setOnSearchActionListener(this)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onButtonClicked(buttonCode: Int) {
     //   TODO("Not yet implemented")
    }

    override fun onSearchStateChanged(enabled: Boolean) {
        binding.flipper.showNext()
//        if(enabled){
//            binding.flipper.showNext()
//        }else{
//
//        }
    }

    override fun onSearchConfirmed(text: CharSequence?) {
        viewModel.search(text.toString())
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.explore_menu,menu);
//    }

}



