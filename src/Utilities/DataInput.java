/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Lukass
 */
public class DataInput {
    
    public static String getString() {
        String strInput;
        Scanner sc = new Scanner(System.in, "UTF-8");
        strInput = sc.nextLine();
        return strInput;
    }

    public static String getString(String displayMessage) {
        String strInput;
        System.out.print(displayMessage);
        strInput = getString();
        return strInput;
    }

    public static int getIntegerNumber() throws Exception {
        int number = 0;
        String strInput;
        strInput = getString();
        if (!DataValidation.checkStringWithFormat(strInput, "\\d{1,10}")) {
            throw new Exception("DATA INVALID(INTEGER NUMBER)");
        } else {
            number = Integer.parseInt(strInput);
        }
        return number;
    }

    public static int getIntegerNumber(String displayMessage) throws Exception {
        int number = 0;
        System.out.print(displayMessage);
        number = getIntegerNumber();
        return number;

    }

    public static long getLongNumber(String displayMessage) throws Exception {
        long number = 0;
        String strInput = getString(displayMessage);
        if (DataValidation.checkStringEmplty(strInput)) {
            if (!DataValidation.checkStringWithFormat(strInput, "[0-9]+$")) {
                throw new Exception("DATA INVALID(LONG NUMBER)");
            } else {
                number = Long.parseLong(strInput);
            }
        }
        return number;
    }

    public static LocalDate getDate(String displayMessage) throws Exception {
        String strInput;
        LocalDate date = null;
        strInput = getString(displayMessage);
        if (DataValidation.checkStringEmplty(strInput)) {
            try {
                date = LocalDate.parse(strInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } catch (Exception e) {
                throw new Exception("DATA INVALID(DATE)");
            }
        }
        return date;
    }
    
}
