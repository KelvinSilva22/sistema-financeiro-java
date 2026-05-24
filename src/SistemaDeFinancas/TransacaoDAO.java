package SistemaDeFinancas;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TransacaoDAO {

    public void cadastrar(Transacao t) {
        String sql =
                "INSERT INTO transacoes(descricao, valor, tipo) VALUES (?, ?, ?)";

        try{

            Connection conn = Conexao.conectar();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, t.getDescricao());
            ps.setDouble(2, t.getValor());
            ps.setString(3, t.getTipo());

            ps.execute();

            System.out.println("Transação Salva!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar");
        }


    }

    public ArrayList<Transacao> listar() {
        ArrayList<Transacao> lista = new ArrayList<>();

        String sql = "SELECT * FROM transacoes";

        try {
            Connection conn = Conexao.conectar();

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Transacao t = new Transacao(
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getString("tipo")
                );

                lista.add(t);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar");
        }
        return lista;
    }

    public double pegarSaldo() {
        double saldo = 0;

        String sql = """
        SELECT SUM(
            CASE 
                WHEN tipo = 'ENTRADA' THEN valor
                ELSE -valor
            END
        ) AS saldo
        FROM transacoes
    """;

        try {
            Connection conn = Conexao.conectar();

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                saldo = rs.getDouble("saldo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saldo;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM transacoes WHERE id = ?";

        try {
            Connection conn = Conexao.conectar();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Transação exluida!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizar(int id, String descricao, double valor, String tipo) {
        String sql = "UPDATE transacoes SET descricao = ?, valor = ?, tipo = ? WHERE id = ?";

        try{
            Connection conn = Conexao.conectar();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, descricao);
            ps.setDouble(2, valor);
            ps.setString(3, tipo);
            ps.setInt(4, id);

            ps.executeUpdate();

            System.out.println("Transaçao atualizada!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
