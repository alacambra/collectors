package de.lacambra.utils.collectors;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by alacambra on 30/05/16.
 */
public class Container<T>{

    T max;
    T min;

    public Optional<T> getMax() {
        return Optional.ofNullable(max);
    }

    public Optional<T> getMin() {
        return Optional.ofNullable(min);
    }

}
