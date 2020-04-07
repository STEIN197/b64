package site.stein197.b64;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(abbreviateSynopsis = true)
public class Application implements Callable<Integer> {

	@Option(names = "-s", description = "If this option is present then only the Base64 output will be present. Otherwise the output will contain additional data for using it in CSS url() function")
	private boolean onlyB64;

	@Parameters(index = "0", description = "A file which need to be converted to Base64")
	private String filepath;

	public static void main(String... args) {
		var app = new Application();
		int exitCode = new CommandLine(app).execute(args);
		System.exit(exitCode);
	}

	@Override
	public Integer call() {
		if (this.filepath == null || this.filepath.length() == 0) {
			System.out.println("No file is present");
			return 3;
		}
		var encoder = new Encoder(this.filepath, this.onlyB64);
		try {
			System.out.println(encoder.encode());
			return 0;
		} catch (FileNotFoundException ex) {
			System.out.println(String.format("File %s does not exist", this.filepath));
			return 1;
		} catch (IOException ex) {
			System.out.println(String.format("Can't read file %s", this.filepath));
			return 2;
		}
	}
}
