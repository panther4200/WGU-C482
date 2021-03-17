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

import romeroinventoryappc482.Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import romeroinventoryappc482.Model.Inventory;
import static romeroinventoryappc482.Model.Inventory.getAllParts;
import romeroinventoryappc482.Model.Product;


/**
 * FXML Controller class
 *
 * @author Paul Romero
 */
public class ModifyProductController implements Initializable {
    
private ObservableList<Part> foundPartList = FXCollections.observableArrayList();
private ObservableList<Part> productParts = FXCollections.observableArrayList();
private int indx;
    
    @FXML
    private TableView<Part> modifyProductFullPartLookupTable;
    @FXML
    private TableColumn<Part, Integer> modifyProductFullPartLookupTablePartIdColumn;
    @FXML
    private TableColumn<Part, String> modifyProductFullPartLookupTablePartNameColumn;
    @FXML
    private TableColumn<Part, Integer> modifyProductFullPartLookupTableInventoryLevelColumn;
    @FXML
    private TableColumn<Part, Double> modifyProductFullPartLookupTablePriceColumn;
    @FXML
    private TableView<Part> modifyProductIncludedPartsTable;
    @FXML
    private TableColumn<Part, Integer> modifyProductIncludedPartsTablePartIdColumn;
    @FXML
    private TableColumn<Part, String> modifyProductIncludedPartsTablePartNameColumn;
    @FXML
    private TableColumn<Part, Integer> modifyProductIncludedPartsTableInventoryLevelColumn;
    @FXML
    private Button modifyProductAddPartToProductButton;
    @FXML
    private Button modifyProductDeletePartFromProductButton;
    @FXML
    private TextField modifyProductSearchForPartField;
    @FXML
    private Button modifyProductSearchForPartButton;
    @FXML
    private Button modifyProductCancelButton;
    @FXML
    private Button modifyProductSaveButton;
    @FXML
    private TextField modifyProductIdField;
    @FXML
    private TextField modifyProductNameField;
    @FXML
    private TextField modifyProductInventoryField;
    @FXML
    private TextField modifyProductPriceField;
    @FXML
    private TextField modifyProductMaxInventoryField;
    @FXML
    private TextField modifyProductMinInventoryField;
    @FXML
    private TableColumn<Part, Double> modifyProductIncludedPartsTablePriceColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modifyProductFullPartLookupTable.setItems(getAllParts());
        modifyProductFullPartLookupTablePartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductFullPartLookupTablePartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductFullPartLookupTableInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductFullPartLookupTablePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));        
        
        modifyProductIncludedPartsTable.setItems(productParts);
        modifyProductIncludedPartsTablePartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductIncludedPartsTablePartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductIncludedPartsTableInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductIncludedPartsTablePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }    
    public void setModProduct (Product product){
        for (int i = 0; i < Inventory.getAllProducts().size(); i++) {
            if (Inventory.getAllProducts().get(i).getId() == product.getId()){
                indx = i;
            }
        }
        for (Part partPane : (product.getAllAssociatedParts())){
            productParts.add(partPane);
        }
                
        modifyProductIdField.setText(Integer.toString(product.getId()));
        modifyProductNameField.setText(product.getName());
        modifyProductInventoryField.setText(Integer.toString(product.getStock()));
        modifyProductPriceField.setText(Double.toString(product.getPrice()));
        modifyProductMaxInventoryField.setText(Integer.toString(product.getMax()));
        modifyProductMinInventoryField.setText(Integer.toString(product.getMin()));
        modifyProductIncludedPartsTable.setItems(productParts);
    }
    
    @FXML
    private void handleModifyProductAddPartToProductButton(ActionEvent event) {
        Part part = modifyProductFullPartLookupTable.getSelectionModel().getSelectedItem();
        productParts.add(part);
    }

    @FXML
    private void handleModifyProductDeletePartFromProductButton(ActionEvent event) {
        Part selectedAssociatedPart = modifyProductIncludedPartsTable.getSelectionModel().getSelectedItem();
        productParts.remove(selectedAssociatedPart);
    }

    @FXML
    private void handleModifyProductSearchForPartButton(ActionEvent event) {
        foundPartList.clear();
        boolean isInteger = true;
        try{
            int num = Integer.parseInt(modifyProductSearchForPartField.getText());
        } 
        catch (Exception e){
            isInteger = false;
        }
        if (isInteger){
            Part foundPart = Inventory.lookupPart(Integer.parseInt(modifyProductSearchForPartField.getText()));
            foundPartList.add(foundPart);
        }
        else {
            String partName = modifyProductSearchForPartField.getText();
            foundPartList = Inventory.lookupPart(partName);
        }
        if (!foundPartList.isEmpty()){
            modifyProductFullPartLookupTable.setItems(foundPartList);
        }

        if (modifyProductSearchForPartField.getText() == null || "".equals(modifyProductSearchForPartField.getText().trim())){
            modifyProductFullPartLookupTable.setItems(getAllParts());
        
        }
    }

    @FXML
    private void handleModifyProductCancelButton(ActionEvent event) throws IOException {
        Parent main_parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_scene);
        app_stage.show();
    }

    @FXML
    private void handleModifyProductSaveButton(ActionEvent event) throws IOException {
        int id = Integer.parseInt(modifyProductIdField.getText());
        String name = modifyProductNameField.getText();
        double price =  Double.parseDouble(modifyProductPriceField.getText());
        int stock = Integer.parseInt(modifyProductInventoryField.getText());
        int min = Integer.parseInt(modifyProductMinInventoryField.getText());
        int max = Integer.parseInt(modifyProductMaxInventoryField.getText());
       
//For Set 1 Exception Control Option - Check to be sure minimum field can't be above the maximum field     
       if (min > max){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Max / Min Value Error!");
           alert.setHeaderText("Min inventory value cannot exceed Max value!");
           alert.setContentText("Please adjust the minimum inventory level to be below the maximum level in order to save your changes.");
           alert.showAndWait();
       }
       else{

//For Set 2 Exception Control Option - Check that product price can not be less than the cost of parts

       
            double partsPriceTotal = 0.0;       
            for (Part partPane : productParts){
                partsPriceTotal = Double.sum(partsPriceTotal,partPane.getPrice());
            }
            int doub = (Double.compare(price, partsPriceTotal));
            if (doub < 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Product Pricing Error!");
                alert.setHeaderText("Price of parts exceeds price of product!");
                alert.setContentText("Please adjust the pricing of your product \"above the total cost of all included parts\" in order to save changes.");
                alert.showAndWait();
            }
              
            else{
                Product newProduct = new Product(id , name, price, stock, min, max);
                for (Part partPane : productParts){
                    newProduct.addAssociatedPart(partPane);
                }
                Inventory.updateProduct(indx,newProduct);
                Parent main_parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
                Scene main_scene = new Scene(main_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(main_scene);
                app_stage.show();
            }
        }
    }
}
