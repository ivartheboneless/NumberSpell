/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numberspell;

import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.util.*;
import javafx.scene.text.Text;
/**
 *
 * @author Smile
 */
public class layoutController implements Initializable {
    
    @FXML
    private TextField textField;
    
    @FXML
    private ListView listView;
    
    @FXML
    private Text welcomeMessage;
    
    @FXML
    private Label combinationsFound;
    
    @FXML
    private Label invalidEntryMessage;
    
    @FXML
    private CheckBox checkbox;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String number = textField.getText();
        char[] numberArray = number.toCharArray();
        
        boolean isInputInvalid = false;
        
        for(char c : numberArray) {
            if(c != '2' &&
               c != '3' && 
               c != '4' &&
               c != '5' &&
               c != '6' &&
               c != '7' &&
               c != '8' &&
               c != '9') {
                isInputInvalid = true;
            }               
        }
        
        if(number.length() > 12) {
            combinationsFound.setVisible(false);
            listView.setVisible(false);
            invalidEntryMessage.setVisible(true);
            invalidEntryMessage.setText("A maximum of 12 digits can be entered.");
        } else if(isInputInvalid == true) {
            combinationsFound.setVisible(false);
            listView.setVisible(false);
            invalidEntryMessage.setVisible(true);
            invalidEntryMessage.setText("Only digits 2-9 are valid. Please try again!");
        } else {
            //Loading dictionary words
            Dictionary dictionary = new Dictionary();
            
            //generating phonewords
            CombinationsGenerator generator = new CombinationsGenerator(number);
            List<String> phonewords = generator.getPhonewords();
            
            //parsing valid phonewords
            List<String> validPhonewords = new ArrayList<>(phonewords);
            for(String w : phonewords) {
                if(!dictionary.getDictionaryWordList().contains(w)) {
                    validPhonewords.remove(w);
                }
            }
            
            //Creating observable lists
            ObservableList<String> phonewordsList = FXCollections.observableArrayList(phonewords);
            ObservableList<String> validPhonewordsList = FXCollections.observableArrayList(validPhonewords);
            
            //Setting visibility of nodes
            invalidEntryMessage.setVisible(false);
            welcomeMessage.setVisible(false);
            listView.setVisible(true);
        
            //Adding items to list view
            if(checkbox.selectedProperty().get()) {
                listView.setItems(validPhonewordsList);
            } else {
                listView.setItems(phonewordsList);
            }
            
            
            //Combinations found message
            int combinations = phonewords.size();
            String combos = Integer.toString(combinations);
            combinationsFound.setVisible(true);
            combinationsFound.setText(combos + " phonewords generated. Never forget an important number again!");
            System.out.println("Phonewords have been generated.");
        }
    }
    
    @FXML
    public void handleClick(MouseEvent event) {
        listView.setVisible(false);
        welcomeMessage.setVisible(true);
        combinationsFound.setVisible(false);
        invalidEntryMessage.setVisible(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
