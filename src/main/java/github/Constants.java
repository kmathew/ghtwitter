package github;

public class Constants {

  private Constants() {
    // restrict instance
  }

  /* GitHub API Constants */
  public static final String API_URL =
      "https://api.github.com/search/repositories?archived=false&sort=updated&q=";
  public static final String API_PAGESIZE = "&per_page=";
  public static final String API_PAGE = "&page=";
  
  /* JSON Constants */
  public static final String JSON_ITEMS = "items";
  public static final String JSON_NAME = "full_name";
  public static final String JSON_DESCRIPTION = "description";
}
