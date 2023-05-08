package model;
public class Product {
	
	//private String serialMKT;
	private String name;
	private int costPricePerStore;
	private int costPricePerCustomer;
	private Customer customer;
	
	public Product(String name, int costPricePerStore, int costPricePerCustomer, Customer customer) {
		this.name = name;
		this.costPricePerStore = costPricePerStore;
		this.costPricePerCustomer = costPricePerCustomer;
		this.customer = customer;
	}

	
	//getters and setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCostPricePerStore() {
		return costPricePerStore;
	}

	public void setCostPricePerStore(int costPricePerStore) {
		this.costPricePerStore = costPricePerStore;
	}

	public int getCostPricePerCustomer() {
		return costPricePerCustomer;
	}

	public void setCostPricePerCustomer(int costPricePerCustomer) {
		this.costPricePerCustomer = costPricePerCustomer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

//toString
	@Override
	public String toString() {
		return "Product name: " + name + ", Manufacture Cost: " + costPricePerStore + ", Selling Price: "
				+ costPricePerCustomer + "; " + customer.toString() ;
	}
	
}
