package com.moor.shelflyfe.ui.booklist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager

import com.moor.shelflyfe.R
import com.moor.shelflyfe.databinding.BookListFragmentBinding
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.search.SearchAdapter
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class BookListFragment : Fragment(), SearchAdapter.OnItemClickListner {


    private lateinit var binding: BookListFragmentBinding
    private  val viewModel: BookListViewModel by sharedViewModel()
    private  val bookList= arrayListOf<Book>()

    private val args: BookListFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = BookListFragmentBinding.inflate(inflater,container,false)
        binding.toolbar.title= args.title
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       viewModel.getBookList().observe(viewLifecycleOwner, Observer { books->
           binding.bookList.adapter= SearchAdapter(books).apply {
               listener=this@BookListFragment
           }
           binding.bookList.layoutManager= LinearLayoutManager(context)
       })
    }

    override fun onClick(book: Book) {
        val action = BookListFragmentDirections.actionBookListFragmentToBookDetailFragment(book.isbn)
        findNavController().navigate(action)
    }

}
