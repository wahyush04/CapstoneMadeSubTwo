package com.wahyush04.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wahyush04.core.R
import com.wahyush04.core.databinding.ItemListBinding
import com.wahyush04.core.domain.model.News

class NewsAdapter(private var onItemClickCallback: OnItemClickCallback): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    private var newsList = ArrayList<News>()
    var onItemClick: ((News) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
//        holder.setIsRecyclable(false)
//        val data = newsList[position]
//        holder.bind(data)
    }

    override fun getItemCount(): Int = differ.currentList.size

//    fun setData(List: List<News>?) {
//        if (List == null) return
//        newsList.apply {
//            clear()
//            addAll(List)
//            notifyDataSetChanged()
//        }
//    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data: News){
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(data)
            }
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(newsImage)
                tvTitle.text = data.title
                tvDate.text = data.publishedAt
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(newsList[absoluteAdapterPosition])
            }
        }

    }

    private val diffCallback = object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.publishedAt == newItem.publishedAt
        }
    }

    val differ = AsyncListDiffer(this,diffCallback)

    interface OnItemClickCallback{
        fun onItemClicked(data: News)
    }

}