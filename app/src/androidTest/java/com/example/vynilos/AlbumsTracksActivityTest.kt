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

class AlbumsTracksActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun albumDetailActivityTest1() {
        val materialButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.btn_albums_menu),
                ViewMatchers.withText("Catalogo de albumes"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        materialButton.perform(ViewActions.scrollTo(), ViewActions.click())
        Thread.sleep(1000)
        val recyclerView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.rvAlbums),
                childAtPosition(
                    ViewMatchers.withClassName(Matchers.`is`("android.widget.FrameLayout")),
                    2
                )
            )
        )
        recyclerView.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                ViewActions.click()
            )
        )
        Thread.sleep(1000)
        val textView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.title), ViewMatchers.withText("Poeta del pueblo"),
                ViewMatchers.withParent(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.principal),
                        ViewMatchers.withParent(IsInstanceOf.instanceOf(RelativeLayout::class.java))
                    )
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textView.check(ViewAssertions.matches(ViewMatchers.withText("Poeta del pueblo")))



        val appCompatTrackAlbumView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.btn_tie_track_to_album),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatTrackAlbumView.perform(ViewActions.click())

        val appCompatTrackAlbumViewTextName = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.txt_name),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatTrackAlbumViewTextName.perform(ViewActions.typeText("Miso Cancion"), closeSoftKeyboard())
        Thread.sleep(1000)

        val appCompatTrackAlbumViewTextDuration = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.txt_duration),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatTrackAlbumViewTextDuration.perform(ViewActions.typeText("1:00"),closeSoftKeyboard())
        Thread.sleep(1000)

        val appCompatTrackAlbumViewButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.btn_create_tie_track_to_album)
            )
        )
        appCompatTrackAlbumViewButton.perform(ViewActions.click())


    }

    @Test
    fun albumDetailActivityTest2() {
        val materialButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.btn_albums_menu),
                ViewMatchers.withText("Catalogo de albumes"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        materialButton.perform(ViewActions.scrollTo(), ViewActions.click())
        Thread.sleep(1000)
        val recyclerView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.rvAlbums),
                childAtPosition(
                    ViewMatchers.withClassName(Matchers.`is`("android.widget.FrameLayout")),
                    2
                )
            )
        )
        recyclerView.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                ViewActions.click()
            )
        )
        Thread.sleep(1000)
        val textView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.title), ViewMatchers.withText("Poeta del pueblo"),
                ViewMatchers.withParent(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.principal),
                        ViewMatchers.withParent(IsInstanceOf.instanceOf(RelativeLayout::class.java))
                    )
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textView.check(ViewAssertions.matches(ViewMatchers.withText("Poeta del pueblo")))



        val appCompatTrackAlbumView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.btn_tie_track_to_album),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatTrackAlbumView.perform(ViewActions.click())

        val appCompatTrackAlbumViewTextName = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.txt_name),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatTrackAlbumViewTextName.perform(ViewActions.typeText("Miso Cancion"), closeSoftKeyboard())
        Thread.sleep(1000)


        val appCompatTrackAlbumViewButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.btn_create_tie_track_to_album)
            )
        )
        appCompatTrackAlbumViewButton.perform(ViewActions.click())

        val textViewWarning = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.tvErrors)
            )
        )
        textViewWarning.check(ViewAssertions.matches(ViewMatchers.withText("Duracion del track requerido \n")))




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