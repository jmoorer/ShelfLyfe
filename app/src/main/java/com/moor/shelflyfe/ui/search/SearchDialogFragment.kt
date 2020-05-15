package com.moor.shelflyfe.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar

import com.moor.shelflyfe.R
import com.moor.shelflyfe.databinding.SearchDialogFragmentBinding
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.booklist.BookListViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class SearchDialogFragment : Fragment(), SearchAdapter.OnItemClickListner,
    MaterialSearchBar.OnSearchActionListener {

    private lateinit var binding: SearchDialogFragmentBinding
    private val viewModel: SearchViewModel by viewModel()
    private  val bookListViewModel: BookListViewModel by sharedViewModel()
    private  val results= arrayListOf<Book>()

    private   val searchAdapter = SearchAdapter(results).apply {
        listener= this@SearchDialogFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding= SearchDialogFragmentBinding.inflate(inflater,container,false)
        binding.loading.visibility=View.GONE

        binding.results.apply {
            adapter= searchAdapter
            layoutManager= LinearLayoutManager(context)
        }
        binding.searchBar.apply {
            setOnSearchActionListener(this@SearchDialogFragment)
            openSearch()
        }
        return  binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.searchResults().observe(viewLifecycleOwner, Observer { books->
            binding.loading.visibility=View.GONE
            this.results.clear()
            this.results.addAll(books)
            searchAdapter.notifyDataSetChanged()
        })
    }

    override fun onClick(book: Book) {
        findNavController().navigate(R.id.bookDetailFragment, bundleOf("isbn" to book.isbn))
    }

    override fun onButtonClicked(buttonCode: Int) {

    }

    override fun onSearchStateChanged(enabled: Boolean) {
        if(!enabled)
            findNavController().popBackStack()
    }

    override fun onSearchConfirmed(text: CharSequence?) {
        binding.loading.visibility=View.VISIBLE
        viewModel.search(text.toString())
    }

}
