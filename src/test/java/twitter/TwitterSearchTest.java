package twitter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.Test;
import junit.framework.TestCase;


public class TwitterSearchTest extends TestCase {

  @Test
  public void testGetProjects() {
    TwitterSearch tMock = mock(TwitterSearch.class);
    ArrayList<Tweet> t = new ArrayList<Tweet>();
    when(tMock.searchTweets("test")).thenReturn(t);
    assertTrue(tMock.searchTweets("test") != null);
  }
}
