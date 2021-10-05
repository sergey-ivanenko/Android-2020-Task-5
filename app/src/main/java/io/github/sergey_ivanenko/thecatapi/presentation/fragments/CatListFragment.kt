package io.github.sergey_ivanenko.thecatapi.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import io.github.sergey_ivanenko.thecatapi.databinding.FragmentCatListBinding
import io.github.sergey_ivanenko.thecatapi.presentation.adapter.CatListAdapter
import io.github.sergey_ivanenko.thecatapi.presentation.viewmodel.CatViewModel

class CatListFragment : Fragment() {

    private var _binding: FragmentCatListBinding? = null
    private val binding: FragmentCatListBinding get() = requireNotNull(_binding)

    private val catViewModel: CatViewModel by viewModels()
    private val adapter: CatListAdapter get() = views { recyclerView.adapter as CatListAdapter }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCatListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        views {
            recyclerView.adapter = CatListAdapter()
            recyclerView.layoutManager = GridLayoutManager(context, 2)

            scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener {
                    v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (v != null) {
                    if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                        renderCatList(page.plus(1))
                    }
                }
            })
        }

        renderCatList(page)
    }

    private fun renderCatList(page: Int = 0) {
        catViewModel.getCatList(page).observe(viewLifecycleOwner, { catList ->
            adapter.catList += catList
        })
    }

    private fun <T> views(block: FragmentCatListBinding.() -> T): T = binding.block()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private var page: Int = 0
    }
}
