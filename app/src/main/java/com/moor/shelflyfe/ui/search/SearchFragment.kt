package com.moor.shelflyfe.ui.search

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar

import com.moor.shelflyfe.databinding.SearchFragmentBinding
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.explore.ExploreFragmentDirections
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), MaterialSearchBar.OnSearchActionListener,
    SearchAdapter.OnItemClickListner {


    private val viewModel: SearchViewModel by viewModel()
    private lateinit var binding:SearchFragmentBinding
    private  val results= arrayListOf<Book>()
    private   val searchAdapter = SearchAdapter(results).apply {
        listener= this@SearchFragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar)

        // this will make it fullscreen without top status bar
        // setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= SearchFragmentBinding.inflate(inflater,container,false)
        binding.searchBar.apply {
            setOnSearchActionListener(this@SearchFragment)
            openSearch()
        }
        binding.results.apply {
            layoutManager=LinearLayoutManager(context)
            adapter= searchAdapter
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.searchResults().observe(viewLifecycleOwner, Observer { books->
            this.results.clear()
            this.results.addAll(books)
            searchAdapter.notifyDataSetChanged()
        })
    }

    override fun onButtonClicked(buttonCode: Int) {
        //dismiss()
    }

    override fun onSearchStateChanged(enabled: Boolean) {
        if (!enabled){
           findNavController().popBackStack()
        }
    }

    override fun onSearchConfirmed(text: CharSequence?) {
        viewModel.search(text.toString())
    }

    override fun onClick(book: Book) {
        val action= SearchFragmentDirections.actionSearchFragmentToBookDetailFragment(book.isbn!!)
        findNavController().navigate(action)
    }


}
