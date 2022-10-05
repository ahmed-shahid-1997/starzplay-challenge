package com.comm.data

import com.comm.data.model.MediaItem
import com.comm.data.model.MediaTypes
import junit.framework.TestCase.*
import org.junit.Test

class MediaItemTest {
    @Test
    fun displayNameIsTitle() {
        val item = MediaItem(mediaType = "J", title = "A")
        assertEquals("A", item.displayName)
    }

    @Test
    fun displayNameIsName() {
        val item = MediaItem(mediaType = "J", name = "A")
        assertEquals("A", item.displayName)
    }

    @Test
    fun originalTitleAvailable() {
        val item = MediaItem(mediaType = "J", originalTitle = "A")
        assertTrue(item.showOriginalTitle)
    }

    @Test
    fun originalTitleUnAvailable() {
        val item = MediaItem(mediaType = "J", originalTitle = null)
        assertFalse(item.showOriginalTitle)
    }

    @Test
    fun voteCountAvailable() {
        val item = MediaItem(mediaType = "J", voteCount = 213)
        assertTrue(item.showVoteCount)
    }

    @Test
    fun voteCountUnAvailable() {
        val item = MediaItem(mediaType = "J", voteCount = null)
        assertFalse(item.showVoteCount)
    }

    @Test
    fun voteAverageAvailable() {
        val item = MediaItem(mediaType = "J", voteAverage = 213.21)
        assertTrue(item.showVoteAverage)
    }

    @Test
    fun voteAverageUnAvailable() {
        val item = MediaItem(mediaType = "J", voteAverage = null)
        assertFalse(item.showVoteAverage)
    }

    @Test
    fun popularityAvailable() {
        val item = MediaItem(mediaType = "J", popularity = 213.21)
        assertTrue(item.showPopularity)
    }

    @Test
    fun popularityUnAvailable() {
        val item = MediaItem(mediaType = "J", popularity = null)
        assertFalse(item.showPopularity)
    }

    @Test
    fun movieIsPlayable() {
        val item = MediaItem(mediaType = MediaTypes.MOVIE.value)
        assertTrue(item.isPlayable)
    }

    @Test
    fun tvIsPlayable() {
        val item = MediaItem(mediaType = MediaTypes.TV.value)
        assertTrue(item.isPlayable)
    }

    @Test
    fun personIsNotPlayable() {
        val item = MediaItem(mediaType = MediaTypes.PERSON.value)
        assertFalse(item.isPlayable)
    }

    @Test
    fun movieHasURL() {
        val item = MediaItem(mediaType = MediaTypes.MOVIE.value)
        assertNotNull(item.url)
    }

    @Test
    fun tvHasURL() {
        val item = MediaItem(mediaType = MediaTypes.TV.value)
        assertNotNull(item.url)
    }

    @Test(expected = IllegalStateException::class)
    fun personDoesNotHaveURL() {
        val item = MediaItem(mediaType = MediaTypes.PERSON.value)
        item.url
    }
}