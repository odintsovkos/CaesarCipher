package ru.odinkos.cipher;

import java.util.HashMap;

public class Alphabet {
	private static final char[] CHARS = {
			'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф',
			'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '
	};

	private static final HashMap<Character, Integer> CHAR_TO_INDEX = new HashMap<>();

	static {
		for (int i = 0; i < CHARS.length; i++) {
			CHAR_TO_INDEX.put(CHARS[i], i);
		}
	}

	public static char getChar(int index) {
		if (index >= 0 && index < CHARS.length) {
			return CHARS[index];
		}
		throw new IllegalArgumentException("Index out of bounds: " + index);
	}

	public static int getIndex(char c) {
		return CHAR_TO_INDEX.getOrDefault(c, -1);
	}

	public static int getLength() {
		return CHARS.length;
	}
}
