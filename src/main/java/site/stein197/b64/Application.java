package site.stein197.b64;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(abbreviateSynopsis = true)
public class Application implements Callable<Integer> {

	@Option(names = "-s", description = "If this option is present then only the Base64 output will be present. Otherwise the output will contain additional data for using it in CSS url() function")
	private boolean onlyB64;

	@Option(names = {"-v", "-version"}, description = "Show application version")
	private boolean showVersion;

	@Parameters(index = "0", description = "A file which need to be converted to Base64")
	private String filepath;

	public static void main(String... args) {
		var app = new Application();
		int exitCode = new CommandLine(app).execute(args);
		System.exit(exitCode);
	}

	@Override
	public Integer call() {
		var result = this.execute();
		System.out.print(result.getValue());
		return result.getKey();
	}

	public SimpleEntry<Integer, String> execute() {
		if (this.showVersion) {
			try {
				return new SimpleEntry<Integer, String>(0, this.getMetadata().get("Specification-Version"));
			} catch (IOException ex) {
				return new SimpleEntry<Integer, String>(4, ex.getMessage());
			}
		}
		boolean filenameIsSupplied = this.filepath != null && this.filepath.trim().length() > 0;
		if (!filenameIsSupplied)
			return new SimpleEntry<Integer, String>(3, "No file is present");
		var encoder = new Encoder(this.filepath, this.onlyB64);
		try {
			return new SimpleEntry<Integer, String>(0, encoder.encode());
		} catch (FileNotFoundException ex) {
			return new SimpleEntry<Integer, String>(1, String.format("File %s does not exist", this.filepath));
		} catch (IOException ex) {
			return new SimpleEntry<Integer, String>(2, String.format("Can't read file %s", this.filepath));
		}
	}

	public Map<String, String> getMetadata() throws IOException {
		InputStream stream = Application.class.getResourceAsStream("/META-INF/MANIFEST.MF");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader rd = new BufferedReader(reader);
		Map<String, String> result = new HashMap<String, String>();
		rd.lines().forEach(str -> {
			String[] parts = str.split("\\s*:\\s*");
			if (parts.length < 2)
				return;
			for (int i = 0; i < parts.length; i++)
				parts[i] = parts[i].trim();
			result.put(parts[0], parts[1]);
		});
		rd.close();
		reader.close();
		stream.close();
		return result;
	}
}
