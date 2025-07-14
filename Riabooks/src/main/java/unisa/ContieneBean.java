package unisa;

import java.io.Serializable;

public class ContieneBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String libro_id;
	private Float prezzo;
	public ContieneBean(String email, String libro_id, Float prezzo) {
		super();
		this.email = email;
		this.libro_id = libro_id;
		this.prezzo = prezzo;
	}
	public ContieneBean() {
		super();
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
	public Float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}
	
	
}
