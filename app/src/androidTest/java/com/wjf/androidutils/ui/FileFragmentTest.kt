package com.wjf.androidutils.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wjf.androidutils.R
import com.wjf.androidutils.origin.ui.FileFragment

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/4 14:40
 */

@RunWith(AndroidJUnit4::class)
class FileFragmentTest {

    val scenario = launchFragmentInContainer<FileFragment>()


    @Before
    fun setUp() {
    }

    @Test
    fun delete_folder(){
        onView(withId(R.id.btn_delete_folder)).perform(click())
    }
}