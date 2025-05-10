package ru.odinkos.cipher;

public class Cipher {
	private static final int ALPHABET_LENGTH = Alphabet.getLength();

	public String encrypt(String input, int key) {
		return process(input, normalizeKey(key));
	}

	public String decrypt(String input, int key) {
		return process(input, normalizeKey(-key));
	}

	private String process(String input, int key) {
		char[] result = new char[input.length()];
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			int index = Alphabet.getIndex(c);
			result[i] = index == -1 ? c : Alphabet.getChar((index + key) % ALPHABET_LENGTH);
		}
		return new String(result);
	}

	private int normalizeKey(int key) {
		return ((key % ALPHABET_LENGTH) + ALPHABET_LENGTH) % ALPHABET_LENGTH;
	}
}
