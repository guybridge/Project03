package au.com.wsit.quizapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import au.com.wsit.quizapp.adapters.AnswerAdapter;
import au.com.wsit.quizapp.utils.AnswerItems;
import au.com.wsit.quizapp.utils.Question;
import au.com.wsit.quizapp.R;

public class MainActivity extends AppCompatActivity
{

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mFirstNumber;
    private TextView mSecondNumber;
    private RecyclerView mAnswerRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AnswerAdapter mAnswerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstNumber = (TextView) findViewById(R.id.firstNumberTextView);
        mSecondNumber = (TextView) findViewById(R.id.secondNumberTextView);

        // Recycler view setup
        mAnswerRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        mAnswerRecyclerView.setLayoutManager(layoutManager);

        displayQuestion();


    }

    private void displayQuestion()
    {
        // Create a question object
        Question question = new Question("EASY");

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

        mAnswerAdapter = new AnswerAdapter(MainActivity.this, possibleAnswerItems, question.getCorrectAnswer());
        mAnswerRecyclerView.setAdapter(mAnswerAdapter);
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
