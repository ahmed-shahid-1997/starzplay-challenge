package com.comm.moviesearch.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.comm.data.model.MediaItem
import com.comm.moviesearch.R
import com.comm.moviesearch.databinding.MediaItemBinding

class MediaItemAdapter(private val onClick: (MediaItem) -> Unit) :
    RecyclerView.Adapter<MediaItemAdapter.ViewHolder>() {
    private val items = arrayListOf<MediaItem>()

    class ViewHolder(private val binding: MediaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mediaItem: MediaItem) = binding.run {
            this.mediaItem = mediaItem
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.media_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount() = items.size

    fun setList(list: List<MediaItem>) = items.run {
        clear()
        addAll(list)
        notifyDataSetChanged()
    }
}