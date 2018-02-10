package ghtwitter;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import junit.framework.TestCase;

public class FileHandlerTest extends TestCase {

	private FileHandler f;
	
	@Before
	public void setUp() {
		f = new FileHandler("test");
	}
	
	@Test
	public void testReadConfig() {
		FileHandler f1 = mock(FileHandler.class);
		when(f1.readConfig("asdsad")).thenReturn(new ArrayList<String>());
		assertTrue(f1.readConfig("asdsad") != null);
	}
	
	@Test
	public void testGetOutputName() {
		assertEquals("test", f.getFileName());
	}
	
	@Test
	public void testSetOutputName() {
		f.setFileName("sweg");
		assertEquals("sweg", f.getFileName());
	}
}
