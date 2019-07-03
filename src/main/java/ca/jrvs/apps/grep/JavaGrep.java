package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {
    /**
     * Top Level serach flow
     *
     * @thow exception
     */
    void process() throws IOException;

    /**
     * Traverse a given directory and return all files
     *
     * @param rootDir input directory
     * @retun files under the rootDir
     */

    List<File> listFiles(String rootDir); // listFiles is a function

    /**
     * Read a file and return all the lines.
     *
     * @param inputFile the file being read.
     * @return lines
     * @throws IllegalArgumentException if inputFile does not exist.
     */
    List<String> readLines(File inputFile) throws IOException;

    /**
     * Check if line contains the regex pattern.
     *
     * @param line the string being checked.
     * @return true when the line matches the regex.
     */
    Boolean containsPattern(String line);

    /**
     * Write lines to file.
     *
     * @param lines matched
     * @throws IOException if write failed
     */

    void writeToFile(List<String> lines) throws IOException;

    String getRootPath();

    void setRootPath(String rootPath);

    String getRegex();

    void setRegex(String regex);

    String getOutFile();

    void setOutFile(String outFile);

}