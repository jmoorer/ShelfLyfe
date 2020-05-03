package com.moor.shelflyfe.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.moor.shelflyfe.R
import com.moor.shelflyfe.databinding.HomeFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {


    val viewModel: HomeViewModel by  viewModel()
    lateinit var binding :HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.getBestSellers().observe(viewLifecycleOwner, Observer {books->
            binding.viewPager.adapter= BestSellerAdapter(books)
        })
        viewModel.getCategories().observe(viewLifecycleOwner, Observer { categories->
            binding.category.adapter = CategoryAdapter(categories)
            binding.category.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        })
        binding = HomeFragmentBinding.inflate(inflater,container,false)

        return binding.root
    }



}
