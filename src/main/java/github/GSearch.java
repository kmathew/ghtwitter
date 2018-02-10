package github;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class GSearch {
	
	private int pageSize;
	private String queryString;
	
	public GSearch(int pageSize, String queryString) {
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

	public ArrayList<Project> getProjects(int page) {
		ArrayList<Project> projects = new ArrayList<Project>();

		JSONArray jsonArray  = searchAPI(pageSize, page);
		for(int i=0; i < jsonArray.size(); i++) {
			JSONObject jo = (JSONObject) jsonArray.get(i);
			String name = (String) jo.get(Constants.JSON_NAME);
			String descr = (String) jo.get(Constants.JSON_DESCRIPTION);
			projects.add(new Project(name, descr));
		}
		
		return projects;
	}

	private JSONArray searchAPI(int pageSize, int page) {
		JSONObject jo = null;
		JSONArray jArr = null;
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(null, null);

		ResponseEntity<String> results = null;
		try {
			// api url is filtered
			String url = Constants.API_URL+ getQueryString() + Constants.API_PAGESIZE + pageSize
					+ Constants.API_PAGE + page;
			results = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		} catch (HttpStatusCodeException ex) {
			System.out.println(ex.getResponseBodyAsString());
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		//parse get request body to json
		JSONParser parser = new JSONParser();
		try {
			jo = (JSONObject) parser.parse(results.getBody().toString());
			jArr = (JSONArray) parser.parse(jo.get(Constants.JSON_ITEMS).toString());
		} catch (ParseException e) {

			e.printStackTrace();
		}

		return jArr;
	}
	
	public static void main(String[] args) {
		
		/*JSONArray jo = searchAPI(2, 1);
		
		JSONObject j = (JSONObject)jo.get(0);
		
		
		System.out.print(j.toJSONString());*/
		GSearch g = new GSearch(23,"yo");
		
		ArrayList<Project> p = g.getProjects(1);
		
		System.out.println(p.size());
	}



}
