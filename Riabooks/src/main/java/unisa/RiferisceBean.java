package unisa;

import java.io.Serializable;

public class RiferisceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int ordine_id;
	private String libro_id;
	private int quantita;
	private float prezzo;
	
	public RiferisceBean(int ordine_id, String libro_id, int quantita, float prezzo) {
		super();
		this.ordine_id = ordine_id;
		this.libro_id = libro_id;
		this.quantita = quantita;
		this.prezzo = prezzo;
	}
	public RiferisceBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the ordine_id
	 */
	public int getOrdine_id() {
		return ordine_id;
	}
	/**
	 * @param ordine_id the ordine_id to set
	 */
	public void setOrdine_id(int ordine_id) {
		this.ordine_id = ordine_id;
	}
	/**
	 * @return the libro_id
	 */
	public String getLibro_id() {
		return libro_id;
	}
	/**
	 * @param libro_id the libro_id to set
	 */
	public void setLibro_id(String libro_id) {
		this.libro_id = libro_id;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	/**
	 * @return the prezzo
	 */
	public float getPrezzo() {
		return prezzo;
	}
	/**
	 * @param prezzo the prezzo to set
	 */
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	
	
	
}
