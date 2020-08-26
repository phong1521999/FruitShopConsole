/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1lp0023;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author PhongFPT
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Fruit> fruitList = new ArrayList<>();
        Hashtable<String, ArrayList<FruitOrder>> customerOrderInfo = new Hashtable<>();
        Manager test=new Manager();
        do{
            test.menu();
            switch(InputClass.inputMenuOption()){
                case 1: test.createFruit(fruitList);
                    break;
                case 2: test.viewOrders(customerOrderInfo);
                    break;
                case 3: test.customerShopping(fruitList,customerOrderInfo);
                    break;
                case 4: System.out.println("Good Bye");
                    return;
            }
        }while(true);
    }
    
}
