package modelos;

public class Artista extends Pessoa {
	private float cache;
	private int horaInicio;
	private int minutoInicio;
	private int horaFim;
	private int minutoFim;
	
	public float getCache() {
		return cache;
	}
	public void setCache(float cache) {
		this.cache = cache;
	}
	public int getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}
	public int getMinutoInicio() {
		return minutoInicio;
	}
	public void setMinutoInicio(int minutoInicio) {
		this.minutoInicio = minutoInicio;
	}
	public int getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(int horaFim) {
		this.horaFim = horaFim;
	}
	public int getMinutoFim() {
		return minutoFim;
	}
	public void setMinutoFim(int minutoFim) {
		this.minutoFim = minutoFim;
	}
}
