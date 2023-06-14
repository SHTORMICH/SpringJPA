package com.epam.kabaldin.chatgpt.developer;

import com.epam.kabaldin.chatgpt.developer.tests.BinaryNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryNumberTest {
    @Test
    public void testEliminateUnsetBits() {
        assertEquals(255, BinaryNumber.eliminateUnsetBits("11010101010101"));
        assertEquals(7, BinaryNumber.eliminateUnsetBits("111"));
        assertEquals(1, BinaryNumber.eliminateUnsetBits("1000000"));
        assertEquals(0, BinaryNumber.eliminateUnsetBits("000"));
    }
}