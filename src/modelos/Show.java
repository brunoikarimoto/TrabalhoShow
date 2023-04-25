package modelos;

import java.util.ArrayList;

import util.Session;

public class Show {
	private int ingressos;
	private ArrayList<Artista> artistas;
	private int dia;
	private int mes;
	private int ano;
	private String nome;
	private float valor;
	
	public Show() {
		ingressos = 5000;
		valor = 15;
		nome = "Show do Momento";
		dia = 19;
		mes = 11;
		ano = 2023;
		artistas = Session.banco.getArtistas();
	}
	
	public int getIngressos() {
		return ingressos;
	}
	public void setIngressos(int ingressos) {
		this.ingressos = ingressos;
	}
	public ArrayList<Artista> getArtistas() {
		return artistas;
	}
	public void setArtistas(ArrayList<Artista> artistas) {
		this.artistas = artistas;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
}
