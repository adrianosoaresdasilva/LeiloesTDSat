
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto(ProdutosDTO produto) {
        try {
            conn = new conectaDAO().connectDB(); // Conecta ao banco de dados

            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate(); // Executa a inserção

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!"); // Mensagem de sucesso

            prep.close();
            conn.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public ArrayList<ProdutosDTO> listarProdutos(){
        try {
            conn = new conectaDAO().connectDB();
            String sql = "SELECT * FROM produtos";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }

            prep.close();
            conn.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return listagem;
    }
    
        
}

