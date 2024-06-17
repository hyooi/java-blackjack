package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.IntPredicate;

import static org.assertj.core.api.Assertions.assertThat;

class LambdaTest {
    private List<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = List.of(1, 2, 3, 4, 5, 6);
    }

    @Test
    void sumAll() {
        final int result = sumAll(numbers);
        assertThat(result).isEqualTo(21);
    }

    @Test
    void sumAllEven() {
        final int result = sumAllEven(numbers);
        assertThat(result).isEqualTo(12);
    }

    @Test
    void sumOverThree() {
        final int result = sumOverThree(numbers);
        assertThat(result).isEqualTo(15);
    }

    private int sumWithFilter(List<Integer> numbers) {
        return sumWithFilter(numbers, num -> true);
    }

    private int sumWithFilter(List<Integer> numbers, IntPredicate predicate) {
        return numbers.stream()
                .mapToInt(value -> value)
                .filter(predicate)
                .sum();
    }

    private int sumAll(List<Integer> numbers) {
        return sumWithFilter(numbers);
    }

    private int sumAllEven(List<Integer> numbers) {
        return sumWithFilter(numbers, num -> num % 2 == 0);
    }

    private int sumOverThree(List<Integer> numbers) {
        return sumWithFilter(numbers, num -> num > 3);
    }
}
