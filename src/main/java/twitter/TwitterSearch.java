package twitter;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterSearch {

  private Twitter twitter;

  /**
   * Default Constructor
   * 
   * Making the assumption that all input is valid and not null.
   * 
   * @param consumerKey consumerkey from Twitter Apps
   * @param consumerSecret consumersecret related to consumerkey from Twitter Apps
   * @param token token generated from Twitter Apps
   * @param tokenSecret tokensecret related to token from Twitter Apps
   */
  public TwitterSearch(String consumerKey, String consumerSecret, String token,
      String tokenSecret) {

    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret)
        .setOAuthAccessToken(token).setOAuthAccessTokenSecret(tokenSecret);
    TwitterFactory tf = new TwitterFactory(cb.build());
    twitter = tf.getInstance();
  }

  /**
   * Grabs the tweets related to the project. If no tweets, returns empty list.
   * 
   * @param project GitHub project name to search for
   * @return list of tweets related to project
   */
  public ArrayList<Tweet> searchTweets(String project) {
    ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    List<Status> list = getTweets(project);

    for (Status st : list) {
      tweets.add(new Tweet(st.getUser().getName(), st.getText()));
    }

    return tweets;
  }

  /**
   * Searches twitter's tweets for anything related to search term.
   * 
   * @param queryString query to search for on twitter
   * @return list of tweets related to search term.
   */
  private List<Status> getTweets(String queryString) {
    List<Status> tweets = null;
    Query query = new Query(queryString);
    QueryResult result;
    try {
      result = twitter.search(query);
      tweets = result.getTweets();
    } catch (TwitterException e) {
      // TODO Auto-generated catch block
      System.out.println("Twitter API Rate Limit Reached\n");
      e.printStackTrace();
    }

    return tweets;
  }

}
