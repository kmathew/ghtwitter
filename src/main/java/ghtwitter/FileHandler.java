package ghtwitter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Handles input output for files. Reading config. Writing output. Idea behind separating this was
 * to have the modularity for future enhancements in case of support for different formats, etc.
 * 
 * @author kevin mathew
 *
 */
public class FileHandler {

  private final Logger logger = LoggerFactory.getLogger(FileHandler.class);

  private String outputFileName = "";
  private FileWriter file = null;

  public FileHandler() {

  }

  public FileHandler(String output) {
    this.outputFileName = output;
  }

  public String getFileName() {
    return this.outputFileName;
  }

  public void setFileName(String fileName) {
    this.outputFileName = fileName;
  }

  /**
   * Reads in the configuration file used for Twitter auth. It will keep reading until EOL, but only
   * the first 4 lines are used in secrets as its passed. Making the assumption that the user is
   * only adding the necessary information to the configuration file. If any of the four lines is
   * incorrect or missing, this application will fail to authenticate with Twitter.
   * 
   * @param configName configuration file name
   * @return list of secrets (consumer key, consumer secret, token, token secret)
   */
  public List<String> readConfig(String configName) {

    List<String> secrets = new ArrayList<String>();

    try {
      // iterate through line by line for secrets
      BufferedReader bufferedReader = new BufferedReader(new FileReader(configName));
      String line = bufferedReader.readLine();
      while (line != null) {
        secrets.add(line);
        line = bufferedReader.readLine();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return secrets;
  }

  /**
   * Takes in a json Array and writes it to the specified output file name in the class.
   * 
   * @param listGitter list of Gitters json content soon to be written
   */
  public void writeToFile(ArrayList<Gitter> listGitter) {

    JSONArray jsonArray = new JSONArray();
    jsonArray.put(listGitter);

    try {
      if (file == null) {
        // open file and append
        this.file = new FileWriter(this.outputFileName, true);
      }
      logger.debug("Writing to file...");
      file.write(jsonArray.toString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        file.close();
        logger.debug("File saved.");
        file = null;
      } catch (IOException e) {
        logger.debug("File is closed already. Input Stream failing to write\n" + e.getMessage());
      }
    }
  }

}
