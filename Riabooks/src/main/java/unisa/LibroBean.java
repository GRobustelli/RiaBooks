package unisa;

import java.awt.Image;
import java.io.Serializable;
import java.sql.Blob;

public class LibroBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private String autore;
	private double prezzo;
	private String descrizione;
	private String categoria;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the nome
	 */
	
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome the nome to set
	 */
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return the autore
	 */
	
	public String getAutore() {
		return autore;
	}
	
	/**
	 * @param autore the autore to set
	 */
	
	public void setAutore(String autore) {
		this.autore = autore;
	}
	
	/**
	 * @return the prezzo
	 */
	
	public double getPrezzo() {
		return prezzo;
	}
	
	/**
	 * @param prezzo the prezzo to set
	 */
	
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	/**
	 * @return the descrizione
	 */
	
	public String getDescrizione() {
		return descrizione;
	}
	
	/**
	 * @param descrizione the descrizione to set
	 */
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/**
	 * @return the categoria
	 */
	
	public String getCategoria() {
		return categoria;
	}
	
	/**
	 * @param categoria the categoria to set
	 */
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	

}
