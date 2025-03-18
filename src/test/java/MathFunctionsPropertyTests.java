
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;

public class MathFunctionsPropertyTests {
    @Property
    void multiplyByTwoShouldBeGreaterOrEqualForPositiveNumbers(@ForAll @IntRange(min = 1, max = 1073741823) int number) {
        int result = MathFunctions.multiplyByTwo(number);
        Assertions.assertTrue(result >= number,
                () -> "O resultado " + result + " não é maior ou igual a " + number);
    }
}
