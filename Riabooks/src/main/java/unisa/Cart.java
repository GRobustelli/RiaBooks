package unisa;

import java.util.ArrayList;
import java.util.Iterator;
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
		
		 Iterator<LibroBean> it = carrello.iterator();
		    while (it.hasNext()) {
		        LibroBean find = it.next();
		        if (find.getId().equals(libro.getId())) {
		            it.remove(); 
		            break; 
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
		List<LibroBean> libri = new ArrayList<LibroBean>();
		Iterator<LibroBean> it = carrello.iterator();
		while (it.hasNext()) {
			LibroBean libro = it.next();
			if (libro.isMostra()) {
				libri.add(libro);
			}
		}
		return libri;
	}
	
	public boolean isEmpty() {
		return carrello.isEmpty();
	}
	}
