package model;

public class Customer implements Observer{
	
	private String name;
	private String phone;
	private Boolean receivingSaleUpdates;
	
	public Customer(String name, String phone, Boolean receivingSaleUpdates) {
		this.name = name;
		this.phone = phone;
		this.receivingSaleUpdates = receivingSaleUpdates;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getReceivingSaleUpdates() {
		return receivingSaleUpdates;
	}

	public void setReceivingSaleUpdates(Boolean receivingSaleUpdates) {
		this.receivingSaleUpdates = receivingSaleUpdates;
	}

	@Override
	public String toString() {
		return "Customer name: " + name + ", phone: " + phone + ", Receiving Sale Updates: " + (receivingSaleUpdates?  "Yes\n" : "No\n");
	}

	@Override
	public Customer updateCustomer() {
		
		return this;
	}
	
}
