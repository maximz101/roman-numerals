package com.maxime.romans;

public sealed interface RomanSymbol extends Comparable<RomanSymbol> permits One, Five, Ten, Fifty, OneHundred, FiveHundred, OneThousand {
    static RomanSymbol charToSymbol(Character c) {
        return switch (c) {
            case 'I' -> new One();
            case 'V' -> new Five();
            case 'X' -> new Ten();
            case 'L' -> new Fifty();
            case 'C' -> new OneHundred();
            case 'D' -> new FiveHundred();
            case 'M' -> new OneThousand();
            default -> throw new InvalidInputException();
        };
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