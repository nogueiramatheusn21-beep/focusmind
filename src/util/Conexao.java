package src.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection conectar() {

        try {

            return DriverManager.getConnection(

                "jdbc:mysql://localhost:3306/sistema_estudos",

                "root",

                "21122006"
            );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}