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

public class TSearch {
	
	private Twitter twitter;
	
	public TSearch(String consumerKey, String consumerSecret, String token, String tokenSecret) {
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(consumerKey)
		  .setOAuthConsumerSecret(consumerSecret)
		  .setOAuthAccessToken(token)
		  .setOAuthAccessTokenSecret(tokenSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}
	
	public ArrayList<Tweet> searchTweets(String project) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		
		List<Status> list = getTweets(project);
		
		for(Status st : list) {
			tweets.add(new Tweet(st.getUser().getName(), st.getText()));
		}

		return tweets;
	}
	
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
