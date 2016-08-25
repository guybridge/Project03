package au.com.wsit.quizapp.utils;

import java.util.Random;

import au.com.wsit.quizapp.utils.Number;

/**
 * Created by guyb on 23/08/16.
 */
public class Question
{
    private String mSkillLevel;
    private int mNumberRange;
    private int mFirstNumber;
    private int mSecondNumber;
    private int mAnswer;

    public Question(String skillLevel)
    {
        mSkillLevel = skillLevel;

        if (mSkillLevel.equals("EASY"))
        {
            // Set number range
            mNumberRange = 10;
        }
        else if(mSkillLevel.equals("MEDIUM"))
        {
          // Set number range
            mNumberRange = 100;
        }
        else if(mSkillLevel.equals("HARD"))
        {
            // Set number range
            mNumberRange = 1000;
        }
    }


    // Creates two random numbers
    public void create()
    {

        // Create two numbers
        Number firstNumber = new Number(mNumberRange);
        Number secondNumber = new Number(mNumberRange);

        // Generate the numbers and store in the member variables

        mFirstNumber = firstNumber.generate();
        mSecondNumber = secondNumber.generate();


    }

    public int getFirstNumber()
    {
        return mFirstNumber;
    }

    public int getSecondNumber()
    {
        return mSecondNumber;
    }

    public int[] getAnswers()
    {

        int answers[] = new int[3];

        // Solve the real answer
        mAnswer = mFirstNumber + mSecondNumber;
        answers[0] = mAnswer;

        // Create other wrong answers
        answers[1] = new Random().nextInt(mNumberRange);
        answers[2] = new Random().nextInt(mNumberRange);


        return answers;
    }

    public int getCorrectAnswer()
    {
        return mAnswer;
    }



}
