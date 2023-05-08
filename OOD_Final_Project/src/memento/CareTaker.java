package memento;

public class CareTaker {
	//private Stack<StoreMemento> stack;
	private StoreMemento memento;

	public CareTaker() {
		memento=null;
	}
	
	public void save(StoreMemento memento) {
		this.memento=memento;
	}
	
	public StoreMemento restore() {
		return memento;
	}

}
