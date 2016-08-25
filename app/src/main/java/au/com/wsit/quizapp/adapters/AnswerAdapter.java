package au.com.wsit.quizapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import au.com.wsit.quizapp.R;
import au.com.wsit.quizapp.ui.MainActivity;
import au.com.wsit.quizapp.utils.AnswerItems;
import au.com.wsit.quizapp.utils.Constants;

/**
 * Created by guyb on 25/08/16.
 */
public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder>
{
    private Context mContext;
    private AnswerItems[] mItems;
    private int mCorrectAnswer;


    public AnswerAdapter(Context context, AnswerItems[] items, int answer)
    {
        mContext = context;
        mItems = items;
        mCorrectAnswer = answer;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answers_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        holder.bindAnswer(mItems[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(Integer.valueOf(mItems[position].getmPossibleAnswer()) == mCorrectAnswer)
                {
                    Toast.makeText(mContext, "Correct !", Toast.LENGTH_SHORT).show();

                    // Save counter data
                    int numCorrect = PreferenceManager.getDefaultSharedPreferences(mContext).getInt(Constants.KEY_CORRECT_ANSWERS, 0);
                    numCorrect++;

                    // Save
                    PreferenceManager.getDefaultSharedPreferences(mContext).edit().putInt(Constants.KEY_CORRECT_ANSWERS, numCorrect).apply();

                }
                else
                {
                    Toast.makeText(mContext, "Wrong : (", Toast.LENGTH_SHORT).show();

                    // Save counter data
                    int numWrong = PreferenceManager.getDefaultSharedPreferences(mContext).getInt(Constants.KEY_WRONG_ANSWERS, 0);
                    numWrong++;

                    // Save
                    PreferenceManager.getDefaultSharedPreferences(mContext).edit().putInt(Constants.KEY_WRONG_ANSWERS, numWrong).apply();
                }

                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mItems.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mPossibleAnswer;

        public ViewHolder(View itemView)
        {
            super(itemView);

            mPossibleAnswer = (TextView) itemView.findViewById(R.id.possibleAnswerTextView);


        }

        public void bindAnswer(AnswerItems item)
        {
            mPossibleAnswer.setText(item.getmPossibleAnswer());
        }
    }
}
