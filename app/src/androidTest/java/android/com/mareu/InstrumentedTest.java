package android.com.mareu;

import android.com.mareu.ui.list.ListMeetingActivity;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.com.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    @Rule
    public ActivityScenarioRule<ListMeetingActivity> mActivityRule =
            new ActivityScenarioRule(ListMeetingActivity.class);

    @Test
    public void useAppContext() {
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("android.com.mareu", appContext.getPackageName());
    }

    @Test
    public void meetingDateIsFiltered() {
        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(0));
        onView(withId(R.id.meeting_add_button)).perform(click());
        onView(withId(R.id.meeting_subject_edittext)).perform(typeText("meeting test"));
        onView(withId(R.id.date_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 4, 24));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.beginning_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(10, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.ending_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(11, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.room_spinner)).perform(click());
        onView(withText("Room A")).perform(click());
        onView(withId(R.id.add_email_edittext)).perform(typeText("email@test.com"));
        onView(withId(R.id.add_email_image)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.validate_meeting_btn)).perform(click());
        onView(withId(R.id.meeting_add_button)).perform(click());
        onView(withId(R.id.meeting_subject_edittext)).perform(typeText("meeting test2"));
        onView(withId(R.id.date_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 4, 25));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.beginning_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(10, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.ending_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(11, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.room_spinner)).perform(click());
        onView(withText("Room B")).perform(click());
        onView(withId(R.id.add_email_edittext)).perform(typeText("email2@test.com"));
        onView(withId(R.id.add_email_image)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.validate_meeting_btn)).perform(click());
        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(2));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Filtrer par date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 4, 25));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(1));
    }

    @Test
    public void meetingRoomIsFiltered() {
        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(0));
        onView(withId(R.id.meeting_add_button)).perform(click());
        onView(withId(R.id.meeting_subject_edittext)).perform(typeText("meeting test"));
        onView(withId(R.id.date_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 4, 24));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.beginning_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(10, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.ending_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(11, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.room_spinner)).perform(click());
        onView(withText("Room A")).perform(click());
        onView(withId(R.id.add_email_edittext)).perform(typeText("email@test.com"));
        onView(withId(R.id.add_email_image)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.validate_meeting_btn)).perform(click());
        onView(withId(R.id.meeting_add_button)).perform(click());
        onView(withId(R.id.meeting_subject_edittext)).perform(typeText("meeting test2"));
        onView(withId(R.id.date_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 4, 24));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.beginning_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(10, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.ending_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(11, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.room_spinner)).perform(click());
        onView(withText("Room B")).perform(click());
        onView(withId(R.id.add_email_edittext)).perform(typeText("email2@test.com"));
        onView(withId(R.id.add_email_image)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.validate_meeting_btn)).perform(click());
        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(2));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Filtrer par salle")).perform(click());
        onView(withText("Room B")).perform(click());
        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(1));
    }

    @Test
    public void shouldAddNewMeetingThenDelete() {
        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(0));
        onView(withId(R.id.meeting_add_button)).perform(click());
        onView(withId(R.id.meeting_subject_edittext)).perform(typeText("meeting test"));
        onView(withId(R.id.date_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 4, 24));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.beginning_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(10, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.ending_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(11, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.room_spinner)).perform(click());
        onView(withText("Room A")).perform(click());
        onView(withId(R.id.add_email_edittext)).perform(typeText("email@test.com"));
        onView(withId(R.id.add_email_image)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.validate_meeting_btn)).perform(click());
        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(1));
        onView(withId(R.id.delete_btn)).perform(click());
        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(0));
    }

    @Test
    public void checkMeetingCannotCreateIfOverlapExisting() {
        onView(withId(R.id.meeting_add_button)).perform(click());
        onView(withId(R.id.meeting_subject_edittext)).perform(typeText("meeting test"));
        onView(withId(R.id.date_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 4, 24));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.beginning_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(10, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.ending_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(11, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.room_spinner)).perform(click());
        onView(withText("Room A")).perform(click());
        onView(withId(R.id.add_email_edittext)).perform(typeText("email@test.com"));
        onView(withId(R.id.add_email_image)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.validate_meeting_btn)).perform(click());
        onView(withId(R.id.meeting_add_button)).perform(click());
        onView(withId(R.id.meeting_subject_edittext)).perform(typeText("meeting test"));
        onView(withId(R.id.date_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 4, 24));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.beginning_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(10, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.ending_meeting_time_textview)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(11, 0));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.room_spinner)).perform(click());
        onView(withText("Room A")).perform(click());
        onView(withId(R.id.add_email_edittext)).perform(typeText("email@test.com"));
        onView(withId(R.id.add_email_image)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.validate_meeting_btn)).perform(click());
        //onView(withText(R.string.rooms_not_available)).inRoot(new ToastMatcher()).check(matches(withText("La salle de meeting n'est pas disponible ?? ces horraires")));
        pressBack();
        onView(withId(R.id.meeting_recyclerView)).check(withItemCount(1));
    }
}