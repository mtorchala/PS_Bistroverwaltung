import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BasisModel bm = new BasisModel();
		BasisControl bc = new BasisControl();
		
		try {
			primaryStage.setTitle("Bistro-Verwaltung");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("BasisView.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 700,500);
			scene.getStylesheets().add(getClass().getResource("application.css")
			.toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}