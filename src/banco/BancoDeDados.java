package banco;

import java.util.ArrayList;
import modelos.*;
import util.Session;

public class BancoDeDados {	
	private ArrayList<Cliente> clientes;
	private ArrayList<Artista> artistas;
	private ArrayList<Funcionario> funcionarios;
	private String senha;
	
	public BancoDeDados() {
		clientes = new ArrayList<Cliente>();
		artistas = new ArrayList<Artista>();
		funcionarios = new ArrayList<Funcionario>();
		senha = "coxinha123";
	}
	
	public void adicionarCliente(Cliente cliente) {
		clientes.add(cliente);
	}
	public void deletarCliente(Cliente cliente) {
		clientes.remove(cliente);
	}
	public void atualizaCliente(Cliente cliente, int pos) {
		clientes.set(pos, cliente);
	}
	public void adicionarArtista(Artista artista) {
		artistas.add(artista);
		Session.show.setArtistas(artistas);
	}
	public void deletarArtista(Artista artista) {
		artistas.remove(artista);
		Session.show.setArtistas(artistas);
	}
	public void atualizaArtista(Artista artista, int pos) {
		artistas.set(pos, artista);
		Session.show.setArtistas(artistas);
	}
	public void adicionaFuncionario(Funcionario funcionario) {
		funcionarios.add(funcionario);
	}
	public void deletarFuncionario(Funcionario funcionario) {
		funcionarios.remove(funcionario);
	}
	public void atualizaFuncionario(Funcionario funcionario, int pos) {
		funcionarios.set(pos, funcionario);
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	public ArrayList<Artista> getArtistas() {
		return artistas;
	}
	public void setArtistas(ArrayList<Artista> artistas) {
		this.artistas = artistas;
	}
	public ArrayList<Funcionario> getFuncionarios(){
		return funcionarios;
	}
	public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public String getSenha() {
		return senha;
	}
}
