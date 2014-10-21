package java112.analyzer;
import java.util.*;
import java.io.*;

/**
 * This class implements the Analyzer interface. It uses a TreeSet to
 * ensure that no tokens larger than 18 characters are output output report file.
 *
 * @author bgreen
 */
public class BigWordAnalyzer implements Analyzer {

    private Properties properties;
    private Set<String> bigWords;
    private int minimumWordLength;

/**
 * Get method for the BigWordAnalyzer Set.
 *
 * @return bigWords TreeSet of all tokens of X length or larger.
 */
    public Set<String> getBigWords () {

        return bigWords;
    }

/**
 * Every time a token is passed to this method, it is added to the TreeSet
 * iff the word is determined to be >= the predefined variable length.
 * Any duplicate tokens are automatically rejected and not included in the
 * final analysis.
 *
 * @param token individual word/token.
 */
    public void processToken(String token) {

        if (token.length() >= minimumWordLength) {

            bigWords.add(token);

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
                + properties.getProperty("output_file_bigwords");


        try {

            writer = new PrintWriter(new BufferedWriter(new FileWriter(path)));

            for (String word : bigWords) {
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

    public BigWordAnalyzer() {

        bigWords = new TreeSet<String>();
    }

/**
 * When BigWordAnalyzer object is created with a
 * an object of type Property as an argument, the constructors are chained
 * such that the empty constuctor, which instantiates a TreeSet of type
 * String, is called before the passed properties object is assigned to
 * the class's instance variable of type Property. This constructor then
 * reads the .properties file to determine the minimum number of characters
 * words will need to be included in the bigWords TreeSet.
 *
 * @param properties Properties object w/ loaded .properties file.
 */
    public BigWordAnalyzer(Properties properties) {
        this();
        this.properties = properties;
        String minimum = properties.getProperty("bigwords_minimum_length");
        minimumWordLength = Integer.parseInt(minimum);
    }

}

