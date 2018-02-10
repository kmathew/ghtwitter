package ghtwitter;

import github.Project;

import java.util.ArrayList;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  /**
   * Main method
   * 
   *    Calls other package methods to write json file filled with projects and their respective
   * tweets.
   * 
   *    Only outputs first 10 projects with tweets because Twitter rate limits for API calls. Up to
   * 6000 projects from GitHub can be pulled within an hour. Only 180 Twitter API calls can be made
   * in 15 minutes. As agreed from Edgar, I would pull in 1000 GitHub projects and run through them
   * and spit out 10 with their tweets and omitting ones without any tweets.
   * 
   *    It has support for some options on command line, but this was not necessary based on the
   * requirements. There are no validation checks for it as of right now.It's here because of force
   * of habit. It will be left here in the case of future enhancements.
   * 
   * 
   * @param args command line arguments
   */
  public static void main(String[] args) {

    final Logger logger = LoggerFactory.getLogger(Main.class);

    String configFileName = "";
    String projectName = "";
    String outputFileName = "";

    Options options = new Options();

    // 3 options
    // output file name required
    // options.addOption(new Option("o", false, Constants.HELP_OUTPUT));
    options.addOption(Option.builder("o").longOpt("output").required(false)
        .desc(Constants.HELP_OUTPUT).hasArg().build());

    // search other project names
    // default = constants
    options.addOption(Option.builder("s").longOpt("search").required(false)
        .desc(Constants.HELP_PROJECT).hasArg().build());
    options.addOption(Option.builder("c").longOpt("config").required(false)
        .desc(Constants.HELP_CONFIG).hasArg().build());

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();
    CommandLine line = null;

    try {
      // parse command line args
      line = parser.parse(options, args);

      if (line.hasOption("c")) {
        configFileName = (String) line.getParsedOptionValue("c");
      }
      if (line.hasOption("s")) {
        projectName = (String) line.getParsedOptionValue("s");
      }
      if (line.hasOption("o")) {
        outputFileName = (String) line.getParsedOptionValue("o");
      }

    } catch (ParseException e) {
      logger.debug("Failure to parse command line args - " + e.getMessage());
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

    logger.debug("configuration file name: " + configFileName);
    logger.debug("project search term: " + projectName);
    logger.debug("output file name: " + outputFileName);

    // read configfile
    FileHandler fileHandler = new FileHandler(outputFileName);

    // init GHTwitter search construct
    GitterSearch ght = new GitterSearch(configFileName, projectName);

    ArrayList<Project> projectList = new ArrayList<Project>();
    // call GitHubSearch
    // limit due to rate calls
    for (int i = 1; i <= Constants.MAX_PAGES; i++) {
      logger.debug("Adding projects to list: page " + i + " of " + Constants.MAX_PAGES);
      projectList.addAll(ght.searchGitHub(i)); // returns 100 projects
      // appending projects list
    }

    // limit # of projects returning to 10 because of rate limits
    ArrayList<Gitter> listGitter = ght.searchTwitter(projectList, Constants.MAX_PROJECTS);

    fileHandler.writeToFile(listGitter);
  }

}
