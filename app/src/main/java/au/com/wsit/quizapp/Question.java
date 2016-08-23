package au.com.wsit.quizapp;

/**
 * Created by guyb on 23/08/16.
 */
public class Question
{
    private String mSkillLevel;
    private int mFirstNumber;
    private int mSecondNumber;

    public Question(String skillLevel)
    {
        mSkillLevel = skillLevel;

        if (mSkillLevel.equals("EASY"))
        {
            // Set number range

        }
        else if(mSkillLevel.equals("MEDIUM"))
        {
          // Set number range
        }
        else if(mSkillLevel.equals("HARD"))
        {
            // Set number range
        }
    }

    public int[] create()
    {
        mFirstNumber = 10;

        return null;


    }


}
