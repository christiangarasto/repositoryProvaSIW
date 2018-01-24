package persistence.dao;

import java.util.LinkedList;

import model.Evento;

public interface EventoDao {
	
	public void save(Evento utente);  // Create
	public Evento findByPrimaryKey(String codice);     // Retrieve
	public LinkedList<Evento> findAll();
	public void update(Evento evento); //Update
	public void delete(Evento evento); //Delete	

	
	/*****************************************************/
	
	public LinkedList<Evento> eventiDaMostrare(java.sql.Date dataOdierna);	// [mostra gli eventi a partire da una settimana fino al futuro]
	public LinkedList<Evento> eventiOggi(String valoreFiltro);
	public LinkedList<Evento> eventiPerData(String valoreFiltro);
	public LinkedList<Evento> eventiPerOra(String valoreFiltro);
	public LinkedList<Evento> eventiPerComune(String comune);
	public LinkedList<Evento> eventiPerProvincia(String provincia);
	public LinkedList<Evento> eventiPassatiDaUnaSettimana(java.sql.Date dataOdierna);
	public LinkedList<Evento> eventiGratuiti();
	public LinkedList<Evento> eventiAPagamento();
	public LinkedList<Evento> eventiPerGenere(String genere);
	public LinkedList<Evento> eventiPerLuogo(String luogo, boolean b);
	public LinkedList<Evento> eventiPerLuogo(String luogo);
	
	/*****************************************************/
	
}
