package com.wjf.androidutils.ui.home


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wjf.androidutils.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/3 14:11
 */

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    val scenario = launchFragmentInContainer<HomeFragment>()
    @Before
    fun setUp() {
    }

    @Test
    fun into_design_back(){
        onView(ViewMatchers.withId(R.id.btn_design)).perform(click())
        onView(ViewMatchers.withId(R.id.linear_back)).perform(click())
        onView(ViewMatchers.withId(R.id.btn_design)).check(matches(isDisplayed()))
    }

    @Test
    fun into_persistent_back(){
        onView(ViewMatchers.withId(R.id.btn_persistent)).perform(click())
        onView(ViewMatchers.withId(R.id.linear_back)).perform(click())
    }

    @Test
    fun into_toast_back(){
        onView(ViewMatchers.withId(R.id.btn_toast)).perform(click())
        onView(ViewMatchers.withId(R.id.linear_back)).perform(click())
    }

    @Test
    fun into_array_back(){
        onView(ViewMatchers.withId(R.id.btn_array)).perform(click())
        onView(ViewMatchers.withId(R.id.linear_back)).perform(click())
    }

    @Test
    fun into_file_back(){
        onView(ViewMatchers.withId(R.id.btn_file)).perform(click())
        onView(ViewMatchers.withId(R.id.linear_back)).perform(click())
    }
}