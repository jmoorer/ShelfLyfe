package com.moor.shelflyfe.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.moor.shelflyfe.databinding.HomeFragmentBinding
import com.moor.shelflyfe.ui.Section
import com.moor.shelflyfe.ui.explore.FeaturedAdapter
import org.koin.android.viewmodel.ext.android.viewModel



class HomeFragment : Fragment() {


    private val viewModel: HomeViewModel by  viewModel()
    private lateinit var binding : HomeFragmentBinding

    private  var sections= arrayListOf<Section>()
    private  var sectionAdapter=SectionAdapter(sections)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.featured.observe(viewLifecycleOwner, Observer {books->
            binding.viewPager.adapter=
                FeaturedAdapter(books)
        })
//        viewModel.getPopularList().observe(viewLifecycleOwner, Observer { section->
//            sections.add(section)
//            sectionAdapter.notifyDataSetChanged()
//        })
        binding = HomeFragmentBinding.inflate(inflater,container,false)
        binding.sections.apply{
            adapter= sectionAdapter
            layoutManager= LinearLayoutManager(context)
        }
        return binding.root
    }


}
