package au.com.wsit.quizapp;

/**
 * Created by guyb on 23/08/16.
 */
public class Question
{
    private String mSkillLevel;
    private int mNumberRange;

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
    public int[] create()
    {

        // Create two numbers
        Number firstNumber = new Number(mNumberRange);
        Number secondNumber = new Number(mNumberRange);

        // Generate the numbers and store in the array

        int[] numbers = {firstNumber.generate(), secondNumber.generate()};

        // return the array
        return numbers;


    }


}
