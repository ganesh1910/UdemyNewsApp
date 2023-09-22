package com.gk.udemynewsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.gk.udemynewsapp.databinding.FragmentInfoBinding
import com.gk.udemynewsapp.presentation.viewmodel.NewsViewModel
import com.gk.udemynewsapp.presentation.viewmodel.NewsViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding

    @Inject
    lateinit var newsViewModelFactory: NewsViewModelFactory

    private val viewModel: NewsViewModel by activityViewModels {
        newsViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val newsId = arguments?.let { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            it.getParcelable("article",Article::class.java)
        } else {
          it.getParcelable("article")
        }*/

        val args: InfoFragmentArgs by navArgs()
        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            if (article.url.isNotBlank()) {
                loadUrl(article.url)
            }
        }

        binding.floatingActionButton.setOnClickListener {
            article.let {
                viewModel.saveArticleInDB(article = it)
                if (viewModel.isInserted.value)
                    Snackbar.make(view, "Saved Successfully", Snackbar.LENGTH_LONG).show()
                else
                    Snackbar.make(view, "Saved Failed", Snackbar.LENGTH_LONG).show()
            }
        }

    }


}