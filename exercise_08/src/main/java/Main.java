import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        String[] arr = new String[]{"A", "B", "A", "B", "C", "A"};

        String result = Arrays.stream(arr)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .get().getKey();

        System.out.println(result);

        List<Person> persons = List.of(
                new Person("Max", 23, 500000),
                new Person("Doni", 23, 1000000),
                new Person("Eroha", 24, 150000),
                new Person("Galik", 24, 0),
                new Person("Kana", 23, 500000)
        );

        persons.stream().mapToDouble(Person::getSalary).average().ifPresent(System.out::println);

        final int N = 2;
        persons.stream().sorted((o1, o2) -> o2.age - o1.age).limit(N).map(Person::getName).forEach(System.out::println);
    }
}
