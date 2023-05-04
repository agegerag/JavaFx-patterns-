/**
 * 
 */
package csc2a.acsse.gui;
import java.io.File;

import acsse.csc2a.file.DataReader;
import acsse.csc2a.model.EncryptedMessage;
import acsse.csc2a.model.Message;
import acsse.csc2a.model.NormalMessage;
import acsse.csc2a.model.SOSMessage;
import acsse.csc2a.model.Ship;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.geometry.Insets;

/**
 * @author kgosi
 *
 */
public class ShipPane extends StackPane{
	private Ship ship;
	/**
	 * Create Constructor for the layout
	 */
public ShipPane() {
	//create menu bar
		MenuBar menubar=new MenuBar();
		Menu theMenu=new Menu("File");
		//add the menu to the menubar
		menubar.getMenus().add(theMenu);
		MenuItem m1=new MenuItem("Open");
		theMenu.getItems().add(m1);
		
		//adding an action listener for the "Open" menu item
		m1.setOnAction(e -> {
		//create a file chooser
		final FileChooser filC=new FileChooser();
		//provide it a title
		filC.setTitle("Choose File");
		//set starting directory to data directory
		filC.setInitialDirectory(new File("data"));
		File thefile=filC.showOpenDialog(null);
		
		if(thefile!=null) {
			//read data if file is not empty
			ship=DataReader.readShipFile(thefile);
			createChildrn();
		}
		});//VBox to stack menu bar vertically
		VBox Layout=new VBox();
		Layout.getChildren().addAll(menubar);
		this.getChildren().add(Layout);//add VBox to the ShipPane
		this.setWidth(400);
		this.setHeight(500);
}
/**
 * Create component nodes to exist inside the StackPane
 */
private void createChildrn() {
	//the TitledPane for displaying the Ship header
	TitledPane TpShip=new TitledPane();
	TpShip.setText("Ship Information");
	//ADD the Ship's GridPane to the TP
	TpShip.setContent(createShipGrid(ship));
	
	//TitledPane for Displaying messages found in the Ship
	TitledPane TpSOSMessages=new TitledPane();
	TitledPane TpEncryptedMessages=new TitledPane();
	TitledPane TpNormalMessages=new TitledPane();
	
	TpSOSMessages.setText("SOS Messages");
	ScrollPane spSOSMessages=new ScrollPane();
	VBox vbSOSMessages=new VBox();
	for(Message x:ship.getMessages()) {
		//Creating a GridPane for each SOSMessage
		if(x instanceof SOSMessage)
		{
			vbSOSMessages.getChildren().add(createSOSMessageGrid(x));
		}
	}
	spSOSMessages.setContent(vbSOSMessages);
	TpSOSMessages.setContent(spSOSMessages);
	
	TpEncryptedMessages.setText("Encryptred Messages");
	ScrollPane spEncryMessages=new ScrollPane();
	VBox vbEncryptedMessages=new VBox();
	for(Message x:ship.getMessages()) {
		//Creating a GridPane for each Encrypted Message
		if(x instanceof EncryptedMessage)
		{
			vbEncryptedMessages.getChildren().add(createEncryptedGrid(x));
		}
		
	}
	spEncryMessages.setContent(vbEncryptedMessages);
	TpEncryptedMessages.setContent(spEncryMessages);
	
	TpNormalMessages.setText("Normal Messages");
	ScrollPane spNormMessages=new ScrollPane();
	VBox vbNormalMessages=new VBox();
	for(Message x:ship.getMessages()) {
		//Creating a GridPane for each Normal Message
		if(x instanceof NormalMessage)
		{
			vbNormalMessages.getChildren().add(createNormalGrid(x));
		}
	}
	spNormMessages.setContent(vbNormalMessages);
	TpNormalMessages.setContent(spNormMessages);
	
	//create a list view to display messages as list
	ListView<TitledPane> listView=new ListView<>();
	listView.getItems().add(TpShip);
	listView.getItems().add(TpSOSMessages);
	listView.getItems().add(TpEncryptedMessages);
	listView.getItems().add(TpNormalMessages);
	
	//add list to the ShipPane
	this.getChildren().clear();
	this.getChildren().add(listView);
}
/**
 * Create GridPane for NormalMessage
 * @param message
 * @return NormalMessages GridPane
 */
private GridPane createNormalGrid(Message m) {
	GridPane normgrid=new GridPane();
	
	//add horizontal and vertical spacing
	normgrid.setVgap(5);
	normgrid.setHgap(1);
	normgrid.setPadding(new Insets(5,5,5,5));//add padding
	
	//message ID
	normgrid.add(new Label("ID: "), 0, 0);
	normgrid.add(new TextField(m.getID()), 1, 0);
	//message  type
	normgrid.add(new Label("Type: "), 0, 1);
	normgrid.add(new TextField(m.getMessage_type().toString()), 1, 1);
	normgrid.add(new Label("Contents: "), 0, 2);
	
	normgrid.add(new Label("Source: "), 2, 0);
	normgrid.add(new TextField(m.getPlanet_source().toString()), 3, 0);
	normgrid.add(new Label("Destination: "), 2, 1);
	normgrid.add(new TextField(m.getPlanet_destination().toString()), 3, 1);
	
	TextField conTextField = new TextField(m.getContents());
	normgrid.add(conTextField, 1, 2, 3, 1);
	
	return normgrid;
}
/**
 * Create GridPane for EncryptedMessage
 * @param message
 * @return EncryptedMessages GridPane
 */
private GridPane createEncryptedGrid(Message m) {
	GridPane Encrygrid=new GridPane();
	//add horizontal and vertical spacing
	Encrygrid.setVgap(5);
	Encrygrid.setHgap(1);
	Encrygrid.setPadding(new Insets(5,5,5,5));//add padding
		
	//message ID
	Encrygrid.add(new Label("ID: "), 0, 0);
	Encrygrid.add(new TextField(m.getID()), 1, 0);
	//message  type
	Encrygrid.add(new Label("Type: "), 0, 1);
	Encrygrid.add(new TextField(m.getMessage_type().toString()), 1, 1);
	Encrygrid.add(new Label("Contents: "), 0, 2);
	Encrygrid.add(new TextField(m.getContents()), 1, 2,3,1);
	Encrygrid.add(new Label("Source: "), 2, 0);
	Encrygrid.add(new TextField(m.getPlanet_source().toString()), 3, 0);
	Encrygrid.add(new Label("Destination: "), 2, 1);
	Encrygrid.add(new TextField(m.getPlanet_destination().toString()), 3, 1);
	return Encrygrid;
}
/**
 * Create GridPane for SOSMessage
 * @param message
 * @return SOSMessages GridPane
 */
private GridPane createSOSMessageGrid(Message m) {
	GridPane SOSgrid=new GridPane();
	//add horizontal and vertical spacing
	SOSgrid.setVgap(5);
	SOSgrid.setHgap(1);
	SOSgrid.setPadding(new Insets(5,5,5,5));//add padding
	
	//message ID
	SOSgrid.add(new Label("ID: "), 0, 0);
	SOSgrid.add(new TextField(m.getID()), 1, 0);
	//message  type
	SOSgrid.add(new Label("Type: "), 0, 1);
	SOSgrid.add(new TextField(m.getMessage_type().toString()), 1, 1);
	SOSgrid.add(new Label("Contents: "), 0, 2);
	SOSgrid.add(new TextField(m.getContents()), 1, 2,3,1);
	SOSgrid.add(new Label("Source: "), 2, 0);
	SOSgrid.add(new TextField(m.getPlanet_source().toString()), 3, 0);
	SOSgrid.add(new Label("Destination: "), 2, 1);
	SOSgrid.add(new TextField(m.getPlanet_destination().toString()), 3, 1);
	return SOSgrid;
}
/**
 * Create a GridPane for Ship
 * @param Ship used to create the GridPane
 * @return The ship GridPane
 */
private GridPane createShipGrid(Ship ship2) {
	GridPane shipgrid=new GridPane();
	//add horizontal and vertical spacing
	shipgrid.setVgap(4);
	shipgrid.setHgap(1);
	shipgrid.setPadding(new Insets(5,5,5,5));
	shipgrid.add(new Label("Ship ID: "), 0, 0);
	shipgrid.add(new TextField(ship2.getID()), 0, 1);
	shipgrid.add(new Label("Ship Name: "), 1, 0);
	shipgrid.add(new TextField(ship2.getName()), 1, 1);
	return shipgrid;
}
}
