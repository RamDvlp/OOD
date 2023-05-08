package view;


import javafx.stage.Stage;
import javafx.util.Duration;
import model.Customer;
import model.Model;
import model.Observer;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class View {

	private Stage stage;
	private BorderPane bp;
	private HBox up,down;
	private VBox right;
	private Text headlineBanner;
	private Pane root;
	private TextField productName, SKU, manufacturaCost, sellingPrice;
	private Text txtProductName, txtSKU, txtManufactureCost, txtSellingPrice; 
	private Button btnExit,btnSumbit, btnUndo,btnRemoveBySKU,btnRemoveAll ,btnShowAll,btnReadFileContent;
	private Label client;
	private Label showResult;
	private Text txtClientName, txtPhoneNumber, txtUpdates;
	private TextField clientName, clientPhoneNumber;
	private CheckBox cbUpdates;
	private ComboBox<String> sortOption;
	private Button btnSendAds, btnShowRespondedClient;
	private Button btnShowProduct;
	private Button btnClear;
	
	public View(Stage stage) {
		this.stage = stage;
		bp = new BorderPane();
		
		//up - headline
		up = new HBox();
		headlineBanner = new Text("Welcome To Store Management System!");
		headlineBanner.setFont(Font.font("david", FontWeight.BOLD, 40));
		headlineBanner.setStroke(Color.DARKBLUE);
		
		Reflection r = new Reflection();
		r.setFraction(0.4);
		headlineBanner.setEffect(r);
		
		up.setAlignment(Pos.CENTER);
		up.getChildren().add(headlineBanner);
		
		//body
		root = new Pane();
		double x = root.getLayoutBounds().getMinX();
		double y = root.getLayoutBounds().getMinY();
		
		txtSKU = new Text("Product's SKU (Mandatory)");
		txtSKU.setFont(new Font(15));
		txtSKU.setStroke(Color.RED);
		txtSKU.setUnderline(true);
		txtSKU.setLayoutX(x+50);
		txtSKU.setLayoutY(y+50);
		
		SKU = new TextField();
		SKU.setLayoutX(x+50);
		SKU.setLayoutY(y+60);
		
		
		txtProductName = new Text("Product Name:");
		txtProductName.setFont(new Font(15));
		txtProductName.setLayoutX(x+50);
		txtProductName.setLayoutY(y+130);
		
		productName = new TextField();
		productName.setLayoutX(x+50);
		productName.setLayoutY(y+140);
		
		txtManufactureCost = new Text("Manufacture Cost:");
		txtManufactureCost.setFont(new Font(15));
		txtManufactureCost.setLayoutX(x+50);
		txtManufactureCost.setLayoutY(y+210);
		
		manufacturaCost = new TextField();
		manufacturaCost.setLayoutX(x+50);
		manufacturaCost.setLayoutY(y+220);
		
		txtSellingPrice = new Text("Selling price:");
		txtSellingPrice.setFont(new Font(15));
		txtSellingPrice.setLayoutX(x+50);
		txtSellingPrice.setLayoutY(y+290);
		
		sellingPrice = new TextField();
		sellingPrice.setLayoutX(x+50);
		sellingPrice.setLayoutY(y+300);
		
		root.getChildren().addAll(txtSKU, SKU, txtProductName, productName, txtManufactureCost, manufacturaCost, txtSellingPrice,sellingPrice);
		
		//body - client
		client = new Label("Client Information:");
		client.setFont( Font.font("arial", FontWeight.BOLD, 20));
		client.setUnderline(true);
		client.setLayoutX(x+50);
		client.setLayoutY(y+360);
		
		txtClientName = new Text("Client Name:");
		txtClientName.setFont(new Font(15));
		txtClientName.setLayoutX(x+50);
		txtClientName.setLayoutY(y+440);
		
		clientName = new TextField();
		clientName.setLayoutX(x+50);
		clientName.setLayoutY(y+450);
		
		txtPhoneNumber = new Text("Phone Number:");
		txtPhoneNumber.setFont(new Font(15));
		txtPhoneNumber.setLayoutX(x+50);
		txtPhoneNumber.setLayoutY(y+520);
		
		clientPhoneNumber = new TextField();
		clientPhoneNumber.setLayoutX(x+50);
		clientPhoneNumber.setLayoutY(y+530);
		
		txtUpdates = new Text("Send Ads to Client?");
		txtUpdates.setFont(new Font(15));
		txtUpdates.setLayoutX(x +50);
		txtUpdates.setLayoutY(y+600);
		
		cbUpdates = new CheckBox();
		cbUpdates.setLayoutX(x+200);
		cbUpdates.setLayoutY(y+585);
		
		showResult = new Label("result:\n");
		showResult.setFont( Font.font("arial", FontWeight.BOLD,15));
		showResult.setLayoutX(x+300);
		showResult.setLayoutY(y+50);
		showResult.setTextFill(Color.DARKBLUE);
		
		
		root.getChildren().addAll(client,txtClientName,clientName, txtPhoneNumber,clientPhoneNumber,txtUpdates,cbUpdates,showResult);
		
		ScrollPane scrPane = new ScrollPane(showResult);
		scrPane.setLayoutX(x+300);
		scrPane.setLayoutY(y+50);
		scrPane.setMinSize(300, 500);
		scrPane.setMaxSize(root.getLayoutBounds().getMaxX() - root.getLayoutBounds().getMinX(),
				root.getLayoutBounds().getMaxY()- root.getLayoutBounds().getMinY());
		root.getChildren().add(scrPane);
		
		btnClear = new Button(" Clear ");
		btnClear.setTextFill(Color.GREEN);
		btnClear.setFont(new Font(15));
		btnClear.setVisible(false);
		btnClear.setLayoutX(x+420);
		btnClear.setLayoutY(y+550);
		root.getChildren().add(btnClear);
		
		//bottom - buttons
		down = new HBox();
		
		btnExit = new Button(" Exit ");
		btnExit.setTextFill(Color.RED);
		btnExit.setFont(Font.font("arial",FontWeight.BOLD,15));
		
		btnSumbit =new Button(" Submit ");
		btnSumbit.setTextFill(Color.BLUE);
		btnSumbit.setFont(Font.font("arial",FontWeight.BOLD,15));
		
		down.getChildren().addAll(btnSumbit,btnExit);
		down.setAlignment(Pos.CENTER);
		HBox.setMargin(btnExit, new Insets(10,10,10,10));
		
		
		//right
		right = new VBox();
		
		sortOption = new ComboBox<String>();
		String def = "Sort Options...";
		sortOption.getItems().addAll(def, "Lexicographic Natural order", "Lexicographic Descending order", "Input order");
		sortOption.setValue(def);
		
		btnUndo = new Button("       Undo       ");
		btnUndo.setFont(Font.font("arial", FontWeight.BOLD, 15));
		btnUndo.setTextFill(Color.GREEN);
		
		btnRemoveBySKU = new Button("   Remove Product   ");
		btnRemoveBySKU.setFont(Font.font("arial", FontWeight.BOLD, 15));
		
		btnRemoveAll = new Button(" Remove All Products ");
		btnRemoveAll.setFont(Font.font("arial", FontWeight.BOLD, 15));
		
		btnShowAll = new Button("      Show All      ");
		btnShowAll.setFont(Font.font("arial" , FontWeight.BOLD, 15));
		
		btnReadFileContent = new Button("Upload product from File");
		btnReadFileContent.setFont(Font.font("arial", FontWeight.BOLD, 15));
		btnReadFileContent.setTextFill(Color.GREEN);
		
		btnSendAds = new Button(" Send Notification to Client");
		btnSendAds.setFont(Font.font("arial", FontWeight.BOLD, 15));
		
		btnShowRespondedClient = new Button("   Show Interested Clients    ");
		btnShowRespondedClient.setFont(Font.font("arial", FontWeight.BOLD, 15));
		
		btnShowProduct = new Button("   Show Product   ");
		btnShowProduct.setFont(Font.font("arial", FontWeight.BOLD, 15));
		
		Text menu = new Text("Menu");
		menu.setUnderline(true);
		menu.setFont(Font.font("arial", FontWeight.BOLD, 15));
		menu.setFill(Color.CRIMSON);
		
		right.getChildren().addAll(menu,sortOption, btnUndo,btnShowAll,btnShowProduct, btnRemoveBySKU, btnRemoveAll, btnReadFileContent,btnSendAds,btnShowRespondedClient);
		
		for(int i =0; i<right.getChildren().size();i++) {
			VBox.setMargin(right.getChildren().get(i), new Insets(10,10,10,10));	
		}
		
		right.setAlignment(Pos.CENTER);
		
		bp.setTop(up);
		bp.setCenter(root);
		bp.setBottom(down);
		bp.setRight(right);
		
		Scene scene = new Scene(bp,900,700);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public String getSKU() {
		return SKU.getText();
	}
	
	public String getProguctName() {
		return productName.getText();
	}
	
	public String getManufactureCost() {
		return manufacturaCost.getText();
	}
	
	public String getSellingPrice() {
		return sellingPrice.getText();
	}
	
	public String getClientName() {
		return clientName.getText();
	}
	
	public String getPhoneNumber() {
		return clientPhoneNumber.getText();
	}
	
	public Boolean getUpdates() {
		return cbUpdates.isSelected();
	}

	public void clearSKU() {
		SKU.setText("");
	}
	
	public void clearProguctName() {
		productName.setText("");
	}
	
	public void clearManufactureCost() {
		manufacturaCost.setText("");
	}
	
	public void clearSellingPrice() {
		sellingPrice.setText("");
	}
	
	public void clearClientName() {
		clientName.setText("");
	}
	
	public void clearPhoneNumber() {
		 clientPhoneNumber.setText("");
	}
	
	public void clearUpdates() {
		 cbUpdates.setSelected(false);
	}
	
	
	public void updateResult(Model m) {
		
		showResult.setText(m.getAllProductsInfo());
		
	}
	
	public ComboBox<String> getSortOption() {
		return sortOption;
	}
	
	
	public void addEventHandlerToExitButton(EventHandler<ActionEvent> evenForExitButton) {
		btnExit.setOnAction(evenForExitButton);
	}
	
	public void addChangeListenerEventToSort(ChangeListener<String> cl) {
		sortOption.valueProperty().addListener(cl);
	}
	
	public void addEventHandlerToSubmitButton(EventHandler<ActionEvent> eventForSubmit) {
		btnSumbit.setOnAction(eventForSubmit);
	}
	
	public void addEventHandlerToUndoButton(EventHandler<ActionEvent> eventForUndo) {
		btnUndo.setOnAction(eventForUndo);
	}
	
	public void addEventHandlerToShowAllButton(EventHandler<ActionEvent> eventForShowProduct) {
		btnShowAll.setOnAction(eventForShowProduct);
	}
	
	public void addEventHandlerToRemoveProduct(EventHandler<ActionEvent> eventForRemoveProduct) {
		btnRemoveBySKU.setOnAction(eventForRemoveProduct);
	}
	
	public void addEventHandlerToRemoveAll(EventHandler<ActionEvent> eventForRemoveAll) {
		btnRemoveAll.setOnAction(eventForRemoveAll);
	}
	
	public void addEventHandlerToUploadFromFile(EventHandler<ActionEvent> eventForUploadFromFile) {
		btnReadFileContent.setOnAction(eventForUploadFromFile);
	}
	
	public void addEventHandlerToSendNotification( EventHandler<ActionEvent> eventForSentNotification) {
		btnSendAds.setOnAction(eventForSentNotification);
	}
	
	public void addEventHandlerToShowClients(EventHandler<ActionEvent> eventToShowClients) {
		btnShowRespondedClient.setOnAction(eventToShowClients);
	}
	
	public void addEventHandlertoShowProduct(EventHandler<ActionEvent> eventToShowProduct) {
		btnShowProduct.setOnAction(eventToShowProduct);
	}
	
	public void addEventHandlerToClear (EventHandler<ActionEvent> eventToClear) {
		btnClear.setOnAction(eventToClear);
	}

	public void showClientResponse(ArrayList<Customer> customer ) {
		//this.respondedCustomers = customers;
		//final ArrayList<Observer> customers = customer;
		//customers.add(new Customer("Moshe", "050-6255163", true));
		//customers.add(new Customer("Dani", "050-6255163", true));
	
		showResult.setText("Responded Customers:\n");
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {

		    private int i = 0;    

		    @Override
		    public void handle(ActionEvent event) {
		    	
		    	showResult.setText(showResult.getText() + customer.get(i).getName()+ " Responded!\n"); 
		        i++;
		    }
		}));
		timeline.setCycleCount(customer.size());
		timeline.play();
		timeline.setOnFinished(e->{
			btnClear.setVisible(true);
		});
			
	}

	public void hideClear() {

		btnClear.setVisible(false);
	}

}
