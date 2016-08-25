package au.com.wsit.quizapp.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

import au.com.wsit.quizapp.R;
import au.com.wsit.quizapp.utils.Constants;

/**
 * Created by guyb on 25/08/16.
 */
public class Settings extends PreferenceActivity
{
    public static final String TAG = Settings.class.getSimpleName();
    ListPreference listPreference;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        listPreference = (ListPreference) findPreference(Constants.KEY_SKILL_LEVEL);
        // Get the value
        String skillLevel = mSharedPreferences.getString(Constants.KEY_SKILL_LEVEL, "Easy");

        listPreference.setValue(skillLevel);
        listPreference.setTitle(skillLevel);


        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
        {


            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue)
            {

                Log.i(TAG, "Skill level changed" + preference.getKey() + " to " + newValue.toString());
                mSharedPreferences.edit().putString(Constants.KEY_SKILL_LEVEL, newValue.toString()).apply();

                listPreference.setTitle(newValue.toString());

                return false;
            }
        });


    }
}
