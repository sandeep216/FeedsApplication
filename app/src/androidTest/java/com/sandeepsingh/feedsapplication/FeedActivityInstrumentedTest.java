package com.sandeepsingh.feedsapplication;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.sandeepsingh.feedsapplication.base.utils.Utils;
import com.sandeepsingh.feedsapplication.feature.view.FeedActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * Created by Sandeep on 11/17/18.
 */
@RunWith(AndroidJUnit4.class)
public class FeedActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<FeedActivity> mActivityRule = new ActivityTestRule<>(FeedActivity.class);

    @Test
    public void testActivityOnBasedOnInternetConnectivity() {
        if (Utils.Companion.haveNetworkConnection(mActivityRule.getActivity())) {
            onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
        } else {
            onView(withId(R.id.layout_no_internet)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void testRecyclerViewItemDataClick() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }

    @Test
    public void testRecyclerViewChildLayoutAfterScrolling() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.recycler_view)).perform(scrollToPosition(5));
        onView(withId(R.id.recycler_view)).perform((RecyclerViewActions.actionOnItemAtPosition(5,click())));
    }

}
