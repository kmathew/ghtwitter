package ghtwitter;

import java.util.ArrayList;

import github.Project;
import twitter.Tweet;

public class GHTwitter {

	private Project project;
	private ArrayList<Tweet> tweets;

	public GHTwitter(Project project, ArrayList<Tweet> tweets) {
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
