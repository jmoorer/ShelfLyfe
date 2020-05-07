package com.moor.shelflyfe.ui.bookdetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs

import com.moor.shelflyfe.R
import com.moor.shelflyfe.databinding.BookDetailFragmentBinding
import com.moor.shelflyfe.load
import org.koin.android.viewmodel.ext.android.sharedViewModel

class BookDetailFragment : Fragment() {


    private lateinit var binding: BookDetailFragmentBinding
    private val viewModel: BookDetailViewModel by sharedViewModel()
    private val args:BookDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=BookDetailFragmentBinding.inflate(inflater,container,false)
        viewModel.getBookDetails(args.isbn).observe(viewLifecycleOwner, Observer { book->

            book?.let {
                binding.apply {
                    title.text=book.volumeInfo.title
                    cover.load(book.volumeInfo.imageLinks.thumbnail)
                    author.text=book.volumeInfo.authors?.first()
                    description.text=book.volumeInfo.description
                }

            }


        })
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}
