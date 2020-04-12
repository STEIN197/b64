package site.stein197.b64;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import picocli.CommandLine;

public class ApplicationTest {

	@Test
	public void nullNames_Returns3() {
		var app = new Application();
		new CommandLine(app).parseArgs("");
		assertEquals(new Integer(3), app.execute().getKey());
	}
}
