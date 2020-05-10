package com.moor.shelflyfe.ui.bookdetail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager

import com.moor.shelflyfe.R
import com.moor.shelflyfe.databinding.BookDetailFragmentBinding
import com.moor.shelflyfe.load
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.OnBookClickListner
import com.moor.shelflyfe.ui.Section
import com.moor.shelflyfe.ui.SectionAdapter
import com.moor.shelflyfe.ui.booklist.BookListFragmentDirections
import org.koin.android.viewmodel.ext.android.sharedViewModel

class BookDetailFragment : Fragment(), OnBookClickListner {


    private lateinit var binding: BookDetailFragmentBinding
    private val viewModel: BookDetailViewModel by sharedViewModel()
    private val args:BookDetailFragmentArgs by navArgs()
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=BookDetailFragmentBinding.inflate(inflater,container,false)
        viewModel.getBookDetails(args.book).observe(viewLifecycleOwner, Observer { book->

            book?.let {
                binding.apply {
                    title.text=book.title
                    cover.load(book.imageUrl){ exception, palette ->
                        palette?.vibrantSwatch?.let { swatch ->
                            header.setBackgroundColor(swatch.rgb)
                            title.setTextColor(swatch.titleTextColor)
                            author.setTextColor(swatch.bodyTextColor)
                        }?: kotlin.run {
                            palette?.swatches?.first()?.let { swatch->
                                header.setBackgroundColor(swatch.rgb)
                                title.setTextColor(swatch.titleTextColor)
                                author.setTextColor(swatch.bodyTextColor)
                            }
                        }
                    }

                    author.text=book.author.name
                    authorImage.load(book.author.imageUrl)
                    authorName.text=book.author.name
                    description.text=book.description
                    similar.apply {
                        adapter= SectionAdapter(listOf(Section("Similar",book.similarBooks))).apply {
                            listener= this@BookDetailFragment
                        }
                        layoutManager= LinearLayoutManager(context)
                    }
                }

            }


        })
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onClick(book: Book) {

        findNavController().navigate(R.id.bookDetailFragment, bundleOf("book" to book))
    }

}
