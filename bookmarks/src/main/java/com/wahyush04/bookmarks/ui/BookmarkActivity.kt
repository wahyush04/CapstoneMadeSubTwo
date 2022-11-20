package com.wahyush04.bookmarks.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahyush04.bookmarks.databinding.ActivityBookmarkBinding
import com.wahyush04.bookmarks.di.bookmarkModule
import com.wahyush04.capstonemadesubone.ui.detail.DetailActivity
import com.wahyush04.capstonemadesubone.ui.detail.DetailActivity.Companion.EXTRA_DATA
import com.wahyush04.core.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class BookmarkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookmarkBinding

    private val bookmarkViewModel: BookmarkViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(bookmarkModule)

        val newsAdapter = NewsAdapter()

        showLoading(true)

        newsAdapter.onItemClick = { news ->
            Intent(this@BookmarkActivity, DetailActivity::class.java).also {
                it.putExtra(EXTRA_DATA, news)
                startActivity(it)
            }
        }

        bookmarkViewModel.newsBookmarkList.observe(this@BookmarkActivity) {
            newsAdapter.setData(it)
            showLoading(false)
        }

        binding.rvBookmarkList.apply {
            layoutManager = LinearLayoutManager(this@BookmarkActivity)
            setHasFixedSize(true)
            adapter = newsAdapter
        }


    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}