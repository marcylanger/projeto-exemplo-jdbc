package application;
import java.util.List;

import application.model.Cliente;
import application.model.DAO.ClienteDAO;

public class MainTeste {

	public static void main(String[] args) {
		ClienteDAO dao = new ClienteDAO();
		
		//Cadastrando um cliente
		System.out.println("===== CADASTRAR CLIENTE =====");
		Cliente cliente = new Cliente();
		cliente.setNome("Carlos Cabral2");
		cliente.setCpf("654.654.654-88");
		cliente.setDataNascimento(null); //cliente.setDataNascimento(LocalDate.now());
		
		dao.cadastrarCliente(cliente);
		/*
		//Listando clientes cadastrados
		System.out.println("===== LISTA DE CLIENTES =====");
		List<Cliente> clientesConsultados = dao.consultarClientes();
		for (Cliente cliente2 : clientesConsultados) {
			System.out.println(cliente2);
		}
		
		//Recuperando um cliente por código
		System.out.println("===== DETALHAR CLIENTE =====");
		Cliente clienteDetalhado = dao.detalharCliente(1);
		System.out.println(clienteDetalhado);
		
		//Editando um cliente
		System.out.println("===== EDITAR CLIENTE =====");
		clienteDetalhado.setNome("NOVO NOME DO CLIENTE");
		dao.atualizarCliente(clienteDetalhado);
		clienteDetalhado = dao.detalharCliente(1);
		System.out.println(clienteDetalhado);
		
		
		//Removendo um cliente
		System.out.println("===== REMOVER CLIENTE =====");
		dao.removerCliente(5);
		clientesConsultados = dao.consultarClientes();
		for (Cliente cliente2 : clientesConsultados) {
			System.out.println(cliente2);
		}
		
		*/
		
	}

}
