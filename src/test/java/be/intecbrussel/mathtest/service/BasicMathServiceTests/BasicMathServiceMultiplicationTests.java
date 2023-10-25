package be.intecbrussel.mathtest.service.BasicMathServiceTests;

import be.intecbrussel.mathtest.service.BasicMathService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class BasicMathServiceMultiplicationTests {
    @Autowired
    private BasicMathService basicMathService;

    @ParameterizedTest
    @MethodSource("mathMultiplyData")
    public void testMultiplication_ReturningString(String n1, String n2, String result) {
        String res = basicMathService.multiply(n1, n2);
        Assertions.assertEquals(result, res);
    }

    @ParameterizedTest
    @MethodSource("mathMultiplyExceptionData")
    public void testMultiplication_ThrowingException(String n1, String n2, Class<Exception> result) {
        Assertions.assertThrows(result, () -> basicMathService.multiply(n1, n2));
    }

    private static Stream<Arguments> mathMultiplyData() {
        return Stream.of(
                //res > 0
                Arguments.of("5", "4", "20"), // 5 * 4 = 20
                Arguments.of("10", "3", "30"), // 10 * 3 = 30
                Arguments.of("6", "7", "42"), // 6 * 7 = 42
                Arguments.of("12", "2", "24"), // 12 * 2 = 24
                Arguments.of("12", "2", "24"), // 12 * 2 = 24
                Arguments.of("2.5", "2.0", "5"),
                Arguments.of("3.5", "2", "7"),
                Arguments.of("3", "5.5", "16.5"),

                //res < 0
                Arguments.of("5", "-4", "-20"), // 5 * (-4) = -20
                Arguments.of("-10", "3", "-30"), // (-10) * 3 = -30
                Arguments.of("-6", "7", "-42"), // (-6) * 7 = -42
                Arguments.of("12", "-2", "-24"), // 12 * (-2) = -24
                Arguments.of("2.0", "-2", "-4"),
                Arguments.of("-1", "0.5", "-0.5"),

                //res == 0
                Arguments.of("0", "5", "0"), // 0 * 5 = 0
                Arguments.of("10", "0", "0"), // 10 * 0 = 0
                Arguments.of("0", "-7", "0"), // 0 * (-7) = 0
                Arguments.of("-6", "0", "0"), // (-6) * 0 = 0

                Arguments.of("10", "5", "50"),
                Arguments.of("5", "10", "50"),
                Arguments.of("0", "0", "0"),
                Arguments.of("0", "55", "0"),
                Arguments.of("25", "0", "0"),
                Arguments.of("2000000000", "2000000000", "4000000000000000000")

        );
    }

    private static Stream<Arguments> mathMultiplyExceptionData() {
        return Stream.of(
                Arguments.of("", "55", Exception.class),
                Arguments.of("five", "55", Exception.class),
                Arguments.of("50", "potato", Exception.class)
        );
    }
}
