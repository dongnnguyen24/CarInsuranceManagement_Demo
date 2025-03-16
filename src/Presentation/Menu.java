/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Utilities.DataInput;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Lukass
 */
public class Menu {
    
    public static void print(String str) {
        List<String> menuList = Arrays.asList(str.split("\\|"));
        menuList.forEach(menuItem -> {
            if (menuItem.equalsIgnoreCase("SELECT")) {
                System.out.println(menuItem);
            } else {
                System.out.println(menuItem);
            }
        });
    }

    public static int getUserChoice() {
        int number = 0;
        try {
            number = DataInput.getIntegerNumber();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return number;
    }
    
}
