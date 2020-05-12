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
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.moor.shelflyfe.R
import com.moor.shelflyfe.databinding.BookDetailFragmentBinding
import com.moor.shelflyfe.db.Favorite
import com.moor.shelflyfe.db.ObjectBox
import com.moor.shelflyfe.load
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.OnBookClickListner
import com.moor.shelflyfe.ui.Section
import com.moor.shelflyfe.ui.SectionAdapter
import com.moor.shelflyfe.ui.booklist.BookListFragmentDirections
import com.moor.shelflyfe.ui.list.ListItem
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class BookDetailFragment : Fragment(), OnBookClickListner {

    private lateinit var binding: BookDetailFragmentBinding
    private val viewModel: BookDetailViewModel by viewModel()


    private val args:BookDetailFragmentArgs by navArgs()
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=BookDetailFragmentBinding.inflate(inflater,container,false)
        binding.favoriteButton.setOnClickListener {
            viewModel.toggleFavorite()
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onClick(book: Book) {
        findNavController().navigate(R.id.bookDetailFragment, bundleOf("isbn" to book.isbn))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.isFavorited(args.isbn).observe(viewLifecycleOwner, Observer { isFavorited ->
            if (isFavorited){
                binding.favoriteButton.setImageResource(R.drawable.ic_star)
            }else{
                binding.favoriteButton.setImageResource(R.drawable.ic_star_border)
            }
        })

        viewModel.getSubjects(args.isbn).observe(viewLifecycleOwner, Observer { subjects->
            binding.tags.apply{
                adapter=TagAdapter(subjects)
                layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            }
        })
        viewModel.getBookDetails(args.isbn).observe(viewLifecycleOwner, Observer { book->

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

                    author.text=book.author.joinToString(",")

                    description.text=book.description

                }

            }


        })
    }

}
