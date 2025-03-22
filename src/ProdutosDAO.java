/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try (Connection conn = new conectaDAO().connectDB(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            
            int rowsInserted = stmt.executeUpdate();
            System.out.println("Produto Cadastrado! ");
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            return false;
        }
    }

    public List<ProdutosDTO> listarProdutos() {
        List<ProdutosDTO> listaProdutos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = new conectaDAO().connectDB(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listaProdutos.add(produto);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
        return listaProdutos;
    }

}
