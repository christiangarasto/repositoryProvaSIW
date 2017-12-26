package model;

import java.util.ArrayList;

public class Utente {

	private String pIva;
	private String nome;
	private ArrayList<Luogo> luoghi;
	
	public Utente() {}
	
	public Utente(String pIva, String nome) {
		this.pIva = pIva;
		this.nome = nome;
	}
	
	public String getpIva() {
		return pIva;
	}
	public void setpIva(String pIva) {
		this.pIva = pIva;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ArrayList<Luogo> getLuoghi() {
		return luoghi;
	}
	public void setLuoghi(ArrayList<Luogo> luoghi) {
		this.luoghi = luoghi;
	}
	
	
	
}
