package ru.odinkos.bruteforce;

import ru.odinkos.cipher.Alphabet;
import ru.odinkos.cipher.Cipher;

public class BruteForceDecryptor {
	private final Cipher cipher;

	public BruteForceDecryptor(Cipher cipher) {
		this.cipher = cipher;
	}

	public String decrypt(String encryptedText) {
		String bestResult = encryptedText;
		double bestScore = -1;
		int bestKey = 0;

		for (int key = 0; key < Alphabet.getLength(); key++) {
			String decrypted = cipher.decrypt(encryptedText, key);
			double score = calculateSpaceScore(decrypted);
			if (score > bestScore) {
				bestScore = score;
				bestResult = decrypted;
				bestKey = key;
			}
		}
		System.out.println("Ключ: " + bestKey);
		return bestResult;
	}

	private double calculateSpaceScore(String decrypted) {
		int spaceCount = 0;
		int cyrillicCount = 0;
		int totalChars = decrypted.length();

		for (char c : decrypted.toCharArray()) {
			if (c == ' ') {
				spaceCount++;
			}
			if (Alphabet.getIndex(c) != -1 && Character.isLetter(c)) {
				cyrillicCount++;
			}
		}

		double spaceFrequency = totalChars > 0 ? (double) spaceCount / totalChars : 0;
		double cyrillicRatio = totalChars > 0 ? (double) cyrillicCount / totalChars : 0;
		return spaceFrequency * cyrillicRatio * 1000;
	}
}
