/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1lp0023;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 *
 * @author PhongFPT
 */
public class IsExistClass {

    static public boolean isExistID(ArrayList<Fruit> fruitList, int fruitID) {
        for (Fruit fruit : fruitList) {
            if (fruitID == fruit.getID()) {
                return true;
            }
        }
        return false;
    }

    static public boolean isExistFruit(ArrayList<Fruit> fruitList, String name, String origin) {
        for (Fruit fruit : fruitList) {
            if (fruit.getName().equalsIgnoreCase(name) && fruit.getOrigin().equalsIgnoreCase(origin)) {
                return true;
            }
        }
        return false;
    }
    static public boolean isExistCustomer(Hashtable<String, ArrayList<FruitOrder>> customerOrderInfo, String customerName){
        Set<String> customerKey=customerOrderInfo.keySet();
        for(String key: customerKey){
            if(key.equalsIgnoreCase(customerName)){
                return true;
            }
        }
        return false;
    }
    static public boolean isExistOrderFruit(ArrayList<FruitOrder> listOfOrder, String fruitName){
        for (FruitOrder fruit : listOfOrder) {
            if(fruit.getName().equalsIgnoreCase(fruitName)) return true;
        }
        return false;
    }
}
