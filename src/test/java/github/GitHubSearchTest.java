package github;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

public class GitHubSearchTest extends TestCase {

  private GitHubSearch g;
  private ArrayList<Project> p;

  @Before
  public void setUp() {
    g = new GitHubSearch(10, "Doge");
    Project p1 = new Project("batman", "darknight");
    p = new ArrayList<Project>();
    p.add(p1);
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
  public void testGetProjects() {
    GitHubSearch gMock = mock(GitHubSearch.class);
    when(gMock.searchProjects(1)).thenReturn(p);
    assertTrue(gMock.searchProjects(1) != null);
  }
}
