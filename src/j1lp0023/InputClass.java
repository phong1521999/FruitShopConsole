

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1lp0023;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PhongFPT
 */
public class InputClass {
    //input program menu option
    static public int inputMenuOption() {
        Scanner sc = new Scanner(System.in);
        String inputOptionString;
        int inputOption;
        do {
            System.out.print("Your option: ");
            inputOptionString = sc.nextLine().trim();
            try {
                inputOption = Integer.parseInt(inputOptionString);
                if (!(inputOption >= 1 && inputOption <= 4)) {
                    System.out.println("Your option does not available");
                } else {
                    return inputOption;
                }
            } catch (NumberFormatException e) {
                if(inputOptionString.isEmpty()) System.out.println("You input nothing");
                else System.out.println("Your input \"" + inputOptionString + "\" is not a digit");
            }
        } while (true);
    }
    //input fruit name
    static public String inputFruitName() {
        Scanner sc = new Scanner(System.in);
        String fruitName;
        do {            
           System.out.print("Input Fruit Name: ");
           fruitName = sc.nextLine().trim(); 
           if(!fruitName.isEmpty()) return fruitName;
           System.out.println("You input nothing");
        } while (true); 
    }
    //input origin name
    static public String inputFruitOriginName() {
        Scanner sc = new Scanner(System.in);
        String originName;
        do {            
           System.out.print("Input Fruit Origin: ");
           originName = sc.nextLine().trim(); 
           if(!originName.isEmpty()) return originName;
           System.out.println("You input nothing");
        } while (true); 
    }
    //input customer name
    static public String inputCustomerName(){
        Scanner sc = new Scanner(System.in);
        String customerName;
        do {            
           System.out.print("Input your name: ");
           customerName = sc.nextLine().trim(); 
           if(!customerName.isEmpty()) return customerName;
           System.out.println("You input nothing");
        } while (true); 
    }
    //input fruit quantity
    static public int inputFruitQuantity() {
        Scanner sc = new Scanner(System.in);
        String fruitQuantityString;
        int fruitQuantity;
        do {
            System.out.print("Input fruit quantity: ");
            fruitQuantityString = sc.nextLine().trim();
            try {
                fruitQuantity = Integer.parseInt(fruitQuantityString);
                if(fruitQuantity<0) System.out.println("Quantity cannnot be negative number");
                else if(fruitQuantity==0){
                    System.out.println("You buy nothing");
                }else return fruitQuantity;
            } catch (NumberFormatException e) {
                if(fruitQuantityString.isEmpty()) System.out.println("You input nothing");
                else System.out.println("Your input \"" + fruitQuantityString + "\" is not a digit");
            }
        } while (true);
    }
    //input fruit price
    static public double inputFruitPrice() {
        Scanner sc = new Scanner(System.in);
        String fruitQuantityString;
        double fruitQuantity;
        do {
            System.out.print("Input fruit price: ");
            fruitQuantityString = sc.nextLine().trim();
            try {
                fruitQuantity = Double.parseDouble(fruitQuantityString);
                if(fruitQuantity<0) System.out.println("Fruit Price cannnot be negative number");
                else return fruitQuantity;
            } catch (NumberFormatException e) {
                if(fruitQuantityString.isEmpty()) System.out.println("You input nothing");
                else System.out.println("Your input \"" + fruitQuantityString + "\" is not a digit");
            }
        } while (true);
    }
    //service for option 2: input customer select item
    static public int inputCustomerSelectedItem(ArrayList<Fruit> fruitList) {
        
        Scanner sc = new Scanner(System.in);
        String fruitQuantityString;
        int fruitQuantity;
        do {
            System.out.print("Please select your item:  ");
            fruitQuantityString = sc.nextLine().trim();
            try {
                fruitQuantity = Integer.parseInt(fruitQuantityString);
                if(!(fruitQuantity>=1&&fruitQuantity<=fruitList.size())) System.out.println("Your selected is not exist");
                else return fruitQuantity;
            } catch (NumberFormatException e) {
                if(fruitQuantityString.isEmpty()) System.out.println("You input nothing");
                else System.out.println("Your input \"" + fruitQuantityString + "\" is not a digit");
            }
        } while (true);
    }
    //make sure user input only "y" or "n", then return
    static public String inputContinueOption() {
        String option;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Your option: ");
            option = sc.nextLine().toLowerCase().trim();
            if(option.isEmpty()) System.out.println("You input nothing");
            else if (option.equalsIgnoreCase("y") || option.equalsIgnoreCase("n")) {
                return option;
            } else {
                System.out.println("Your input \"" + option + "\" is not a choice, please re-input");
            }
        } while (true);
    }
}
