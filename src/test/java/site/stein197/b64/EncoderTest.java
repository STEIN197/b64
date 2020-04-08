package site.stein197.b64;

import org.junit.Test;

public class EncoderTest {

	@Test(expected = IllegalArgumentException.class)
	public void newInstance_OnEmptyString_ThrowsException() {
		new Encoder("", false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void newInstance_OnNull_ThrowsException() {
		new Encoder(null, false);
	}

	public void encode_OnNotExistingFile_ThrowsException() {}
	public void encode_OnDirectory_ThrowsException() {}
	public void encode_IfOnlyb64_ReturnsOnlyB64() {}
	public void encode_WhenOnlyB64IsFalse_ReturnsCSSURL() {}
}
