package ghtwitter;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;


import github.Project;
import github.GSearch;
import twitter.TSearch;
import twitter.Tweet;

public class GHTSearch {

	private GSearch gSearch;
	private TSearch tSearch;

	public GHTSearch() {

		List<String> secrets = new FileHandler().readConfig(Constants.DEFAULT_CONFIG);
		this.gSearch = new GSearch(Constants.MAX_PAGE_SIZE, Constants.DEFAULT_PROJECT);
		// init TwitterSearch with secrets
		this.tSearch = new TSearch(secrets.get(0), secrets.get(1), secrets.get(2), secrets.get(3));
	}

	public GHTSearch(String config, String project) {
		
		List<String> secrets = new FileHandler().readConfig(config);
		this.gSearch = new GSearch(Constants.MAX_PAGE_SIZE, project);
		this.tSearch = new TSearch(secrets.get(0), secrets.get(1), secrets.get(2), secrets.get(3));
	}

	public GHTSearch(String config, String project, int pageSize) {

		List<String> secrets = new FileHandler().readConfig(config);
		this.gSearch = new GSearch(pageSize, project);
		this.tSearch = new TSearch(secrets.get(0), secrets.get(1), secrets.get(2), secrets.get(3));
	}


	public ArrayList<Project> searchGitHub(int pageNum) {

		// assuming pageNum is valid integer
		// GitHubSearch for project list
		return gSearch.getProjects(pageNum);
	}

	public ArrayList<GHTwitter> searchTwitter(ArrayList<Project> projects) {

		ArrayList<GHTwitter> list = new ArrayList<GHTwitter>();
		// need to check for empty projects
		if (projects != null && !projects.isEmpty()) {
			// initialize data structure to store
			for (int i = 0; i < projects.size() && i < twitter.Constants.API_MAX_CALL; i++) {
				// iterate through list of project full names
				ArrayList<Tweet> tweets = tSearch.searchTweets(projects.get(i).getFullName());

				// if no tweets prune project name from list
				if (tweets != null && !tweets.isEmpty()) {
					list.add(new GHTwitter(projects.get(i), tweets));
				}
				try {
					//attempting to slow down twitter api calls due to rate limits
					TimeUnit.SECONDS.sleep(6);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
