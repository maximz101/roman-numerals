package com.maxime.romans;

import java.util.List;
import java.util.Optional;

public final class RomanNumeral {
    private final List<RomanSymbol> symbols;

    private RomanNumeral(List<RomanSymbol> list) {
        symbols = list;
    }

    static RomanNumeral from(List<RomanSymbol> list) {
        return new RomanNumeral(list);
    }

    public int value() {
        int r = 0;
        int i = 0;

        while (i < symbols.size()) {
            var current = symbols.get(i);
            var maybeNext = getNext(i);

            if (maybeNext.isPresent() && current.compareTo(maybeNext.get()) < 0) {
                r += maybeNext.get().subtract(current);
                i += 2;
                continue;
            }

            r += current.value();
            i++;
        }
        return r;
    }

    private Optional<RomanSymbol> getNext(int i) {
        return i < symbols.size() - 1 ? Optional.of(symbols.get(i + 1)) : Optional.empty();
    }
}
