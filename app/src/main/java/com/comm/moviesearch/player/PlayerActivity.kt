package com.comm.moviesearch.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.comm.data.model.MediaItem
import com.comm.moviesearch.R
import com.comm.moviesearch.databinding.ActivityPlayerBinding
import com.comm.moviesearch.initActionbar
import com.comm.moviesearch.util.constants.IntentConstants

class PlayerActivity : AppCompatActivity() {
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityPlayerBinding>(this, R.layout.activity_player)
    }
    private val mediaItem by lazy {
        intent.getSerializableExtra(IntentConstants.MEDIA_ITEM) as MediaItem
    }
    private val url by lazy {
        mediaItem.url
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActionbar(mediaItem.displayName, true)
        binding.url = url
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}