/*
 * *
 *  * Created by Tinto Mathew on 25/11/2022, 10:47
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 25/11/2022, 10:47
 *
 */

package com.music.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.music.model.Attributes
import com.music.model.Author
import com.music.model.Category
import com.music.model.Entry
import com.music.model.Feed
import com.music.model.Icon
import com.music.model.Id
import com.music.model.IdX
import com.music.model.ImArtist
import com.music.model.ImContentType
import com.music.model.ImContentTypeSecondary
import com.music.model.ImImage
import com.music.model.ImItemCount
import com.music.model.ImName
import com.music.model.ImPrice
import com.music.model.ImReleaseDate
import com.music.model.Link
import com.music.model.Name
import com.music.model.PlaylistData
import com.music.model.Rights
import com.music.model.Title
import com.music.model.Updated
import com.music.model.Uri
import com.music.service.PlayListApiService
import com.music.util.Resource
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class PlayListRepositoryTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @MockK
    var playListApiService: PlayListApiService = mockk()

    // class under test
    private lateinit var playListRepository: PlayListRepository

    @Before
    fun setup() {
        playListRepository = PlayListRepository(playListApiService)
    }

    @Test
    fun playListDataSuccessTest() {
        val attributes = Attributes(
            "", "", "", "", "",
            "", "", "", "", ""
        )
        val artist = ImArtist(attributes, "")
        val contentType = ImContentType(attributes, ImContentTypeSecondary(attributes))
        val entry = Entry(
            Category(
                attributes
            ),
            Id(attributes, ""),
            artist,
            contentType,
            listOf(ImImage(attributes, "")),
            ImItemCount(""),
            ImName(""),
            ImPrice(attributes, ""),
            ImReleaseDate(attributes, ""),
            Link(attributes),
            Rights(""),
            Title("")
        )
        val playlistData = PlaylistData(
            Feed(
                Author(
                    name = Name("Jack"),
                    uri = Uri(""),
                ),
                entry = listOf(entry),
                Icon(""),
                IdX(""),
                listOf<Link>(),
                Rights(""),
                Title(""),
                Updated("")
            )
        )
        coEvery { playListApiService.getPlayList() } returns Response.success(
            playlistData
        )
        runBlocking {
            val response = playListRepository.getPlayList()
            Assert.assertEquals(
                "Playlist response success test failed",
                playlistData.feed.author.name,
                response.data?.feed?.author?.name
            )
        }
    }

    @Test
    fun playListDataErrorTest() {
        coEvery { playListApiService.getPlayList() } returns Response.error(
            400,
            "{\"key\":[\"error\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        runBlocking {
            val response = playListRepository.getPlayList()
            Assert.assertTrue(
                "playlist response error message test failed",
                response is Resource.Error
            )
        }
    }
}