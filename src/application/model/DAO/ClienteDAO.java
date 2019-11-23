package application.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.config.JDBCConnection;
import application.model.Cliente;

public class ClienteDAO {

	/**
	 * Método que cadastra um novo cliente na base de dados
	 * 
	 * @param cliente
	 * @return
	 */
	public Cliente cadastrarCliente(Cliente cliente) {
		try {
			JDBCConnection.JDBCConnect();
			Statement stmt = JDBCConnection.conn.createStatement();
			try {
				String nome = "'" + cliente.getNome() + "'";
				String cpf = "'" + cliente.getCpf() + "'";
				// Tratamento pois é um campo nullable
				String data = (cliente.getDataNascimento() != null ? ("'" + cliente.getDataNascimento() + "'") : null);
				stmt.execute("insert into cliente   " + "(nome, cpf, data_nascimento) " + "values (" + nome + ", "
						+ cpf + ", " + data + ") returning codigo");
				
				/*
				 * Por vezes é necessário recuperar o código que a inserção gerou
				 * Nesses casos acrescentamos "returning codigo" ao final da query, como acima
				 * E recuperamos criando um ResultSet (como quando é uma consulta)
				 * Isso é muito usado em casos de venda e item venda,
				 * onde é inserida uma venda e os itens venda precisam do codigo da venda inserida
				 * para setar na foreing key
				 */
				ResultSet last_insert_cliente = stmt.getResultSet();
				if(last_insert_cliente.next()) {
				   cliente.setCodigo(last_insert_cliente.getInt(1));
				}
			} catch (SQLException e) {
				System.out.println("Erro ao inserir tupla " + e);
			}

			stmt.close();
			JDBCConnection.conn.close();

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return cliente;
	}

	/**
	 * Método que atualiza o registro de um cliente no banco de dados
	 * 
	 * @param cliente
	 * @return
	 */
	public Cliente atualizarCliente(Cliente cliente) {
		try {
			JDBCConnection.JDBCConnect();
			Statement stmt = JDBCConnection.conn.createStatement();
			try {
				String nome = "'" + cliente.getNome() + "'";
				String cpf = "'" + cliente.getCpf() + "'";
				// Tratamento pois é um campo nullable
				String data = (cliente.getDataNascimento() != null ? ("'" + cliente.getDataNascimento() + "'") : null);
				stmt.executeUpdate("update cliente set " + "nome = " + nome + ", cpf = " + cpf + ", data_nascimento = "
						+ data + " where codigo = " + cliente.getCodigo());
			} catch (SQLException e) {
				System.out.println("Erro ao alterar tupla " + e);
			}

			stmt.close();
			JDBCConnection.conn.close();

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return cliente;
	}

	/**
	 * Método que remove um registro de cliente do banco de dados
	 * 
	 * @param clienteId
	 */
	public void removerCliente(Integer clienteId) {
		try {
			JDBCConnection.JDBCConnect();
			Statement stmt = JDBCConnection.conn.createStatement();
			try {
				stmt.executeUpdate("delete from cliente where codigo = " + clienteId);
			} catch (SQLException e) {
				System.out.println("Erro ao remover tupla " + e);
			}

			stmt.close();
			JDBCConnection.conn.close();

		} catch (SQLException e1) {

			e1.printStackTrace();
		}

	}

	/**
	 * Método que busca todos os registros de clientes do banco de dados
	 * 
	 * @return
	 */
	public List<Cliente> consultarClientes() {

		List<Cliente> listaClientes = new ArrayList<Cliente>();
		try {
			JDBCConnection.JDBCConnect();
			Statement stmt = JDBCConnection.conn.createStatement();

			String sql = "select c.codigo, c.nome, c.cpf, c.data_nascimento " + "from cliente c";
			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				LocalDate dataNascimento = null;
				if (rset.getDate(4) != null) {
					dataNascimento = rset.getDate(4).toLocalDate();
				}
				Cliente cliente = new Cliente(rset.getInt(1), rset.getString(2), rset.getString(3), dataNascimento);
				listaClientes.add(cliente);
			}

			stmt.close();
			JDBCConnection.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaClientes;
	}

	/**
	 * Método que busca o registro de um cliente do banco de dados através do seu código
	 * @param clienteId
	 * @return
	 */
	public Cliente detalharCliente(Integer clienteId) {

		Cliente cliente = new Cliente();
		try {
			JDBCConnection.JDBCConnect();
			Statement stmt = JDBCConnection.conn.createStatement();

			String sql = "select c.codigo, c.nome, c.cpf, c.data_nascimento " + "from cliente c where c.codigo = "
					+ clienteId;
			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				LocalDate dataNascimento = null;
				if (rset.getDate(4) != null) {
					dataNascimento = rset.getDate(4).toLocalDate();
				}
				cliente = new Cliente(rset.getInt(1), rset.getString(2), rset.getString(3), dataNascimento);
			}

			stmt.close();
			JDBCConnection.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cliente;
	}

}
