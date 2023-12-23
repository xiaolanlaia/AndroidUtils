package com.wjf.androidutils.base.transit

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wjf.androidutils.R
import com.wjf.androidutils.origin.base.transit.TitleBarActivity
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2023/12/4 9:12
 */
@RunWith(AndroidJUnit4::class)
class TitleBarActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TitleBarActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun into_design_back(){
        onView(withId(R.id.btn_design)).perform(click())
        onView(withId(R.id.linear_back)).perform(click())
    }

    @Test
    fun into_persistent_back(){
        onView(withId(R.id.btn_persistent)).perform(click())
        onView(withId(R.id.linear_back)).perform(click())
    }

    @Test
    fun into_toast_back(){
        onView(withId(R.id.btn_toast)).perform(click())
        onView(withId(R.id.linear_back)).perform(click())
    }

    @Test
    fun into_array_back(){
        onView(withId(R.id.btn_array)).perform(click())
        onView(withId(R.id.linear_back)).perform(click())
    }

    @Test
    fun into_file_back(){
        onView(withId(R.id.btn_file)).perform(click())
        onView(withId(R.id.linear_back)).perform(click())
    }
}


