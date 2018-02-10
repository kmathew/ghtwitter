package ghtwitter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import org.json.simple.JSONObject;

public class FileHandler {

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

	public void writeToFile(JSONObject jsonObj) {
		try {
			if (file == null) {
				// open file and append
				this.file = new FileWriter(this.outputFileName, true);
			}
			file.write(jsonObj.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
