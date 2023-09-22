package com.gk.udemynewsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gk.udemynewsapp.R
import com.gk.udemynewsapp.data.util.Resource
import com.gk.udemynewsapp.databinding.FragmentNewsBinding
import com.gk.udemynewsapp.presentation.viewmodel.NewsViewModel
import com.gk.udemynewsapp.presentation.viewmodel.NewsViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    @Inject
    lateinit var newsRecyclerAdapter: NewsRecyclerAdapter

    @Inject
    lateinit var newsViewModelFactory: NewsViewModelFactory

    private val viewModel: NewsViewModel by activityViewModels{
        newsViewModelFactory
    }

    private var country = "us"
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        viewModel = (activity as MainActivity).viewModel
//        viewModel =
//            ViewModelProvider(requireActivity(), newsViewModelFactory)[NewsViewModel::class.java]
        initialRecyclerView()
        viewNewsList()
        searchViewNews()
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadlines(country = country, page = page)
        viewModel.newsHeadlines.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { data ->
                        newsRecyclerAdapter.differ.submitList(data.articles)
                        pages = if (data.totalResults % 20 == 0) {
                            data.totalResults / 20
                        } else {
                            data.totalResults / 20 + 1
                        }
                        isLastPage = page == pages
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    Snackbar.make(binding.root, it.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initialRecyclerView() {
        binding.recyclerView.apply {
            adapter = newsRecyclerAdapter
            addOnScrollListener(onScrollListener)
        }
        newsRecyclerAdapter.setOnItemClickListener { article ->
            // ways to pass arguments bun
            val bundle = Bundle().apply {
                putParcelable("article", article)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_infoFragment,
                bundle
            )
            // Ways to pass as action
            /* val action = NewsFragmentDirections.actionNewsFragmentToInfoFragment(article = article)
             findNavController().navigate(action)*/
        }
    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.GONE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if (shouldPaginate) {
                page++
                viewModel.getNewsHeadlines(country, page)
                isScrolling = false
            }
        }
    }

    private fun searchViewNews() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchNews("us", query.toString(), page)
                searchedNewsView()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                MainScope().launch {
                    delay(2000)
                    viewModel.searchNews("us", newText.toString(), page)
                    searchedNewsView()
                }
                return false
            }
        })

        binding.searchView.setOnCloseListener(object :SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                initialRecyclerView()
                viewNewsList()
                return false
            }
        })
    }

    private fun searchedNewsView() {
        viewModel.searchNews.observe(requireActivity()) {
            when (it) {
                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { data ->
                        newsRecyclerAdapter.differ.submitList(data.articles)
                        pages = if (data.totalResults % 20 == 0) {
                            data.totalResults / 20
                        } else {
                            data.totalResults / 20 + 1
                        }
                        isLastPage = page == pages
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    Snackbar.make(binding.root, it.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}