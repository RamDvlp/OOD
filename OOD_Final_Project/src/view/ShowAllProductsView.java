package view;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Model;

public class ShowAllProductsView {
	private Stage stage;
	private BorderPane bp;
	private ScrollPane scrlPane;
	private Text content;
	private Text headLine;
	

	public ShowAllProductsView() {
		bp = new BorderPane();
		headLine = new Text(" All Products: ");
		headLine.setFont(Font.font("arial", FontWeight.BOLD, 20));
		
		content = new Text("Text");
		content.setFont(new Font(15));
		scrlPane = new ScrollPane(content);
		
		bp.setTop(headLine);
		bp.setCenter(scrlPane);
		
	}
	
	public void openView(Stage stage) {
		this.stage = stage;
		Scene scene = new Scene(bp, 400,500 );
		this.stage.setScene(scene);
		this.stage.show();
		
	}

	public void showProducts(Model theModel) {
		content.setText( theModel.getAllProducts());
	}
}
