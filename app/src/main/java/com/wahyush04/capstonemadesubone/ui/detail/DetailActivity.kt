package com.wahyush04.capstonemadesubone.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wahyush04.capstonemadesubone.R
import com.wahyush04.capstonemadesubone.databinding.ActivityDetailBinding
import com.wahyush04.core.domain.model.News
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var urlRead: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        intent.getParcelableExtra<News>(EXTRA_DATA). also {
            showDetails(it)
        }

        binding.btnReadMore.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(urlRead)
            startActivity(i)
        }
    }

    private fun showDetails(news: News?){
        binding.apply {
            news?.apply {
                Glide.with(this@DetailActivity)
                    .load(image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivImage)
                tvTitle.text = title
                tvDesc.text = description
                tvContent.text = content
                urlRead = url

                var bookmarkStatus = isBookmark

                setBookmarkStatus(bookmarkStatus)
                fab.setOnClickListener {
                    bookmarkStatus = !bookmarkStatus
                    detailViewModel.bookmark(this, bookmarkStatus)
                    setBookmarkStatus(bookmarkStatus)
                }
            }
        }
    }

    private fun setBookmarkStatus(state: Boolean){
        binding.fab.apply {
            if (state){
                setImageDrawable(getDrawable(this@DetailActivity, R.drawable.ic_bookmark_white_solid))
            }else{
                setImageDrawable(getDrawable(this@DetailActivity, R.drawable.ic_bookmark_white_border))
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}