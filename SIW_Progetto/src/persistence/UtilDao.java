package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Evento;

public class UtilDao {

	
private DataSource dataSource;

public UtilDao(DataSource dataSource) {
		this.dataSource=dataSource;
	}

public void dropDatabase(){
	
	Connection connection = dataSource.getConnection();
	try {
		String delete = "drop SEQUENCE if EXISTS sequenza_id;"
				+ "drop table if exists ticket;"
				+ "drop table if exists evento;"
				+ "drop table if exists luogo;"	
				+ "drop table if exists utente;"
				;
		PreparedStatement statement = connection.prepareStatement(delete);
		
		statement.executeUpdate();
		System.out.println("Executed drop database");
		
	} catch (SQLException e) {
		
		throw new PersistenceException(e.getMessage());
	} finally {
		try {
			connection.close();
		} catch (SQLException e) {
			
			throw new PersistenceException(e.getMessage());
		}
	}
}

public void createDatabase(){
	
	Connection connection = dataSource.getConnection();
	try {
		
		String delete = "create SEQUENCE sequenza_id;"
				+ "create table utente(\"piva\" varchar(255) primary key, \"nome\" varchar(255), \"email\" varchar(255), \"password\" varchar(50));"
				+ "create table luogo(\"titolare\" varchar(255) REFERENCES utente(\"piva\"), \"nome\" varchar(255), \"codice\" varchar(255) primary key, \"provincia\" varchar(255), \"comune\" varchar(255), \"indirizzo\" varchar(255));"
				+ "create table evento(\"titolo\" varchar(255),\"descrizione\" varchar(255),\"genere\" varchar(255), \"codice\" varchar(255) primary key, \"data\" DATE, \"ora\" TIME, \"luogo\" varchar(255) REFERENCES luogo(\"codice\"));"
				+ "create table ticket(\"codice\" varchar(255),\"prezzo\" varchar(255),\"intestatario\" varchar(255), \"evento\" varchar(255) REFERENCES evento(\"codice\"));"
				;
		
		PreparedStatement statement = connection.prepareStatement(delete);
		
		statement.executeUpdate();
		System.out.println("Executed create database");
		
	} catch (SQLException e) {
		
		throw new PersistenceException(e.getMessage());
	} finally {
		try {
			connection.close();
		} catch (SQLException e) {
			
			throw new PersistenceException(e.getMessage());
		}
	}
}


public  void resetDatabase() {
		
		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM utente";
			PreparedStatement statement = connection.prepareStatement(delete);
			
			statement.executeUpdate();

			delete = "delete FROM evento";
			statement = connection.prepareStatement(delete);
			
			delete = "delete FROM luogo";
			statement = connection.prepareStatement(delete);

			delete = "delete FROM ticket";
			statement = connection.prepareStatement(delete);
						
			statement.executeUpdate();
		} catch (SQLException e) {
			
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new PersistenceException(e.getMessage());
			}
		}
	}
}
