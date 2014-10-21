package java112.analyzer;
import java.util.*;
import java.io.*;

/**
 * This class implements the Analyzer interface. It counts the number of
 * occurrances that words of any particular legth are found in the text. It
 * outputs a simple text file with this information and it also produces a
 * histogram of the information.
 *
 * @author bgreen
 */

public class TokenSizeAnalyzer implements Analyzer {

private Map<Integer, Counter> tokenSizes;
private Properties properties;
private int maximumSize;

/**
 * Get method for tokenSizes variable/object.
 *
 * @return tokenSizes map object.
 */

    public Map<Integer, Counter> getTokenSizes() {

        return tokenSizes;
    }

/**
 * Get method for maximumSize variable.
 *
 * @return maximumSize count variabble.
 */

    public int getMaximumSize() {

        return maximumSize;
    }

/**
 * Method has a counter for every time the method is invoked. If the keywordMap
 * array contains the token that is passed to the method, then the method calls
 * the corresponding Count object method (value of the array object) to increment
 * the value by one.
 *
 * @param token individual word/token.
 */

    public void processToken(String token) {

        int tokenSize = token.length();
        int currentCount = 1;

        if (tokenSizes.containsKey(tokenSize)) {

            tokenSizes.get(tokenSize).increment();

        } else {

            tokenSizes.put(tokenSize, new Counter());
        }

        currentCount = tokenSizes.get(tokenSize).getCount();

        if (currentCount > maximumSize) {

            maximumSize = currentCount;
        }

    }

/**
 * Output file consists of a number for each word length and then the total
 * number of words that fall under that count.  This method also engages in
 * some basic calculations in order to output a histogram representation of the
 * word count distribution.
 *
 * @param inputFilePath file path of text document used in token analysis.
 */

    public void writeOutputFile(String inputFilePath) {

        PrintWriter writer = null;
        String path = properties.getProperty("output_dir")
                + properties.getProperty("output_file_token_size");

        try {

            writer = new PrintWriter(new BufferedWriter(new FileWriter(path)));

        for (Map.Entry<Integer, Counter> entry : tokenSizes.entrySet()) {

            writer.print(entry.getKey());
            writer.print("\t");
            writer.println(entry.getValue().getCount());

        }

       /* } catch (FileNotFoundException ex) {

        ex.printStackTrace();*/

        } catch (IOException ex) {

        ex.printStackTrace();

        } catch (Exception ex) {

        ex.printStackTrace();

        } finally {

            try {

            if (writer != null) {

                writer.close();

            }

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }

        try {

            writer = new PrintWriter(new BufferedWriter(new FileWriter(path)));

        for (Map.Entry<Integer, Counter> entry : tokenSizes.entrySet()) {

            writer.print(entry.getKey());
            writer.print("\t");
            writer.println(entry.getValue().getCount());

        }

            writer.println();
            writer.println();


        for (Map.Entry<Integer, Counter> entry : tokenSizes.entrySet()) {

            int size = entry.getValue().getCount();
            double relation = (double) size / (double) maximumSize;
            int totalAsterisks = (int) Math.round(72 * relation);
            String asterisks = entry.getValue().toString(totalAsterisks);
            writer.print(entry.getKey());
            writer.print("\t");
            writer.println(asterisks);

        }

       /* } catch (FileNotFoundException ex) {

        ex.printStackTrace();*/

        } catch (IOException ex) {

        ex.printStackTrace();

        } catch (Exception ex) {

        ex.printStackTrace();

        } finally {

            try {

            if (writer != null) {

                writer.close();

            }

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }

    }

    public TokenSizeAnalyzer() {
        tokenSizes = new TreeMap<Integer, Counter>();

    }

/**
 * When TokenSizeAnalyzer object is created with a an object of type
 * Property as an argument, the passed properties object is assigned to
 * the class's instance variable of type Property. TreeMap object is first
 * instantiated.
 *
 * @param properties Properties object w/ loaded .properties file.
 */

    public TokenSizeAnalyzer(Properties properties) {
        this();
        this.properties = properties;

    }

}
