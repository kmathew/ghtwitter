package github;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class GHProjectTest  extends TestCase{

	private Project p;
	
	@Before
	public void setUp() {
		p = new Project("kemathew", "awesome");
	}

	@Test
	public void testGetFullName() {
		assertEquals("kemathew", p.getFullName());
	}
	
	@Test
	public void testSetUser() {
		p.setFullName("Kevin");
		assertEquals("Kevin", p.getFullName());
	}
	
	@Test
	public void testGetDescription() {
		assertEquals("awesome", p.getDescription());
	}
	
	@Test
	public void testSetDescription() {
		p.setDescription("meh");
		assertEquals("meh", p.getDescription());
	}

}
