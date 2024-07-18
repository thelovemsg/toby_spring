package tobyspring.hello_spring;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public List<String> sortByLength(List<String> list) {
        list.sort(Comparator.comparingInt(String::length));
        return list;
    }

    public static void main(String[] args) {
        List<String> scores = Arrays.asList("h","a","d","c");
        scores.sort(Comparator.comparingInt(String::length));
    }
}
