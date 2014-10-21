package java112.analyzer;
import java.util.*;
import java.io.*;

/**
 * This class implements the Analyzer interface. It uses a TreeMap to
 * capture each unique token (key) and its number of occurances in the text
 * document (value). This information is output to a report file.
 *
 * @author bgreen
 */

public class TokenCountAnalyzer implements Analyzer {

    private Properties properties;
    private Map<String, Integer> tokenCounts;

/**
 * Get method for the tokenCounts Map.
 *
 * @return tokenCounts TreeMap of all unique tokens and their counts.
 */
    public Map getTokenCounts () {

        return tokenCounts;
    }
/**
 * Every time a token is passed to this method, the TreeMap is checked to see
 * if it already contains that particular token. If not, the token is added
 * and its count is automatically set to 1. If, however, it already exists
 * in the TreeMap, the count for that token at the time the method is called
 * is checked, and then incremented by one.
 *
 * @param token individual word/token.
 */
    public void processToken(String token) {

        int count = 1;

        if (tokenCounts.containsKey(token)) {
            count = tokenCounts.get(token);
            tokenCounts.put(token, ++count);
        } else {
            tokenCounts.put(token, count);
        }

    }

/**
 * PrintWriter is opened to write output report. Name of the output report
 * and its file path are set according to the Properties object.
 *
 * @param inputFilePath file path of text document used in token analysis.
 */
    public void writeOutputFile(String inputFilePath) {

        PrintWriter writer = null;
        String path = properties.getProperty("output_dir")
                + properties.getProperty("output_file_token_count");


        try {

            writer = new PrintWriter(new BufferedWriter(new FileWriter(path)));

        for (Map.Entry<String, Integer> entry : tokenCounts.entrySet()) {

            writer.print(entry.getKey());
            writer.print("\t");
            writer.println(entry.getValue());

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

    public TokenCountAnalyzer() {
    tokenCounts = new TreeMap<String, Integer>();

    }
/**
 * When TokenCountAnalyzer object is created with a
 * an object of type Property as an argument, the constructors are chained
 * such that the empty constuctor, which instantiates a TreeMap of type
 * String, Integer, is called before the passed properties object is assigned to
 * the class's instance variable of type Property.
 *
 * @param properties Properties object w/ loaded .properties file.
 */
    public TokenCountAnalyzer(Properties properties) {
    this();
    this.properties = properties;

    }

}

