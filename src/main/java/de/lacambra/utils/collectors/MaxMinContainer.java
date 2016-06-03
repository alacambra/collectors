package de.lacambra.utils.collectors;

import java.util.Optional;

/**
 * Created by alacambra on 29/05/16.
 */
public class MaxMinContainer {

    Integer max;
    Integer min;

    public Optional<Integer> getMax() {
        return Optional.ofNullable(max);
    }

    public Optional<Integer> getMin() {
        return Optional.ofNullable(min);
    }
}
