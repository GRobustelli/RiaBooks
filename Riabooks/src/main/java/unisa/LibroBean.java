package unisa;

import java.io.Serializable;


public class LibroBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String titolo;
	private String autore;
	private double prezzo;
	private String descrizione;
	private String categoria;
	private String immagine;
	/**
	 * @return the id
	 */
	
	
	
	public String getId() {
		return id;
	}
	
	public LibroBean(String id, String titolo, String autore, double prezzo, String descrizione, String categoria, String immagine) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.autore = autore;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.immagine = immagine;
	}
	
	

	/**
	 * @return the immagine
	 */
	public String getImmagine() {
		return immagine;
	}

	/**
	 * @param immagine the immagine to set
	 */
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public LibroBean() {
		 super();
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
	
	public String getTitolo() {
		return titolo;
	}
	
	/**
	 * @param nome the nome to set
	 */
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
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
