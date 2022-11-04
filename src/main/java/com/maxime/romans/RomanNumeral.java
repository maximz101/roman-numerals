package com.maxime.romans;

import java.util.List;
import java.util.Optional;

public final class RomanNumeral {
    private final List<RomanSymbol> symbols;

    private RomanNumeral(List<RomanSymbol> list) {
        symbols = list;
    }

    static RomanNumeral from(List<RomanSymbol> list) {
        for (int i = list.size() - 2; i >= 0; i--) {
            var n = list.get(i);
            if (n.isIllegalRight(list.get(i + 1))) {
                throw new InvalidInputException();
            }
        }
        return new RomanNumeral(list);
    }

    public int value() {
        int r = 0;
        int i = 0;

        while (i < symbols.size()) {
            var current = symbols.get(i);
            var maybeNext = getNext(i);
            if (maybeNext.isEmpty()) {
                r += current.value();
                return r;
            }
            var next = maybeNext.get();
            if (current.compareTo(next) < 0) {
                r += next.subtract(current);
                i += 2;
            } else {
                r += current.value();
                i++;
            }
        }
        return r;
    }

    private Optional<RomanSymbol> getNext(int i) {
        return i < symbols.size() - 1 ? Optional.of(symbols.get(i + 1)) : Optional.empty();
    }
}
