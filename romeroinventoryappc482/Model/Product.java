/*
 * 
 * 
 * 
 */
package romeroinventoryappc482.Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Paul Romero
 */
public class Product {
    private IntegerProperty id;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty stock;
    private IntegerProperty min;
    private IntegerProperty max;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
public Product(int id, String name, double price, int stock, int min, int max){
    this.id = new SimpleIntegerProperty(id);
    this.name = new SimpleStringProperty(name);
    this.price = new SimpleDoubleProperty(price);
    this.stock = new SimpleIntegerProperty(stock);
    this.min = new SimpleIntegerProperty(min);
    this.max = new SimpleIntegerProperty(max);
}

// Set Methods
public void setId(int id){
    this.id.set(id);
}
public void setName(String name){
    this.name.set(name);    
}
public void setPrice(double price){
    this.price.set(price);
}
public void setStock(int stock){
    this.stock.set(stock);    
}
public void setMin(int min){
    this.min.set(min);
}
public void setMax(int max){
    this.max.set(max);
}
public void addAssociatedPart(Part part){
    associatedParts.add(part);
}

//Get Methods
public int getId() {
    return id.get();
}
public String getName() {
    return name.get();
}
public double getPrice() {
    return price.get();
}
public int getStock() {
    return stock.get();
}
public int getMin() {
    return min.get();
}
public int getMax() {
    return max.get();
}
public ObservableList<Part> getAllAssociatedParts(){
    return associatedParts;
}

//Other Methods for Associated Parts

   
public boolean deleteAssociatedPart(Part selectedAssociatedPart){
    associatedParts.remove(selectedAssociatedPart);
    
//return not used at this time
    return false;
    
}
}
