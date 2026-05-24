


package SistemaDeFinancas;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public static Connection conectar() {
        try {
            String url = "jdbc:mysql://localhost:3306/financeiro";
            String user = "root";
            String senha = "as12as12";

            return DriverManager.getConnection(url, user, senha);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
