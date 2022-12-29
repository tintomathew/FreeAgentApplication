/*
 * *
 *  * Created by Tinto Mathew on 25/11/2022, 10:50
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 25/11/2022, 10:50
 *
 */

package com.music.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.music.CoroutineTestRule
import com.music.MainCoroutineRule
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
import com.music.repository.PlayListRepository
import com.music.ui.main.home.HomeViewModel
import com.music.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @MockK
    var playListRepository: PlayListRepository = mockk()

    // class under test
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = HomeViewModel(playListRepository)
    }

    @Test
    fun showProgressTest() {
        Assert.assertTrue(
            "progress indicator test failed",
            viewModel.isLoading.value == true
        )
    }

    @Test
    fun playListDataResponseTest() {
        val attributes = Attributes(
            "", "", "", "", "",
            "", "", "", "", ""
        )
        val artist = ImArtist(attributes, "Tinto")
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
                Icon("Icon"),
                IdX(""),
                listOf<Link>(),
                Rights(""),
                Title(""),
                Updated("")
            )
        )
        coEvery { playListRepository.getPlayList() } returns Resource.Success(
            playlistData
        )
        coroutinesTestRule.testDispatcher.run {
            viewModel.getData()
            Assert.assertEquals(
                "Playlist data test failed",
                playlistData.feed.icon.label,
                viewModel.responseData.value?.feed?.icon?.label
            )
        }

        val artistList =  viewModel.getArtistList(playlistData.feed.entry)
        val response =  viewModel.getArtistFilteredData(playlistData.feed.entry,"Tinto")

        Assert.assertEquals("Artist name test failed", "Tinto", artistList[0])
        Assert.assertEquals("Artist filter test failed", "Tinto", response[0].artist.label)
    }
}