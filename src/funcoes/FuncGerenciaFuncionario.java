package funcoes;

import java.util.ArrayList;
import java.util.Scanner;

import menus.Menus;
import modelos.Funcionario;
import util.Session;

public class FuncGerenciaFuncionario {
	public static Funcionario procuraFuncionario(String cpf) {
		Funcionario retorno = null;
		ArrayList<Funcionario> funcionarios = Session.banco.getFuncionarios();
		
		for(Funcionario funcionario : funcionarios) {
			if(funcionario.getCpf().equals(cpf)) {
				retorno = funcionario;
				break;
			}
		}
		return retorno;
	}
	
	public static int pegaIndexFuncionario(String cpf) {
		int retorno = -123;
		ArrayList<Funcionario> funcionarios = Session.banco.getFuncionarios();
		
		for(int i = 0; i < funcionarios.size(); i++) {
			Funcionario funcionario = funcionarios.get(i);
			if(funcionario.getCpf().equals(cpf)) {
				retorno = i;
				break;
			}
		}
		return retorno;
	}
	
	public static void cadastroFuncionario(Scanner sc) {
		Funcionario funcionario = new Funcionario();
		
		String cpf = FuncGerais.pedeCPF(sc);
		
		int result = pegaIndexFuncionario(cpf);
		
		if(result != -123) {
			System.out.println("CPF já cadastrado.");
			return;
		}
		
		funcionario.setCpf(cpf);
		funcionario.setNome(FuncGerais.pedeNome(sc));
		funcionario.setIdade(FuncGerais.pedeIdade(sc));
		System.out.print("Digite o salário");
		funcionario.setSalario(FuncGerais.pegaValores(sc));
		funcionario.setPosicao(Menus.menuFuncaoFuncionario(sc));
		
		System.out.println("Funcionário cadastrado com sucesso.");
		Session.banco.adicionaFuncionario(funcionario);
	}
	
	public static void printaTodosFuncionariosFuncao(String funcao) {
		ArrayList<Funcionario> funcionarios = Session.banco.getFuncionarios();
		
		for(Funcionario funcionario : funcionarios) {
			if(funcionario.getPosicao().equals(funcao)) {
				printaFuncionario(funcionario);
				FuncGerais.separador();
			}
		}
	}
	
	public static void printaFuncionario(Funcionario funcionario) {
		System.out.println("Nome: " + funcionario.getNome());
		System.out.println("CPF: " + funcionario.getCpf());
		System.out.println("Idade: " + funcionario.getIdade());
		System.out.println("Salário: R$" + funcionario.getSalario());
		System.out.println("Função: " + funcionario.getPosicao());
	}
	
	public static void deletaFuncionario(String cpf) {
		Funcionario funcionario = procuraFuncionario(cpf);
		
		if(funcionario != null) {
			Session.banco.deletarFuncionario(funcionario);
			System.out.println("Funcionário deletado.");
		}
		else {
			System.out.println("Funcionário não encontrado.");
		}
	}
	
	public static void atualizaFuncionario(String cpf, Scanner sc) {
		ArrayList<Funcionario> funcionarios = Session.banco.getFuncionarios();
		
		int i = pegaIndexFuncionario(cpf);
		
		if(i != -123) {
			Funcionario funcionario = funcionarios.get(i);
			
			System.out.println("Funcionário encontrado!");
			System.out.println("Digite as novas informações:");
			
			funcionario.setNome(FuncGerais.pedeNome(sc));
			funcionario.setIdade(FuncGerais.pedeIdade(sc));
			System.out.print("Digite o salário");
			funcionario.setSalario(FuncGerais.pegaValores(sc));
			funcionario.setPosicao(Menus.menuFuncaoFuncionario(sc));
			
			Session.banco.atualizaFuncionario(funcionario, i);
			System.out.println("Funcionário atualizado com sucesso.");
		}
		else {
			System.out.println("Funcionário não encontrado.");
		}
	}
}
