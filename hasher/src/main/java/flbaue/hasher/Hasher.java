/*
 * Florian Bauer
 * flbaue@posteo.de
 * Copyright (c) 2014.
 */

package flbaue.hasher;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

/**
 * Hasher
 * <p>
 * An example implementation to hash passwords safely
 * <p>
 * Created by Florian Bauer on 09.10.14. flbaue@posteo.de
 */
public class Hasher {

    private final static int ITERATIONS = 30000;
    private final static int KEY_LENGTH_IN_BIT = 512;
    private final static int SALT_LENGTH_IN_BYTE = 16;

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        new Hasher().run();
    }

    private void run() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Password:");
        String password = scanner.nextLine().trim();
        String hash = generateStrongPasswordHash(password);
        System.out.println("Hash:");
        System.out.println(hash);
        System.out.printf("validated:" + validatePassword(password, hash));
    }

    private String generateStrongPasswordHash(String password) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, KEY_LENGTH_IN_BIT);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return ITERATIONS + ":" + DatatypeConverter.printHexBinary(salt) + ":" + DatatypeConverter.printHexBinary(hash);
    }

    private byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[SALT_LENGTH_IN_BYTE];
        sr.nextBytes(salt);
        return salt;
    }

    private boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = DatatypeConverter.parseHexBinary(parts[1]);
        byte[] hash = DatatypeConverter.parseHexBinary(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        return MessageDigest.isEqual(hash, testHash);
    }

}
