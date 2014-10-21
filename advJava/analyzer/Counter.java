package java112.analyzer;

/**
 * An instance of this class is intantiated for every unique word length
 * in the examined text. It keeps track of the running and overall count of
 * words of that particular length. It also assists in histogram output.
 *
 * @author bgreen
 */

public class Counter {

    private int count;

/**
 * returns current word count.
 *
 */

    public int getCount() {

        return count;
    }

/**
 * increases word count total by 1.
 *
 */

    public void increment() {

        count++;
    }

/**
 * constructs a String of '*' characters of a length determined by the
 * function that calls it.
 *
 * @param totalAsterisks # of asterisks for histogram output.
 * @return String of asterisks for output.
 */

    public String toString(int totalAsterisks) {

        String x = "";

        for (int i = 0; i <= totalAsterisks; i++) {

            x += "*";

        }

        return x;
    }

    public Counter() {

        count = 1;
    }

}


