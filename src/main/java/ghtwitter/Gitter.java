package ghtwitter;

import java.util.ArrayList;
import github.Project;
import twitter.Tweet;

/**
 * Gitter Data Object holds a 'Project' and its 'Tweet's.
 * 
 * @author kevin mathew
 *
 */
public class Gitter {

  private Project project;
  private ArrayList<Tweet> tweets;


  /**
   * Constructor to make a Gitter.
   * 
   * @param project Project object from github package
   * @param tweets List of Tweet objects from twitter package
   */
  public Gitter(Project project, ArrayList<Tweet> tweets) {

    this.setProject(project);
    this.tweets = tweets;
  }

  public ArrayList<Tweet> getTweets() {

    return this.tweets;
  }

  public void setTweets(ArrayList<Tweet> tweets) {

    this.tweets = tweets;
  }

  public Project getProject() {

    return project;
  }

  public void setProject(Project project) {

    this.project = project;
  }

}
