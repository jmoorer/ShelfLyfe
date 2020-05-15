package com.moor.shelflyfe.ui.home


import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codekidlabs.storagechooser.StorageChooser
import com.folioreader.FolioReader
import com.folioreader.model.locators.ReadLocator
import com.google.android.material.tabs.TabLayout
import com.moor.shelflyfe.DownloadsAdapter
import com.moor.shelflyfe.FavoritesAdapter
import com.moor.shelflyfe.R
import com.moor.shelflyfe.databinding.HomeFragmentBinding
import com.moor.shelflyfe.db.Download
import com.moor.shelflyfe.db.Favorite
import nl.siegmann.epublib.epub.EpubReader
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.FileInputStream
import java.io.IOException


class HomeFragment : Fragment(), FavoritesAdapter.OnFavoriteClickListener,
    DownloadsAdapter.OnDownloadClickListener {


    private val viewModel: HomeViewModel by  viewModel()


    private lateinit var binding : HomeFragmentBinding

    private val reader:FolioReader by inject()

    private val PERMISSIONS = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private  val REQUEST_PERMISSIONS_CODE_WRITE_STORAGE= 1


    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HomeFragmentBinding.inflate(inflater,container,false)
        binding.importButton.setOnClickListener {
            importEbook()
        }
        binding.tabs.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.switcher.displayedChild= tab.position
            }

        })
        binding.favoriteEmptyState.exploreButton.setOnClickListener {
           findNavController().navigate(R.id.exploreFragment)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.favorites.observe(viewLifecycleOwner, Observer { favs->

            binding.favoriteEmptyState.root.visibility= if(favs.any()) View.GONE else View.VISIBLE
            binding.favorites.apply{
                adapter= FavoritesAdapter(favs).apply {
                    listener= this@HomeFragment
                }
                layoutManager= LinearLayoutManager(context)
            }
        })
        viewModel.downloads.observe(viewLifecycleOwner, Observer { downloads->
                binding.downloadsEmptyState.root.visibility= if(downloads.any()) View.GONE else View.VISIBLE
                binding.downloads.apply {
                    adapter= DownloadsAdapter(downloads).apply {
                        listener= this@HomeFragment
                    }
                    layoutManager= LinearLayoutManager(context)
                }

        })
    }

    override fun onClick(favorite: Favorite) {
      findNavController().navigate(R.id.bookDetailFragment, bundleOf("isbn" to favorite.isbn13))
    }

    override fun onMenuClick(favorite: Favorite, menuItem: MenuItem) {
       when(menuItem.itemId){
           R.id.action_remove->{
               viewModel.removeFavorite(favorite.id)
           }
       }
    }

    fun importEbook(){

        if(ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(
                PERMISSIONS,
                REQUEST_PERMISSIONS_CODE_WRITE_STORAGE
            )
        } else{
            val theme = StorageChooser.Theme(context)
            theme.scheme = resources.getIntArray(R.array.paranoid_theme)
            val chooser = StorageChooser.Builder()
                .withActivity(activity)
                .withFragmentManager(activity?.fragmentManager)
                .withMemoryBar(true)
                .allowCustomPath(true)
                .setType(StorageChooser.FILE_PICKER)
                .setTheme(theme)
                .build()


            chooser.show()

            chooser.setOnSelectListener { path ->
                    if(path.endsWith("epub")){
                        ingestEbook(path)
//                        val edittext = EditText(context)
//                        val alert = AlertDialog.Builder(context)
//                       .setMessage("Enter your file name")
//                        .setTitle("Import as ")
//                        .setView(edittext)
//                        .setPositiveButton("Ok"){ dialog, whichButton ->
//
//                            val name = edittext.text.toString()
//                            val target= File(requireContext().getExternalFilesDir("books"),"${name}.epub")
//                            File(path).let {source->
//                                source.copyTo(target)
//                                ingestEbook(target.path)
//                            }
//
//                        }.setNegativeButton(
//                            "Cancel"){ dialog, whichButton ->
//                        }
//                        alert.show()



                    }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSIONS_CODE_WRITE_STORAGE) {
            if(permissions.contentEquals(permissions) &&
                grantResults.all { it==PackageManager.PERMISSION_GRANTED }) {
               importEbook()
            }
        }
    }

    fun ingestEbook(path:String){
        try {

            val alert = AlertDialog.Builder(context)
                .setMessage("Importing")
                .setView(ProgressBar(context))
                .create()
            alert.show()
            val book = EpubReader().readEpub(FileInputStream(path))
            val targetArray = ByteArray(book.coverImage.inputStream.available())
            book.coverImage.inputStream.read(targetArray)
            viewModel.saveDownload(Download(
                title = book.title,
                author = book.metadata.authors.first()?.let {"${it.firstname} ${it.lastname}"}?:"",
                path = path,
                image = targetArray
            ))
            alert.dismiss()

        } catch (e: IOException) {
            Log.e("epublib", e.message)
        }

    }

    override fun onClick(download: Download) {
        if(download.lastLocation.isNotBlank()){
            val location= ReadLocator.fromJson(download.lastLocation)
            reader.setReadLocator(location)
        }
        reader.openBook(download.path)
        reader.setReadLocatorListener {readLocator ->
            download.lastLocation= readLocator.toJson()?:""
        }
        reader.setOnClosedListener {
            viewModel.saveDownload(download)
        }
    }

    override fun onMenuClick(download: Download, menuItem: MenuItem) {
        when(menuItem.itemId){
            R.id.action_remove->{
                viewModel.removeDownload(download.id)
            }
        }
    }


}
