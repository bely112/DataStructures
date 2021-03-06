package com.datastructure.ds.interview.bit;

// TC: O(b). SC: O(1). Where b is the length of the sequence(number of bits)
public class FlipBitToWin2 {

    int flipBit(int a) {
        // if all 1s, this is already the longest sequence
        if (~a == 0) return Integer.BYTES * 8;

        int currentLength = 0;
        int previousLength = 0;
        int maxLength = 1; // we can always have a sequence of at least one 1
        while (a != 0) {
            if ((a & 1) == 1) {
                currentLength++;
            } else if ((a & 1) == 0) {
                previousLength = (a & 2) == 0 ? 0 : currentLength;
                currentLength = 0;
            }
            maxLength = Math.max(previousLength + currentLength + 1, maxLength);
            a >>>= 1;
        }
        return maxLength;
    }
}