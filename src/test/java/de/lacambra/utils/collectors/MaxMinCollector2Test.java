package de.lacambra.utils.collectors;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by alacambra on 30/05/16.
 */
public class MaxMinCollector2Test {

    MaxMinCollector2 cut;

    @Before
    public void setUp() throws Exception {

        cut = new MaxMinCollector2();

    }

    @Test
    public void testCollector(){
        MaxMinContainer result = IntStream.of(1, 2, 3, 4, 5, 6).boxed()
                .collect(new MaxMinCollector2());

        assertThat(result.getMax().get(), is(6));
        assertThat(result.getMin().get(), is(1));

        result = IntStream.of(1000, 2, 3342, 421, 523, 6).boxed()
                .collect(new MaxMinCollector2());

        assertThat(result.getMax().get(), is(3342));
        assertThat(result.getMin().get(), is(2));
    }

}