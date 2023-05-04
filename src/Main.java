import csc2a.acsse.gui.ShipPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 */

/**
 * @author kgosi
 *
 */
public class Main extends Application{

	private ShipPane shippane=null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	try {
		shippane=new ShipPane();
		//place the shippane in the Scene
		Scene scene=new Scene(shippane);
		
		//Setup the Stage
		primaryStage.setTitle("Ship Transportation Information");
		primaryStage.setWidth(400);
		primaryStage.setHeight(600);
		//add the scene to the Stage
		primaryStage.setScene(scene);
		//Displaying the Stage
		primaryStage.show();
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	}

}
