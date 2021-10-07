package io.github.sergey_ivanenko.thecatapi.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import io.github.sergey_ivanenko.thecatapi.databinding.FragmentCatDetailBinding
import io.github.sergey_ivanenko.thecatapi.domain.CatItem

class CatDetailFragment : Fragment() {

    private var _binding: FragmentCatDetailBinding? = null
    private val binding: FragmentCatDetailBinding get() = requireNotNull(_binding)

    // private val catListViewModel: CatListViewModel by activityViewModels()
    /*private val catViewModel: CatViewModel by activityViewModels()*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCatDetailBinding.inflate(inflater, container, false)

        loadCatImage()

        return binding.root
    }

    private fun loadCatImage() {
        /*val catImageId = arguments?.getString("currentId")
        val catImageUrl = arguments?.getString("currentUrl")*/
        val catItem = arguments?.getParcelable<CatItem>("currentCat")
        if (/*catImageUrl != null*/catItem != null) {
            /*binding.fullscreenCatImageView.load(catImageUrl)*/
            binding.fullscreenCatImageView.load(catItem.url)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
