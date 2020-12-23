package com.evolyb;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class StringHelper {
    public static boolean isNullOrEmpty(Object str) {
        return str == null || str.toString().isEmpty();
    }

    public static String generatePassword(int length,
                                          boolean hasNumber,
                                          boolean hasCharNormal,
                                          boolean hasCharUp)
            throws Exception {
        if (length <= 0) return "";
        Random r = new Random();

        String patern = "";
        String numberArr = "0123456789";
        String textArr = "abcdefghijkmnopqrstuvwxyz";

        if (hasNumber) patern += numberArr;
        if (hasCharNormal) patern += textArr;
        if (hasCharUp) patern += textArr.toUpperCase();
        if (patern.equals("")) {
            throw new Exception("Method generate passwors must have any number or char or char upcase option.");
        }

        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = patern.charAt(r.nextInt(patern.length()));
        }

        return new String(chars);
    }

    // Use 0 instead of # for keeping the length of string
    public static String getNumberFormat(Object input, int numbOfDecimalPlace) {
        if (StringHelper.isNullOrEmpty(input) || !(input instanceof Number) || numbOfDecimalPlace <0) return "0";
        String pattern = "##,###";
        if (numbOfDecimalPlace > 0) pattern += ".";
        for (int i = 0; i <numbOfDecimalPlace ; i++) {
            pattern += "#";
        }

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');

        DecimalFormat formatter = new DecimalFormat(pattern + ";-#", otherSymbols);
        return formatter.format(new BigDecimal(input.toString()));
    }
}
