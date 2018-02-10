package twitter;

import org.junit.Before;
import org.junit.Test;

import twitter.Tweet;
import junit.framework.TestCase;

public class TweetTest extends TestCase {
	
	private Tweet t;
	
	@Before
	public void setUp() {
		t = new Tweet("kemathew", "Doge");
	}

	@Test
	public void testGetUser() {
		assertEquals("kemathew", t.getUser());
	}
	
	@Test
	public void testSetUser() {
		t.setUser("zeke");
		assertEquals("zeke", t.getUser());
	}
	
	@Test
	public void testGetText() {
		assertEquals("Doge", t.getText());
	}
	
	@Test
	public void testSetText() {
		t.setText("kitty");
		assertEquals("kitty", t.getText());
	}

}
