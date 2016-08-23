package au.com.wsit.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private TextView mFirstNumber;
    private TextView mSecondNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstNumber = (TextView) findViewById(R.id.firstNumberTextView);
        mSecondNumber = (TextView) findViewById(R.id.secondNumberTextView);

        // Generate a question






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
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
