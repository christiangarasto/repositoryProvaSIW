package persistence.dao;

import java.util.LinkedList;
import java.util.List;

import model.Luogo;
import model.Utente;

public interface UtenteDao {
	public void save(Utente utente);  // Create
	public Utente findByPrimaryKey(String pIva);     // Retrieve
	public List<Utente> findAll();       
	public void update(Utente utente); //Update
	public void delete(Utente utente); //Delete	
	public void setPassword(String piva, String password);
	public void setEmail(String piva, String email);
	public String findByCredenziali(String nome, String password);
	public boolean passwordCambiata(String piva, String passwordNuovo);
	public boolean emailCambiata(String piva, String emailNuovo);
	
	public LinkedList<Luogo> findAllLocation(String pIva);

}
