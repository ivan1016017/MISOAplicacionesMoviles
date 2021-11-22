package com.example.vynilos


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ArtistDetailActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun artistDetailActivityTest() {
        val materialButton = onView(
                allOf(withId(R.id.btn_artists_menu), withText("Catalogo de artistas"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                2)))
        materialButton.perform(scrollTo(), click())
        Thread.sleep(2000)
        val recyclerView = onView(
                allOf(withId(R.id.rvArtists),
                        childAtPosition(
                                withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                                2)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        Thread.sleep(2000)
        val textView = onView(
                allOf(withId(R.id.title), withText("Queen"),
                        withParent(allOf(withId(R.id.principal),
                                withParent(IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java)))),
                        isDisplayed()))
        textView.check(matches(withText("Queen")))

        val textView2 = onView(
                allOf(withId(R.id.date), withText("01/Jan/1970"),
                        withParent(allOf(withId(R.id.principal),
                                withParent(IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java)))),
                        isDisplayed()))
        textView2.check(matches(withText("01/Jan/1970")))

        val appCompatImageView = onView(
                allOf(withId(R.id.left_icon),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        0),
                                0),
                        isDisplayed()))
        appCompatImageView.perform(click())

        val textView3 = onView(
                allOf(withId(R.id.toolbar_text), withText("Artistas"),
                        withParent(withParent(withId(R.id.toolbar))),
                        isDisplayed()))
        textView3.check(matches(withText("Artistas")))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

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
