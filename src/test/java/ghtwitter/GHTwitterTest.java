package ghtwitter;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import twitter.Tweet;
import github.Project;

public class GHTwitterTest {

	private GHTwitter ght;
	private Project p;
	private Tweet t;
	private ArrayList<Tweet> al;
	
	@Before
	public void setUp() {
		p = new Project("monsterhunter", "awesome");
		t = new Tweet("kemathew", "love mh world");
		al = new ArrayList<Tweet>();
		al.add(t);
		ght = new GHTwitter(p, al);
	}
	
	@Test
	public void testGetProject() {
		assertEquals(p, ght.getProject());
	}
	
	@Test
	public void testGetTweets() {
		assertEquals(t, ght.getTweets().get(0));
	}
	
	@Test
	public void testSetTweets() {
		ArrayList<Tweet> sf = al = new ArrayList<Tweet>();
		ght.setTweets(sf);
		assertTrue(ght.getTweets().size() == 0);
	}
	
	@Test
	public void testSetProject() {
		Project p2 = new Project("no", "way");
		ght.setProject(p2);
		assertEquals("no", ght.getProject().getFullName());
	}

}
