package com.wjf.androidutils.compose.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wjf.androidutils.compose.nav.MyNavHost
import com.wjf.androidutils.compose.theme.MyAppTheme
import com.wjf.androidutils.compose.utils.TAG_COMMON_TITLE_BACK
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/8 14:28
 */

@RunWith(AndroidJUnit4::class)
class EnterComponentTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            MyAppTheme{
                val navHostController : NavHostController = rememberNavController()
                MyNavHost(navHostController)
            }
        }
    }

    @Test
    fun checkTextShow_click(){
        tvContentList.forEach {
            composeRule.onNodeWithContentDescription(it)
                .assertIsDisplayed()

            composeRule
                .onNodeWithText(it)
                .assertIsDisplayed()
                .performClick()
            runBlocking {
                delay(500)
                composeRule.onNodeWithTag(TAG_COMMON_TITLE_BACK).performClick()
                delay(500)
            }
        }
    }

}