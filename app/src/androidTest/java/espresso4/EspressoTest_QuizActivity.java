package espresso4;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.model.FlagsDAO;
import com.example.sqlitedemo.model.FlagsDatabase;
import com.example.sqlitedemo.view.HomeActivity;
import com.example.sqlitedemo.view.QuizActivity;
import com.example.sqlitedemo.view.ResultActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest_QuizActivity {

    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule =
            new ActivityScenarioRule<>(QuizActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @Test
    public void isTextCorrectDisplayed() {
        onView(withId(R.id.textViewCorrect)).check(matches(isDisplayed()));
    }

    @Test
    public void isTextEmptyDisplayed() {
        onView(withId(R.id.textViewEmpty)).check(matches(isDisplayed()));
    }

    @Test
    public void isTextWrongDisplayed() {
        onView(withId(R.id.textViewWrong)).check(matches(isDisplayed()));
    }

    @Test
    public void isNextButtonDisplayed() {
        onView(withId(R.id.imageViewNext)).check(matches(isDisplayed()));
    }


    @Test
    public void isFlagDisplayed() {
        // Verify that the flag image is displayed
        onView(withId(R.id.imageViewFlag)).check(matches(isDisplayed()));
    }

    @Test
    public void testNextButton() {
        // click the "Next" Button
        onView(withId(R.id.imageViewNext)).perform(click());

        // Verify that the next question is displayed
        onView(withId(R.id.textViewQuestion)).check(matches(isDisplayed()));
    }

    @Test
    public void testIfNextButtonChangeActivityAfter10Questions() {

        int questionsAnswered = 0;
        do {
            // Click the "Next" button
            onView(withId(R.id.imageViewNext)).perform(click());

            if(questionsAnswered == 9) {
                // Verify that the ResultActivity was launched
                intended(hasComponent(ResultActivity.class.getName()));
                break;
            }
            // Verify that the next question is displayed
            onView(withId(R.id.textViewQuestion)).check(matches(isDisplayed()));

            questionsAnswered++;
        } while(questionsAnswered < 10);


    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }




}
