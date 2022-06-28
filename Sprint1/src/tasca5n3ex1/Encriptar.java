package tasca5n3ex1;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

import java.io.*;
import java.nio.file.Path;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encriptar {

	// metode per generar un IV
	public static IvParameterSpec generateIv() {
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		return new IvParameterSpec(iv);
	}

	// metode per generar clau AES de tamany 128,192 o 256 bits
	public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(n);
		SecretKey key = keyGenerator.generateKey();
		return key;
	}

	public static void encryptAndDecryptFile(String algorithm, SecretKey key, IvParameterSpec iv, File inputFile,
			File outputFile, int MODE) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		Cipher cipher = Cipher.getInstance(algorithm);
		// Cipher.ENCRYPT_MODE = 1 and Cipher.DECRYPT_MODE = 2
		// encriptar o desencriptar
		cipher.init(MODE, key, iv);

		FileInputStream inputStream = new FileInputStream(inputFile);
		FileOutputStream outputStream = new FileOutputStream(outputFile);

		byte[] buffer = new byte[(int) inputFile.length()];// new byte[64];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			byte[] output = cipher.update(buffer, 0, bytesRead);

			if (output != null) {
				outputStream.write(output);
			}
		}
		byte[] outputBytes = cipher.doFinal();
		if (outputBytes != null) {
			outputStream.write(outputBytes);
		}
		inputStream.close();
		outputStream.close();
	}

	public static void givenFile_whenEncrypt_thenSuccess(Path inputPath, Path encryptPath, Path decryptPath)

			throws NoSuchAlgorithmException, IOException, IllegalBlockSizeException, InvalidKeyException,
			BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

		SecretKey key = Encriptar.generateKey(128);
		String algorithm = "AES/CBC/PKCS5Padding";
		IvParameterSpec ivParameterSpec = Encriptar.generateIv();

		File inputFile = inputPath.toFile();

		File encryptedFile = encryptPath.toFile();

		File decryptedFile = decryptPath.toFile();

		Encriptar.encryptAndDecryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile, 1);
		Encriptar.encryptAndDecryptFile(algorithm, key, ivParameterSpec, encryptedFile, decryptedFile, 2);

	}

}
