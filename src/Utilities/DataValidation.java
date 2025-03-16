/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.text.Normalizer;

/**
 *
 * @author Lukass
 */
public class DataValidation {
    
    public static boolean checkNumberInMinMax(int number, int min, int max) {
        boolean result = true;
        if (number < min || number > max) {
            result = false;
        }
        return result;
    }

    public static boolean checkStringEmplty(String value) {
        boolean result = true;
        if (value.isEmpty()) {
            result = false;
        }
        return result;
    }

    public static boolean checkStringLengthInRange(String value, int min, int max) {
        String str = Normalizer.normalize(value, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return str.length() >= min && str.length() <= max;
    }

    public static boolean checkStringWithFormat(String value, String pattern) {
        boolean result = false;
        if (value.matches(pattern)) {
            result = true;
        }
        return result;
    }
    
}
