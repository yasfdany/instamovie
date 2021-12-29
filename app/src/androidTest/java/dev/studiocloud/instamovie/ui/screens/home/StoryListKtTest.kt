package dev.studiocloud.instamovie.ui.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.studiocloud.instamovie.ui.MainActivity
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalAnimationApi
@InternalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
class StoryListKtTest {
    @get:Rule
    val activityTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun storyTabletList() {
    }

    @Test
    fun storyMobileList() {
        val storyList = activityTestRule.onNode(hasTestTag("story_list"))
        val storyImage = activityTestRule.onNode(hasTestTag("story_image"))

        storyList.assertIsDisplayed()
        Thread.sleep(1000)
        storyList.onChildAt(0).assertIsDisplayed()
        Thread.sleep(500)
    }
}