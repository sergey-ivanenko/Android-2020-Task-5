package io.github.sergey_ivanenko.thecatapi.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import io.github.sergey_ivanenko.thecatapi.databinding.FragmentCatListBinding
import io.github.sergey_ivanenko.thecatapi.domain.CatItem
import io.github.sergey_ivanenko.thecatapi.presentation.adapter.CatListPagingAdapter
import io.github.sergey_ivanenko.thecatapi.presentation.viewmodel.CatListViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/*import jp.wasabeef.recyclerview.animators.FlipInLeftYAnimator*/

class CatListFragment : Fragment() {

    private var _binding: FragmentCatListBinding? = null
    private val binding: FragmentCatListBinding get() = requireNotNull(_binding)

    /*private val catViewModel: CatViewModel by activityViewModels()*/
    private val catListViewModel: CatListViewModel by activityViewModels()
    private val pagingAdapter: CatListPagingAdapter get() =
        views { recyclerView.adapter as CatListPagingAdapter }
    /*private val adapter: CatListAdapter get() = views { recyclerView.adapter as CatListAdapter }*/

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
            recyclerView.adapter = CatListPagingAdapter()
            recyclerView.layoutManager = GridLayoutManager(context, 2)
        }

        catListViewModel.catItems.onEach(::loadMore).launchIn(lifecycleScope)
        /*lifecycleScope.launch {
            catListViewModel.getListData().collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }

        }*/
    }

    private suspend fun loadMore(catItems: PagingData<CatItem>) {
        pagingAdapter.submitData(catItems)
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        views {
            recyclerView.adapter = CatListAdapter()
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            *//*recyclerView.itemAnimator = FlipInLeftYAnimator().apply {
                addDuration = 300
            }*//*

            scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener {
                    v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (v != null) {
                    if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                        CatViewModel.page.plus(1)
                        renderCatList(*//*page.plus(1)*//*)
                    }
                }
            })
        }

        *//*renderCatList(page)*//*
        catViewModel.catItems.observe(viewLifecycleOwner, { catList ->
            adapter.catList = catList
        })
    }

    private fun renderCatList(*//*page: Int = 0*//*) {
        *//*catViewModel.getCatList(page).observe(viewLifecycleOwner, { catList ->
            adapter.catList += catList
        })*//*
        val prevCatListItem = adapter.catList
        val fullCatListItem = mutableListOf<CatItem>()
        fullCatListItem.addAll(prevCatListItem)
        catViewModel.loadMore(*//*page*//*)
        catViewModel.catItems.value?.let { fullCatListItem.addAll(it) }
        adapter.catList = fullCatListItem
    }*/

    private fun <T> views(block: FragmentCatListBinding.() -> T): T = binding.block()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /*companion object {
        private var page: Int = 0
    }*/
}
