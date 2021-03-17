/*
 * 
 * 
 * 
 */
package romeroinventoryappc482.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author Paul Romero
 */
public class OutsourcedPart extends Part{
    
private StringProperty companyName;
    
    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.companyName = new SimpleStringProperty(companyName);
    }
    public void setCompanyName(String companyName){
        this.companyName.set(companyName);
    }
    public String getCompanyName(){
        return companyName.get();
    }
}    