package de.lacambra.utils.collectors;


import java.util.Optional;

public class MaxMinCollector{

    Integer max;
    Integer min;

    public void accumulate(Integer val){

        if(max == null){
            max = val;
        }else if(max < val){
            max = val;
        }

        if(min == null){
            min = val;
        }else if(min > val){
            min = val;
        }

    }

    public void combine(MaxMinCollector other){
        if(max == null){
            other.getMax().ifPresent(v -> max = v);
        }else {
            other.getMax().ifPresent(v -> max = max < v ? v : max);
        }

        if(min == null){
            other.getMin().ifPresent(v -> min = v);
        }else {
            other.getMax().ifPresent(v -> min = min > v ? v : min);
        }
    }

    public Optional<Integer> getMax() {
        return Optional.ofNullable(max);
    }

    public Optional<Integer> getMin() {
        return Optional.ofNullable(min);
    }
}
