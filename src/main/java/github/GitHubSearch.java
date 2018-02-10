package github;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class GitHubSearch {

  private final Logger logger = LoggerFactory.getLogger(GitHubSearch.class);

  private int pageSize;
  private String queryString;

  public GitHubSearch(int pageSize, String queryString) {
    this.setPageSize(pageSize);
    this.setQueryString(queryString);
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public String getQueryString() {
    return queryString;
  }

  public void setQueryString(String queryString) {
    this.queryString = queryString;
  }

  /**
   * Used to call and retrieve project from GitHub for specific page of elements
   * 
   * @param page page number
   * @return list of projects with their full name and description
   */
  public ArrayList<Project> searchProjects(int page) {
    ArrayList<Project> projects = new ArrayList<Project>();

    JSONArray jsonArray = searchAPI(pageSize, page);
    for (int i = 0; i < jsonArray.size(); i++) {
      JSONObject jo = (JSONObject) jsonArray.get(i);
      String name = (String) jo.get(Constants.JSON_NAME);
      String descr = (String) jo.get(Constants.JSON_DESCRIPTION);
      projects.add(new Project(name, descr));
    }

    return projects;
  }

  /**
   * Private method to make the actual call to GitHub's API using spring.
   * 
   * There is a flaw in the code from the filter. The url has a filter to sort by updated. The
   * possibility of retrieving duplicates is within reason if during the next iteration or page/call
   * for this method one of the projects in the big list gets updated or pushed to. Removing the
   * sort by updated filter would fix it, but Edgar had mentioned in his e-mail that he wanted to me
   * to pull the most recently updated ones.
   * 
   * @param pageSize number of elements on page max 100 min 30
   * @param page page number for pagination
   * @return JSONArray object response from GitHub's API
   */
  private JSONArray searchAPI(int pageSize, int page) {
    JSONObject jo = null;
    JSONArray jarr = null;
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<String> request = new HttpEntity<String>(null, null);

    ResponseEntity<String> results = null;
    try {
      // api url is filtered
      String url = Constants.API_URL + getQueryString() + Constants.API_PAGESIZE + pageSize
          + Constants.API_PAGE + page;
      results = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
    } catch (HttpStatusCodeException ex) {
      logger.debug(ex.getResponseBodyAsString());
    } catch (RestClientException e) {
      e.printStackTrace();
    }

    // parse get request body to json
    JSONParser parser = new JSONParser();
    try {
      jo = (JSONObject) parser.parse(results.getBody().toString());
      // only care about items array where projects info is
      jarr = (JSONArray) parser.parse(jo.get(Constants.JSON_ITEMS).toString());
    } catch (ParseException e) {
      logger.debug("Failure to parse GitHub JSON - " + e.getMessage());
    }

    return jarr;
  }
}
