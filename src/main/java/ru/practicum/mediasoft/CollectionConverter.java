package ru.practicum.mediasoft;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionConverter {

    public static <T, R> List<R> convertCollection(Collection<T> sourceCollection, Function<T, R> converter) {
        return sourceCollection.stream()
                .map(converter)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<String> strings = List.of("Java", "Generics", "Stream", "Function");
        List<Integer> lengths = convertCollection(strings, String::length);
        System.out.println(lengths);

        List<String> numbers = List.of("1", "2", "3", "42");
        List<Integer> intNumbers = convertCollection(numbers, Integer::parseInt);
        System.out.println(intNumbers);

        List<Integer> integers = List.of(1,2,3,4,5);
        List<String> strings1 = convertCollection(integers, String::valueOf);
        System.out.println(strings1);

        List<String> strings2 = List.of("true", "false");
        List<Boolean> booleans = convertCollection(strings2, Boolean::parseBoolean);
        System.out.println(booleans);

        List<Boolean> booleans1 = List.of(true, false, true);
        List<String> strings3 = convertCollection(booleans1, String::valueOf);
        System.out.println(strings3);
    }

}
