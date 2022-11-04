package com.maxime.romans;

import java.util.Map;

public sealed interface RomanSymbol extends Comparable<RomanSymbol> permits One, Five, Ten, Fifty, OneHundred, FiveHundred, OneThousand {

    Character I = 'I';
    Character V = 'V';
    Character X = 'X';
    Character L = 'L';
    Character C = 'C';
    Character D = 'D';
    Character M = 'M';
    Map<Character, RomanSymbol> SYMBOLS = Map.of(
            I, new One(),
            V, new Five(),
            X, new Ten(),
            L, new Fifty(),
            C, new OneHundred(),
            D, new FiveHundred(),
            M, new OneThousand()
    );

    static RomanSymbol charToSymbol(Character c) {
        return SYMBOLS.get(c);
    }

    int value();

    @Override
    default int compareTo(RomanSymbol other) {
        return Integer.compare(value(), other.value());
    }

    default int subtract(RomanSymbol other) {
        return value() - other.value();
    }
}

record One() implements RomanSymbol {
    @Override
    public int value() {
        return 1;
    }
}

record Five() implements RomanSymbol {
    @Override
    public int value() {
        return 5;
    }
}

record Ten() implements RomanSymbol {
    @Override
    public int value() {
        return 10;
    }
}

record Fifty() implements RomanSymbol {
    @Override
    public int value() {
        return 50;
    }
}

record OneHundred() implements RomanSymbol {
    @Override
    public int value() {
        return 100;
    }
}

record FiveHundred() implements RomanSymbol {
    @Override
    public int value() {
        return 500;
    }
}

record OneThousand() implements RomanSymbol {
    @Override
    public int value() {
        return 1000;
    }
}