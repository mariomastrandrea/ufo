package it.polito.tdp.ufo;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import it.polito.tdp.ufo.model.UfoModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class FXMLController 
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> shapesComboBox;

    @FXML
    private Button contaButton;

    @FXML
    private Label numAvvistamentiLabel;

    private UfoModel ufoModel;
    private String lastShape;
    
    
    @FXML
    void handleConta(ActionEvent event) 
    {
    	String selectedShape = this.shapesComboBox.getValue();
    	
    	if(selectedShape == null || selectedShape.isBlank())
    	{
    		this.numAvvistamentiLabel.setText("Devi selezionare una forma");
    		return;
    	}
    	else if (selectedShape.equals(this.lastShape))
		{
			return;
		}
    	else 
    	{
    		this.lastShape = selectedShape;
			int numOfSightings = this.ufoModel.getNumSightingsOf(selectedShape);
			
    		this.numAvvistamentiLabel.setText("Numero di avvistamenti: " + numOfSightings);
   		}
    }

    @FXML
    void initialize() 
    {
        assert shapesComboBox != null : "fx:id=\"shapesComboBox\" was not injected: check your FXML file 'Scene_ufo.fxml'.";
        assert contaButton != null : "fx:id=\"contaButton\" was not injected: check your FXML file 'Scene_ufo.fxml'.";
        assert numAvvistamentiLabel != null : "fx:id=\"numAvvistamentiLabel\" was not injected: check your FXML file 'Scene_ufo.fxml'.";
    }

	public void setModel(UfoModel model)
	{
		this.ufoModel = model;
		
		Collection<String> shapes = this.ufoModel.getAllShapes();
		this.shapesComboBox.getItems().addAll(shapes);
	}
}
