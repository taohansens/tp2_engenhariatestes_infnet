
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;

import java.util.List;

public class MathFunctionsPropertyTests {
    @Property
    void multiplyByTwoShouldBeGreaterOrEqualForPositiveNumbers(@ForAll @IntRange(min = 1, max = 1073741823) int number) {
        int result = MathFunctions.multiplyByTwo(number);
        Assertions.assertTrue(result >= number,
                () -> "O resultado " + result + " não é maior ou igual a " + number);
    }

    @Provide("positiveNumbers")
    public Arbitrary<Integer> positiveNumbers() {
        return Arbitraries.integers().between(1, 1073741823);
    }

    @Property
    void multiplyByTwoShouldBeGreaterOrEqualWithSafeData(@ForAll("positiveNumbers") int number) {
        int result = MathFunctions.multiplyByTwo(number);
        Assertions.assertTrue(result >= number,
                () -> "Erro: O resultado " + result + " não é maior ou igual a " + number);
    }

    @Property(tries = 90000, edgeCases = EdgeCasesMode.FIRST)
    void multiplyByTwoShouldReturnEvenForEvenInput(@ForAll("positiveNumbers") int number) {
        Assumptions.assumeTrue(number % 2 == 0);
        int result = MathFunctions.multiplyByTwo(number);
        Assertions.assertTrue(result % 2 == 0,
                () -> "Erro: O resultado " + result + " não é par para o número " + number);
    }
}
