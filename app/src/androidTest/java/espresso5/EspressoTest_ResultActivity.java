package espresso5;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.view.HomeActivity;
import com.example.sqlitedemo.view.ResultActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest_ResultActivity {

    @Rule
    public ActivityScenarioRule<ResultActivity> activityRule =
            new ActivityScenarioRule<>(ResultActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @Test
    public void testIsTotalCorrectAnswerDisplayed() {
        onView(withId(R.id.textViewTotalCorrect)).check(matches(isDisplayed()));
    }

    @Test
    public void testIsTotalWrongAnswerDisplayed() {
        onView(withId(R.id.textViewTotalWrong)).check(matches(isDisplayed()));
    }

    @Test
    public void testIsTotalEmptyAnswerDisplayed() {
        onView(withId(R.id.textViewTotalEmpty)).check(matches(isDisplayed()));
    }

    @Test
    public void test_PlayAgain_ButtonIsWorking() {

        // Click the "Play Again" Button
        onView(withId(R.id.buttonAgain)).perform(click());

        // Verify that the QuizActivity was launched
        intended(hasComponent(HomeActivity.class.getName()));
    }

    @Test
    public void test_QuitGame_ButtonIsWorking() {

        // Click the "Play Again" Button
        onView(withId(R.id.buttonQuit)).perform(click());

        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.pressHome();

        // Check if the app has closed
        assertFalse(getInstrumentation().getTargetContext().getPackageName()
                .equals(device.getCurrentPackageName()));

    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }


}
