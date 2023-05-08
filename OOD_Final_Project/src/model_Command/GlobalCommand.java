package model_Command;

import model.Store;

public class GlobalCommand {
	
	private FileCommand file;
	private ProductCommand product;
	
	public GlobalCommand(Store store) {
		
		this.file = new ConcretFileCommand(store);
		this.product = new ConcretProductCommand(store);
	}
	
	public FileCommand getFileCommand() {
		return file;
	}
	
	public ProductCommand getProductCommand() {
		return product;
	}
	
	

}
