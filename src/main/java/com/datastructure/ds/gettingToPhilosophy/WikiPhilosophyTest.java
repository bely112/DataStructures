package com.datastructure.ds.gettingToPhilosophy;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;

public class WikiPhilosophyTest {

    @Test
    public void testMain() {
        // because this lab is more open-ended than others, we can't provide unit
        // tests.  Instead, we just check that you've modified WikiPhilosophy.java
        // so it doesn't throw an exception
        String[] args = {};
        try {
            WikiPhilosophy.main(args);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}
