package funcoes;

import modelos.*;
import util.Session;
import menus.Menus;

import java.util.ArrayList;
import java.util.Scanner;

public class FuncClientes {
	public static void cadastroCliente(Scanner sc) {
		Cliente cliente = new Cliente();
		Comanda comanda = new Comanda();
		
		String cpf = FuncGerais.pedeCPF(sc);
		
		int result = pegaIndexCliente(cpf);
		
		if(result != -123) {
			System.out.println("CPF já cadastrado.");
			return;
		}
		
		cliente.setCpf(cpf);
		cliente.setNome(FuncGerais.pedeNome(sc));
		cliente.setIdade(FuncGerais.pedeIdade(sc));
		cliente.setqntIngressos(0);
		
		comanda.setCodigo(100);
		comanda.setValor(0);
		
		System.out.println("Cliente cadastrado com sucesso!");
		
		Session.banco.adicionarCliente(cliente);
	}
	
	public static void cadastroCliente(Scanner sc, String cpf) {
		Cliente cliente = new Cliente();
		Comanda comanda = new Comanda();
		
		cliente.setCpf(cpf);
		cliente.setNome(FuncGerais.pedeNome(sc));
		cliente.setIdade(FuncGerais.pedeIdade(sc));
		cliente.setqntIngressos(0);
		
		comanda.setCodigo(Session.banco.getClientes().size() + 100);
		comanda.setValor(0);
		
		System.out.println("Cliente cadastrado com sucesso!");
		
		Session.banco.adicionarCliente(cliente);
	}
	
	public static void printaTodosClientes() {
		ArrayList<Cliente> clientes = Session.banco.getClientes();
		
		for(int i = 0; i < clientes.size(); i++) {
			printaCliente(clientes.get(i));
			FuncGerais.separador();
		}
	}
	
	public static void printaCliente(Cliente cliente) {
		System.out.println("Nome: " + cliente.getNome());
		System.out.println("CPF: " + cliente.getCpf());
		System.out.println("Idade: " + cliente.getIdade());
		System.out.println("Ingressos: " + cliente.getQntIngressos());
	}
	
	public static Cliente procuraCliente(String cpf) {
		Cliente clienteRetorno = null;
		ArrayList<Cliente> clientes = Session.banco.getClientes();
		
		for(Cliente cliente : clientes) {
			if(cliente.getCpf().equals(cpf)) {
				clienteRetorno = cliente;
				break;
			}
		}
		
		return clienteRetorno;
	}
	
	public static int pegaIndexCliente(String cpf) {
		int retorno = -123;
		ArrayList<Cliente> clientes = Session.banco.getClientes();
		
		for(int i = 0; i < clientes.size(); i++) {
			Cliente cliente = clientes.get(i);
			if(cliente.getCpf().equals(cpf)) {
				retorno = i;
				break;
			}
		}
		
		return retorno;
	}
	
	public static void deletaCliente(String cpf) {
		Cliente cliente = procuraCliente(cpf);
		
		if(cliente != null) {
			Session.banco.deletarCliente(cliente);
			System.out.println("Cliente deletado com sucesso.");
		}
		else {
			System.out.println("Cliente não encontrado");
		}
	}
	
	public static void atualizaCliente(String cpf, Scanner sc) {
		ArrayList<Cliente> clientes = Session.banco.getClientes();
		
		int i = pegaIndexCliente(cpf);
		
		if(i != -123) {
			Cliente cliente = clientes.get(i);
			
			System.out.println("Cliente encontrado!");
			System.out.println("Digite suas novas informações:");
			
			cliente.setCpf(FuncGerais.pedeCPF(sc));
			cliente.setNome(FuncGerais.pedeNome(sc));
			cliente.setIdade(FuncGerais.pedeIdade(sc));
			
			Session.banco.atualizaCliente(cliente, i);
			System.out.println("Cliente atualizado com sucesso");
		}
		
		System.out.println("Cliente não encontrado.");
	}
	
	public static void comprarIngresso(Scanner sc, String cpf) {
		float valorIngresso = Session.show.getValor();
		int ingressos = Session.show.getIngressos();
		int i = pegaIndexCliente(cpf);
		Cliente cliente = Session.banco.getClientes().get(i);
		
		System.out.println("Quantidade de ingressos restantes: " + ingressos);
		System.out.print("Quantos vai querer? ");
		int quantidade = sc.nextInt();
		
		if((quantidade > ingressos) || (quantidade <= 0)) {
			System.out.println("Não é possível comprar essa quantidade.");
			return;
		}
		
		System.out.println("Valor final: R$" + (quantidade * valorIngresso));
		System.out.println("Deseja finalizar a compra?");
		int resp = Menus.menuSimNao(sc);
		
		if(resp == 1) {
			ingressos = ingressos - quantidade;
			Session.show.setIngressos(ingressos);
			cliente.setqntIngressos(quantidade);
			Session.banco.atualizaCliente(cliente, i);
			System.out.println("Compra realizada com sucesso.");
		}
		else {
			System.out.println("Que pena, até a próxima!");
		}
	}
	
	public static void tranfereIngressos(int posEnvia, int posRecebe, Scanner sc) {
		int quantidade, totalRecebe, totalEnvia;
		ArrayList<Cliente> clientes = Session.banco.getClientes();
		Cliente envia = clientes.get(posEnvia);
		Cliente recebe = clientes.get(posRecebe);
		
		System.out.println("Quantos ingressos deseja enviar? ");
		quantidade = sc.nextInt();
		sc.nextLine();
		
		if(envia.getQntIngressos() < quantidade || quantidade < 0) {
			System.out.println("Não é possível tranferir essa quantidade de ingressos.");
			return;
		}
		
		totalRecebe = recebe.getQntIngressos() + quantidade;
		totalEnvia = envia.getQntIngressos() - quantidade;
		recebe.setqntIngressos(totalRecebe);
		envia.setqntIngressos(totalEnvia);
		Session.banco.atualizaCliente(recebe, posRecebe);
		Session.banco.atualizaCliente(envia, posEnvia);
		
		System.out.println("Tranferência efetuada com sucesso!");
	}
}
