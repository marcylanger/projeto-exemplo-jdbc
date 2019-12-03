package application;

import java.util.List;
import java.util.Scanner;

import application.model.Cliente;
import application.model.TipoCliente;
import application.model.DAO.ClienteDAO;

public class MainTeste {

	private static ClienteDAO dao;
	private static Scanner ler;

	public static void main(String[] args) {

		ler = new Scanner(System.in);
		apresentarMenuPrincipal();

		ler.close();

	}

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

			break;

		case 3:

			break;
		default:
			System.out.println("Opção inválida.");
			break;
		}
	}

	public static void apresentarMenuCliente() {
		dao = new ClienteDAO();
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

	public static void cadastrarCliente() {
		// Cadastrando um cliente
		System.out.println("===== CADASTRAR CLIENTE =====");
		Cliente cliente = new Cliente();
		cliente.setNome("Carlos Cabral2");
		cliente.setCpf("654.654.654-88");
		cliente.setTipoCliente(TipoCliente.SUPER);
		cliente.setDataNascimento(null); 
		// cliente.setDataNascimento(LocalDate.now());

		dao.cadastrarCliente(cliente);
	}

	public static void listarClientes() {
		// Listando clientes cadastrados
		System.out.println("===== LISTA DE CLIENTES =====");
		List<Cliente> clientesConsultados = dao.consultarClientes();
		for (Cliente cliente2 : clientesConsultados) {
			System.out.println(cliente2);
		}
	}

	public static void detalharCliente() {
		// Recuperando um cliente por código
		System.out.println("===== DETALHAR CLIENTE =====");
		Cliente clienteDetalhado = dao.detalharCliente(1);
		System.out.println(clienteDetalhado);
	}

	public static void editarCliente() {
		// Editando um cliente System.out.println("===== EDITAR CLIENTE =====");
		Cliente clienteDetalhado = dao.detalharCliente(1);
		clienteDetalhado.setNome("NOVO NOME DO CLIENTE");
		dao.atualizarCliente(clienteDetalhado);
		clienteDetalhado = dao.detalharCliente(1);
		System.out.println(clienteDetalhado);
	}

	public static void removerCliente() {
		// Removendo um cliente System.out.println("===== REMOVER CLIENTE =====");
		dao.removerCliente(5);
		List<Cliente> clientesConsultados = dao.consultarClientes();
		for (Cliente cliente2 : clientesConsultados) {
			System.out.println(cliente2);
		}
	}

}
