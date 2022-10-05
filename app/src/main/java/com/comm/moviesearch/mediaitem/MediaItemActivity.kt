package com.comm.moviesearch.mediaitem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.comm.data.model.MediaItem
import com.comm.moviesearch.player.PlayerActivity
import com.comm.moviesearch.R
import com.comm.moviesearch.databinding.ActivityMediaItemBinding
import com.comm.moviesearch.util.constants.IntentConstants

class MediaItemActivity : AppCompatActivity() {
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMediaItemBinding>(this, R.layout.activity_media_item)
    }
    private val mediaItem by lazy {
        intent.getSerializableExtra(IntentConstants.MEDIA_ITEM) as MediaItem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActionbar()
        binding.mediaItem = mediaItem
        binding.playButton.setOnClickListener {
            startActivity(Intent(this, PlayerActivity::class.java).apply {
                putExtra(IntentConstants.MEDIA_ITEM, mediaItem)
            })
        }
    }

    private fun initActionbar() {
        supportActionBar?.run {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = mediaItem.displayName
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}