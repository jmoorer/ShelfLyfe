package com.moor.shelflyfe.ui.home


import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codekidlabs.storagechooser.StorageChooser
import com.folioreader.FolioReader
import com.folioreader.model.locators.ReadLocator
import com.moor.shelflyfe.DownloadsAdapter
import com.moor.shelflyfe.FavoritesAdapter
import com.moor.shelflyfe.R
import com.moor.shelflyfe.databinding.HomeFragmentBinding
import com.moor.shelflyfe.db.Download
import com.moor.shelflyfe.db.Favorite
import nl.siegmann.epublib.epub.EpubReader
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.File
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
            }else if(binding.viewSwitcher.nextView!=binding.sections){
                binding.viewSwitcher.showNext()
            }
            binding.sections.apply{
                adapter= FavoritesAdapter(favs).apply {
                    listener= this@HomeFragment
                }
                layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            }
        })
        viewModel.downloads.observe(viewLifecycleOwner, Observer { downloads->
                binding.downloads.apply {
                    adapter= DownloadsAdapter(downloads).apply {
                        listener= this@HomeFragment
                    }
                    layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
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
            val chooser = StorageChooser.Builder()
                .withActivity(activity)
                .withFragmentManager(activity?.fragmentManager)
                .withMemoryBar(true)
                .allowCustomPath(true)
                .setType(StorageChooser.FILE_PICKER)
                .build()


            chooser.show()

            chooser.setOnSelectListener { path ->
                    if(path.endsWith("epub")){
                        val alert: AlertDialog.Builder = AlertDialog.Builder(context)
                        val edittext = EditText(context)
                        alert.setMessage("Enter your file name")
                        alert.setTitle("Import as ")

                        alert.setView(edittext)

                        alert.setPositiveButton("Ok"){ dialog, whichButton ->
                             //What ever you want to do with the value

                            val name = edittext.text.toString()
                            var target= File(requireContext().getExternalFilesDir("books"),"${name}.epub")
                            File(path).let {source->

                                source.copyTo(target)
                                source.delete()
                                ingestEbook(target.path)
                            }

                        }

                        alert.setNegativeButton(
                            "Cancel"){ dialog, whichButton ->
                                // what ever you want to do with No option.
                        }

                        alert.show()



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
            if (permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               importEbook()
            }
        }
    }

    fun ingestEbook(path:String){
        try {
            // find InputStream for book


            // Load Book from inputStream
            val book = EpubReader().readEpub(FileInputStream(path))
            val targetArray = ByteArray(book.coverImage.inputStream.available())
            book.coverImage.inputStream.read(targetArray)
            viewModel.saveDownload(Download(
                title = book.title,
                author = book.metadata.authors.first()?.let {"${it.firstname} ${it.lastname}"}?:"",
                path = path,
                image = targetArray
            ))

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
        TODO("Not yet implemented")
    }


}
