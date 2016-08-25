package au.com.wsit.quizapp.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import au.com.wsit.quizapp.R;
import au.com.wsit.quizapp.utils.Constants;

/**
 * Created by guyb on 25/08/16.
 */
public class Settings extends PreferenceActivity
{
    public static final String TAG = Settings.class.getSimpleName();
    ListPreference listPreference;
    Preference mResetCounters;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        listPreference = (ListPreference) findPreference(Constants.KEY_SKILL_LEVEL);
        mResetCounters = findPreference(Constants.KEY_RESET);
        // Get the value
        String skillLevel = mSharedPreferences.getString(Constants.KEY_SKILL_LEVEL, "Easy");

        // Set the title and value
        listPreference.setValue(skillLevel);
        listPreference.setTitle(skillLevel);
        // Sets the summary text for the skill level
        setSummary(skillLevel);

        mResetCounters.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
        {
            @Override
            public boolean onPreferenceClick(Preference preference)
            {
                clearCounters();
                return false;
            }
        });


        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
        {


            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue)
            {

                Log.i(TAG, "Skill level changed" + preference.getKey() + " to " + newValue.toString());
                mSharedPreferences.edit().putString(Constants.KEY_SKILL_LEVEL, newValue.toString()).apply();

                listPreference.setTitle(newValue.toString());
                setSummary(newValue.toString());

                return false;
            }
        });


    }

    private void clearCounters()
    {
        mSharedPreferences.edit().putInt(Constants.KEY_CORRECT_ANSWERS, 0).apply();
        mSharedPreferences.edit().putInt(Constants.KEY_WRONG_ANSWERS, 0).apply();

        Toast.makeText(getApplicationContext(), "Counters cleared", Toast.LENGTH_SHORT).show();
    }

    private void setSummary(String skillLevel)
    {
        switch(skillLevel)
        {
            case "Easy":

                listPreference.setSummary("Number range max is 10");
                break;
            case "Medium":
                listPreference.setSummary("Number range max is 100");
                break;
            case "Hard":
                listPreference.setSummary("Number range max is 1000");

        }
    }
}
