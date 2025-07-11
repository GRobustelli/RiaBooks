package unisa;

import java.io.Serializable;

public class RecensioneBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String testo;
	private int valutazione;
	private String email;
	private String libro_id;
	
	
	public RecensioneBean(int id, String testo, int valutazione, String email, String libro_id) {
		super();
		this.id = id;
		this.testo = testo;
		this.valutazione = valutazione;
		this.email = email;
		this.libro_id = libro_id;
	}
	public RecensioneBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the testo
	 */
	public String getTesto() {
		return testo;
	}
	/**
	 * @param testo the testo to set
	 */
	public void setTesto(String testo) {
		this.testo = testo;
	}
	/**
	 * @return the valutazione
	 */
	public int getValutazione() {
		return valutazione;
	}
	/**
	 * @param valutazione the valutazione to set
	 */
	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	
	
}
