package model;

public class Ticket 
{
	private String codice;
	private String prezzo;
	private String intestatario;
	
	private Evento evento;

	public Ticket() {}
	
	public Ticket(String prezzo, String intestatario, Evento evento) {
		this.prezzo = prezzo;
		this.intestatario = intestatario;
		this.evento = evento;
	}
	
	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public String toString(){
		return  " codice : " + this.codice +
				", prezzo : " + this.prezzo +
				", intestatario : " + this.intestatario + 
				", evento : " + evento.getCodice();
	}
	
}
