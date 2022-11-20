package com.wahyush04.capstonemadesubone.ui.main

import android.content.Intent
import android.net.Uri.parse
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahyush04.capstonemadesubone.R
import com.wahyush04.capstonemadesubone.databinding.ActivityMainBinding
import com.wahyush04.capstonemadesubone.ui.detail.DetailActivity
import com.wahyush04.capstonemadesubone.ui.detail.DetailActivity.Companion.EXTRA_DATA
import com.wahyush04.core.Constant.BOOKMARK_URI
import com.wahyush04.core.adapter.NewsAdapter
import com.wahyush04.core.data.Resource
import com.wahyush04.core.domain.model.News
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsAdapter = NewsAdapter(object : NewsAdapter.OnItemClickCallback{
            override fun onItemClicked(data: News) {
                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(EXTRA_DATA, data)
                    startActivity(it)
                }
            }

        })

        mainViewModel.news.observe(this@MainActivity) {
            it.apply {
                if (this != null) {
                    when (this) {
                        is Resource.Loading -> {
                            showLoading(true)
                            binding.tvEror.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            showLoading(false)
                            newsAdapter.differ.submitList(data)
                            binding.tvEror.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            showLoading(false)
                            binding.tvEror.visibility = View.VISIBLE

                        }
                    }
                }
            }
        }

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = newsAdapter
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.bookmark -> {
                val uri = parse(BOOKMARK_URI)
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }
}