package site.stein197.b64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Parameters;

public class Application implements Callable<Integer> {

	@Parameters(index = "0", description = "A file which need to be converted to Base64")
	private String file;
	private byte[] data;
	private String b64;

	public static void main(String... args) {
		var app = new Application();
		int exitCode = new CommandLine(app).execute(args);
		System.out.println(app.b64);
		System.exit(exitCode);
	}

	@Override
	public Integer call() throws FileNotFoundException, IOException {
		File file = new File(this.file);
		this.data = new byte[(int) file.length()];
		InputStream is = new FileInputStream(file);
		is.read(this.data);
		is.close();
		this.b64 = Base64.getEncoder().encodeToString(this.data);
		return null;
	}
}
