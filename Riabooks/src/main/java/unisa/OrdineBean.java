package unisa;

import java.io.Serializable;
import java.sql.Date;

public class OrdineBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private double importo;
	private String metodo;
	private String email;
	private Date data;
	public OrdineBean(int id, double importo, String metodo, String email, Date data) {
		super();
		this.id = id;
		this.importo = importo;
		this.metodo = metodo;
		this.email = email;
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

	public OrdineBean() {
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
	 * @return the importo
	 */
	public double getImporto() {
		return importo;
	}

	/**
	 * @param importo the importo to set
	 */
	public void setImporto(double importo) {
		this.importo = importo;
	}

	/**
	 * @return the metodo
	 */
	public String getMetodo() {
		return metodo;
	}

	/**
	 * @param metodo the metodo to set
	 */
	public void setMetodo(String metodo) {
		this.metodo = metodo;
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
	
	
	
}
