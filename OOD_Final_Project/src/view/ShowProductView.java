package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Product;

public class ShowProductView {
	
	private Stage stage;
	private GridPane root;
	private Text displayProduct;
	private TextField txtdisplayProduct;
	private Button btndisplay;
	private Text messege;
	private VBox vb;
	private Label productInfo;
	
	public ShowProductView() {
		vb = new VBox(10);
		root = new GridPane();
		displayProduct = new Text("Enter SUK of product:");
		displayProduct.setFont(Font.font(15));
		
		
		txtdisplayProduct = new TextField();
		
		
		btndisplay = new Button(" Show ");
		btndisplay.setFont(new Font (15));

		messege = new Text();
		messege.setFont(new Font(15));
		messege.setVisible(false);
		
		
		vb.getChildren().addAll(displayProduct,txtdisplayProduct,btndisplay);
		root.getChildren().addAll(vb);
		
		productInfo = new Label();
		productInfo.setVisible(false);
		vb.getChildren().addAll(messege,productInfo);
		
		VBox.setMargin(displayProduct, new Insets(10, 10, 10, 10));
		VBox.setMargin(btndisplay, new Insets(10, 10, 10, 10));
		VBox.setMargin(txtdisplayProduct, new Insets(10, 10, 10, 10));
		VBox.setMargin(productInfo, new Insets(10, 10, 10, 10));
	}
	
	public void showShowProductView(Stage stage) {
		this.stage= stage;
		Scene scene = new Scene(root, 300, 400);
		this.stage.setScene(scene);
		this.stage.show();
		
	}
	
	public void showProduct(Product prd) {
		if(prd!=null) {
			messege.setText("Product been Found.");
			messege.setFill(Color.GREEN);
			productInfo.setText("Product name: " + prd.getName() + "\nProduct manufacture cost: " + prd.getCostPricePerStore()
			+ "\nProduct selling price: " + prd.getCostPricePerCustomer());
			productInfo.setFont(new Font(15));

			productInfo.setVisible(true);
			VBox.setMargin(messege, new Insets(10, 10, 10, 10));
			VBox.setMargin(productInfo, new Insets(10, 10, 10, 10));
			
		} else {
			messege.setText("Product Not Found.");
			messege.setFill(Color.RED);
			productInfo.setText("");
			VBox.setMargin(messege, new Insets(10, 10, 10, 10));
		}
		
		messege.setVisible(true);
		
		
	}
	
	public void addEventHandlershowProduct (EventHandler<ActionEvent> displayProduct) {
		btndisplay.setOnAction(displayProduct);
	}
	
	public String getSkuToFind() {
		return txtdisplayProduct.getText();
	}

}
