package com.wjf.androidutils.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wjf.androidutils.R
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/4 18:26
 */

@RunWith(AndroidJUnit4::class)
class DeviceFragmentTest {

    val scenario = launchFragmentInContainer<DeviceFragment>()

    @Before
    fun setUp() {
    }

    @Test
    fun getManufacturer(){
        onView(withId(R.id.btn_manufacturer)).perform(click())

    }
    @Test
    fun getProduct(){
        onView(withId(R.id.btn_product)).perform(click())
    }
    @Test
    fun getBrand(){
        onView(withId(R.id.btn_brand)).perform(click())
    }
    @Test
    fun getModel(){
        onView(withId(R.id.btn_model)).perform(click())
    }
    @Test
    fun getBoard(){
        onView(withId(R.id.btn_board)).perform(click())
    }
    @Test
    fun getDevice(){
        onView(withId(R.id.btn_device)).perform(click())
    }
    @Test
    fun getFingerprint(){
        onView(withId(R.id.btn_fingerprint)).perform(click())
    }
    @Test
    fun getRAMInfo(){
        onView(withId(R.id.btn_ram)).perform(click())
    }
}