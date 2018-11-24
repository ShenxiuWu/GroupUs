package com.github.GroupUs.impl;

import com.github.GroupUs.impl.distance;
import org.junit.Test;

import static org.junit.Assert.*;

public class distanceTest {

    @Test
    public void distanceMatirx() {
        distance test = new distance();
        Integer res = test.distanceMatirx(new String[]{"tt"}, new String [] {"Columbia University"});
        assertNotNull(res);
    }
}
