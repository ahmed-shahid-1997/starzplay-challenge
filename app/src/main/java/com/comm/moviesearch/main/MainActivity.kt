package com.comm.moviesearch.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.comm.data.model.MediaItem
import com.comm.data.model.ResultOf
import com.comm.moviesearch.*
import com.comm.moviesearch.databinding.ActivityMainBinding
import com.comm.moviesearch.mediaitem.MediaItemActivity
import com.comm.moviesearch.util.constants.IntentConstants
import com.comm.moviesearch.util.view.VerticalSpaceDecoration

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActionbar(null, false)
        binding.adapter = CarouselAdapter(this::onMediaItemClick)
        binding.moviesList.addItemDecoration(VerticalSpaceDecoration(20))
        binding.searchMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.showProgress = true
                viewModel.search(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?) = true

        })
        viewModel.primaryData.observe(this) {
            binding.showProgress = false
            when (it) {
                is ResultOf.Success ->
                    (binding.adapter as CarouselAdapter).setList(it.item)
                is ResultOf.Failure -> Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.secondaryData.observe(this) {
            (binding.adapter as CarouselAdapter).addToList(it)
        }
    }

    private fun onMediaItemClick(item: MediaItem) =
        startActivity(Intent(this, MediaItemActivity::class.java).apply {
            putExtra(IntentConstants.MEDIA_ITEM, item)
        })
}