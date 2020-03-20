package site.stein197.b64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Application {

	private byte[] data;
	private String encodedData;

	public static void main(String... args) {
		try {
			var app = new Application(args);
			System.out.println(app.encodeFile());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public Application(String... args) throws IllegalArgumentException, FileNotFoundException, IOException {
		this.parseArguments(args);
	}

	public String encodeFile() {
		if (this.encodedData != null)
			return this.encodedData;
		return this.encodedData = Base64.getEncoder().encodeToString(this.data);
	}

	private void parseArguments(String... args) throws IllegalArgumentException, FileNotFoundException, IOException {
		File file = new File(args[1]);
		this.data = new byte[(int) file.length()];
		InputStream is = new FileInputStream(file);
		is.read(this.data);
		is.close();
	}
}
