package model;

import java.util.LinkedList;

public class Luogo 
{
	private Utente titolare;
	private String nome;
	private String codice;
	private String provincia;
	private String comune;
	private String indirizzo;
	
	private LinkedList<Evento> eventi;
	
	public Luogo() {}

	public Luogo(Utente titolare, String nome, String provincia, String comune, String indirizzo) {
		this.titolare = titolare;
		this.nome = nome;
		this.provincia = provincia;
		this.comune = comune;
		this.indirizzo = indirizzo;
	}

//	public void create(String luogo) {		
//		Luogo l;
//		
//		int c = 0;
//		int index = 0;
//		String[] valori = new String[6];
//			for(int i = 0; i < valori.length; i++) {
//				valori[i] = "";
//			}
//			
//		for(int i = 0; i < luogo.length(); i++) {
//			if(luogo.charAt(i) == ':') {
//				index++;
//			}
//			
//			if(luogo.charAt(i) != ':')
//				valori[index] += luogo.charAt(i);		
//		}
//		
//		System.out.println(valori[0]);
//		
//		
//		//titolare = valori[0];
//		nome = valori[1];
//		codice = valori[2];
//		provincia = valori[3];
//		comune = valori[4];
//		indirizzo = valori[5];
//	}
	
	public String getParsedLuogo() {
		return titolare + ":" + nome + ":" + codice + ":" + provincia + ":" + comune + ":" + indirizzo;
	}

	@Override
	public String toString() {
		return "Luogo: " + nome + 
				"\n	Titolare: " + titolare.toString() + 
				"\n	Codice: " + codice + 
				"\n	Indirizzo: " + indirizzo + " [Provincia: " + provincia + ", Comune: " + comune + "]";
	}
	
	public Utente getTitolare() {
		return titolare;
	}

	public void setTitolare(Utente titolare) {
		this.titolare = titolare;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public LinkedList<Evento> getEventi() {
		return eventi;
	}

	public void setEventi(LinkedList<Evento> eventi) {
		this.eventi = eventi;
	}


	
	
	
}
