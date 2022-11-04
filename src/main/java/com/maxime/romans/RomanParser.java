package com.maxime.romans;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public final class RomanParser {
    private RomanParser() {
    }

    public static RomanParser create() {
        return new RomanParser();
    }

    private static void checkIfValid(String roman) {
        if (roman == null) {
            throw new InvalidInputException();
        }
        var r = roman.trim().toUpperCase();
        var allowedChars = Pattern.compile("^(([IVXLCDM])(?!\\2{3}))+$").matcher(r);
        if (!allowedChars.matches()) {
            throw new InvalidInputException();
        }
    }

    public RomanNumeral parse(String roman) {
        checkIfValid(roman);
        var r = roman.toUpperCase(Locale.ROOT);

        var list = new ArrayList<RomanSymbol>(r.length());
        for (char c : r.toCharArray()) {
            list.add(RomanSymbol.charToSymbol(c));
        }
        return RomanNumeral.from(list);
    }
}
