package java112.analyzer;
import java.util.*;
import java.io.*;

/**
 * This class implements the Analyzer interface. It uses a TreeSet to
 * ensure that no duplicate tokens are output to the unique_tokens.txt
 * (output report file).
 *
 * @author bgreen
 */

public class UniqueTokenAnalyzer implements Analyzer {

    private Set<String> uniqueTokensList;
    private Properties properties;

/**
 * Get method for the uniqueTokensList Set.
 *
 * @return uniqueTokensList TreeSet of all unique tokens.
 */

    public Set getUniqueTokensList() {

        return uniqueTokensList;

    }

/**
 * Every time a token is passed to this method, it is added to the TreeSet
 * Any duplicate tokens are automatically rejected and not included in the
 * final analysis.
 *
 * @param token individual word/token.
 */

    public void processToken(String token) {

        uniqueTokensList.add(token);

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
                + properties.getProperty("output_file_unique");

        try {

            writer = new PrintWriter(new BufferedWriter(new FileWriter(path)));

            for (String word : uniqueTokensList) {
            writer.println(word);

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

        public UniqueTokenAnalyzer() {

            uniqueTokensList = new TreeSet<String>();
        }

/**
 * When UniqueTokenAnalyzer object is created with a
 * an object of type Property as an argument, the constructors are chained
 * such that the empty constuctor, which instantiates a TreeSet of type
 * String, is called before the passed properties object is assigned to
 * the class's instance variable of type Property.
 *
 * @param properties Properties object w/ loaded .properties file.
 */
        public UniqueTokenAnalyzer(Properties properties) {
            this();
            this.properties = properties;
        }

}
