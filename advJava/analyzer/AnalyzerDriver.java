package java112.analyzer;

/**
 * This is the launch class for the program.  This program is designed to
 * read a text file document and output six report summaries: one will capture
 * info such as total number of words found in the document, another will
 * capture each unique word in the document, another will tabulate
 * the number of instances that each unique word occurs in the document,
 * another will capture all words in the document that are longer than a
 * defined length of characters, another will capture the length of each
 * token and tabulate the number of instances of tokens of each length, and
 * the final analyzer will capture any 'keywords' (words specified in advance)
 * that occur in the document. There will be six separate classes (Analyzers)
 * used, one each for each separate output report that will be generated.
 * All classes will implement a common interface.  Once the token processing
 * is complete the program will close the file reader and generate the six
 * report files as mentioned above.
 *
 * @author bgreen
 */

public class AnalyzerDriver {

    public static void main(String[] args) {

        AnalyzeFile analyzer = new AnalyzeFile();

        analyzer.runAnalysis(args);

    }

}


