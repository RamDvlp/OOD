package model;

import java.util.ArrayList;

import memento.CareTaker;
import memento.StoreMemento;
import model_Command.GlobalCommand;

public class Model {
	
	private Store store;
	private GlobalCommand command;
	private CareTaker mementoCareTaker;
	
	public Model() {
		store = Store.Instance();
		command = new GlobalCommand(store);
		mementoCareTaker = new CareTaker();
	}

	public String getAllProducts() {
		return command.getProductCommand().showAllProducts();
		
		
	}

	public void declareMap(String newValue) {
		command.getProductCommand().sortSelect(newValue);
		
		
	}

	public void addProduct(String sku, String productName, int manufactureCost, int sellingPrice, String clientName,
			String phone, boolean ads) {
		mementoCareTaker.save(new StoreMemento(store));
		command.getProductCommand().submit(sku, new Product(productName, manufactureCost, sellingPrice, new Customer(clientName, phone, ads)));
		store.saveToFile(); // why int??
		
	}

	public void mementoUndo() {
		command.getProductCommand().undo(mementoCareTaker);
		store.saveToFile();
		
	}

	public String getAllProductsInfo() {
		return store.getAllProductProfit();
	}

	public boolean removeProductBySKU(String sku) {
		return command.getFileCommand().removeProduct(sku);
		
		
	}

	public void removeAllProducts() {
		command.getFileCommand().removeAllProducts();
	}

	public int uploadFromFile() {
		return command.getFileCommand().uploadProducts();
		
	}

	public void sendNotificationToClient() {
		
		command.getProductCommand().sendNotification();
	}

	public ArrayList<Customer> getRespondedClients() {
		
		return command.getProductCommand().reciveAnswer();
	}

	public Product findProduct(String skuToFind) {
		return command.getProductCommand().showProduct(skuToFind);
	}

}
