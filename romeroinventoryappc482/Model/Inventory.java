/*
 * 
 * 
 * 
 */
package romeroinventoryappc482.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Paul Romero
 */
public class Inventory {
    
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int indx;


public Inventory() {
    
}
public static void addPart(Part newPart) {
    allParts.add(newPart);
    
}
public static void addProduct(Product newProduct) {
    allProducts.add(newProduct);
    
}
public static Part lookupPart(int partId){
    
    for (int i = 0; i < allParts.size(); i++) {
        if (allParts.get(i).getId() == partId){
            indx = i;
        }
    }
    Part returnPart = allParts.get(indx);
    return returnPart;

} 
public static Product lookupProduct(int productId){
    
    for (int i = 0; i < allProducts.size(); i++) {
        if (allProducts.get(i).getId() == productId){
            indx = i;
        }
    }
    Product returnProduct = allProducts.get(indx);
    return returnProduct;

}
public static ObservableList<Part> lookupPart(String partName){
    ObservableList<Part> foundPartList = FXCollections.observableArrayList();    
    for (int i = 0; i < allParts.size(); i++) {
        if (allParts.get(i).getName().toLowerCase().contains(partName.toLowerCase())){
            indx = i;
            foundPartList.add(allParts.get(indx));
        }   
    }

    return foundPartList;
}
public static ObservableList<Product> lookupProduct(String productName){
    ObservableList<Product> foundProductList = FXCollections.observableArrayList();    
    for (int i = 0; i < allProducts.size(); i++) {
        if (allProducts.get(i).getName().toLowerCase().contains(productName.toLowerCase())){
            indx = i;
            foundProductList.add(allProducts.get(indx));
        }
    }

    return foundProductList;
}
public static void updatePart(int index, Part selectedPart) {
    allParts.set(index, selectedPart);
    
}
public static void updateProduct(int index, Product newProduct) {
    allProducts.set(index, newProduct);
    
}
public static boolean deletePart(Part selectedPart) {
    allParts.remove(selectedPart);
    
    //boolean return not used at this time
    return false;
}
public static boolean deleteProduct(Product selectedProduct) {
    allProducts.remove(selectedProduct);
    
    //boolean return not used at this time
    return false;
}
public static ObservableList<Part> getAllParts() {
    return allParts;
    
}
public static ObservableList<Product> getAllProducts() {
    return allProducts;
}
}