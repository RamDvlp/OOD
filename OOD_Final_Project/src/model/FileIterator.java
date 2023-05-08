package model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map.Entry;


public class FileIterator implements Iterator<Entry<String, Product>>{

	RandomAccessFile raf;
	int readP;
	int writeP;

	public FileIterator(RandomAccessFile raf) throws IOException {
		this.raf = raf;
		this.raf.seek(0); //goto the start of the file
		readP = 0;
		writeP = 0;
	}

	@Override
	public boolean hasNext() {
		try {
			if (raf.getFilePointer() < raf.length()) //if there are more things to read in the file
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void remove() {

		try {
			byte[] restOfFile = new byte[(int) (raf.length() - readP)]; //save in byte[] the rest of the file fro, the end of reading
			raf.seek(readP);
			raf.read(restOfFile);
			raf.seek(writeP);
			raf.write(restOfFile); //write on the obj product we want to remove from file
			raf.setLength(raf.getFilePointer());
			raf.seek(writeP);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Entry<String, Product> next() {
		try {
			writeP = (int) raf.getFilePointer();
			String productID = readString(raf.readInt());
			String productName = readString(raf.readInt());
			int storeCost = raf.readInt();
			int coustomerPrice = raf.readInt();
			String cName = readString(raf.readInt());
			String cPhoneNumber = readString(raf.readInt());
			boolean receivingSaleUpdates = Boolean.parseBoolean(readString(raf.readInt()));

			Customer customer = new Customer(cName, cPhoneNumber, receivingSaleUpdates);
			Product product = new Product(productName, storeCost, coustomerPrice, customer);
			readP = (int) raf.getFilePointer();

			return new SimpleEntry<String, Product>(productID, product);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	//get the enum of the sor type of the map
	//public SortType readSortType() throws IOException {
	//	raf.seek(0);
	//	return SortType.valueOf(raf.readUTF());
	//}

	//read from the file the String 
	private String readString(int size) throws IOException {
		byte[] data = new byte[size];
		raf.read(data);
		return new String(data);

	}

	
}
