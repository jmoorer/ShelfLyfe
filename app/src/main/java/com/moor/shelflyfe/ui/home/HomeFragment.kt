package com.moor.shelflyfe.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.moor.shelflyfe.FavoritesAdapter
import com.moor.shelflyfe.R
import com.moor.shelflyfe.databinding.HomeFragmentBinding
import com.moor.shelflyfe.ui.Section
import com.moor.shelflyfe.ui.SectionAdapter
import com.moor.shelflyfe.ui.explore.FeaturedAdapter
import org.koin.android.viewmodel.ext.android.viewModel



class HomeFragment : Fragment() {


    private val viewModel: HomeViewModel by  viewModel()
    private lateinit var binding : HomeFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HomeFragmentBinding.inflate(inflater,container,false)
        binding.empty.exploreButton.setOnClickListener {
            findNavController().navigate(R.id.exploreFragment)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.favorites.observe(viewLifecycleOwner, Observer { favs->
            if (favs.size>0 && binding.viewSwitcher.nextView==binding.sections){
                binding.viewSwitcher.showNext()
            }
            binding.sections.apply{
                adapter= FavoritesAdapter(favs)
                layoutManager= LinearLayoutManager(context)
            }
        })
    }


}
