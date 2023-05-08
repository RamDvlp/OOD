package control;

import java.text.ParseException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Model;
import view.RemoveProductView;
import view.ShowAllProductsView;
import view.ShowProductView;
import view.View;

public class Controller {

	private ShowAllProductsView productView;
	private RemoveProductView removeView = new RemoveProductView();
	private ShowProductView showProductView = new ShowProductView();
	private Model theModel;
	private View theView;
	private boolean sort = false, enableUndo=false, notifications = false;
	int counter=0;
	
	public Controller(Model Model, View View) {
		theModel = Model;
		theView = View;
		
		EventHandler<ActionEvent> evenForExitButton = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				theView.getStage().close();
				
			}
		};
		theView.addEventHandlerToExitButton(evenForExitButton);
		
		
		ChangeListener<String> selectSortOption = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.equals(oldValue)) {
					return;
				}
				
				sort=true;
				theModel.declareMap(newValue);
				theView.getSortOption().setDisable(true);
				theView.getSortOption().setVisible(false);
				
			}
		};
		theView.addChangeListenerEventToSort(selectSortOption);
		
		EventHandler<ActionEvent> eventForSubmit = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(sort== false) {
					errorAleart("Please Chose Sorting Type First");
					return;
				}
				String sku = theView.getSKU();
				if(sku.equals("") || sku.equals(null)) {
					errorAleart("SKU is Mandatory Field. Please insert a value or I'll destroy the world.");
					return;
				}
				String productName = theView.getProguctName();

				int manufactureCost;	
				int sellingPrice; 

				try {
					
					String manuf = theView.getManufactureCost();
					String cost = theView.getSellingPrice();
					if(manuf.equals("") || manuf.equals(null)) {
						manufactureCost = 0;
						sellingPrice =0;
					}else {
						 manufactureCost = Integer.parseInt(manuf);	
						 sellingPrice = Integer.parseInt(cost);
						
					}
					if(manufactureCost< 0 || sellingPrice <0)
						throw  new NumberFormatException();
					
				}catch(NumberFormatException e) {
					errorAleart("Manufacture Cost and Selling price must be POSITIVE NUMBERS!");
					return;
				}
				 
				String clientName = theView.getClientName();
				String phone = theView.getPhoneNumber();
				boolean ads = theView.getUpdates();
				
				theModel.addProduct(sku,productName,manufactureCost,sellingPrice,clientName,phone,ads);
				theView.updateResult(theModel);
				
				Alert a = new Alert(AlertType.INFORMATION);
				a.setContentText("Product been added!");
				a.show();
				
				
				enableUndo = true;
				
				theView.clearClientName();
				theView.clearManufactureCost();
				theView.clearPhoneNumber();
				theView.clearProguctName();
				theView.clearSellingPrice();
				theView.clearSKU();
				theView.clearUpdates();
				counter++;
			}
		};
		theView.addEventHandlerToSubmitButton(eventForSubmit);
		
		
		EventHandler<ActionEvent> eventForUndo = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(sort== false) {
					errorAleart("Please Chose Sorting Type First");
					return;
				}
				if(counter== 0) {
					errorAleart("No Products in system (What do you want to undo?...there is nothing..)");
					return;
				}
				if(enableUndo== false) {
					errorAleart("Only last added Product is supported for this opperation.");
					return;
				}
				enableUndo= false;
				
				theModel.mementoUndo();
				theView.updateResult(theModel);
				counter--;
				
				
			}
		};theView.addEventHandlerToUndoButton(eventForUndo);
		
		
		EventHandler<ActionEvent> eventForShowAll = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(sort== false) {
					errorAleart("Please Chose Sorting Type First");
					return;
				}
				if(counter==0) {
					errorAleart("No Products in system (What do you want to see?...there is nothing..)");
					return;
				
				}
				
				
				productView = new ShowAllProductsView();
				productView.openView(new Stage());
				productView.showProducts(theModel);
				
				
			}
		}; theView.addEventHandlerToShowAllButton(eventForShowAll);
		
		
		EventHandler<ActionEvent> eventForRemoveProduct = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(sort== false) {
					errorAleart("Please Chose Sorting Type First");
					return;
				}
				if(counter== 0) {
					errorAleart("No Products in system (What do you want to remove?...there is nothing..)");
					return;
				}
				
				//removeView = new RemoveProductView();
				removeView.showRemoveView(new Stage());
				
				
			}
		};theView.addEventHandlerToRemoveProduct(eventForRemoveProduct);
		
		
		EventHandler<ActionEvent> addEventHandlerForRemove = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String sku = removeView.getSkuToRemove();
				boolean removed = theModel.removeProductBySKU(sku);
				if(removed) 
					counter--;
				
				removeView.showMessege(removed);
				theView.updateResult(theModel);
				
			}
		};removeView.addEventHandlerForRemove(addEventHandlerForRemove);
		
		EventHandler<ActionEvent> addEventHandlerToRemoveAll = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(counter== 0) {
					errorAleart("No Products in system (What do you want to remove?...there is nothing..)");
					return;
				}
				//there is option to remove all and only then chose sorting type.
				
				theModel.removeAllProducts();
				Alert a = new Alert(AlertType.INFORMATION);
				a.setContentText("All Products Been Removed.");
				a.show();
				counter=0;
				theView.updateResult(theModel);
				
			}
		}; theView.addEventHandlerToRemoveAll(addEventHandlerToRemoveAll);
		
		EventHandler<ActionEvent> addEventHandlerToUploadFromFile = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(sort== false) {
					errorAleart("Please Chose Sorting Type First");
					return;
				}
				
				counter = theModel.uploadFromFile();
				if(counter!= 0) {
					Alert a = new Alert(AlertType.INFORMATION);
					a.setContentText("Product been uploaded from file!");
					a.show();
				} else {
					errorAleart("There is no File to upload From. ");
				}
				
				theView.updateResult(theModel);
				
			}
		};theView.addEventHandlerToUploadFromFile(addEventHandlerToUploadFromFile);
		
		EventHandler<ActionEvent> eventForSentNotification = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(sort== false) {
					errorAleart("Please Chose Sorting Type First");
					return;
				}
				if(counter== 0) {
					errorAleart("No Clients in system.");
					return;
				}
				
				
				theModel.sendNotificationToClient();
				notifications = true;
				
				Alert a = new Alert(AlertType.INFORMATION);
				a.setContentText("Notification been Send!");
				a.show();
				
				
			}
		};theView.addEventHandlerToSendNotification(eventForSentNotification);
		
		EventHandler<ActionEvent> eventToShowClients = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(sort== false) {
					errorAleart("Please Chose Sorting Type First");
					return;
				}
				if(counter== 0) {
					errorAleart("No Clients in system.");
					return;
				}
				if(notifications==false) {
					errorAleart("No Notification Been Send.(What you doing now is like no calling somebody" +
				" but expect them to pick up a phone...who does like this?)");
					return;
				}
				
				theView.showClientResponse(theModel.getRespondedClients());
				
				
			}
		};theView.addEventHandlerToShowClients(eventToShowClients);
		
		EventHandler<ActionEvent> eventToShowProduct = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(sort== false) {
					errorAleart("Please Chose Sorting Type First");
					return;
				}
				if(counter== 0) {
					errorAleart("No Products in system.");
					return;
				}
				
				//showProductView = new ShowProductView();
				showProductView.showShowProductView(new Stage());
				
				
			}
		};theView.addEventHandlertoShowProduct(eventToShowProduct);
		
		
		EventHandler<ActionEvent> displayProduct = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				showProductView.showProduct(theModel.findProduct(showProductView.getSkuToFind()));
				
			}
		};showProductView.addEventHandlershowProduct(displayProduct);
		
		
		EventHandler<ActionEvent> eventToClear = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				theView.hideClear();
				theView.updateResult(theModel);
				
			}
		};theView.addEventHandlerToClear(eventToClear);
	}
	
	private void errorAleart(String messege) {
		Alert a = new Alert(Alert.AlertType.ERROR); 
		a.setContentText(messege);
		a.show();

	}

}
