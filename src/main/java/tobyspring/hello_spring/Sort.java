package tobyspring.hello_spring;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public static void main(String[] args) {
        List<String> scores = Arrays.asList("h","a","d","c");
        Collections.sort(scores, Comparator.comparingInt(String::length));
    }
}
