package memento;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import model.Model;
import model.Observer;
import model.Product;
import model.Store;


public class StoreMemento {
	private Map<String, Product> mementoProducts;
	private ArrayList<Observer> mementoCutomer;
	public StoreMemento(Store store) {
		copyMap(store);
	}

	private void copyMap(Store store) {
		
		 
		switch (store.getMyMap().getClass().getSimpleName()) {
		
		case "TreeMap":
			this.mementoProducts=new TreeMap<String, Product>();
			break;
			
		case "DescendingSubMap":
			this.mementoProducts=new TreeMap<String,Product>().descendingMap();
			break;
			
		case "LinkedHashMap":
			this.mementoProducts= new LinkedHashMap<String, Product>();
			break;
			
		default:
			break;
		}
		
		mementoProducts.putAll(store.getMyMap());
		mementoCutomer = new ArrayList<Observer>(store.getCutomer());
	}

	public Map<String, Product> getAllProducts() {
		return mementoProducts;
	}

	public ArrayList<Observer> getALLCustomer() {

		return mementoCutomer;
	}
	
	
	
	

}
