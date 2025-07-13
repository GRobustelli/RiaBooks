package unisa;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private List<LibroBean> carrello;
	
	public Cart() {
		carrello = new ArrayList<LibroBean>();
	}
	
	public void addLibro(LibroBean libro) {
		carrello.add(libro);
	}
	
	public void deleteLibro(LibroBean libro) {
		for (LibroBean find : carrello) {
			if (find.getId() == libro.getId())
			{
				carrello.remove(find);
			}
		}
	}
	
	public void deleteAll() {
		carrello.removeAll(carrello);
		if (carrello.isEmpty()) {
			System.out.println("E' vuoto");
		}
	}
	
	public List<LibroBean> getLibri(){
		return carrello;
	}
	
	public boolean isEmpty() {
		return carrello.isEmpty();
	}
	}
