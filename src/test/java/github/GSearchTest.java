package github;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GSearchTest {

	private GSearch g;
	
	@Before
	public void setUp() {
		g = new GSearch(10, "Doge");
	}

	@Test
	public void testGetPageSize() {
		assertEquals(10, g.getPageSize());
	}
	
	@Test
	public void testGetQueryString() {
		assertEquals("Doge", g.getQueryString());
	}
	
	@Test
	public void testSetPageSize() {
		g.setPageSize(12);
		assertEquals(12, g.getPageSize());
	}
	
	@Test
	public void testSetQueryString() {
		g.setQueryString("seregios");
		assertEquals("seregios", g.getQueryString());
	}	
	
	@Test
	public void testSetUser() {
		
	}
}
