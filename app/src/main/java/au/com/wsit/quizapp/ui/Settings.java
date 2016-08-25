package au.com.wsit.quizapp.ui;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import au.com.wsit.quizapp.R;

/**
 * Created by guyb on 25/08/16.
 */
public class Settings extends PreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
