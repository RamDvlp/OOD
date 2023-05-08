package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import memento.StoreMemento;

import java.util.Map.Entry;

//singelton
public class Store {
	private static final Store Instance = new Store();	
	private ArrayList<Observer> customers;
	private ArrayList<Customer> responded;
	private Map<String,Product> myMap; // the string is the MKT -> serialNum
	private RandomAccessFile raf;
	
	public static Store Instance() {
		return Instance;
	}
	
	private Store() {
		customers = new ArrayList<Observer>();
		getRaf();
		responded  = new ArrayList<Customer>();
	}
	
	public RandomAccessFile getRaf() {
		try {
			raf = new RandomAccessFile("products.txt", "rw");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return raf;
	}

	public void setMap(String value) { 
		if(value.equals("Lexicographic Natural order")) {
			myMap = new TreeMap<String,Product>(); // Save the products on a map in ascending order.
		}
		else if(value.equals("Lexicographic Descending order")) {
			myMap = new TreeMap<String,Product>().descendingMap();//Save the products on a map in descending order
		}
		else {
			myMap = new LinkedHashMap<String,Product>(); //Save the products on a map according to the order of input of the products.
		}
		
	}

	
	public Map<String, Product> getMyMap() {
		return myMap;
	}

	public void addProduct(String key, Product product) {
		myMap.put(key, product);
		if(product.getCustomer()!=null)
		attach(product.getCustomer());
	}


	
	public String printProduct(String key) {
		return "Product's with SKU: " + key + " : " + myMap.get(key).toString();
	}


	
	public void printAllProducts() {
		Iterator<Map.Entry<String, Product>> it = myMap.entrySet().iterator();
		System.out.println("All products: ");
		while(it.hasNext()) {
			printProduct(it.next().getKey());
		}	
	}

	public String getAllProduct() {
		int i = 1;
		StringBuffer sb = new StringBuffer("All Products: \n");
		Iterator<Map.Entry<String, Product>> iter = myMap.entrySet().iterator();
		if(iter.hasNext()) {
		while(iter.hasNext()) {
			sb.append(i + ")" + printProduct(iter.next().getKey()));
			i++;
		}
		
		return sb.toString();
		} 
		
		return " No Products Avaliable";
		
	}
	public int saveToFile() {
		try {
			getRaf();
			raf.setLength(0); // start with a blank file
			//raf.writeUTF(store.geteSortType().name());
			Set<Entry<String, Product>> entrySet = getMyMap().entrySet();
			for (Entry<String, Product> e : entrySet) {

				int idSize = e.getKey().length();
				int pNameSize = e.getValue().getName().length();

				int cNameSize = e.getValue().getCustomer().getName().length();
				int cPhoneNumerSize = e.getValue().getCustomer().getPhone().length();
				int receivingSaleUpdatesSize = Boolean.toString(e.getValue().getCustomer().getReceivingSaleUpdates())
						.length();

				raf.writeInt(idSize);
				raf.write(e.getKey().getBytes());
				raf.writeInt(pNameSize);
				raf.write(e.getValue().getName().getBytes());
				raf.writeInt(e.getValue().getCostPricePerStore());
				raf.writeInt(e.getValue().getCostPricePerCustomer());
				raf.writeInt(cNameSize);
				raf.write(e.getValue().getCustomer().getName().getBytes());
				raf.writeInt(cPhoneNumerSize);
				raf.write(e.getValue().getCustomer().getPhone().getBytes());
				raf.writeInt(receivingSaleUpdatesSize);
				raf.write(Boolean.toString(e.getValue().getCustomer().getReceivingSaleUpdates()).getBytes());

			}
			raf.close();
			return 1;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			raf.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return 0;

	}

	public int uploadProductsFromFile() {
		try {
			getRaf();
			FileIterator fit = new FileIterator(raf);
			if (!fit.hasNext()) {
				raf.close();
				return 0;
				
			}
			//SortType s = fit.readSortType();
			//store.seteSortType(s);
			// store.setMap(s); >> TODO need to change mybe not to string >> get a sort type
/*
			if (s == SortType.eSortByAscendingOrder) {
				store.setMap("Lexicographic Natural order");
			} else if (s == SortType.eSortByDescendingOrder) {
				store.setMap("Lexicographic Descending order");
			} else {
				store.setMap("Input order");
			}
*/
			int counter = 0;
			//store.getSender().deletAll();
			while (fit.hasNext()) {
				Entry<String, Product> entry = (Entry<String, Product>) fit.next();
				getMyMap().put(entry.getKey(), entry.getValue());
				attach(entry.getValue().getCustomer());
				counter++;
			}
			raf.close();
			return counter;
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;

		
	}

	public void restoreMap(StoreMemento restore) {
		myMap = restore.getAllProducts();
		customers = restore.getALLCustomer();
		
	}

	public String getAllProductProfit() {
		int total =0;
		int profit;
		StringBuffer sb = new StringBuffer("All Products Profit:\n");
		Iterator<Map.Entry<String, Product>> iter = myMap.entrySet().iterator();
		if(iter.hasNext()) {
		while(iter.hasNext()) {
			Product temp = iter.next().getValue();
			profit = temp.getCostPricePerCustomer() - temp.getCostPricePerStore();
			sb.append("_"+temp.getName()+"_"+ " manufacture cost: " +temp.getCostPricePerStore()+ " selling price:" +
			temp.getCostPricePerCustomer()+ "\nProfit From product: "+ profit+ "\n" );
			total = total +profit;
		}
			sb.append("\nTotal Store Profit: "+ total);
			return sb.toString();
		}
		
		return "No Products";
	}

	public boolean removeProductBySKU(String sku) {
		boolean removed = false;
		try {
			getRaf();
			FileIterator fit = new FileIterator(raf);
			if (!fit.hasNext()) {
				return false;
			}
			//fit.readSortType();
			while (fit.hasNext()) {
				Entry<String, Product> entry = (Entry<String, Product>) fit.next();
				if (entry.getKey().equals(sku)) {
					fit.remove();
					if (entry.getValue().getCustomer().getReceivingSaleUpdates()) {
						//store.getSender().removeO(entry.getValue().getCustomer());
					}
					removed = true;
					break;
				}
			}
			raf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		if(myMap.containsKey(sku)) {
		dettach(myMap.get(sku).getCustomer());
		myMap.remove(sku);
		}
		return removed;
	}

	public void removeAllProducts() {

		try {
			getRaf();
			FileIterator fit = new FileIterator(raf);
			if (!fit.hasNext()) {
				return;
			}
			//store.getSender().deletAll();
			//fit.readSortType();
			// remove all product by the iteratorof the file >> product by product
			while (fit.hasNext()) {
				fit.next();
				fit.remove();
			}
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dettachAll();
		myMap.clear();

	}

	public Product getProductBySKU(String skuToFind) {
		return myMap.get(skuToFind);
	}
	
	public void attach(Observer o) {
		customers.add(o);
	}
	
	public void dettach(Observer o) {
		customers.remove(o);
	}
	
	public void dettachAll() {
		customers.clear();
	}
	
	public void sendMessege() {
		  responded.clear();
		for(Observer o: customers) {
			Customer tmp = o.updateCustomer();
			if(tmp.getName()==null)
				continue;
			if(tmp.getReceivingSaleUpdates()==false) {
				continue;
			}
			responded.add(tmp);
		}
		
		
	}

	public ArrayList<Customer> getRespondedCutomers() {
		
		return responded;
	}

	public ArrayList<Observer> getCutomer() {
		return customers;
	}

	
}


