package espresso2;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.text.TextUtils;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.view.HomeActivity;
import com.example.sqlitedemo.view.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest_SignIn {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Before
    public void setUp() {
        Intents.init();

    }



    @Test
    public void isTextDisplayed() {
        onView(withText("Sign In")).check(matches(isDisplayed()));
    }

    @Test
    public void testVisibilityButtonSignIn() {

        onView(withId(R.id.signin1)).check(matches(isDisplayed()));

    }

    @Test
    public void testSignInButtonIsWorking() {

        // Perform actions to fill in the form and click the sign in button
        onView(withId(R.id.username)).perform(ViewActions.typeText("1700"));
        onView(withId(R.id.password)).perform(ViewActions.typeText("12345"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signin1)).perform(click());

        // Verify that the HomeActivity was launched
        intended(hasComponent(HomeActivity.class.getName()));
    }

    @Test
    public void test_ButtonDoesNotChangeTheActivityWithEmptyBox() {

        // Click the sign in Button
        onView(withId(R.id.signin1)).perform(click());

        CharSequence username = onView(withId(R.id.username)).perform(ViewActions.typeText("")).toString();
        CharSequence password = onView(withId(R.id.password)).perform(ViewActions.typeText("")).toString();

        // Check if either field is empty
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            // Assert that the current activity remains unchanged
            onView(withText("Sign In")).check(matches(isDisplayed()));
        }


    }

    @After
    public void tearDown() {
        Intents.release();
    }
}
