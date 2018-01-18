package model;

import java.sql.Date;
import java.util.LinkedList;

public class Evento 
{
	private String codice;
	private String titolo;
	private String genere;
	private String descrizione;
	private Date data;

	private Luogo luogo;
	private LinkedList<Ticket> tickets;
	
	public Evento() {}

	public Evento(String titolo, String descrizione, String genere, Date data1, Luogo luogo) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.genere = genere;
		this.data = data1;
		this.luogo = luogo;
	}
	
	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Luogo getLuogo() {
		return luogo;
	}

	public void setLuogo(Luogo luogo) {
		this.luogo = luogo;
	}
	
	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public LinkedList<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(LinkedList<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	@Override
	public String toString() {
		return "Evento: " + codice +
				"\n	Titolo: " + titolo +
				"\n	Descrizione: " + descrizione +
				"\n	Genere: " + genere +
				"\n	Data: " + data +
				"\n	Luogo: " + luogo.getNome();
	}

}