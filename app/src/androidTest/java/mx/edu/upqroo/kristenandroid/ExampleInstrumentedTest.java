package mx.edu.upqroo.kristenandroid;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import mx.edu.upqroo.kristenandroid.ui.activities.LoadActivity;
import mx.edu.upqroo.kristenandroid.ui.activities.LoginActivity;
import mx.edu.upqroo.kristenandroid.ui.activities.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<LoadActivity> activityRule
            = new ActivityTestRule<>(LoadActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @Test
    public void loginTest() {
        // Context of the app under test.
        Context appContext = ApplicationProvider.getApplicationContext();

        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open()); // Open Drawer


        // Start the screen of your activity.
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_logout));

        intended(hasComponent(LoginActivity.class.getName()));

        onView(withId(R.id.field_user_id))
                .perform(typeText("201600001"), closeSoftKeyboard());
        onView(withId(R.id.button_login))
                .perform(click());

        intended(hasComponent(MainActivity.class.getName()));
    }

    @After
    public void finish() {
        Intents.release();
    }
}