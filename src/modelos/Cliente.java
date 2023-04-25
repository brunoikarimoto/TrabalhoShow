package modelos;

public class Cliente extends Pessoa {
	private Comanda comanda;
	private int qntIngressos;

	public Comanda getComanda() {
		return comanda;
	}
	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}
	public int getQntIngressos() {
		return qntIngressos;
	}
	public void setqntIngressos(int qntIngressos) {
		this.qntIngressos = qntIngressos;
	}
}
