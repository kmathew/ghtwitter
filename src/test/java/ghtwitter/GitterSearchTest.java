package ghtwitter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import github.Project;
import junit.framework.TestCase;
import twitter.Tweet;

public class GitterSearchTest extends TestCase {

  private ArrayList<Project> p;
  private ArrayList<Tweet> t;

  @Before
  public void setUp() {
    Project p1 = new Project("batman", "darknight");
    p = new ArrayList<Project>();
    p.add(p1);

    Tweet t1 = new Tweet("robin", "NANNANANANA");
    t = new ArrayList<Tweet>();
    t.add(t1);
  }

  @Test
  public void testSearchGitHub() {

    GitterSearch mockgs = mock(GitterSearch.class);

    when(mockgs.searchGitHub(1)).thenReturn(p);

    ArrayList<Project> list = mockgs.searchGitHub(1);

    assertEquals("batman", list.get(0).getFullName());
  }

  @Test
  public void testTwitter() {

    GitterSearch mockgs = mock(GitterSearch.class);
    Gitter g = new Gitter(p.get(0), t);

    when(mockgs.searchTwitter(p, 1)).thenReturn(new ArrayList<Gitter>());

    ArrayList<Gitter> list = mockgs.searchTwitter(p, 1);

    assertEquals(0, list.size());
  }

}
