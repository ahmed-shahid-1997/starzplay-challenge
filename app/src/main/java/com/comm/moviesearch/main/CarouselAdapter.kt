package com.comm.moviesearch.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.comm.data.model.MediaItem
import com.comm.moviesearch.model.Carousel
import com.comm.moviesearch.R
import com.comm.moviesearch.databinding.CarouselItemBinding
import com.comm.moviesearch.util.view.HorizontalSpaceDecoration
import com.comm.moviesearch.util.view.SnapHelperOneByOne


class CarouselAdapter(private val onMediaItemClick: (MediaItem) -> Unit) :
    RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {
    private val items = mutableListOf<Carousel>()
    private val carouselItems = hashMapOf<String, MutableList<MediaItem>>()

    class ViewHolder(private val binding: CarouselItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(carousel: Carousel, adapter: MediaItemAdapter) = binding.run {
            if (mediaItems.itemDecorationCount > 0) mediaItems.removeItemDecorationAt(0)
            mediaItems.addItemDecoration(HorizontalSpaceDecoration(10))
            this.carousel = carousel
            this.adapter = adapter
            mediaItems.onFlingListener = null
            val linearSnapHelper: LinearSnapHelper = SnapHelperOneByOne()
            linearSnapHelper.attachToRecyclerView(mediaItems)
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.carousel_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val adapter = MediaItemAdapter(onMediaItemClick).apply {
            setList(carouselItems[item.title]!!)
        }
        holder.bind(item, adapter)
    }

    override fun getItemCount() = items.size

    fun setList(list: List<Carousel>) {
        items.clear()
        items.addAll(list)

        carouselItems.clear()
        list.forEach { c ->
            carouselItems[c.title] = c.movies.toMutableList()
        }

        notifyDataSetChanged()
    }

    fun addToList(list: List<Carousel>) {
        list.forEach {
            val index = items.indexOfFirst { c -> c.title == it.title }
            val mediaItems = carouselItems[it.title] ?: mutableListOf()
            mediaItems.addAll(it.movies)
            carouselItems[it.title] = mediaItems
            if (index == -1) {
                items.add(it)
                notifyItemInserted(items.indexOf(it))
            } else notifyItemChanged(index)
        }
    }
}