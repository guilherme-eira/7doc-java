package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MarvelUrlGenerator {

    public static String generate() throws NoSuchAlgorithmException {

        String publicKey = System.getenv("MARVEL_PUBLIC_KEY");
        String privateKey = System.getenv("MARVEL_PRIVATE_KEY");
        String timestamp = String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        String hash = generateHash(publicKey, privateKey, timestamp);

        return "https://gateway.marvel.com:443/v1/public/series?&orderBy=-modified&ts="+ timestamp +"&apikey=" + publicKey + "&hash=" + hash + "&limit=100";
    }

    private static String generateHash(String publicKey, String privateKey, String timestamp) throws NoSuchAlgorithmException {

        String input = timestamp + privateKey + publicKey;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
