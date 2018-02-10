package twitter;

/**
 * Tweet Object
 * 
 * Only stores username and text from tweet. Additions can be made if necessary.
 * 
 * @author kevin mathew
 *
 */
public class Tweet {

  private String user;
  private String text;

  /**
   * Constructor.
   * 
   * @param user username of tweeter
   * @param text text of tweet
   */
  public Tweet(String user, String text) {
    this.setUser(user);
    this.setText(text);
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}
