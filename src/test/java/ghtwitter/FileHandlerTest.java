package ghtwitter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;

public class FileHandlerTest {

	private FileHandler f;


	@Rule
	public ExpectedException exceptions = ExpectedException.none();
	
	@Before
	public void setUp() {
		f = new FileHandler();
	}
	
	@Test
	public void testReadConfig() {
		f.readConfig("twitter4j.properties");
	}
}
