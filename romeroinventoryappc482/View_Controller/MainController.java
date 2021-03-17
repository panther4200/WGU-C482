/*
 * 
 * 
 * 
 */
package romeroinventoryappc482.View_Controller;

import java.io.IOException;
import romeroinventoryappc482.Model.Part;
import romeroinventoryappc482.Model.Product;
import javafx.application.Platform;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import romeroinventoryappc482.Model.InhousePart;
import romeroinventoryappc482.Model.Inventory;
import static romeroinventoryappc482.Model.Inventory.deletePart;
import static romeroinventoryappc482.Model.Inventory.deleteProduct;
import static romeroinventoryappc482.Model.Inventory.getAllParts;
import static romeroinventoryappc482.Model.Inventory.getAllProducts;
import romeroinventoryappc482.Model.OutsourcedPart;



/**
 * FXML Controller class
 *
 * @author Paul Romero
 */
public class MainController implements Initializable {
        private static ObservableList<Part> foundPartList = FXCollections.observableArrayList();
        private static ObservableList<Product> foundProductList = FXCollections.observableArrayList();

    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private Button inventorySearchPartButton;
    @FXML
    private Button inventoryModifyPartButton;
    @FXML
    private Button inventoryDeletePartButton;
    @FXML
    private Button inventorySearchProductsButton;
    @FXML
    private Button inventoryAddProductButton;
    @FXML
    private Button inventoryModifyProductButton;
    @FXML
    private Button inventoryDeleteProductButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button inventoryAddPartButton;
    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;
    @FXML
    private TableColumn<Product, Double> productPriceColumn;
    @FXML
    private TextField inventorySearchPartField;
    @FXML
    private TextField inventorySearchProductField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

     
        //Load Parts Table
        partsTable.setItems(getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Load Product Table
        productTable.setItems(getAllProducts());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));     
        
// Uncomment the following to add parts to the table on load
        
        //if (getAllParts().isEmpty()){
        //Part newPart = new InhousePart(0, "Gold Medal", 9.50, 15, 5, 50, 2258);
        //Inventory.addPart(newPart);
        //newPart = new InhousePart(1 , "Silver Medal", 7.50, 33, 5, 50, 2244);
        //Inventory.addPart(newPart);
        //newPart = new InhousePart(2 , "Copper Medal", 6.50, 26, 5, 50, 2217);
        //Inventory.addPart(newPart);
        //newPart = new OutsourcedPart(3 , "Necklass", 1.20, 77, 20, 200, "Necks ties");
        //Inventory.addPart(newPart);
        //}
     
    }    

    @FXML
    private void handleInventoryAddPart(ActionEvent event) throws IOException {
        Parent add_part_parent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene add_part_scene = new Scene(add_part_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(add_part_scene);
        app_stage.show();
    }

    @FXML
    private void handleInventoryDeletePart(ActionEvent event) {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        deletePart(selectedPart);
        partsTable.setItems(getAllParts());
    }

    @FXML
    private void handleInventoryAddProduct(ActionEvent event) throws IOException {
        Parent add_product_parent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene add_product_scene = new Scene(add_product_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(add_product_scene);
        app_stage.show();
    }

    @FXML
    private void handleInventoryDeleteProduct(ActionEvent event) {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        deleteProduct(selectedProduct);
        productTable.setItems(getAllProducts());
    }

    @FXML
    private void handleExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void handleInventorySearchPart(ActionEvent event) {
        foundPartList.clear();
        boolean isInteger = true;
        try{
            int num = Integer.parseInt(inventorySearchPartField.getText());
        } 
        catch (Exception e){
            isInteger = false;
        }
        if (isInteger){
            Part foundPart = Inventory.lookupPart(Integer.parseInt(inventorySearchPartField.getText()));
            foundPartList.add(foundPart);
        }
        else {
            String partName = inventorySearchPartField.getText();
            foundPartList = Inventory.lookupPart(partName);
        }
        if (!foundPartList.isEmpty()){
            partsTable.setItems(foundPartList);
        }

        if (inventorySearchPartField.getText() == null || "".equals(inventorySearchPartField.getText().trim())){
            partsTable.setItems(getAllParts());
        
        }
        
    }

    @FXML
    private void handleInventorySearchProduct(ActionEvent event) {
        foundProductList.clear();
        boolean isInteger = true;
        try{
            int num = Integer.parseInt(inventorySearchProductField.getText());
        } 
        catch (Exception e){
            isInteger = false;
        }
        if (isInteger){
            Product foundProduct = Inventory.lookupProduct(Integer.parseInt(inventorySearchProductField.getText()));
            foundProductList.add(foundProduct);
        }
        else {
            String productName = inventorySearchProductField.getText();
            foundProductList = Inventory.lookupProduct(productName);
        }
        if (!foundProductList.isEmpty()){
        productTable.setItems(foundProductList);
        }
        if (inventorySearchProductField.getText() == null || "".equals(inventorySearchProductField.getText().trim())){
            productTable.setItems(getAllProducts());
        
        }
        
    }
   

    @FXML
    private void handleInventoryModifyPart(ActionEvent event) throws IOException {
        
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        
        if (selectedPart != null) {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
            Parent modify_part_parent = loader.load();
            Scene modify_part_scene = new Scene(modify_part_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ModifyPartController controller = loader.<ModifyPartController>getController();
            controller.setModPart(selectedPart);
            app_stage.setScene(modify_part_scene);      
            app_stage.show();
        }
    }

    @FXML
    private void handleInventoryModifyProduct(ActionEvent event) throws IOException {
        
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        
        if (selectedProduct != null) {
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
            Parent modify_product_parent = loader.load();
            Scene modify_product_scene = new Scene(modify_product_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ModifyProductController controller = loader.<ModifyProductController>getController();
            controller.setModProduct(selectedProduct);
            app_stage.setScene(modify_product_scene);
            app_stage.show();
        }
    }
    
}
