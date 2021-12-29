package dev.studiocloud.instamovie.ui.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.studiocloud.instamovie.ui.MainActivity
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@InternalCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@RunWith(AndroidJUnit4::class)
class HomeScreenKtTest {
    @get:Rule
    val activityTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun homeScreen() {
        val movieTab = activityTestRule.onNode(hasContentDescription("movie"))
        val tvTab = activityTestRule.onNode(hasContentDescription("tv"))
        val movieList = activityTestRule.onNode(hasTestTag("movie_list"))
        val storyList = activityTestRule.onNode(hasTestTag("story_list"))
        val tvList = activityTestRule.onNode(hasTestTag("tv_list"))
        val searchBar = activityTestRule.onNode(hasTestTag("search_bar"))
        val clearSearchButton = activityTestRule.onNode(hasTestTag("clear_search"))

        movieTab.assertIsDisplayed()
        tvTab.assertIsDisplayed()

        movieList.assertIsDisplayed()
        storyList.assertIsDisplayed()

        //Test scrolling story
        Thread.sleep(1000)
        storyList.performScrollToIndex(6)
        //End test scrolling story

        //Test scrolling movie
        Thread.sleep(1000)
        movieList.performScrollToIndex(6)

        Thread.sleep(1000)
        movieList.performScrollToIndex(12)

        Thread.sleep(1000)
        movieList.performScrollToIndex(18)

        Thread.sleep(1000)
        movieList.performScrollToIndex(24)
        Thread.sleep(1000)
        //End test scrolling movie

        tvTab.performClick()
        searchBar.assertIsDisplayed()
        tvList.assertIsDisplayed()

        //Test search tv
        searchBar.performTextInput("Spiderman")
        Thread.sleep(1000)
        clearSearchButton.assertIsDisplayed()
        clearSearchButton.performClick()
        Thread.sleep(1000)
        //End test search tv

        //Test scrolling tv
        Thread.sleep(1000)
        tvList.performScrollToIndex(6)

        Thread.sleep(1000)
        tvList.performScrollToIndex(12)

        Thread.sleep(1000)
        tvList.performScrollToIndex(18)

        Thread.sleep(1000)
        tvList.performScrollToIndex(24)
        Thread.sleep(1000)
        //End scrolling tv

        movieTab.performClick()
        Thread.sleep(1000)
    }
}