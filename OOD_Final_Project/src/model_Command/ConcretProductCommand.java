package model_Command;

import java.util.ArrayList;

import memento.CareTaker;
import model.Customer;
import model.Observer;
import model.Product;
import model.Store;

public class ConcretProductCommand implements ProductCommand{

	private Store store;
	public ConcretProductCommand(Store store) {
		this.store = store;
	}

	@Override
	public void sortSelect(String newValue) {
		store.setMap(newValue);
		
	}

	@Override
	public void submit(String sku, Product product) {
		store.addProduct(sku, product);
		
	}

	@Override
	public void undo(CareTaker mementoCareTaker) {
		store.restoreMap(mementoCareTaker.restore());
		
	}

	@Override
	public String showAllProducts() {	
		return store.getAllProduct();
		
	}

	@Override
	public Product showProduct(String skuToFind) {
		return store.getProductBySKU(skuToFind);
	}

	@Override
	public void sendNotification() {
		store.sendMessege();
		
	}

	@Override
	public ArrayList<Customer> reciveAnswer() {
		return store.getRespondedCutomers();
		
	}

}
