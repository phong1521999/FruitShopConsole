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
public class Manager {

    //display program menu
    void menu() {
        System.out.println("------------FRUIT SHOP SYSTEM------------");
        System.out.println("1. Create Fruit");
        System.out.println("2. View Orders");
        System.out.println("3. Shopping(for clients)");
        System.out.println("4. Exit");
    }

    //perform option 1: create fruit
    void createFruit(ArrayList<Fruit> fruitList) {
        int fruitID;
        String fruitName, fruitOrigin;
        double price;
        int quantity;
        String continueOption;
        do {
            System.out.println("--------Create fruit--------");      
            fruitName = InputClass.inputFruitName();
            fruitOrigin = InputClass.inputFruitOriginName();
            //do not allow user input duplicated fruit
            if (IsExistClass.isExistFruit(fruitList, fruitName, fruitOrigin)) {
                System.out.println("Your fruit is already in shop");
            } 
            //create a new fruit product and store it
            else {
                price = InputClass.inputFruitPrice();
                quantity = InputClass.inputFruitQuantity();
                //Fruit ID increase automatically
                fruitID = fruitList.size() + 1;
                fruitList.add(new Fruit(fruitID, fruitName, price, quantity, fruitOrigin));
                System.out.println("Create sucessfully");
            }
            //ask user to continue create fruit product
            System.out.println("Do you want to continue (Y/N)?  ");
            continueOption = InputClass.inputContinueOption();
        } while (continueOption.equalsIgnoreCase("y"));
        //when user dont want to continue, display all fruits have been created
        System.out.format("%-15s%-20s%-15s%-15s%s", "| ++ Item ++ | ", "++ Fruit Name ++ | ", "++ Origin ++ | ",
                "++ Price ++ |  ", "++ Quantity ++");
        System.out.println("");
        for (Fruit fruit : fruitList) {
            System.out.format("%-15s%-20s%-15s%-15s%s\n", "     "+fruit.getID(), "   "+fruit.getName(), "   "+fruit.getOrigin(), "   "+fruit.getPrice()+"$", "     "+fruit.getQuantity());
        }
    }

    //perform option 2: View Orders
    void viewOrders(Hashtable<String, ArrayList<FruitOrder>> customerOrderInfo) {
        //notify user when shop haven't had any order yet
        if (customerOrderInfo.isEmpty()) {
            System.out.println("Shop has no order");
            return;
        } 
        //save "customerOrderInfo" key list into "keySet"
        Set<String> keySet = customerOrderInfo.keySet();
        System.out.println(keySet);
        //display all orders
        for (String key : keySet) {
            double total=0;
            System.out.println("Customer name: " + key);
            System.out.format("%-5s%-10s%-15s%-10s%s", "", "Product  |  ", "  Quantity  |", "Price  |", "Amount\n");
            ArrayList<FruitOrder> listOfOrders = customerOrderInfo.get(key);
            int i = 1;
            //display "listOfOrders" corresponding with customer name
            for (FruitOrder fruitOrder : listOfOrders) {
                System.out.format("%-5s%-10s%-15s%-10s%s", " "+i, " "+fruitOrder.getName(), "\t"+fruitOrder.getQuantity(), "  "+fruitOrder.getPrice()+"$", "  "+fruitOrder.getAmount()+"$");
                System.out.println("");
                i++;
                total+=fruitOrder.getAmount();
            }
            System.out.println("Total "+ total);
        }
    }
    
    
    int getIndexByName(ArrayList<FruitOrder> customerOrderList,String name){
        for (int i = 0; i < customerOrderList.size(); i++) {
            if(customerOrderList.get(i).getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }
    //check if store has any fruit left
    boolean isAnyFruitLeft(ArrayList<Fruit> fruitList){
        for (Fruit fruit : fruitList) {
            if(fruit.getQuantity()!=0){
                return true;
            }
        }
        return false;
    }
    //perform option 3: shopping
    void customerShopping(ArrayList<Fruit> fruitList, Hashtable<String, ArrayList<FruitOrder>> customerOrderInfo) {
        String customerName;
        ArrayList<FruitOrder> listOfOrder = new ArrayList<>();
        int fruitID;
        Fruit fruitOrder = null;
        int quantity;
        //notify user when shop has nothing
        if (fruitList.isEmpty()||!isAnyFruitLeft(fruitList)) {
            System.out.println("There is no fruit to buy, sorrry :(");
            return;
        }
        
        String continueShopping = null;
        do {
            quantity = 0;
            System.out.format("%-15s%-20s%-15s%s", "| ++ Item ++ | ", "++ Fruit Name ++ | ", "++ Origin ++ | ", "++ Price ++ |  \n");
            //display the list of fruit for customer
            for (Fruit fruit : fruitList) {
                System.out.format("%-15s%-15s%-11s%s\n", "\t"+fruit.getID(), "\t"+fruit.getName(), "\t"+fruit.getOrigin(), "   "+fruit.getPrice()+"$");
            }
            //customer select fruit item
            fruitID = InputClass.inputCustomerSelectedItem(fruitList);
            //read fruit list
            for (Fruit fruit : fruitList) {
                //take the selected fruit
                if (fruit.getID() == fruitID) {
                    fruitOrder = fruit;
                    break;
                }
            }
            System.out.println("You selected: " + fruitOrder.getName());
            //ask user to re-enter number of fruit when their order is greater than available fruit
            do {
                quantity = InputClass.inputFruitQuantity();
                if(fruitOrder.getQuantity()==0){
                    //notify user when this fruit product has nothing left
                    System.out.println("there is nothing "+fruitOrder.getName()+" left");
                    return;
                }
                if (quantity > fruitOrder.getQuantity()) {
                    //notify user when user order 
                    System.out.println("Don't have enough " + fruitOrder.getName());
                }
            }
            while (quantity > fruitOrder.getQuantity());

            // if fruit order is already in orderList
            if (IsExistClass.isExistOrderFruit(listOfOrder, fruitOrder.getName())) {
                int fruitOrderIndex = -1;
                //get the index of fruitOrder in fruitList
                for (int i = 0; i < fruitList.size(); i++) {
                    if (fruitOrder.getName().equalsIgnoreCase(fruitList.get(i).getName())) {
                        fruitOrderIndex = i;
                        break;
                    }
                }
                //update quantity and amout of fruitOrder
                listOfOrder.get(fruitOrderIndex).setQuantity(listOfOrder.get(fruitOrderIndex).getQuantity() + quantity);
                listOfOrder.get(fruitOrderIndex).setAmount(listOfOrder.get(fruitOrderIndex).getAmount() + quantity * fruitOrder.getPrice());
                //minus quantity of fruit in fruitList
                fruitOrder.setQuantity(fruitOrder.getQuantity() - quantity);

            }
            //if fruit order is new 
            else {
                //create new order, stort in listOfOrder
                listOfOrder.add(new FruitOrder(fruitOrder.getName(), quantity, fruitOrder.getPrice(), quantity * fruitOrder.getPrice()));
                //minus quantity of fruit in fruitList
                fruitOrder.setQuantity(fruitOrder.getQuantity() - quantity);
            }
            //ask user to continue shopping
            System.out.println("Do you want to order now (Y/N)");
            continueShopping = InputClass.inputContinueOption();
        } while (continueShopping.equalsIgnoreCase("n"));
        //when customer finishes their shopping, ask them to input their name
        customerName = InputClass.inputCustomerName();
        //if customer is already exist in customerOrderInfo
        if (IsExistClass.isExistCustomer(customerOrderInfo, customerName)) {
            //get this customer data
            ArrayList<FruitOrder> customerIndividualOrderList=customerOrderInfo.get(customerName);
            for (FruitOrder fruit : listOfOrder) {
                int indexOfUpdatedFruit;
                //if the fruitOrder of listOfOrder exist in "customerOrderList"
                if(IsExistClass.isExistOrderFruit(customerIndividualOrderList, fruit.getName())){
                    //get the index of this fruitOrder in "customerOrderList"
                    indexOfUpdatedFruit=getIndexByName(customerIndividualOrderList, fruit.getName());
                    //update this fruitOrder information
                    customerIndividualOrderList.get(indexOfUpdatedFruit).setQuantity(quantity+customerIndividualOrderList.get(indexOfUpdatedFruit).getQuantity());
                    customerIndividualOrderList.get(indexOfUpdatedFruit).setAmount(quantity*fruit.getPrice()+customerIndividualOrderList.get(indexOfUpdatedFruit).getAmount());
                }
                //if the fruitOrder of listOfOrder is new
                else{
                    //store this fruitOrder 
                    customerOrderInfo.get(customerName).add(fruit); 
                }
               
            }
            //display listOfOrder
            System.out.format("%-5s%-10s%-15s%-10s%s", "", "Product  |  ", "  Quantity  |", "Price  |", "Amount\n"); 
            int i = 1;
            double totalprice = 0;
            for (FruitOrder fruit : customerIndividualOrderList) {
                System.out.format("%-5s%-10s%-15s%-10s%s", i, fruit.getName(), fruit.getQuantity(), fruit.getPrice()+"$", fruit.getAmount()+"$");
                System.out.println("");
                i++;
                totalprice+=fruit.getAmount();
            }
            System.out.println("Total: " + totalprice);
        }
        //if the customer is new
        else {
            //put the customer order data into "customerOrderInfo"
            customerOrderInfo.put(customerName, listOfOrder);
            //display listOfOrder
            System.out.println("Product | Quantity | Price | Amount");
            double totalprice = 0;
            for (FruitOrder fruit : listOfOrder) {
                System.out.println(fruit.getName() + " | " + "  " + fruit.getQuantity() + "  | "
                        + fruit.getPrice() + "$  |" + fruit.getAmount() + "$");
                totalprice += fruit.getAmount();
            }
            System.out.println("Total: " + totalprice);
        }
    }
    //get index by name
   
    
}
