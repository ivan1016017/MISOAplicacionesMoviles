package com.example.vynilos

import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test

class CreateAlbumActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun albumDetailActivityTest1() {
        val materialButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.btn_create_album),
                ViewMatchers.withText("Crear album"),
            )
        )
        materialButton.perform(ViewActions.scrollTo(), ViewActions.click())
        Thread.sleep(1000)


        val appCompatAlbumViewTextName = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etName),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatAlbumViewTextName.perform(ViewActions.typeText("Nuevo album miso"), closeSoftKeyboard())
        Thread.sleep(1000)

        val appCompatAlbumViewTextUrl = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etCover),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatAlbumViewTextUrl.perform(ViewActions.typeText("https://i.ytimg.com/vi/d4IoXr_TOGs/maxresdefault.jpg"), closeSoftKeyboard())
        Thread.sleep(1000)

        val appCompatCreateAlbumViewTextDescription = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etDescription),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatCreateAlbumViewTextDescription.perform(ViewActions.typeText("Me siento orgulloso de pertenecer a miso"), closeSoftKeyboard())
        Thread.sleep(3000)

        val appCompatCreateAlbumViewButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.saveBtn),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatCreateAlbumViewButton.perform(ViewActions.click())
        Thread.sleep(1000)

    }




    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}