package de.lacambra.utils.collectors;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by alacambra on 29/05/16.
 */
public class MaxMinCollectorTest {

    MaxMinCollector cut;

    @Before
    public void setUp() throws Exception {
        cut = new MaxMinCollector();
    }

    @Test
    public void testMaxMinSerie() {

        MaxMinCollector result = IntStream.of(1, 2, 3, 4, 5, 6)
                .collect(MaxMinCollector::new, MaxMinCollector::accumulate, MaxMinCollector::combine);

        assertThat(result.getMax().get(), is(6));
        assertThat(result.getMin().get(), is(1));

        result = IntStream.of(1000, 2, 3342, 421, 523, 6)
                .collect(MaxMinCollector::new, MaxMinCollector::accumulate, MaxMinCollector::combine);

        assertThat(result.getMax().get(), is(3342));
        assertThat(result.getMin().get(), is(2));
    }

    @Test
    public void emptySerie(){
        MaxMinCollector result = IntStream.of()
                .collect(MaxMinCollector::new, MaxMinCollector::accumulate, MaxMinCollector::combine);

        assertThat(result.getMax().isPresent(), is(false));
        assertThat(result.getMin().isPresent(), is(false));
    }

    @Test
    public void oneValueSerie(){
        MaxMinCollector result = IntStream.of(34)
                .collect(MaxMinCollector::new, MaxMinCollector::accumulate, MaxMinCollector::combine);

        assertThat(result.getMax().get(), is(34));
        assertThat(result.getMin().get(), is(34));
    }


    @Test
    public void accumulate() throws Exception {

    }

    @Test
    public void combine() throws Exception {

    }

}