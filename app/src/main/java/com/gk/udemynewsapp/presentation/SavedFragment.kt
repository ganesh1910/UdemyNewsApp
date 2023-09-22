package com.gk.udemynewsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gk.udemynewsapp.databinding.FragmentSavedBinding
import com.gk.udemynewsapp.presentation.viewmodel.NewsViewModel
import com.gk.udemynewsapp.presentation.viewmodel.NewsViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding

    @Inject
    lateinit var newsRecyclerAdapter: NewsRecyclerAdapter

    @Inject
    lateinit var newsViewModelFactory: NewsViewModelFactory

    val viewModel: NewsViewModel by activityViewModels {
        newsViewModelFactory
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = MaterialDividerItemDecoration(
            binding.recyclerViewSaved.context,
            layoutManager.orientation
        ).apply {
            dividerColor = Color.RED
            dividerThickness = 10
            dividerInsetEnd = 10
            dividerInsetStart = 10
        }*/
        binding.recyclerViewSaved.apply {
            adapter = newsRecyclerAdapter
//            addItemDecoration(dividerItemDecoration)
        }

        viewModel.getAllNewsArticles().observe(requireActivity()) {
            newsRecyclerAdapter.differ.submitList(it)
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsRecyclerAdapter.differ.currentList[position]
                viewModel.deleteNews(article = article)
                Snackbar.make(view, "Deleted Successfully", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveArticleInDB(article = article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerViewSaved)
        }

    }
}