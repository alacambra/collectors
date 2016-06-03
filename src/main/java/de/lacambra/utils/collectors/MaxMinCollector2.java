package de.lacambra.utils.collectors;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MaxMinCollector2 implements Collector<Integer, MaxMinContainer, MaxMinContainer>{

    public void accumulate(MaxMinContainer container, Integer val){

        if(container.max == null){
            container.max = val;
        }else if(container.max < val){
            container.max = val;
        }

        if(container.min == null){
            container.min = val;
        }else if(container.min > val){
            container.min = val;
        }

    }

    public MaxMinContainer combine(MaxMinContainer a, MaxMinContainer b){
        if(a.max == null){
            b.getMax().ifPresent(v -> a.max = v);
        }else {
            b.getMax().ifPresent(v -> a.max = a.max < v ? v : a.max);
        }

        if(a.min == null){
            b.getMin().ifPresent(v -> a.min = v);
        }else {
            b.getMax().ifPresent(v -> a.min = a.min > v ? v : a.min);
        }

        return a;
    }

    @Override
    public Supplier<MaxMinContainer> supplier() {
        return MaxMinContainer::new;
    }

    @Override
    public BiConsumer<MaxMinContainer, Integer> accumulator() {
        return this::accumulate;
    }

    @Override
    public BinaryOperator<MaxMinContainer> combiner() {
        return this::combine;
    }

    @Override
    public Function<MaxMinContainer, MaxMinContainer> finisher() {
        return (a) -> a;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return new HashSet<>(Arrays.asList(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED));
    }
}
