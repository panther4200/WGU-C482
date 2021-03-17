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
import romeroinventoryappc482.Model.Inventory;
import romeroinventoryappc482.Model.OutsourcedPart;
import romeroinventoryappc482.Model.Part;

/**
 * FXML Controller class
 *
 * @author Paul Romero
 */
public class ModifyPartController implements Initializable  {
    private int indx;
    
    @FXML
    private RadioButton modifyPartInhouseToggle;
    @FXML
    private ToggleGroup modifyPartInOrOut;
    @FXML
    private RadioButton modifyPartOutsourcedToggle;
    @FXML
    private TextField modifyPartIdField;
    @FXML
    private TextField modifyPartNameField;
    @FXML
    private TextField modifyPartInventoryField;
    @FXML
    private TextField modifyPartPriceField;
    @FXML
    private TextField modifyPartMaxInventoryField;
    @FXML
    private TextField modifyPartMinInventoryField;
    @FXML
    private TextField modifyPartCompanyNameField;
    @FXML
    private Text modifyPartCompanyNameText;
    @FXML
    private Button modifyPartSaveButton;
    @FXML
    private Button modifyPartCancelButton;
    @FXML
    private TextField modifyPartMachineIdField;
    @FXML
    private Text modifyPartMachineIdText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
    public void setModPart (Part part) {
        for (int i = 0; i < Inventory.getAllParts().size(); i++) {
            if (Inventory.getAllParts().get(i).getId() == part.getId()){
                indx = i;
            }
        }
        
        modifyPartIdField.setText(Integer.toString(part.getId()));
        modifyPartNameField.setText(part.getName());
        modifyPartInventoryField.setText(Integer.toString(part.getStock()));
        modifyPartPriceField.setText(Double.toString(part.getPrice()));
        modifyPartMaxInventoryField.setText(Integer.toString(part.getMax()));
        modifyPartMinInventoryField.setText(Integer.toString(part.getMin()));
        
        
        if (part instanceof InhousePart) {
            modifyPartInhouseToggle.setSelected(true);
            modifyPartMachineIdField.setText(Integer.toString(((InhousePart) part).getMachineId()));

        }
        
        if (part instanceof OutsourcedPart) {
            modifyPartOutsourcedToggle.setSelected(true);
            modifyPartCompanyNameField.setVisible(true);
            modifyPartCompanyNameText.setVisible(true);
            modifyPartMachineIdField.setVisible(false);
            modifyPartMachineIdText.setVisible(false);
            modifyPartCompanyNameField.setText(((OutsourcedPart) part).getCompanyName());
        
        }
       
    }
    
    
    @FXML
    private void handleModifyPartInhouseToggle(ActionEvent event) {
        modifyPartCompanyNameField.setVisible(false);
        modifyPartCompanyNameText.setVisible(false);
        modifyPartMachineIdField.setVisible(true);
        modifyPartMachineIdText.setVisible(true);
    }

    @FXML
    private void handleAddPartOutsourcedToggle(ActionEvent event) {
        modifyPartCompanyNameField.setVisible(true);
        modifyPartCompanyNameText.setVisible(true);
        modifyPartMachineIdField.setVisible(false);
        modifyPartMachineIdText.setVisible(false);
    }

    @FXML
    private void handleModifyPartSaveButton(ActionEvent event) throws IOException {
       int id = Integer.parseInt(modifyPartIdField.getText());
       String name = modifyPartNameField.getText();
       double price =  Double.parseDouble(modifyPartPriceField.getText());
       int stock = Integer.parseInt(modifyPartInventoryField.getText());
       int min = Integer.parseInt(modifyPartMinInventoryField.getText());
       int max = Integer.parseInt(modifyPartMaxInventoryField.getText());
       
//For Set 1 Exception Control Option - Check to be sure minimum field can't be above the maximum field     
       if (min > max){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Max / Min Value Error!");
           alert.setHeaderText("Min inventory value cannot exceed Max value!");
           alert.setContentText("Please adjust the minimum inventory level to be below the maximum level in order to save your changes.");
           alert.showAndWait();
       }
       else{       
            if (modifyPartInhouseToggle.isSelected()) {
                int machineId = Integer.parseInt(modifyPartMachineIdField.getText());
                Part newPart = new InhousePart(id , name, price, stock, min, max, machineId);
                Inventory.updatePart(indx,newPart);
        
            }
        
            if (modifyPartOutsourcedToggle.isSelected()) {
            String companyName = modifyPartCompanyNameField.getText();
            Part newPart = new OutsourcedPart(id , name, price, stock, min, max, companyName);
            Inventory.updatePart(indx,newPart);
     
            }
        
            Parent main_parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene main_scene = new Scene(main_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(main_scene);
            app_stage.show();        
                
        }
    }
    @FXML
    private void handleModifyPartCancelButton(ActionEvent event) throws IOException {
        Parent main_parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_scene);
        app_stage.show();
    }
    
}
