package model;

import java.util.ArrayList;

public class Utente {

	private String pIva;
	private String nome;
	private String password;
	private ArrayList<Luogo> luoghi;

	public Utente() {
	}

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

	public String getParsedUtente() {
		return pIva + ":" + nome;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + "\nPIva: " + pIva;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}