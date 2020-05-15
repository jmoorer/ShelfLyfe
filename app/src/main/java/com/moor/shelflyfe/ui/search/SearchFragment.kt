package com.moor.shelflyfe.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar
import com.moor.shelflyfe.R
import com.moor.shelflyfe.databinding.SearchFragmentBinding
import com.moor.shelflyfe.db.Genre
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.booklist.BookListViewModel
import com.moor.shelflyfe.ui.list.ListItem
import kotlinx.android.synthetic.main.item_favorite.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class SearchFragment : Fragment(),
    SearchAdapter.OnItemClickListner, GenreAdapter.OnGenreClickListener {


    private val viewModel: SearchViewModel by viewModel()
    private  val bookListViewModel:BookListViewModel by sharedViewModel()
    private lateinit var binding:SearchFragmentBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= SearchFragmentBinding.inflate(inflater,container,false)

        binding.searchBar.setOnClickListener {
           findNavController().navigate(R.id.searchDialogFragment)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getGenres().observe(viewLifecycleOwner, Observer { genres->
            binding.genres.apply {
                layoutManager=LinearLayoutManager(context)
                adapter= GenreAdapter(genres) .apply {
                    listener= this@SearchFragment
                }
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    ))
            }
        })
    }





    override fun onClick(book: Book) {
        val action= SearchFragmentDirections.actionSearchFragmentToBookDetailFragment(book.isbn!!)
        findNavController().navigate(action)
    }

    override fun onGenreClick(genre: Genre) {
        findNavController().navigate(R.id.bookListFragment, bundleOf("title" to genre.name))
        bookListViewModel.loadBooksByGenre(genre.key)
    }


}
