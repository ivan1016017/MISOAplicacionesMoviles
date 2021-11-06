package com.example.vynilos


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumsActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun albumsActivityTest2() {
        val materialButton = onView(
            allOf(
                withId(R.id.btn_albums_menu), withText("Catalogo de albumes"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        materialButton.perform(scrollTo(), click())

        val textView = onView(
            allOf(
                withId(R.id.toolbar_text), withText("Álbumes"),
                withParent(withParent(withId(R.id.toolbar))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Álbumes")))

        val view = onView(
            allOf(
                withId(R.id.rvAlbums),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        view.check(matches(isDisplayed()))

        val appCompatImageView = onView(
            allOf(
                withId(R.id.left_icon), withContentDescription("GOBACKROWIMAGE"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.toolbar),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val textView2 = onView(
            allOf(
                withText("Bienvenido a MusicApp"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Bienvenido a MusicApp")))
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
