package test.testart;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter4JTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("ggDbvDVjcw8DDeb5BftBAGwXI")
		  .setOAuthConsumerSecret("PzcLjsRtBAkHMS0qYYliRBXZwXM1T8xOFTE8PNwDK8Buz4Cnvo")
		  .setOAuthAccessToken("39268172-CEmtWwy8BPFSwnT7lfhWjeTZt42MPXceqg0T58Boo")
		  .setOAuthAccessTokenSecret("Mh1YKtLhGuIwS0qYZT6DyR1AII2D3fA3N4AObFu3gSaPb");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		/*try {
			List<Status> status = twitter.getHomeTimeline();
			for(Status st : status) {
				
				System.out.println(st.getUser().getName()+"-------"+st.getText());
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String queryString = "yolo";
		Query query = new Query(queryString);
		QueryResult result;
		try {
			result = twitter.search(query);
			System.out.println("ASDFASFASFASFASFAS----\n\n\n\n" + twitter.getRateLimitStatus());
			List<Status> tweets = result.getTweets();
			for(Status st : tweets) {
				System.out.println(st.getUser().getName() + "--------" + st.getText());
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}

}
