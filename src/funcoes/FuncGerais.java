package funcoes;

import java.util.Scanner;

import util.Session;

public class FuncGerais {
	public static String pedeCPF(Scanner sc) {
		String cpf;
		
		System.out.print("Digite cpf: ");
		cpf = sc.nextLine();
		
		return cpf;
	}
	
	public static String pedeNome(Scanner sc) {
		String nome;
		
		System.out.print("Nome: ");
		nome = sc.nextLine();
		nome = nome.substring(0,1).toUpperCase() + nome.substring(1).toLowerCase();
		
		return nome;
	}
	
	public static int pedeIdade(Scanner sc) {
		int idade;
		
		System.out.print("Idade: ");
		idade = sc.nextInt();
		
		return idade;
	}
	
	public static int pedeIngressos(Scanner sc) {
		int ingressos;
		
		System.out.println("Quantidade: ");
		ingressos = sc.nextInt();
		
		return ingressos;
	}
	
	public static void separador() {
		System.out.println("---------------------------------");
	}
	
	public static float pegaValores(Scanner sc) {
		float valor;
		
		System.out.print(": ");
		valor = sc.nextFloat();
		
		return valor;
	}
	
	public static int pegaHora(Scanner sc) {
		int hora;
		
		do {
			System.out.print("Hora: ");
			hora = sc.nextInt();
		}while(hora < 0 || hora > 23);
		
		return hora;
	}
	
	public static int pegaMinuto(Scanner sc) {
		int minuto;
		
		do {
			System.out.print("Minuto: ");
			minuto = sc.nextInt();
		}while(minuto < 0 || minuto > 59);
		
		return minuto;
	}
	
	public static void printaHoraArtista(int hora, int minuto) {
		String horaString;
		String minutoString;
		
		if(hora == 0) {
			horaString = "00";
		}
		else {
			horaString = Integer.toString(hora);
		}
		if(minuto == 0) {
			minutoString = "00";
		}
		else {
			minutoString = Integer.toString(minuto);
		}
		System.out.print(": " + horaString + ":" + minutoString);
		System.out.println("");
	}
	
	public static void printaDataShow(int dia, int mes, int ano) {
		System.out.print(dia + "/" + mes + "/" + ano);
		System.out.println("");
	}
	
	public static int pegaDia(Scanner sc, int mes) {
		int dia;
		int max;
		
		if(mes == 2) {
			max = 28;
		}
		else if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			max = 31;
		}
		else {
			max = 30;
		}
		
		do {
			System.out.println("Dia: ");
			dia = sc.nextInt();
		}while(dia > max || dia < 0);
		
		return dia;
	}
	
	public static int pegaMes(Scanner sc) {
		int mes;
		
		do {
			System.out.println("Mês: ");
			mes = sc.nextInt();
		}while(mes < 0 || mes > 12);
		
		return mes;
	}
	
	public static int pegaAno(Scanner sc) {
		int ano;
		int min = 2023;
		
		do {
			System.out.println("Ano: ");
			ano = sc.nextInt();
		}while(ano < min || ano > (min + 5));
		
		return ano;
	}
	
	public static int valida(String senha) {
		int retorno = 0;
		
		if(senha.equals(Session.banco.getSenha())) {
			retorno = 1;
		}
		
		return retorno;
	}
}
