package com.moor.shelflyfe.ui.bookdetail

import android.annotation.SuppressLint
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
import com.moor.shelflyfe.creatLoadingDialog
import com.moor.shelflyfe.databinding.BookDetailFragmentBinding
import com.moor.shelflyfe.load
import com.moor.shelflyfe.ui.*
import com.moor.shelflyfe.ui.booklist.BookListViewModel
import com.moor.shelflyfe.ui.explore.LinkAdapter
import com.moor.shelflyfe.ui.list.ListItem
import com.moor.shelflyfe.ui.search.SearchDialogFragment
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class BookDetailFragment : Fragment(), OnBookClickListner, OnItemClickListener<ListItem> {

    private lateinit var loading: SearchDialogFragment

    private lateinit var binding: BookDetailFragmentBinding
    private val viewModel: BookDetailViewModel by viewModel()
    private  val bookListViewModel: BookListViewModel by sharedViewModel()


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
        loading= SearchDialogFragment()
    }

    override fun onClick(book: Book) {
        findNavController().navigate(R.id.bookDetailFragment, bundleOf("isbn" to book.isbn))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.isFavorited(args.isbn).observe(viewLifecycleOwner, Observer { isFavorited ->
            if (isFavorited){
                binding.favoriteButton.setImageResource(R.drawable.ic_bookmark)
            }else{
                binding.favoriteButton.setImageResource(R.drawable.ic_bookmark_border)
            }
        })

        viewModel.getBookData(args.isbn).observe(viewLifecycleOwner, Observer { data->
            var sections = arrayListOf<TagSection>()

            data?.subjects?.let {subjects->
               sections.add(TagSection("Subjects", subjects.map { ListItem(it.url,it.name)}))
            }
            data?.publishers?.let { publishers->
                sections.add(TagSection("Publishers",publishers.map { ListItem(it.name,it.name) }))
            }
            data?.subjectPeople?.let { people->
                sections.add(TagSection("People",people.map { ListItem(it.url,it.name) }))
            }
            data?.subjectPlaces?.let{places->
                sections.add(TagSection("Places",places.map { ListItem(it.url,it.name) }))
            }
            sections.any().let {
                binding.tags.apply{
                    adapter=TagSectionAdapter(sections).apply {
                        listener= this@BookDetailFragment
                    }
                    layoutManager= LinearLayoutManager(context)
                    addItemDecoration(SpacesItemDecoration(8))
                }
            }
            data?.links?.let {links ->
                binding.links.apply {
                    adapter= LinkAdapter(links.map { ListItem(it.url,it.title) })
                    layoutManager= LinearLayoutManager(context)
                    addItemDecoration(SpacesItemDecoration(8))
                }
            }?: kotlin.run {
                binding.linksSection.visibility=View.GONE
            }

        })
        val alert= creatLoadingDialog()
        alert?.show()
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
            alert?.dismiss()
            //loading.dismiss()
        })
    }

    override fun onClick(item: ListItem) {
        findNavController().navigate(R.id.bookListFragment, bundleOf("title" to item.value))
        val key= item.key.split("/").last().split(":").last()
        bookListViewModel.loadBooksByGenre(key)
    }

}
