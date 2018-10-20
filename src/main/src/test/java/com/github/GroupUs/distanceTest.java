package com.github.GroupUs;

import org.junit.Test;

import static org.junit.Assert.*;

public class distanceTest {

    @Test
    public void geoCoding() {
        distance test = new distance();
        String res = test.geoCoding();
        assertNotNull(res);
    }

    @Test
    public void distanceMatirx() {
        distance test = new distance();
        String res = test.distanceMatirx();
        assertNotNull(res);
    }
}
