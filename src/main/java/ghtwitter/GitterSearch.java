package ghtwitter;

import github.GitHubSearch;
import github.Project;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter.Tweet;
import twitter.TwitterSearch;

/**
 * This class leverages the GHTwitter object model and constructs a list of projects with their
 * tweets by using the search methods from the github and twitter packages.
 * 
 * @author kevin mathew
 *
 */
public class GitterSearch {

  private final Logger logger = LoggerFactory.getLogger(GitterSearch.class);

  private GitHubSearch ghSearch;
  private TwitterSearch twSearch;

  /**
   * Default constructor.
   * 
   * Assuming secrets is a valid list.
   * 
   */
  public GitterSearch() {

    List<String> secrets = new FileHandler().readConfig(Constants.DEFAULT_CONFIG);
    this.ghSearch = new GitHubSearch(Constants.MAX_PAGE_SIZE, Constants.DEFAULT_PROJECT);
    // init TwitterSearch with secrets
    this.twSearch = new TwitterSearch(secrets.get(0), secrets.get(1), secrets.get(2), 
        secrets.get(3));
  }

  /**
   * Constructor taking in custom search term and config file name.
   * 
   * @param config configuration file name
   * @param project key term to search for on github
   */
  public GitterSearch(String config, String project) {

    List<String> secrets = new FileHandler().readConfig(config);
    this.ghSearch = new GitHubSearch(Constants.MAX_PAGE_SIZE, project);
    this.twSearch = new TwitterSearch(secrets.get(0), secrets.get(1), secrets.get(2), 
        secrets.get(3));
  }

  /**
   * Constructor taking in custom search term config file and page size.
   * 
   * @param config configuration file name
   * @param project key term to search for on github
   * @param pageSize modifies number of projects per page
   */
  public GitterSearch(String config, String project, int pageSize) {

    List<String> secrets = new FileHandler().readConfig(config);
    this.ghSearch = new GitHubSearch(pageSize, project);
    this.twSearch = new TwitterSearch(secrets.get(0), secrets.get(1), secrets.get(2), 
        secrets.get(3));
  }

  /**
   * Pulls a list of projects on GitHub from the specified page. GitHub rate limits to 60 pages of
   * 100 elements per hour
   * 
   * @param pageNum page number index to pull from
   * @return array list of projects
   */
  public ArrayList<Project> searchGitHub(int pageNum) {

    // assuming pageNum is valid integer
    // GitHubSearch for project list
    return ghSearch.searchProjects(pageNum);
  }

  /**
   * Iterates through list of projects and searches for related tweets. It stores only up to the
   * number of projects specified to store because of rate limiting issues. Default limit is 10
   * because that was specified in the assignment. Twitter API calls limited to 180 per 15 min.
   * 
   * @param projects list of projects to search for tweets
   * @param numOfProjects number of projects with tweets to return
   * @return list of projects with their tweets
   */
  public ArrayList<Gitter> searchTwitter(ArrayList<Project> projects, int numOfProjects) {

    ArrayList<Gitter> list = new ArrayList<Gitter>();
    // need to check for empty projects
    if (projects != null && !projects.isEmpty()) {
      for (int i = 0; i < projects.size() && list.size() <= numOfProjects; i++) {
        // iterate through list of project full names
        ArrayList<Tweet> tweets = twSearch.searchTweets(projects.get(i).getFullName());

        // if no tweets prune project name from list
        if (tweets != null && !tweets.isEmpty()) {
          logger
              .debug("Adding " + projects.get(i).getFullName() + " and its tweets to the list.\n");
          list.add(new Gitter(projects.get(i), tweets));
        }
      }
    }
    return list;
  }
}
