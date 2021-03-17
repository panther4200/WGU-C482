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
import romeroinventoryappc482.Model.Product;
import romeroinventoryappc482.Model.Inventory;
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
import static romeroinventoryappc482.Model.Inventory.getAllParts;
import static romeroinventoryappc482.Model.Inventory.getAllProducts;

/**
 * FXML Controller class
 *
 * @author Paul Romero
 */
public class AddProductController implements Initializable {

private ObservableList<Part> foundPartList = FXCollections.observableArrayList();
private ObservableList<Part> productParts = FXCollections.observableArrayList();

    @FXML
    private TableView<Part> addProductFullPartLookupTable;
    @FXML
    private TableColumn<Part, Integer> addProductFullPartLookupTablePartIdColumn;
    @FXML
    private TableColumn<Part, String> addProductFullPartLookupTablePartNameColumn;
    @FXML
    private TableColumn<Part, Integer> addProductFullPartLookupTableInventoryLevelColumn;
    @FXML
    private TableColumn<Part, Double> addProductFullPartLookupTablePriceColumn;
    @FXML
    private TableView<Part> addProductIncludedPartsTable;
    @FXML
    private TableColumn<Part, Integer> addProductIncludedPartsTablePartIdColumn;
    @FXML
    private TableColumn<Part, String> addProductIncludedPartsTablePartNameColumn;
    @FXML
    private TableColumn<Part, Integer> addProductIncludedPartsTableInventoryLevelColumn;
    @FXML
    private TableColumn<Part, Double> addProductIncludedPartsTablePriceColumn;
    @FXML
    private Button addProductAddPartToProductButton;
    @FXML
    private Button addProductDeletePartFromProductButton;
    @FXML
    private TextField addProductSearchForPartField;
    @FXML
    private Button addProductSearchForPartButton;
    @FXML
    private Button addProductCancelButton;
    @FXML
    private Button addProductSaveButton;
    @FXML
    private TextField addProductIdField;
    @FXML
    private TextField addProductNameField;
    @FXML
    private TextField addProductInventoryField;
    @FXML
    private TextField addProductPriceField;
    @FXML
    private TextField addProductMaxInventoryField;
    @FXML
    private TextField addProductMinInventoryField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (getAllProducts().isEmpty()){
            addProductIdField.setText("0");
        }
        else{
           int listIndex = (getAllProducts().size()-1);
           addProductIdField.setText(Integer.toString(getAllProducts().get(listIndex).getId()+1));
        }

        addProductFullPartLookupTable.setItems(getAllParts());
        addProductFullPartLookupTablePartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductFullPartLookupTablePartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductFullPartLookupTableInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductFullPartLookupTablePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        addProductIncludedPartsTable.setItems(productParts);
        addProductIncludedPartsTablePartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductIncludedPartsTablePartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductIncludedPartsTableInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductIncludedPartsTablePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }    

    @FXML
    private void handleAddProductAddPartToProductButton(ActionEvent event) {
        Part part = addProductFullPartLookupTable.getSelectionModel().getSelectedItem();
        productParts.add(part);
    }

    @FXML
    private void handleAddProductDeletePartFromProductButton(ActionEvent event) {
        Part selectedAssociatedPart = addProductIncludedPartsTable.getSelectionModel().getSelectedItem();
        productParts.remove(selectedAssociatedPart);        
    }

    @FXML
    private void handleAddProductSearchForPartButton(ActionEvent event) {
        foundPartList.clear();
        boolean isInteger = true;
        try{
            int num = Integer.parseInt(addProductSearchForPartField.getText());
        } 
        catch (Exception e){
            isInteger = false;
        }
        if (isInteger){
            Part foundPart = Inventory.lookupPart(Integer.parseInt(addProductSearchForPartField.getText()));
            foundPartList.add(foundPart);
        }
        else {
            String partName = addProductSearchForPartField.getText();
            foundPartList = Inventory.lookupPart(partName);
        }
        if (!foundPartList.isEmpty()){
            addProductFullPartLookupTable.setItems(foundPartList);
        }

        if (addProductSearchForPartField.getText() == null || "".equals(addProductSearchForPartField.getText().trim())){
            addProductFullPartLookupTable.setItems(getAllParts());
        
        }
    }

    @FXML
    private void handleAddProductCancelButton(ActionEvent event) throws IOException {
        Parent main_parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(main_scene);
        app_stage.show();
    }

    @FXML
    private void handleAddProductSaveButton(ActionEvent event) throws IOException {
       int id = Integer.parseInt(addProductIdField.getText());
       String name = addProductNameField.getText();
       double price =  Double.parseDouble(addProductPriceField.getText());
       int stock = Integer.parseInt(addProductInventoryField.getText());
       int min = Integer.parseInt(addProductMinInventoryField.getText());
       int max = Integer.parseInt(addProductMaxInventoryField.getText());
       
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
                newProduct.addAssociatedPart(partPane);}
                Inventory.addProduct(newProduct);
        
                Parent main_parent = FXMLLoader.load(getClass().getResource("Main.fxml"));
                Scene main_scene = new Scene(main_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(main_scene);
                app_stage.show();
            }
        }
    }
}
