package site.stein197.b64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Encoder {

	private String filepath;
	private String encodedData;
	private boolean onlyB64;

	public Encoder(String filepath, boolean onlyB64) {
		this.filepath = filepath;
		this.onlyB64 = onlyB64;
	}

	public String encode() throws FileNotFoundException, IOException {
		if (this.encodedData != null)
			return this.encodedData;
		var file = new File(this.filepath);
		var data = new byte[(int) file.length()];
		InputStream is = new FileInputStream(file);
		is.read(data);
		is.close();
		this.encodedData = Base64.getEncoder().encodeToString(data);
		if (!this.onlyB64)
			this.encodedData = "data:" + this.getMIME(file) + ";base64," + this.encodedData;
		return this.encodedData;
	}

	private String getMIME(File file) throws IOException {
		return file.toURL().openConnection().getContentType();
	}
}
