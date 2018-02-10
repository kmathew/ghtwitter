package ghtwitter;

import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import github.Project;

public class Main {

	public static void main(String[] args) {

		String configFileName = "";
		String projectName = "";
		String outputFileName = "";

		Options options = new Options();

		// 3 options
		// output file name required
		options.addOption(new Option("o", false, Constants.HELP_OUTPUT));

		// search other project names
		// default = constants
		options.addOption(new Option("f", false, Constants.HELP_PROJECT));
		options.addOption(new Option("c", false, Constants.HELP_CONFIG));

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine line = null;

		try {
			// parse command line args
			line = parser.parse(options, args);

			if (line.hasOption("c")) {
				configFileName = (String) line.getParsedOptionValue("c");
			}
			if (line.hasOption("f")) {
				projectName = (String) line.getParsedOptionValue("f");
			}
			if (line.hasOption("o")) {
				outputFileName = (String) line.getParsedOptionValue("o");
			}

		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("GHTwitter", options);

			System.exit(1);
			return;
		}

		// if option not used then set to default
		if (outputFileName != null && outputFileName.isEmpty()) {
			outputFileName = Constants.DEFAULT_OUTPUT;
		}
		if (projectName != null && projectName.isEmpty()) {
			projectName = Constants.DEFAULT_PROJECT;
		}
		if (configFileName != null && configFileName.isEmpty()) {
			configFileName = Constants.DEFAULT_CONFIG;
		}
		
		// read configfile
		FileHandler fileHandler = new FileHandler(outputFileName);

		// init GHTwitter search construct
		GHTSearch ght = new GHTSearch(configFileName, projectName);
		
		

		// call GitHubSearch
		for(int i=1; i <= Constants.MAX_PAGES; i++) {
			ArrayList<Project> projectList = ght.searchGitHub(i); // returns 100 projects
			ArrayList<GHTwitter> listGHT = ght.searchTwitter(projectList);
			
			System.out.println(listGHT.size());
			//construct JSON
			//file write append
			//sleep??
			//move to next
		}
	}

}
