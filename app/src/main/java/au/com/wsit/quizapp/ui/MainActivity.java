package au.com.wsit.quizapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import au.com.wsit.quizapp.adapters.AnswerAdapter;
import au.com.wsit.quizapp.utils.AnswerItems;
import au.com.wsit.quizapp.utils.Constants;
import au.com.wsit.quizapp.utils.Question;
import au.com.wsit.quizapp.R;

public class MainActivity extends AppCompatActivity
{

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mFirstNumber;
    private TextView mSecondNumber;
    private TextView mCorrectCount;
    private TextView mWrongCount;
    private TextView mSkillLevel;
    private RecyclerView mAnswerRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AnswerAdapter mAnswerAdapter;
    private String mSkillLevelSetting;
    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        mFirstNumber = (TextView) findViewById(R.id.firstNumberTextView);
        mSecondNumber = (TextView) findViewById(R.id.secondNumberTextView);
        mCorrectCount = (TextView) findViewById(R.id.correctTextView);
        mWrongCount = (TextView) findViewById(R.id.wrongTextView);
        mSkillLevel = (TextView) findViewById(R.id.skillLevelTextView);

        // Recycler view setup
        mAnswerRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        mAnswerRecyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // Get the skill level and display
        mSkillLevelSetting = mSharedPreferences.getString(Constants.KEY_SKILL_LEVEL, "Easy");
        mSkillLevel.setText(mSkillLevelSetting);

        // Display the counters
        try
        {
            mCorrectCount.setText("Correct " + mSharedPreferences.getInt(Constants.KEY_CORRECT_ANSWERS, 0));
            mWrongCount.setText("Wrong " + mSharedPreferences.getInt(Constants.KEY_WRONG_ANSWERS, 0));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        displayQuestion();
    }

    private void displayQuestion()
    {
        // Create a question object
        Log.i(TAG, "Skill level is on " + mSkillLevelSetting);
        Question question = new Question(mSkillLevelSetting);

        // Generate
        question.create();

        mFirstNumber.setText(question.getFirstNumber() + "");
        mSecondNumber.setText(question.getSecondNumber() + "");


        AnswerItems[] possibleAnswerItems = new AnswerItems[question.getAnswers().length];

        // Loop through the possible answers and display

        for (int i = 0; i < possibleAnswerItems.length; i++)
        {
            AnswerItems item = new AnswerItems();
            String possibleAnswer = String.valueOf(question.getAnswers()[i]);
            Log.i(TAG, "Possible answer: " + possibleAnswer);

            item.setmPossibleAnswer(possibleAnswer);

            possibleAnswerItems[i] = item;

        }

        // Randomise the array items

        mAnswerAdapter = new AnswerAdapter(MainActivity.this, shuffleAnswers(possibleAnswerItems), question.getCorrectAnswer());
        mAnswerRecyclerView.setAdapter(mAnswerAdapter);
    }

    // Reference: Durstenfeld shuffle algorithm
    private AnswerItems[] shuffleAnswers(AnswerItems[] items)
    {
        for (int i = items.length - 1; i > 0; i--) {
            int j = (int) Math.floor(Math.random() * (i + 1));
            AnswerItems temp = items[i];
            items[i] = items[j];
            items[j] = temp;
        }
        return items;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.settings:
                // Start settings
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
