package com.maxime.romans;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * https://www.math-only-math.com/roman-numerals.html
 * */
class RomanParserTest {

    private final RomanParser romanParser = RomanParser.create();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create_return_nonNullInstace() {
        assertNotNull(RomanParser.create());
    }

    @Test
    void parse_emptyOrNullParam_exceptionThrown() {
        assertThrows(InvalidInputException.class, () -> romanParser.parse(null));
        assertThrows(InvalidInputException.class, () -> romanParser.parse(""));
    }

    @Test
    void parse_singleLetterParam_correspondingNum() {
        assertEquals(1, romanParser.parse("I").value());
        assertEquals(5, romanParser.parse("V").value());
        assertEquals(10, romanParser.parse("X").value());
        assertEquals(50, romanParser.parse("L").value());
        assertEquals(100, romanParser.parse("C").value());
        assertEquals(500, romanParser.parse("D").value());
        assertEquals(1000, romanParser.parse("M").value());
    }

    @Test
    void parse_repeatedSymbolMoreThan3Times_exceptionThrown() {
        assertThrows(InvalidInputException.class, () -> romanParser.parse("IIII"));
        assertThrows(InvalidInputException.class, () -> romanParser.parse("XXXX"));
        assertThrows(InvalidInputException.class, () -> romanParser.parse("IXXXX"));
        assertThrows(InvalidInputException.class, () -> romanParser.parse("CIIIII"));
    }

    // 4. The symbol V,L and D are not repeated to form a bigger number.
    @Test
    void parse_repeatedVLD_exceptionThrown() {
        assertThrows(InvalidInputException.class, () -> romanParser.parse("IVV"));
        assertThrows(InvalidInputException.class, () -> romanParser.parse("LLL"));
        assertThrows(InvalidInputException.class, () -> romanParser.parse("MDDD"));
    }

    // 1: Multiplication Rule
    @Test
    void parse_repeatedSymbol_multiply() {
        assertEquals(2, romanParser.parse("II").value());
        assertEquals(3, romanParser.parse("III").value());
        assertEquals(20, romanParser.parse("XX").value());
        assertEquals(200, romanParser.parse("CC").value());
        assertEquals(200, romanParser.parse("CC").value());
    }

    // 2. Addition Rule:
    @Test
    void parse_smallerNumberToTheRight_addAll() {
        assertEquals(6, romanParser.parse("VI").value());
        assertEquals(11, romanParser.parse("XI").value());
        assertEquals(15, romanParser.parse("XV").value());
    }

    // 3. Subtraction Rule:
    @Test
    void parse_smallerNumberToTheLeft_Subtract() {
        assertEquals(4, romanParser.parse("IV").value());
        assertEquals(9, romanParser.parse("IX").value());
        assertEquals(40, romanParser.parse("XL").value());
        // We do not subtract 5 from any symbol. VX is not correct.
        assertThrows(InvalidInputException.class, ()-> romanParser.parse("VX"));
        assertThrows(InvalidInputException.class, ()-> romanParser.parse("CVC"));
    }

    @Test
    void parse_randomNumbers_correctValue(){
        assertEquals(42, romanParser.parse("XLII").value());
        assertEquals(1789, romanParser.parse("MDCCLXXXIX").value());
        assertEquals(1914, romanParser.parse("MCMXIV").value());
        assertEquals(1918, romanParser.parse("MCMXVIII").value());
        assertEquals(2000, romanParser.parse("MM").value());
        assertEquals(2022, romanParser.parse("MMXXII").value());
    }
}