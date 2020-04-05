package site.stein197.b64;

import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Parameters;

public class Application implements Callable<Integer> {

	@Parameters(index = "0", description = "A file which need to be converted to Base64")
	private String file;

	public static void main(String... args) {
		System.out.println("HH");
		int exitCode = new CommandLine(new Application()).execute(args);
		System.exit(exitCode);
	}

	@Override
	public Integer call() {
		return null;
	}
}
