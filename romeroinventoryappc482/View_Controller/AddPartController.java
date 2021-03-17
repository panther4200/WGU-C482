/*
 * Copyright (C) 2020 panther4200
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package romeroinventoryappc482.View_Controller;

import romeroinventoryappc482.Model.Inventory;
import romeroinventoryappc482.Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import romeroinventoryappc482.Model.InhousePart;
import static romeroinventoryappc482.Model.Inventory.getAllParts;
import romeroinventoryappc482.Model.OutsourcedPart;

/**
 * FXML Controller class
 *
 * @author Paul Romero
 */
public class AddPartController implements Initializable {

    @FXML
    private RadioButton addPartInhouseToggle;
    @FXML
    private RadioButton addPartOutsourcedToggle;
    @FXML
    private TextField addPartIdField;
    @FXML
    private TextField addPartNameField;
    @FXML
    private TextField addPartInventoryField;
    @FXML
    private TextField addPartPriceField;
    @FXML
    private TextField addPartMaxInventoryField;
    @FXML
    private TextField addPartMinInventoryField;
    @FXML
    private TextField addPartCompanyNameField;
    @FXML
    private Text addPartCompanyNameText;
    @FXML
    private Button addPartSaveButton;
    @FXML
    private Button addPartCancelButton;
    @FXML
    private Text addPartMachineIdText;
    @FXML
    private ToggleGroup addPartInOrOut;
    @FXML
    private TextField addPartMachineIdField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (getAllParts().isEmpty()){
            addPartIdField.setText("0");
        }
        else{
           int listIndex = (getAllParts().size()-1);
           addPartIdField.setText(Integer.toString(getAllParts().get(listIndex).getId()+1));
        }

        // TODO
    }    

    @FXML
    private void handleAddPartInhouseToggle(ActionEvent event) {
        addPartCompanyNameField.setVisible(false);
        addPartCompanyNameText.setVisible(false);
        addPartMachineIdField.setVisible(true);
        addPartMachineIdText.setVisible(true);
    }

    @FXML
    private void handleAddPartOutsourcedToggle(ActionEvent event) {
        addPartCompanyNameField.setVisible(true);
        addPartCompanyNameText.setVisible(true);
        addPartMachineIdField.setVisible(false);
        addPartMachineIdText.setVisible(false);
    }

    @FXML
    private void handleAddPartSaveButton(ActionEvent event) throws IOException {
       int id = Integer.parseInt(addPartIdField.getText());
       String name = addPartNameField.getText();
       double price =  Double.parseDouble(addPartPriceField.getText());
       int stock = Integer.parseInt(addPartInventoryField.getText());
       int min = Integer.parseInt(addPartMinInventoryField.getText());
       int max = Integer.parseInt(addPartMaxInventoryField.getText());

//For Set 1 Exception Control Option - Check to be sure minimum field can't be above the maximum field     
       if (min > max){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Max / Min Value Error!");
           alert.setHeaderText("Min inventory value cannot exceed Max value!");
           alert.setContentText("Please adjust the minimum inventory level to be below the maximum level in order to save your changes.");
           alert.showAndWait();
       }
       else{
            if (addPartInhouseToggle.isSelected()) {
            int machineId = Integer.parseInt(addPartMachineIdField.getText());
            Part newPart = new InhousePart(id , name, price, stock, min, max, machineId);
            Inventory.addPart(newPart);
        
            }
        
            if (addPartOutsourcedToggle.isSelected()) {
            String companyName = addPartCompanyNameField.getText();
            Part newPart = new OutsourcedPart(id , name, price, stock, min, max, companyName);
            Inventory.addPart(newPart);
     
            }
        
            Parent main_parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene main_scene = new Scene(main_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(main_scene);
            app_stage.show();        
                
        }
    }

    @FXML
    private void handleAddPartCancelButton(ActionEvent event) throws IOException {
        Parent main_parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_scene);
        app_stage.show();
    }
    
}
