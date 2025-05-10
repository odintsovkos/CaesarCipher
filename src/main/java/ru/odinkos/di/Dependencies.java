package ru.odinkos.di;

import ru.odinkos.bruteforce.BruteForceDecryptor;
import ru.odinkos.cipher.Cipher;
import ru.odinkos.cipher.Validator;
import ru.odinkos.filemanager.FileManager;

public record Dependencies(Cipher cipher, Validator validator, FileManager fileManager, BruteForceDecryptor decryptor) {
}
