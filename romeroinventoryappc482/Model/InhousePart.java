/*
 * 
 * 
 * 
 */
package romeroinventoryappc482.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Paul Romero
 */
public class InhousePart extends Part {
    
private IntegerProperty machineId;
    
    public InhousePart(int id, String name, double price, int stock, int min, int max, int machineId){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.machineId = new SimpleIntegerProperty(machineId);
        
    } 
    public void setMachineId(int machineId){
        this.machineId.set(machineId);
    }
    public int getMachineId(){
        return machineId.get();
    }
}
