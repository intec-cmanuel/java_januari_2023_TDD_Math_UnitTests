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
public class BasicMathServiceDivisionTests {
    @Autowired
    private BasicMathService basicMathService;

    @ParameterizedTest
    @MethodSource("mathDivideData")
    public void testDivision_ReturningString(String n1, String n2, String result) {
        String res = basicMathService.divide(n1, n2);
        Assertions.assertEquals(result, res);
    }

    @ParameterizedTest
    @MethodSource("mathDivideExceptionData")
    public void testDivision_ThrowingException(String n1, String n2, Class<Exception> result) {
        Assertions.assertThrows(result, () -> basicMathService.divide(n1, n2));
    }

    private static Stream<Arguments> mathDivideData() {
        return Stream.of(
                //res > 0
                Arguments.of("20", "5", "4"), // 20 / 5 = 4
                Arguments.of("1", "3", "0.3333333333333333"), // 30 / 3 = 10
                Arguments.of("42", "7", "6"), // 42 / 7 = 6
                Arguments.of("24", "2", "12"), // 24 / 2 = 12


                //res < 0
                Arguments.of("20", "-5", "-4"), // 20 / (-5) = -4
                Arguments.of("-30", "3", "-10"), // (-30) / 3 = -10
                Arguments.of("-42", "7", "-6"), // (-42) / 7 = -6
                Arguments.of("24", "-2", "-12"), // 24 / (-2) = -12

                //res == 0
                Arguments.of("0", "5", "0"), // 0 / 5 = 0
                Arguments.of("0", "-7", "0"), // 0 / (-7) = 0


                Arguments.of("10", "5", "2"),
                Arguments.of("5", "10", "0.5"),
                Arguments.of("0", "55", "0"),
                Arguments.of("2000000000", "2000000000", "1")
        );
    }

    private static Stream<Arguments> mathDivideExceptionData() {
        return Stream.of(
                Arguments.of("", "55", Exception.class),
                Arguments.of("five", "55", Exception.class),
                Arguments.of("50", "potato", Exception.class),
                Arguments.of("0", "0", ArithmeticException.class),
                Arguments.of("25", "0", ArithmeticException.class)
        );
    }
}
