package model;

import java.sql.Date;

public class Evento {

	private String descrizione;
	private String codice;
	private Date data;
	private Luogo luogo;
	
	public Evento() {}

	public Evento(String descrizione, Date data1, Luogo luogo) {
		super();
		this.descrizione = descrizione;
		this.data = data1;
		this.luogo = luogo;
	}
	
	@Override
	public String toString() {
		return "Evento: " + codice +
				"\n	Descrizione: " + descrizione + 
				"\n	Data: " + data +
				"\n	Luogo: " + luogo.getNome();
		
	}



	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
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
	
}