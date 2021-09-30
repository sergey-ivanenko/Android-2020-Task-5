package io.github.sergey_ivanenko.thecatapi.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.sergey_ivanenko.thecatapi.databinding.FragmentCatDetailBinding

class CatDetailFragment : Fragment() {

    private var _binding: FragmentCatDetailBinding? = null
    private val binding: FragmentCatDetailBinding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatDetailBinding.inflate(inflater, container, false)

        return binding.root
    }
}
