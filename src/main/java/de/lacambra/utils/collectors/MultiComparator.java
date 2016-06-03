package de.lacambra.utils.collectors;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by alacambra on 30/05/16.
 */
public class MultiComparator<T> {

    List<Comparator<T>> comparators;
    List<Container<T>> results;

    public MultiComparator(Comparator<T>... comparator) {
        comparators = Arrays.asList(comparator);
        results = comparators.stream().map(c -> new Container<T>()).collect(toList());
    }

    public void accumulate(T var) {

        for (int i = 0; i < comparators.size(); i++) {
            Comparator<T> c = comparators.get(i);
            Container<T> r = results.get(i);

            if(r.getMax().isPresent()){
                if (c.compare(r.getMax().get(), var) < 0) {
                    r.max = var;
                }
            }else {
                r.max = var;
            }

            if(r.getMin().isPresent()){
                if (c.compare(r.getMin().get(), var) > 0) {
                    r.min = var;
                }
            }else {
                r.min = var;
            }

        }
    }

    public void combine(MultiComparator<T> other) {

        for (int i = 0; i < comparators.size(); i++) {


            Comparator<T> c = comparators.get(i);
            Container<T> r = results.get(i);

            if(r.getMax().isPresent()){
                if (c.compare( r.getMax().get(), other.results.get(i).getMax().orElse(r.getMax().get())) < 0) {
                    r.max = other.results.get(i).getMax().get();
                }
            }else if(other.results.get(i).getMax().isPresent()){
                r.max = other.results.get(i).getMax().get();
            }

            if(r.getMin().isPresent()){
                if (c.compare(r.getMin().get(), other.results.get(i).getMin().orElse(r.getMin().get())) > 0) {
                    r.min = other.results.get(i).getMin().get();
                }
            }else if(other.results.get(i).getMin().isPresent()){
                r.min = other.results.get(i).getMin().get();
            }
        }
    }

    public List<Container<T>> getResults() {
        return results;
    }
}
