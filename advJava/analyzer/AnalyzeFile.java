package java112.analyzer;
import java.io.*;
import java.util.*;

/**
 * This class controls of the overall program. Every token processed by
 * each Analyzer class is first extracted here. This class performs the
 * the additional function of instantiating the Properties object that
 * each class uses.
 *
 * @author bgreen
 */

public class AnalyzeFile {

    private static final int NOARS = 2;
    private String inputFilePath;
    private List<Analyzer> analyzers;

/**
 * this method sends Analyzer classes the text file (input) file path
 * upon calling each class's particular writeOutputFile().
 *
 */

    public void writeAllOutputFiles() {

        for (Analyzer analyzer : analyzers) {

            analyzer.writeOutputFile(inputFilePath);
        }

    }

/**
 * this method ensures that tokens sent to all Analyzer classes
 * for processing are at least one character in length.
 *
 * @param token a single word as taken from the text.
 */

    public void processTokenWithAnalyzers(String token) {

        if (token.length() > 0) {

            for (Analyzer analyzer: analyzers) {

                analyzer.processToken(token);
            }

        }

    }

/**
 * Line of text is broken up further into individual String words/tokens.
 * These tokens are then delivered to a String[] that is looped through
 * to extract individual tokens for analysis.
 *
 * @param line a single line of text (single string containing many words)
 * from the text file.
 */

    public void tokenizeLine(String line) {

        String[] lineTokenArray = line.split("\\W");

        for (int i = 0; i < lineTokenArray.length; i++) {

            this.processTokenWithAnalyzers(lineTokenArray[i]);

        }

    }

/**
 * Input argument (text file) is opened so it can be read. the
 * BufferedReader reads file line by line, sending EACH line to
 * to the tokenizeLine() for further processing. BufferedReader is
 * closed once the file has been completely read through.
 *
 */

    public void readAndAnalyzeInputFile() {

        BufferedReader input = null;

        try {

            String line = null;
            input = new BufferedReader(new FileReader(inputFilePath));

            while (input.ready()) {
                line = input.readLine();
                this.tokenizeLine(line);
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

        this.writeAllOutputFiles();

        }
    }

/**
 * An ArrayList of type Analyzer is created. KeywordAnalyzer,
 * TokenSizeAnalyzer, SummaryReport, UniqueTokenAnalyzer, TokenCountAnalyzer,
 * BigWordAnalyzer, KeywordAnalyzer, and TokenSizeAnalyzer objects are created,
 * passing the instantiated Properties object as an argument. The analyzer
 * objects are placed in the ArrayList.
 *
 * @param properties the instantiated Properties object.
 */

    public void createAnalyzers(Properties properties) {

        analyzers = new ArrayList<Analyzer>();
        analyzers.add(new SummaryReport(properties));
        analyzers.add(new UniqueTokenAnalyzer(properties));
        analyzers.add(new TokenCountAnalyzer(properties));
        analyzers.add(new BigWordAnalyzer(properties));
        analyzers.add(new KeywordAnalyzer(properties));
        analyzers.add(new TokenSizeAnalyzer(properties));

        this.readAndAnalyzeInputFile();

    }

/**
 * Instantiates Properties object.
 *
 * @param propertiesFilePath .properties file to be loaded.
 */
    public void loadProperties(String propertiesFilePath) {

        Properties properties = new Properties();

        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        }
        catch(IOException ioe) {
            System.out.println("Cant load the properties file");
            ioe.printStackTrace();
        }
        catch(Exception e) {
            System.out.println("Problem: " + e);
            e.printStackTrace();
        }

        this.createAnalyzers(properties);
    }



/**
 * This method ensures that only one command line argument was passed
 * to the program.  If not, program instructs the user of as much and
 * the program terminates.  Otherwise, the input file is assigned to
 * the inputFilePath instance variable and the loadProperties() is called with
 * the .properties file as an argument.
 *
 * @param args String array of files: [0]token analysis [1].properties.
 */

    public void runAnalysis(String[] args) {

        if (args.length != NOARS) {

            System.out.print("Please enter ONE and ONLY ONE argument to "
               + "the command line");

        } else {

            inputFilePath = args[0];
            this.loadProperties(args[1]);

        }

    }

}
