package model_Command;

import model.Store;

public class ConcretFileCommand implements FileCommand{

	private Store store;
	
	public ConcretFileCommand(Store store) {
		this.store = store;
	}

	@Override
	public boolean removeProduct(String sku) {
		return store.removeProductBySKU(sku);
		
	}

	@Override
	public void removeAllProducts() {
		store.removeAllProducts();
		
	}

	@Override
	public int uploadProducts() {
		return store.uploadProductsFromFile();
		
	}

}
