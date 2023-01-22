package espresso3.espresso;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertFalse;

import android.text.TextUtils;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.view.HomeActivity;
import com.example.sqlitedemo.view.LoginActivity;
import com.example.sqlitedemo.view.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest_SignUp {

    public View decorView;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void isTextDisplayed() {
        onView(withText("Sign Up")).check(matches(isDisplayed()));
    }

    @Test
    public void testVisibilityButtonSignUp() {

        onView(withId(R.id.signup)).check(matches(isDisplayed()));

    }


    @Test
    public void testVisibilityButtonAlreadyHaveAccount() {

        onView(withId(R.id.signin)).check(matches(isDisplayed()));

    }


    @Before
    public void setUp() {
        Intents.init();
    }

    @Test
    public void testRegisterSuccessfully() {

        // Perform actions to fill in the form and click the signup button
        onView(withId(R.id.username)).perform(ViewActions.typeText("1700"));
        onView(withId(R.id.password)).perform(ViewActions.typeText("12345"));
        onView(withId(R.id.repassword)).perform(ViewActions.typeText("12345"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signup)).perform(click());

        // Verify that the HomeActivity was launched
        intended(hasComponent(HomeActivity.class.getName()));

    }

    @Test
    public void testAlreadyHaveAccountButton() {
        //click the "Already have Account" Button
        onView(withId(R.id.signin)).perform(click());

        // Verify that the LoginActivity was launched
        intended(hasComponent(LoginActivity.class.getName()));

    }

    @Test
    public void testUseralreadyexists() {

        //Perform actions to fill in the form and click the signup button
        onView(withId(R.id.username)).perform(ViewActions.typeText("300"));
        onView(withId(R.id.password)).perform(ViewActions.typeText("1234"));
        onView(withId(R.id.repassword)).perform(ViewActions.typeText("1234"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signup)).perform(click());

        // Assert that the current activity remains unchanged
        onView(withText("Sign Up")).check(matches(isDisplayed()));

    }

    @Test
    public void passwordsAreNotMatching_Test1() {
        onView(ViewMatchers.withId(R.id.username)).perform(ViewActions.typeText("300"));
        onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("1234"));
        onView(ViewMatchers.withId(R.id.repassword)).perform(ViewActions.typeText("123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signup)).perform(click());

        // Assert that the current activity remains unchanged
        onView(withText("Sign Up")).check(matches(isDisplayed()));


    }

    @Test
    public void passwordsAreNotMatching_Test2() {

        String passwordLine1 = "1234";
        String passwordLine2 = "123";
        onView(ViewMatchers.withId(R.id.username)).perform(ViewActions.typeText("300"));
        onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText(passwordLine1));
        onView(ViewMatchers.withId(R.id.repassword)).perform(ViewActions.typeText(passwordLine2));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signup)).perform(click());

        //Check if the in password Line 2 is the same password like in Password Line 1
        assertFalse(passwordLine2.contains(passwordLine1));

    }

    @Test
    public void testAllfieldsrequired() {
        // Click the sign up Button
        onView(withId(R.id.signup)).perform(click());

        CharSequence username = onView(withId(R.id.username)).perform(ViewActions.typeText("")).toString();
        CharSequence password = onView(withId(R.id.password)).perform(ViewActions.typeText("")).toString();
        CharSequence repassword = onView(withId(R.id.password)).perform(ViewActions.typeText("")).toString();

        // Check if either field is empty
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
            // Assert that the current activity remains unchanged
            onView(withText("Sign Up")).check(matches(isDisplayed()));
        }

    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}
