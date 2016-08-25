package au.com.wsit.quizapp.utils;

import java.util.Random;

/**
 * Class which generates a random number
 */
public class Number
{
    // Member variables
    private int mRandomNumberMax;

    // Constructor
    public Number(int numberMax)
    {
        mRandomNumberMax = numberMax;
    }

    public int generate()
    {
        Random random = new Random();
        int number = random.nextInt(mRandomNumberMax);

        return number;

    }
}
