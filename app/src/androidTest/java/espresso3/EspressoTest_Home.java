package espresso3;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.view.HomeActivity;

import com.example.sqlitedemo.view.QuizActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest_Home {

    @Rule
    public ActivityScenarioRule<HomeActivity> activityRule =
            new ActivityScenarioRule<>(HomeActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @Test
    public void isTextDisplayed() {
        onView(withId(R.id.buttonStart)).check(matches(isDisplayed()));
    }

    @Test
    public void testButton_StartQuiz() {
        // click the "Start Quiz" Button
        onView(withId(R.id.buttonStart)).perform(click());

        // Verify that the HomeActivity was launched
        intended(hasComponent(QuizActivity.class.getName()));
    }


    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
}
