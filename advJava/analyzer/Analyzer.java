package java112.analyzer;

/**
 * Interface for SummarySupport, UniqueTokenAnalyzer, BigWordAnalyzer,
 * and TokenCountAnalyzer classes.
 *
 *  @author bgreen
 */

public interface Analyzer {

/**
 * All implementations are made sure to be able to handle a String arguement.
 *
 * @param token is the single word that will be processed.
 */

 void processToken(String token);

/**
 * All implementations will define file path before creating report files.
 *
 * @param inputFilePath defines the file/text document used in token analysis.
 */

    void writeOutputFile(String inputFilePath);

}
