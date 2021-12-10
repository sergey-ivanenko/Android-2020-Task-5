package io.github.sergey_ivanenko.thecatapi.presentation.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import coil.load
import io.github.sergey_ivanenko.thecatapi.R
import io.github.sergey_ivanenko.thecatapi.databinding.FragmentCatDetailBinding
import io.github.sergey_ivanenko.thecatapi.domain.CatItem
import io.github.sergey_ivanenko.thecatapi.domain.CatsLoader

class CatDetailFragment : Fragment() {

    private var _binding: FragmentCatDetailBinding? = null
    private val binding: FragmentCatDetailBinding get() = requireNotNull(_binding)
    private var catImageUrl: String? = null

    // private val catListViewModel: CatListViewModel by activityViewModels()
    /*private val catViewModel: CatViewModel by activityViewModels()*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCatDetailBinding.inflate(inflater, container, false)

        loadCatImage()
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun loadCatImage() {
        /*val catImageId = arguments?.getString("currentId")
        val catImageUrl = arguments?.getString("currentUrl")*/
        val catItem = arguments?.getParcelable<CatItem>("currentCat")
        if (/*catImageUrl != null*/catItem != null) {
            /*binding.fullscreenCatImageView.load(catImageUrl)*/
            binding.fullscreenCatImageView.load(catItem.url)
            catImageUrl = catItem.url
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_save) {
            if (isPermission()) {
                saveImageToGallery()
            } else {
                askPermission()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun askPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            REQUEST_CODE
        )
    }

    private fun saveImageToGallery() {
        val filename = catImageUrl?.removePrefix("https://cdn2.thecatapi.com/images/")
        val bitmap: Bitmap = binding.fullscreenCatImageView.drawable.toBitmap()

        CatsLoader.saveMediaToStorage(activity?.applicationContext, bitmap, filename)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val REQUEST_CODE: Int = 100
    }
}
