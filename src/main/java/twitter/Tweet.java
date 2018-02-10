package twitter;

public class Tweet {
	
	private String user;
	private String text;
	
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
