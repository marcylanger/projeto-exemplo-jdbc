package application.model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.config.JDBCConnection;
import application.model.Cliente;
import application.model.ItemVenda;
import application.model.Produto;
import application.model.Venda;

public class VendaDAO {

	public Venda cadastrarVenda(Venda venda) {
		try {
			JDBCConnection.JDBCConnect();
			Statement stmt = JDBCConnection.conn.createStatement();
			try {
				String data = "'" + venda.getDataVenda() + "'";
				stmt.execute("insert into venda   " + "(data_venda, valor_total, cliente_codigo) " + "values ("
						+ data + ", " + venda.getValorTotal() + ", " + venda.getCliente().getCodigo() + ") returning codigo");

				/*
				 * Por vezes é necessário recuperar o código que a inserção gerou Nesses casos
				 * acrescentamos "returning codigo" ao final da query, como acima E recuperamos
				 * criando um ResultSet (como quando é uma consulta) Isso é muito usado em casos
				 * de venda e item venda, onde é inserida uma venda e os itens venda precisam do
				 * codigo da venda inserida para setar na foreing key
				 */
				ResultSet last_insert_venda = stmt.getResultSet();
				if (last_insert_venda.next()) {
					venda.setCodigo(last_insert_venda.getInt(1));
					//Inserindo os itens da venda no banco de dados e na lista de itens
					for (ItemVenda item : venda.getItens()) {
						inserirItemVenda(item, venda.getCodigo());
					}
					
				}
			} catch (SQLException e) {
				System.out.println("Erro ao inserir tupla " + e);
			}

			stmt.close();
			JDBCConnection.conn.close();

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return venda;
	}
	
	private ItemVenda inserirItemVenda(ItemVenda item, Integer codigoVenda) {
		try {
			JDBCConnection.JDBCConnect();
			Statement stmt = JDBCConnection.conn.createStatement();
			try {
				stmt.execute("insert into item_venda   " + "(venda_codigo, produto_codigo, valor_item, quantidade) " + "values ("
						+ codigoVenda + ", " + item.getProduto().getCodigo() + ", " + item.getValorItem() + ", " + item.getQuantidade() + ") returning codigo");

				/*
				 * Por vezes é necessário recuperar o código que a inserção gerou Nesses casos
				 * acrescentamos "returning codigo" ao final da query, como acima E recuperamos
				 * criando um ResultSet (como quando é uma consulta) Isso é muito usado em casos
				 * de venda e item venda, onde é inserida uma venda e os itens venda precisam do
				 * codigo da venda inserida para setar na foreing key
				 */
				ResultSet last_insert_item = stmt.getResultSet();
				if (last_insert_item.next()) {
					item.setCodigo(last_insert_item.getInt(1));
				}
			} catch (SQLException e) {
				System.out.println("Erro ao inserir tupla " + e);
			}

			stmt.close();
			JDBCConnection.conn.close();

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return item;
	}
	
	public List<Venda> consultarVendas() {

		List<Venda> listaVendas = new ArrayList<Venda>();
		try {
			JDBCConnection.JDBCConnect();
			Statement stmt = JDBCConnection.conn.createStatement();

			String sql = "select v.codigo, v.data_venda, v.valor_total, v.cliente_codigo " + "from venda v";
			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				Venda venda = new Venda();
				
				LocalDate data = null;
				if (rset.getDate(2) != null) {
					venda.setDataVenda(rset.getDate(2).toLocalDate());
				}
				
				//Recupera o cliente
				ClienteDAO clienteDAO = new ClienteDAO();
				Cliente cliente = clienteDAO.detalharCliente(rset.getInt(4));
				venda.setCliente(cliente);
				venda.setCodigo(rset.getInt(1));
				venda.setValorTotal(rset.getFloat(3));
				
				//recuperar itens da venda
				venda.getItens().addAll(this.listarItensVenda(venda));
			
				listaVendas.add(venda);
			}

			stmt.close();
			JDBCConnection.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaVendas;
	}
	
	
	private List<ItemVenda> listarItensVenda(Venda venda){
		List<ItemVenda> itens = new ArrayList<ItemVenda>();
		try {
			JDBCConnection.JDBCConnect();
			Statement stmt = JDBCConnection.conn.createStatement();

			String sql = "select iv.codigo, iv.produto_codigo, iv.valor_item, iv.quantidade " + "from item_venda iv "
					+ "where venda_codigo = " + venda.getCodigo();
			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				ItemVenda item = new ItemVenda();
				
				//Recupera o produto
				ProdutoDAO produtoDAO = new ProdutoDAO();
				Produto produto = produtoDAO.detalharProduto(rset.getInt(2));
				item.setCodigo(rset.getInt(1));
				item.setProduto(produto);
				item.setQuantidade(rset.getInt(4));
				item.setValorItem(rset.getFloat(3));
				item.setVenda(venda);
				
				//recuperar itens da venda
				
				itens.add(item);
			}

			stmt.close();
			JDBCConnection.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itens;
	}
	
	public Venda detalharVenda(Integer vendaCodigo) {

		Venda venda = new Venda();
		try {
			JDBCConnection.JDBCConnect();
			Statement stmt = JDBCConnection.conn.createStatement();

			String sql = "select v.codigo, v.data_venda, v.valor_total, v.cliente_codigo " + "from venda v "
					+ "where v.codigo = " + vendaCodigo;
			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
	
				
				LocalDate data = null;
				if (rset.getDate(2) != null) {
					venda.setDataVenda(rset.getDate(2).toLocalDate());
				}
				
				//Recupera o cliente
				ClienteDAO clienteDAO = new ClienteDAO();
				Cliente cliente = clienteDAO.detalharCliente(rset.getInt(4));
				venda.setCliente(cliente);
				venda.setCodigo(rset.getInt(1));
				venda.setValorTotal(rset.getFloat(3));
				
				//recuperar itens da venda
				venda.getItens().addAll(this.listarItensVenda(venda));
			
			}

			stmt.close();
			JDBCConnection.conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return venda;
	}
}
