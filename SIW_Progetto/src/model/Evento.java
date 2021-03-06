package model;

public class Evento {
	private String codice;
	private String titolo;
	private String genere;
	private String descrizione;
	private java.sql.Date data;
	private java.sql.Time ora;

	private Luogo luogo;

	public Evento() {
	}

	public Evento(String titolo, String descrizione, String genere, java.sql.Date data, java.sql.Time ora,
			Luogo luogo) {
		super();
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.genere = genere;
		this.data = data;
		this.ora = ora;
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

	public java.sql.Date getData() {
		return data;
	}

	public void setData(java.sql.Date data) {
		this.data = data;
	}

	public java.sql.Time getOra() {
		return ora;
	}

	public void setOra(java.sql.Time ora) {
		this.ora = ora;
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

	@Override
	public String toString() {
		return "Evento: " + codice + "\n	Titolo: " + titolo + "\n	Descrizione: " + descrizione + "\n	Genere: "
				+ genere + "\n	Data: " + data + "\n	Ora: " + ora + "\n	Luogo: " + luogo.getNome();
	}

}