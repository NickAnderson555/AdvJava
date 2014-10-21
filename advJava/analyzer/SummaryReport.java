package java112.analyzer;
import java.util.*;
import java.text.*;
import java.io.*;

/**
 * This class implements the Analyzer interface. It processes tokens to
 * provide a total token count and also produces a summary output file
 * that is created, in part, by using the passed Properties object.
 *
 * @author bgreen
 */

public class SummaryReport implements Analyzer {

    private int totalTokensCount;
    private Properties properties;

/**
 * Get method for totalTokensCount variable.
 *
 * @return totalTokensCount Sum total of counted tokens.
 */

    public int getTotalTokensCount() {

        return totalTokensCount;

    }

/**
 * Very simple method by which total number of individual tokens have
 * been passed to this method.
 *
 * @param token individual word/token.
 */

    public void processToken(String token) {

        totalTokensCount += 1;

    }

/**
 * PrintWriter is opened to write output report. Name of the output report
 * and its file path are set according to the Properties object. The
 * Properties object is used to obtain other information used in the report
 * output.
 *
 * @param inputFilePath file path of text document used in token analysis.
 */
    public void writeOutputFile(String inputFilePath) {

        PrintWriter writer = null;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String time = dateFormat.format(date);
        String path = properties.getProperty("output_dir") + properties.getProperty("output_file_summary");


        try {

            writer = new PrintWriter(new BufferedWriter(new FileWriter(path)));
            writer.println("Application: "
                    + properties.getProperty("application_name"));
            writer.println("Name: " + properties.getProperty("author"));
            writer.println("Email: "
                    + properties.getProperty("author_email_address"));
            writer.println("Input file: " + inputFilePath);
            writer.println("Analyzed on: " + time);
            writer.println("Total token count: " + totalTokensCount);

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

    public SummaryReport() {

    }

/**
 * When SummaryReport object is created with a an object of type
 * Property as an argument, the passed properties object is assigned to
 * the class's instance variable of type Property.
 *
 * @param properties Properties object w/ loaded .properties file.
 */
    public SummaryReport(Properties properties) {

        this.properties = properties;
    }

}
