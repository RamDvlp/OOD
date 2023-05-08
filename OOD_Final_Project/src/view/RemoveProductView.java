package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RemoveProductView {
	
	private Stage stage;
	private GridPane root;
	private Text removeProduct;
	private TextField txtRemoveProduct;
	private Button btnRemove;
	private Text messege;
	private VBox vb;
	
	public RemoveProductView() {
		vb = new VBox(10);
		root = new GridPane();
		removeProduct = new Text("Enter SUK of product:");
		removeProduct.setFont(Font.font(15));
		
		
		txtRemoveProduct = new TextField();
		
		
		btnRemove = new Button(" Remove ");
		btnRemove.setFont(new Font (15));

		messege = new Text();
		messege.setFont(new Font(15));
		
		vb.getChildren().addAll(removeProduct,txtRemoveProduct,btnRemove,messege);
		root.getChildren().addAll(vb);
		
		VBox.setMargin(removeProduct, new Insets(10, 10, 10, 10));
		VBox.setMargin(btnRemove, new Insets(10, 10, 10, 10));
		VBox.setMargin(txtRemoveProduct, new Insets(10, 10, 10, 10));
	}
	
	public void showRemoveView(Stage stage) {
		
		this.stage= stage;
		Scene scene = new Scene(root, 300, 200);
		this.stage.setScene(scene);
		this.stage.show();
		
	}
	
	public void showMessege(boolean isRemoved) {
		if(isRemoved) {
			messege.setText("Product been removed.");
			messege.setFill(Color.GREEN);
		} else {
			messege.setText("Product Not Found.");
			messege.setFill(Color.RED);
		}
		
		VBox.setMargin(messege, new Insets(10, 10, 10, 10));
		
	}
	
	public void addEventHandlerForRemove (EventHandler<ActionEvent> preformeRemove) {
		btnRemove.setOnAction(preformeRemove);
	}
	
	public String getSkuToRemove() {
		return txtRemoveProduct.getText();
	}

}
