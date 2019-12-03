package application.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.config.JDBCConnection;
import application.model.Cliente;
import application.model.Produto;

public class ProdutoDAO {

	public Produto cadastrarProduto(Produto produto) {

		try {
			JDBCConnection.JDBCConnect();
			Statement stmt = JDBCConnection.conn.createStatement();
			try {
				String referencia = "'" + produto.getReferencia() + "'";
				// Tratamento pois é um campo nullable
				String descricao = (produto.getDescricao() != null ? ("'" + produto.getDescricao() + "'") : null);

				stmt.execute("insert into produto   " + "(referencia, descricao, valor_unitario) " + "values ("
						+ referencia + ", " + descricao + ", " + produto.getValorUnitario() + ") returning codigo");

				/*
				 * Por vezes é necessário recuperar o código que a inserção gerou Nesses casos
				 * acrescentamos "returning codigo" ao final da query, como acima E recuperamos
				 * criando um ResultSet (como quando é uma consulta) Isso é muito usado em casos
				 * de venda e item venda, onde é inserida uma venda e os itens venda precisam do
				 * codigo da venda inserida para setar na foreing key
				 */
				ResultSet last_insert_produto = stmt.getResultSet();
				if (last_insert_produto.next()) {
					produto.setCodigo(last_insert_produto.getInt(1));
				}
			} catch (SQLException e) {
				System.out.println("Erro ao inserir tupla " + e);
			}

			stmt.close();
			JDBCConnection.conn.close();

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return produto;
	}

	public List<Produto> consultarProdutos() {

		List<Produto> listaprodutos = new ArrayList<Produto>();
		try {
			JDBCConnection.JDBCConnect();
			Statement stmt = JDBCConnection.conn.createStatement();

			String sql = "select p.codigo, p.referencia, p.descricao, p.valor_unitario " + "from produto p";
			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				Produto produto = new Produto(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getFloat(4));
				listaprodutos.add(produto);
			}

			stmt.close();
			JDBCConnection.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaprodutos;
	}

	public Produto detalharProduto(Integer produtoCodigo) {
		Produto produto = new Produto();
		try {
			JDBCConnection.JDBCConnect();
			Statement stmt = JDBCConnection.conn.createStatement();

			String sql = "select p.codigo, p.referencia, p.descricao, p.valor_unitario " + "from produto p "
					+ " where codigo = " + produtoCodigo;
			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				produto = new Produto(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getFloat(4));
			}

			stmt.close();
			JDBCConnection.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produto;
	}
}
