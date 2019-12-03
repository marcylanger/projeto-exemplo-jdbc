package application;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import application.model.Cliente;
import application.model.ItemVenda;
import application.model.Produto;
import application.model.TipoCliente;
import application.model.Venda;
import application.model.DAO.ClienteDAO;
import application.model.DAO.ProdutoDAO;
import application.model.DAO.VendaDAO;

public class MainTeste {

	private static ClienteDAO daoCliente;
	private static ProdutoDAO daoProduto;
	private static VendaDAO daoVenda;
	private static Scanner ler;

	public static void main(String[] args) {

		daoCliente = new ClienteDAO();
		daoVenda = new VendaDAO();
		daoProduto = new ProdutoDAO();
		
		ler = new Scanner(System.in);
		apresentarMenuPrincipal();

		ler.close();

	}
	
	/*
	 * ========= FUNÇÕES DE APRESENTAÇÃO DE MENUS ===========
	 */

	public static void apresentarMenuPrincipal() {

		System.out.println("Que menu você deseja acessar?");
		System.out.println("1- Cliente");
		System.out.println("2- Produto");
		System.out.println("3- Venda");

		Integer opcaoMenu = ler.nextInt();

		switch (opcaoMenu) {
		case 1:
			apresentarMenuCliente();
			break;

		case 2:
			apresentarMenuProduto();
			break;

		case 3:
			apresentarMenuVenda();
			break;
		default:
			System.out.println("Opção inválida.");
			break;
		}
	}

	public static void apresentarMenuCliente() {
		
		System.out.println("Que ação gostaria de realizar?");
		System.out.println("1- Listar clientes cadastrados");
		System.out.println("2- Ver os detalhes de um cliente cadastrado");
		System.out.println("3- Remover um cliente cadastrado");
		System.out.println("4- Cadastrar um novo cliente");
		System.out.println("5- Alterar o cadastro de um cliente");

		Integer opcaoCliente = ler.nextInt();

		switch (opcaoCliente) {
		case 1:
			listarClientes();
			break;
		case 2:
			detalharCliente();
			break;
		case 3:
			removerCliente();
			break;
		case 4:
			cadastrarCliente();
			break;
		case 5:
			editarCliente();
			break;

		default:
			System.out.println("Opção inválida.");
			break;
		}
	}

	public static void apresentarMenuProduto() {
		
		System.out.println("Que ação gostaria de realizar?");
		System.out.println("1- Listar produtos cadastrados");
		System.out.println("2- Ver os detalhes de um produto cadastrado");
		System.out.println("3- Remover um produto cadastrado");
		System.out.println("4- Cadastrar um novo produto");
		System.out.println("5- Alterar o cadastro de um produto");

		Integer opcaoProduto = ler.nextInt();

		switch (opcaoProduto) {
		case 1:
			listarProdutos();
			break;
		case 2:
			detalharProduto();
			break;
		case 3:
			removerProduto();
			break;
		case 4:
			cadastrarProduto();
			break;
		case 5:
			editarProduto();
			break;

		default:
			System.out.println("Opção inválida.");
			break;
		}
	}
	
	public static void apresentarMenuVenda() {
		
		System.out.println("Que ação gostaria de realizar?");
		System.out.println("1- Listar vendas realizadas");
		System.out.println("2- Ver os detalhes de uma venda realizada");
		System.out.println("3- Cancelar uma venda realizada");
		System.out.println("4- Realizar uma nova venda");
		System.out.println("5- Alterar uma venda realizada");

		Integer opcaoVenda = ler.nextInt();

		switch (opcaoVenda) {
		case 1:
			listarVendas();
			break;
		case 2:
			detalharVenda();
			break;
		case 3:
			removerVenda();
			break;
		case 4:
			cadastrarVenda();
			break;
		case 5:
			editarVenda();
			break;

		default:
			System.out.println("Opção inválida.");
			break;
		}
	}
	
	/*
	 * ========= FUNÇÕES DE CLIENTE ===========
	 */

	public static void cadastrarCliente() {
		// Cadastrando um cliente
		System.out.println("===== CADASTRAR CLIENTE =====");
		Cliente cliente = new Cliente();
		cliente.setNome("Carlos Cabral2");
		cliente.setCpf("654.654.654-88");
		cliente.setTipoCliente(TipoCliente.SUPER);
		cliente.setDataNascimento(null);
		// cliente.setDataNascimento(LocalDate.now());

		daoCliente.cadastrarCliente(cliente);
		System.out.println("Cliente cadastrado com sucesso.");
	}

	public static void listarClientes() {
		// Listando clientes cadastrados
		System.out.println("===== LISTA DE CLIENTES =====");
		List<Cliente> clientesConsultados = daoCliente.consultarClientes();
		for (Cliente cliente2 : clientesConsultados) {
			System.out.println(cliente2);
		}
	}

	public static void detalharCliente() {
		// Recuperando um cliente por código
		System.out.println("===== DETALHAR CLIENTE =====");
		Cliente clienteDetalhado = daoCliente.detalharCliente(1);
		System.out.println(clienteDetalhado);
	}

	public static void editarCliente() {
		// Editando um cliente 
		System.out.println("===== EDITAR CLIENTE =====");
		Cliente clienteDetalhado = daoCliente.detalharCliente(1);
		clienteDetalhado.setNome("NOVO NOME DO CLIENTE");
		daoCliente.atualizarCliente(clienteDetalhado);
		System.out.println("Cliente alterado com sucesso.");
	}

	public static void removerCliente() {
		// Removendo um cliente 
		System.out.println("===== REMOVER CLIENTE =====");
		daoCliente.removerCliente(5);
		System.out.println("Cliente removido com sucesso.");
	}

	/*
	 * ========= FUNÇÕES DE PRODUTO ===========
	 */
	
	public static void cadastrarProduto() {
		// Cadastrando um cliente
		System.out.println("===== CADASTRAR PRODUTO =====");
		Produto produto = new Produto();
		produto.setReferencia("Coca-cola 2L");
		produto.setDescricao("Refrigerante coca-cola 2 L, embalagem descartável.");
		produto.setValorUnitario(8.99F);

		daoProduto.cadastrarProduto(produto);
		System.out.println("Produto cadastrado com sucesso.");
	}

	public static void listarProdutos() {
		// Listando produtos cadastrados
		System.out.println("===== LISTA DE PRODUTOS =====");
		List<Produto> produtosConsultados = daoProduto.consultarProdutos();
		for (Produto produto : produtosConsultados) {
			System.out.println(produto);
		}
	}

	public static void detalharProduto() {
		// Recuperando um produto por código
		System.out.println("===== DETALHAR PRODUTO =====");
		Produto produtoDetalhado = daoProduto.detalharProduto(1);
		System.out.println(produtoDetalhado);
	}

	public static void editarProduto() {
		// Editando um produto 
		System.out.println("===== EDITAR PRODUTO -- não implementado =====");
		
	}

	public static void removerProduto() {
		// Removendo um produto 
		System.out.println("===== REMOVER PRODUTO -- não implementado =====");

	}
	
	/*
	 * ========= FUNÇÕES DE VENDA ===========
	 */
	
	public static void cadastrarVenda() {
		// Realizando venda
		System.out.println("===== REALIZAR VENDA =====");
		
		Venda venda = new Venda();
		
		Cliente cliente = daoCliente.detalharCliente(1);
		
		venda.setCliente(cliente);
		venda.setDataVenda(LocalDate.now());

		Produto produto1 = daoProduto.detalharProduto(1);
		
		ItemVenda item1 = new ItemVenda();
		item1.setProduto(produto1);
		item1.setValorItem(produto1.getValorUnitario());
		item1.setQuantidade(2);
		item1.setVenda(venda);
		
		venda.getItens().add(item1);
		
		
		Produto produto2 = daoProduto.detalharProduto(2);
		ItemVenda item2 = new ItemVenda();
		item2.setProduto(produto2);
		item2.setValorItem(produto2.getValorUnitario());
		item2.setQuantidade(3);
		item2.setVenda(venda);
		
		venda.getItens().add(item2);
		
		venda.calcularValorTotal();
		
		daoVenda.cadastrarVenda(venda);
		System.out.println("Venda registrada com sucesso.");
	}

	public static void listarVendas() {
		// Listando vendas realizadas
		System.out.println("===== LISTA DE VENDAS =====");
		List<Venda> vendasConsultadas = daoVenda.consultarVendas();
		for (Venda venda : vendasConsultadas) {
			System.out.println(venda);
		}
	}

	public static void detalharVenda() {
		// Recuperando uma venda por código
		System.out.println("===== DETALHAR VENDA =====");
		Venda vendaDetalhada = daoVenda.detalharVenda(1);
		System.out.println(vendaDetalhada);
	}

	public static void editarVenda() {
		// Editando um produto 
		System.out.println("===== EDITAR VENDA -- não implementado =====");
		
	}

	public static void removerVenda() {
		// Removendo um produto 
		System.out.println("===== REMOVER VENDA -- não implementado =====");

	}
}
