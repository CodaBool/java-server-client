package application;
	
import java.text.DateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage clientWindow) {
		Stage serverWindow = new Stage(); // create a second window
		TextField inputField = new TextField(); //input to be checked if prime
		Label label = new Label("Enter a number to check prime: "); //label for inputField
		TextArea textClient = new TextArea(); //textArea to work as a console for feedback
		TextArea textServer = new TextArea(); //textArea to work as a console for feedback
		
		textServer.setText("Server started at " + DateFormat.getDateTimeInstance().format(new Date())); //begins server message
		inputField.setOnKeyReleased(event -> { //takes input of TextField
	        if (event.getCode() == KeyCode.ENTER){ //if enter was pressed
	        	try{ //check if the input was an int
	        	    int inputNum = Integer.parseInt(inputField.getText());
	        	    textServer.appendText("\nNumber receivied from client: " + inputNum + " ... sending calculation");
	        	    textClient.appendText("\nnumber is " + inputNum + "\n" + inputNum + getPrime(inputNum));
	        	} catch (NumberFormatException ex) { // if it was not an int then message the user
	        		textClient.appendText("\nPlease enter a valid number");
	        	}
	        	inputField.setText(""); //clear the input field
	        }
        });
		
		inputField.setMinWidth(380);
		textClient.setPrefHeight(260);
		textClient.setPrefWidth(560);
		textServer.setPrefHeight(290);
		textServer.setPrefWidth(560);
		
		FlowPane client = new FlowPane(Orientation.HORIZONTAL, 5, 5); 
		FlowPane server = new FlowPane(Orientation.HORIZONTAL, 5, 5); 
		
		Scene scene = new Scene(client, 570, 300); 
		Scene secondScene = new Scene(server, 570, 300);
		
		client.setPadding(new Insets(5));
		server.setPadding(new Insets(5));
		
		server.getChildren().addAll(textServer);
		client.getChildren().addAll(label, inputField, textClient);
        
		clientWindow.setTitle("Client");
		clientWindow.setScene(scene);
		clientWindow.show();
		
		serverWindow.setTitle("Server");
        serverWindow.setScene(secondScene);
        serverWindow.setX(clientWindow.getX() + 50);// Set position of second window, related to primary window.
        serverWindow.show();
	}
	public static String getPrime(int num) { //returns result in a string to print if number was prime
		for(int i = 2; i <= num/2; ++i) {
            if(num % i == 0) {
            	return " is not prime";
            }
        }
		return " is prime";
	}
	public static void main(String[] args) {
		launch(args);
	}
}
