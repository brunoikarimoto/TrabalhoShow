package menus;

import java.util.Scanner;
import funcoes.*;
import modelos.*;


public class Menus {
	public static void menuPrincipal(Scanner sc) {
		int user;
		
		do {
			System.out.println("1 - Cliente");
			System.out.println("2 - Gerenciar");
			System.out.println("3 - Informações do show");
			System.out.println("4 - Sair");
			
			user = sc.nextInt();
			sc.nextLine();
			
			switch(user) {
				case 1:
					menuClientes(sc);
					break;
				case 2:
					System.out.println("Digite a senha:");
					String senha = sc.nextLine();
					int retorno = FuncGerais.valida(senha);
					if(retorno == 1) {
						menuGerencia(sc);
					}
					else {
						System.out.println("Senha incorreta.");
					}
					break;
				case 3:
					FuncGerenciaShow.mostraInfoShow(sc);
					break;
				case 4:
					break;
				default:
					System.out.println("Opção inválida.");
			}
		}while(user != 4);
	}
	
	public static void menuGerencia(Scanner sc) {
		int user;
		
		do {
			System.out.println("1 - Gerenciar funcionários");
			System.out.println("2 - Gerenciar o show");
			System.out.println("3 - Sair");
			
			user = sc.nextInt();
			sc.nextLine();
			
			switch(user) {
				case 1:
					menuGerenciaFuncionario(sc);
					break;
				case 2:
					menuGerenciaShow(sc);
					break;
				case 3:
					break;
				default:
					System.out.println("Opção inválida.");
			}
		}while(user != 3);
	}
	
	public static void menuGerenciaShow(Scanner sc) {
		int user;
		
		do {
			System.out.println("1 - Adicionar artista");
			System.out.println("2 - Procurar artista");
			System.out.println("3 - Deletar artista");
			System.out.println("4 - Atualizar artista");
			System.out.println("5 - Mudar informações do show");
			System.out.println("6 - Sair");
			
			user = sc.nextInt();
			sc.nextLine();
			
			switch(user) {
				case 1:
					FuncGerenciaShow.cadastrarArtista(sc);
					break;
				case 2:
					String cpfProcura = FuncGerais.pedeCPF(sc);
					Artista artista = FuncGerenciaShow.procuraArtista(cpfProcura);
					if(artista != null) {
						FuncGerenciaShow.printaArtista(artista);
					}
					else {
						System.out.println("Artista não encontrado.");
					}
					break;
				case 3:
					String cpfDeleta = FuncGerais.pedeCPF(sc);
					FuncGerenciaShow.deletaArtista(cpfDeleta);
					break;
				case 4:
					String cpfAtualiza = FuncGerais.pedeCPF(sc);
					FuncGerenciaShow.atualizaArtista(cpfAtualiza, sc);
					break;
				case 5:
					menuAlterarShow(sc);
					break;
				case 6:
					break;
				default:
					System.out.println("Opção inválida.");
			}
		}while(user != 6);
	}
	
	public static void menuGerenciaFuncionario(Scanner sc) {
		int user;
		
		do {
			System.out.println("1 - Cadastrar funcionário");
			System.out.println("2 - Deletar funcionário");
			System.out.println("3 - Buscar funcionário");
			System.out.println("4 - Lista de todos funcionários por função");
			System.out.println("5 - Atualizar funcionário");
			System.out.println("6 - Sair");
			
			user = sc.nextInt();
			sc.nextLine();
			
			switch(user) {
				case 1:
					FuncGerenciaFuncionario.cadastroFuncionario(sc);
					break;
				case 2:
					String cpfDeleta = FuncGerais.pedeCPF(sc);
					FuncGerenciaFuncionario.deletaFuncionario(cpfDeleta);;
					break;
				case 3:
					String cpfProcura = FuncGerais.pedeCPF(sc);
					Funcionario funcionario = FuncGerenciaFuncionario.procuraFuncionario(cpfProcura);
					if(funcionario != null) {
						FuncGerenciaFuncionario.printaFuncionario(funcionario);
					}
					else {
						System.out.println("Funcionário não encontrado.");
					}
					break;
				case 4:
					String posicao = Menus.menuFuncaoFuncionario(sc);
					FuncGerenciaFuncionario.printaTodosFuncionariosFuncao(posicao);
					break;
				case 5:
					String cpfAtualiza = FuncGerais.pedeCPF(sc);
					FuncGerenciaFuncionario.atualizaFuncionario(cpfAtualiza, sc);
					break;
				case 6:
					break;
				default:
					System.out.println("Opção inválida.");
			}
		}while(user != 6);
	}
	
	public static void menuClientes(Scanner sc) {
		int user;
		
		do {
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Comprar ingresso");
			System.out.println("3 - Transferir ingresso");
			System.out.println("4 - Cancelar conta");
			System.out.println("5 - Procurar cliente");
			System.out.println("6 - Atualizar cadastro");
			System.out.println("7 - Sair");
			
			user = sc.nextInt();
			sc.nextLine();
			
			switch(user) {
				case 1:
					FuncClientes.cadastroCliente(sc);
					break;
				case 2:
					String cpfCompra = FuncGerais.pedeCPF(sc);
					if(FuncClientes.procuraCliente(cpfCompra) == null) {
						System.out.println("Cliente sem cadastro.");
						System.out.println("Deseja realizar o cadastro?");
						int resp = menuSimNao(sc);
						if(resp == 1) {
							FuncClientes.cadastroCliente(sc, cpfCompra);
						}
						else {
							System.out.println("Até a próxima!");
							break;
						}
					}
					FuncClientes.comprarIngresso(sc, cpfCompra);
					break;
				case 3:
					System.out.println("Seu CPF");
					String envia = FuncGerais.pedeCPF(sc);
					int iEnvia = FuncClientes.pegaIndexCliente(envia);
					if(iEnvia == -123) {
						System.out.println("Cliente não encontrado.");
						break;
					}
					System.out.println("CPF de quem deseja enviar");
					String recebe = FuncGerais.pedeCPF(sc);
					int iRecebe = FuncClientes.pegaIndexCliente(recebe);
					if(iRecebe == -123) {
						System.out.println("Cliente não encontrado.");
						break;
					}
					FuncClientes.tranfereIngressos(iEnvia, iRecebe, sc);
					break;
				case 4:
					String cpfDeleta = FuncGerais.pedeCPF(sc);
					FuncClientes.deletaCliente(cpfDeleta);
					break;
				case 5:
					String cpfProcura = FuncGerais.pedeCPF(sc);
					Cliente cliente = FuncClientes.procuraCliente(cpfProcura);
					if(cliente != null) {
						FuncClientes.printaCliente(cliente);
					}
					else {
						System.out.println("Cliente não encontrado.");
					}
					break;
				case 6:
					String cpfAtualiza = FuncGerais.pedeCPF(sc);
					FuncClientes.atualizaCliente(cpfAtualiza, sc);
					break;
				case 7:
					break;
				default:
					System.out.println("Opção inválida.");
			}
		}while(user != 7);
	}
	
	public static int menuSimNao(Scanner sc) {
		int user;
		
		do {
			System.out.println("1 - Sim");
			System.out.println("2 - Não");
			
			user = sc.nextInt();
			sc.nextLine();
		}while(user != 1 && user != 2);
		
		return user;
	}
	
	public static String menuFuncaoFuncionario(Scanner sc) {
		int user;
		String retorno = null;
		
		do {
			System.out.println("Função:");
			System.out.println("1 - Bar");
			System.out.println("2 - Faxineiro(a)");
			System.out.println("3 - Segurança");
			System.out.println("4 - Garçom");
			
			user = sc.nextInt();
			sc.nextLine();
			
			switch(user) {
				case 1:
					retorno = "Bar";
					break;
				case 2:
					retorno = "Faxineiro(a)";
					break;
				case 3:
					retorno = "Segurança";
					break;
				case 4:
					retorno = "Garçom";
					break;
				default:
					System.out.println("Opção inválida.");
			}
		}while(user < 1 || user > 4);
		
		return retorno;
	}
	
	//dia nome valor
	public static void menuAlterarShow(Scanner sc) {
		int user;
		
		do {
			System.out.println("1 - Data");
			System.out.println("2 - Nome do show");
			System.out.println("3 - Valor do ingresso");
			System.out.println("4 - Sair");
			
			user = sc.nextInt();
			sc.nextLine();
			
			switch(user) {
				case 1:
					FuncGerenciaShow.alteraDataShow(sc);
					break;
				case 2:
					FuncGerenciaShow.alteraNomeShow(sc);
					break;
				case 3:
					FuncGerenciaShow.alteraValorShow(sc);
					break;
				case 4:
					break;
				default:
					System.out.println("Opção inválida.");
			}
		}while(user != 4);
	}
}
