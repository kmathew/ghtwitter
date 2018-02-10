package ghtwitter;

public final class Constants {

  private Constants() {
    // restrict instance
  }

  /* default settings */
  public static final String DEFAULT_CONFIG = "twitter4j.properties";
  public static final String DEFAULT_PROJECT = "Reactive";
  public static final String DEFAULT_OUTPUT = "output.json";

  /* min max limits */
  public static final int MAX_PAGE_SIZE = 100;
  public static final int MAX_PAGES = 10;
  public static final int MAX_PROJECTS = 10;

  /* help description for options */
  public static final String HELP_OUTPUT = "output file name - default=output.json";
  public static final String HELP_PROJECT = "git hub project name search - default=Reactive";
  public static final String HELP_CONFIG = "config file name - default=twitter4j.properties";
  public static final String HELP_NUM = "number of projects with tweets to output - default=10";

}
