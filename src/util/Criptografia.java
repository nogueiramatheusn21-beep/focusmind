package src.util;

import java.security.MessageDigest;

public class Criptografia {

    public static String md5(
        String texto
    ) {

        try {

            MessageDigest md =
            MessageDigest.getInstance("MD5");

            md.update(
                texto.getBytes()
            );

            byte[] bytes =
            md.digest();

            StringBuilder sb =
            new StringBuilder();

            for(byte b : bytes) {

                sb.append(
                    String.format(
                        "%02x",
                        b
                    )
                );
            }

            return sb.toString();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}