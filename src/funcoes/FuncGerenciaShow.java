package funcoes;

import java.util.ArrayList;
import java.util.Scanner;

import modelos.Artista;
import util.Session;

public class FuncGerenciaShow {
	public static Artista procuraArtista(String cpf) {
		Artista retorno = null;
		ArrayList<Artista> artistas = Session.banco.getArtistas();
		
		for(Artista artista : artistas) {
			if(artista.getCpf().equals(cpf)) {
				retorno = artista;
				break;
			}
		}
		return retorno;
	}
	
	public static int pegaIndexArtista(String cpf) {
		int retorno = -123;
		ArrayList<Artista> artistas = Session.banco.getArtistas();
		
		for(int i = 0; i < artistas.size(); i++) {
			Artista artista = artistas.get(i);
			if(artista.getCpf().equals(cpf)) {
				retorno = i;
				break;
			}
		}
		return retorno;
	}
	
	public static void printaArtista(Artista artista) {
		System.out.println("Nome: " + artista.getNome());
		System.out.println("CPF: " + artista.getCpf());
		System.out.println("Idade: " + artista.getIdade());
		System.out.println("Cache: R$" + artista.getCache());
		System.out.print("Horário de início");
		FuncGerais.printaHoraArtista(artista.getHoraInicio(), artista.getMinutoInicio());
		System.out.print("Horário de fim");
		FuncGerais.printaHoraArtista(artista.getHoraFim(), artista.getMinutoFim());
	}
	
	public static void printaShowArtista(Artista artista) {
		FuncGerais.separador();
		System.out.println("Nome: " + artista.getNome());
		System.out.print("Horário de início");
		FuncGerais.printaHoraArtista(artista.getHoraInicio(), artista.getMinutoInicio());
		System.out.print("Horário de fim");
		FuncGerais.printaHoraArtista(artista.getHoraFim(), artista.getMinutoFim());
		FuncGerais.separador();
	}
	
	public static void cadastrarArtista(Scanner sc) {
		Artista artista = new Artista();
		
		String cpf = FuncGerais.pedeCPF(sc);
		int result = pegaIndexArtista(cpf);
		
		if(result != -123) {
			System.out.println("CPF já cadastrado.");
			return;
		}
		
		artista.setCpf(cpf);
		artista.setNome(FuncGerais.pedeNome(sc));
		artista.setIdade(FuncGerais.pedeIdade(sc));
		System.out.print("Digite o cache");
		artista.setCache(FuncGerais.pegaValores(sc));
		System.out.println("Horário início:");
		artista.setHoraInicio(FuncGerais.pegaHora(sc));
		artista.setMinutoInicio(FuncGerais.pegaMinuto(sc));
		System.out.println("Horário fim:");
		int horaFim;
		int valido;
		do {
			int horaFimAux;
			valido = 1;
			horaFim = FuncGerais.pegaHora(sc);
			if(horaFim < artista.getHoraInicio()) {
				horaFimAux = horaFim + 24;
				
				if((horaFimAux - artista.getHoraInicio()) > 6) {
					valido = 0;
				}
			}
		}while(valido != 1);
		artista.setHoraFim(horaFim);
		int minutoFim;
		do {
			valido = 1;
			minutoFim = FuncGerais.pegaMinuto(sc);
			
			if(artista.getHoraFim() == artista.getHoraInicio() && artista.getMinutoInicio() > minutoFim) {
				valido = 0;
			}
		}while(valido != 1);
		artista.setMinutoFim(minutoFim);
		
		Session.banco.adicionarArtista(artista);
		System.out.println("Artista adicionado com sucesso.");
	}
	
	public static void deletaArtista(String cpf) {
		Artista artista = procuraArtista(cpf);
		
		if(artista != null) {
			Session.banco.deletarArtista(artista);
			System.out.println("Artista deletado com sucesso.");
		}
		else {
			System.out.println("Artista não encontrado.");
		}
	}
	
	public static void atualizaArtista(String cpf, Scanner sc) {
		ArrayList<Artista> artistas = Session.banco.getArtistas();
		
		int i = pegaIndexArtista(cpf);
		
		if(i != -123) {
			Artista artista = artistas.get(i);
			
			System.out.println("Artista encontrado!");
			System.out.println("Digite as novas informações:");
			
			artista.setNome(FuncGerais.pedeNome(sc));
			artista.setIdade(FuncGerais.pedeIdade(sc));
			System.out.print("Digite o cache");
			artista.setCache(FuncGerais.pegaValores(sc));
			System.out.println("Horário início:");
			artista.setHoraInicio(FuncGerais.pegaHora(sc));
			artista.setMinutoInicio(FuncGerais.pegaMinuto(sc));
			System.out.println("Horário fim:");
			int horaFim;
			int valido;
			do {
				int horaFimAux;
				valido = 1;
				horaFim = FuncGerais.pegaHora(sc);
				if(horaFim < artista.getHoraInicio()) {
					horaFimAux = horaFim + 24;
					
					if((horaFimAux - artista.getHoraInicio()) > 6) {
						valido = 0;
					}
				}
			}while(valido != 1);
			artista.setHoraFim(horaFim);
			int minutoFim;
			do {
				valido = 1;
				minutoFim = FuncGerais.pegaMinuto(sc);
				
				if(artista.getHoraFim() == artista.getHoraInicio() && artista.getMinutoInicio() > minutoFim) {
					valido = 0;
				}
			}while(valido != 1);
			artista.setMinutoFim(minutoFim);
			
			Session.banco.atualizaArtista(artista, i);
			System.out.println("Artista atualizado com sucesso.");
		}
		else {
			System.out.println("Artista não encontrado.");
		}
	}
	
	public static void alteraNomeShow(Scanner sc) {
		System.out.println("Nome antigo: " + Session.show.getNome());
		System.out.println("Digite o novo:");
		String nome = FuncGerais.pedeNome(sc);
		
		Session.show.setNome(nome);
		System.out.println("Nome alterado com sucesso.");
	}
	
	public static void alteraValorShow(Scanner sc) {
		System.out.println("Valor antigo: " + Session.show.getValor());
		System.out.print("Digite novo valor");
		float valor = FuncGerais.pegaValores(sc);
		
		Session.show.setValor(valor);
		System.out.println("Valor alterado com sucesso.");
	}
	
	public static void alteraDataShow(Scanner sc) {
		System.out.print("Data antiga: ");
		FuncGerais.printaDataShow(Session.show.getDia(), Session.show.getMes(), Session.show.getAno());
		
		System.out.println("Digite a nova data:");
		Session.show.setMes(FuncGerais.pegaMes(sc));
		Session.show.setDia(FuncGerais.pegaDia(sc, Session.show.getMes()));
		Session.show.setAno(FuncGerais.pegaAno(sc));
		
		System.out.println("Data atualizada com sucesso.");
	}
	
	public static void mostraInfoShow(Scanner sc) {
		ArrayList<Artista> artistas = Session.show.getArtistas();
		
		System.out.println(Session.show.getNome());
		System.out.println("Valor do ingresso: R$" + Session.show.getValor());
		System.out.println("Ingressos restantes: " + Session.show.getIngressos());
		System.out.print("Data: ");
		FuncGerais.printaDataShow(Session.show.getDia(), Session.show.getMes(), Session.show.getAno());
		System.out.println("Artistas: ");
		
		for(Artista artista : artistas) {
			printaShowArtista(artista);
		}
		
		System.out.println("Digite qualquer coisa para voltar.");
		sc.nextLine();
		
		return;
	}
}
