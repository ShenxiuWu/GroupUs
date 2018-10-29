package com.github.GroupUs;

import org.junit.Test;

import static org.junit.Assert.*;

public class distanceTest {

    @Test
    public void distanceMatirx() {
        distance test = new distance();
        Integer res = test.distanceMatirx(new String[] {}, new String [] {});
        assertNotNull(res);
    }
}
