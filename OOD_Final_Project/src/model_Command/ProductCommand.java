package model_Command;

import java.util.ArrayList;

import memento.CareTaker;
import model.Customer;
import model.Product;

public interface ProductCommand {
	
	void sortSelect(String newValue);
	void submit(String sku, Product product);
	void undo(CareTaker mementoCareTaker);
	String showAllProducts();
	Product showProduct(String skuToFind);
	void sendNotification();
	ArrayList<Customer> reciveAnswer();
	

}
