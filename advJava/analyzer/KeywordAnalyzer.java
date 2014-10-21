package java112.analyzer;
import java.util.*;
import java.io.*;

/**
 * This class implements the Analyzer interface. It reads 'keyword' entries
 * from a text file and stores them to an map array. As tokens are processed,
 * the array is checked to see if it contains the token. If so, the location in
 * the text of the token occurrance is added to the List object in the array.
 * Report output consists in outputting each keyword and every instance of its
 * occurrance in the text.
 *
 * @author bgreen
 */

public class KeywordAnalyzer implements Analyzer {

private Map<String, List<Integer>> keywordMap;
private Properties properties;
private int tokenOccurrance;

/**
 * Get method for keywordMap variable/object.
 *
 * @return keywordMap map object.
 */

public Map<String, List<Integer>> getKeywordMap() {

    return keywordMap;
}

/**
 * This method is called by the constructor. It loads the keywords text to a
 * BufferedReader. Each keyword is read and stored to Map array.
 *
 * @param keywordsPath location of keywords text to be read.
 */

public void loadKeywordFile(String keywordsPath) {

    BufferedReader input = null;

        try {

            String keyword = null;
            input = new BufferedReader(new FileReader(keywordsPath));

            while (input.ready()) {
                keyword = input.readLine();

                if (keyword.length() > 0) {

                    keywordMap.put(keyword, new ArrayList<Integer>());
                }
            }

        } catch (FileNotFoundException ex) {

        ex.printStackTrace();

        } catch (IOException ex) {

        ex.printStackTrace();

        } catch (Exception ex) {

        ex.printStackTrace();

        } finally {

            try {

                if (input != null) {

                    input.close();

                }

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }
    }

/**
 * Method has a counter for every time the method is invoked. If the keywordMap
 * array contains the token that is passed to the method, then the method adds
 * the occurrance location (the count) to the List array element in the Map.
 *
 * @param token individual word/token.
 */

    public void processToken(String token) {

    tokenOccurrance++;
    List<Integer> keywordOccurrances = null;

       if (keywordMap.containsKey(token)) {

          keywordOccurrances = keywordMap.get(token);

          keywordOccurrances.add(tokenOccurrance);
      }

    }

/**
 * Output file consists of each keyword and the numbered location of each
 * instance of it in the text.
 *
 * @param inputFilePath file path of text document used in token analysis.
 */

    public void writeOutputFile(String inputFilePath) {

        PrintWriter writer = null;
        String path = properties.getProperty("output_dir")
                + properties.getProperty("output_file_keywords");

        try {

            writer = new PrintWriter(new BufferedWriter(new FileWriter(path)));

        for (Map.Entry<String, List<Integer>> entry : keywordMap.entrySet()) {

            int counter = 0;

            List<Integer> printList = entry.getValue();

            if (printList.size() == 0) {

                writer.print(entry.getKey() + " =\n[]");

            } else {

            writer.print(entry.getKey() + " =\n[");

            }

            for (Integer location : printList) {

                counter++;

                int size = printList.size();

                if (size - counter != 0) {

                    writer.print(location + ", ");

                } else {

                    writer.print(location + "]");

                }

                if (counter % 8 == 0) {

                    writer.print("\n");

                }

            }

            writer.print("\n\n");

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

    public KeywordAnalyzer() {

        keywordMap = new TreeMap<String, List<Integer>>();
    }

/**
 * When KeywordAnalyzer object is created with a an object of type
 * Property as an argument, the passed properties object is assigned to
 * the class's instance variable of type Property. TreeMap object is first
 * instantiated. Also, input filepath for the 'keywords' text file is set here.
 *
 * @param properties Properties object w/ loaded .properties file.
 */

    public KeywordAnalyzer(Properties properties) {

        this();
        this.properties = properties;
        String keywordsPath = properties.getProperty("input_dir")
                + properties.getProperty("input_file_keywords");
        this.loadKeywordFile(keywordsPath);
    }

}