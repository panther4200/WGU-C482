/*
 * 
 * 
 * 
 */
package romeroinventoryappc482.Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Paul Romero
 */

// This class does not get built (abstract), used by OutsourcedPart or InhousePart

public abstract class Part {
    protected IntegerProperty id;
    protected StringProperty name;
    protected DoubleProperty price;
    protected IntegerProperty stock;
    protected IntegerProperty min;
    protected IntegerProperty max;
    
    //Set Methods
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
    
    //Get Methods
    public int getId(){
        return id.get();
    }
    public String getName(){
        return name.get();
    }
    public double getPrice(){
        return price.get();
    }
    public int getStock(){
        return stock.get();
    }
    public int getMin(){
        return min.get();
    }
    public int getMax(){
        return max.get();
    }

}
    
    