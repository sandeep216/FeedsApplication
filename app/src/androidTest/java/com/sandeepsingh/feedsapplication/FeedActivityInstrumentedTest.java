package com.sandeepsingh.feedsapplication;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.sandeepsingh.feedsapplication.base.utils.Utils;
import com.sandeepsingh.feedsapplication.feature.model.FeedModel;
import com.sandeepsingh.feedsapplication.feature.pojos.FeedItem;
import com.sandeepsingh.feedsapplication.feature.pojos.Feeds;
import com.sandeepsingh.feedsapplication.feature.presenter.FeedPresenter;
import com.sandeepsingh.feedsapplication.feature.view.FeedActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;

/**
 * Created by Sandeep on 11/17/18.
 */
@RunWith(AndroidJUnit4.class)
public class FeedActivityInstrumentedTest {

    private FeedPresenter mPresenter;
    private FeedModel model;

    @Rule
    public ActivityTestRule<FeedActivity> mActivityRule = new ActivityTestRule<>(FeedActivity.class);

    private Feeds feeds = new Feeds();

    @Before
    public void launchActivity(){

    }

    @Test
     public void testActivityOnBasedOnInternetConnectivity(){
        if (Utils.Companion.haveNetworkConnection(mActivityRule.getActivity())){
            onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
        }else {
            onView(withId(R.id.layout_no_internet)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void testRecyclerViewItemDataClick(){
      /*  onData(hasToString(containsString("Flag"))).perform(click());
        onView(withText(startsWith("Clicked"))).
                inRoot(withDecorView(
                        not(is(mActivityRule.getActivity().
                                getWindow().getDecorView())))).
                check(matches(isDisplayed()));*/
     //  mActivityRule.getActivity().initComponents();
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }


}
