package com.wjf.androidutils.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wjf.androidutils.R
import com.wjf.androidutils.origin.ui.ScreenFragment
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/4 18:01
 */

@RunWith(AndroidJUnit4::class)
class ScreenFragmentTest {
    val scenario = launchFragmentInContainer<ScreenFragment>()

    @Before
    fun setUp() {}


    @Test
    fun getStatusBarHeight() {
        onView(withId(R.id.btn_status_bar)).perform(click())
    }

    @Test
    fun getDeviceWidth() {
        onView(withId(R.id.btn_device_width)).perform(click())
    }

    @Test
    fun getDeviceHeight() {
        onView(withId(R.id.btn_device_height)).perform(click())
    }

    @Test
    fun getIMEI() {
        onView(withId(R.id.btn_imei)).perform(click())
    }
}