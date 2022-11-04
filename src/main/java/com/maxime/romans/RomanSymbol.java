package com.maxime.romans;

import java.util.Map;
import java.util.Set;

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

    Set<RomanSymbol> allowedRight();

    default boolean isIllegalRight(RomanSymbol symbol) {
        return !allowedRight().contains(symbol);
    }

    default int subtract(RomanSymbol other) {
        return value() - other.value();
    }

    @Override
    default int compareTo(RomanSymbol other) {
        return Integer.compare(value(), other.value());
    }
}

record One() implements RomanSymbol {
    @Override
    public int value() {
        return 1;
    }

    @Override
    public Set<RomanSymbol> allowedRight() {
        return Set.of(SYMBOLS.get(I), SYMBOLS.get(V), SYMBOLS.get(X));
    }
}

record Five() implements RomanSymbol {
    @Override
    public int value() {
        return 5;
    }

    @Override
    public Set<RomanSymbol> allowedRight() {
        return Set.of(SYMBOLS.get(I));
    }
}

record Ten() implements RomanSymbol {

    @Override
    public int value() {
        return 10;
    }

    @Override
    public Set<RomanSymbol> allowedRight() {
        return Set.of(SYMBOLS.get(I), SYMBOLS.get(V), SYMBOLS.get(X), SYMBOLS.get(L), SYMBOLS.get(C));
    }

}

record Fifty() implements RomanSymbol {
    @Override
    public int value() {
        return 50;
    }

    @Override
    public Set<RomanSymbol> allowedRight() {
        return Set.of(SYMBOLS.get(I), SYMBOLS.get(V), SYMBOLS.get(X), SYMBOLS.get(D));
    }
}

record OneHundred() implements RomanSymbol {

    @Override
    public int value() {
        return 100;
    }

    @Override
    public Set<RomanSymbol> allowedRight() {
        return Set.of(new One(), SYMBOLS.get(V), SYMBOLS.get(X), SYMBOLS.get(L), SYMBOLS.get(C), SYMBOLS.get(D), SYMBOLS.get(M));
    }

}

record FiveHundred() implements RomanSymbol {
    @Override
    public int value() {
        return 500;
    }

    @Override
    public Set<RomanSymbol> allowedRight() {
        return Set.of(SYMBOLS.get(I), SYMBOLS.get(V), SYMBOLS.get(X), SYMBOLS.get(L), SYMBOLS.get(C));
    }

}

record OneThousand() implements RomanSymbol {
    @Override
    public int value() {
        return 1000;
    }

    @Override
    public Set<RomanSymbol> allowedRight() {
        return Set.of(SYMBOLS.get(I), SYMBOLS.get(V), SYMBOLS.get(X), SYMBOLS.get(L), SYMBOLS.get(C), SYMBOLS.get(D), SYMBOLS.get(M));
    }

}