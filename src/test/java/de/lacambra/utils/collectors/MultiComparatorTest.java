package de.lacambra.utils.collectors;

import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.*;

/**
 * Created by alacambra on 30/05/16.
 */
public class MultiComparatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testNoComparators() {

        MultiComparator<Integer> result = IntStream.of(1, 2, 3, 4, 5, 6).boxed()
                .collect(MultiComparator<Integer>::new, MultiComparator::accumulate, MultiComparator::combine);

        assertThat(result.getResults(), is(empty()));
    }

    @Test
    public void testOneComparators() {
        MultiComparator<Integer> result = IntStream.of(1, 2, 3, 4, 5, 6).boxed()
                .collect(() -> new MultiComparator<>((o1, o2) -> o1 - o2),
                        MultiComparator::accumulate,
                        MultiComparator::combine);

        assertThat(result.getResults(), IsCollectionWithSize.hasSize(1));
        assertThat(result.getResults().get(0).getMax().get(), is(6));
        assertThat(result.getResults().get(0).getMin().get(), is(1));
    }

    @Test
    public void testSeveralComparators() {

        Person p1 = new Person(10, 11.1f, 34l);
        Person p2 = new Person(1, 11.4f, 4l);
        Person p3 = new Person(110, 111.1f, 3334l);

        MultiComparator<Person> result = Stream.of(p1, p2, p3).collect(() -> new MultiComparator<>(
                        (o1, o2) -> o1.age - o2.age,
                        (o3, o4) -> Float.compare(o3.weight, o4.weight),
                        (o1, o2) -> Long.compare(o1.size, o2.size)),
                MultiComparator::accumulate,
                MultiComparator::combine);

        assertThat(result.getResults(), IsCollectionWithSize.hasSize(3));

        assertThat(result.getResults().get(0).getMax().get(), is(p3));
        assertThat(result.getResults().get(0).getMin().get(), is(p2));

        assertThat(result.getResults().get(1).getMax().get(), is(p3));
        assertThat(result.getResults().get(1).getMin().get(), is(p1));

        assertThat(result.getResults().get(2).getMax().get(), is(p3));
        assertThat(result.getResults().get(2).getMin().get(), is(p2));
    }

    @Test
    public void accumulate() throws Exception {

    }

    @Test
    public void combine() throws Exception {

    }

    private static class Person {

        int age;
        float weight;
        long size;

        public Person(int age, float weight, long size) {
            this.age = age;
            this.weight = weight;
            this.size = size;
        }
    }
}