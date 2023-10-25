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
public class BasicMathServiceSubtractionTests {
    @Autowired
    private BasicMathService basicMathService;

    @ParameterizedTest
    @MethodSource("mathSubtractData")
    public void testSubtraction_ReturningString(String n1, String n2, String result) {
        String res = basicMathService.subtract(n1, n2);
        Assertions.assertEquals(result, res);
    }

    @ParameterizedTest
    @MethodSource("mathSubtractExceptionData")
    public void testSubtraction_ThrowingException(String n1, String n2, Class<Exception> result) {
        Assertions.assertThrows(result, () -> basicMathService.subtract(n1, n2));
    }

    private static Stream<Arguments> mathSubtractData() {
        return Stream.of(
                //n1 > n2:
                Arguments.of("100", "50", "50"), // 100 - 50 = 50
                Arguments.of("100", "-50", "150"), // 100 - (-50) = 150
                Arguments.of("10", "5", "5"), // 10 - 5 = 5
                Arguments.of("10", "-5", "15"), // 10 - (-5) = 15
                Arguments.of("7", "3", "4"), // 7 - 3 = 4
                Arguments.of("25", "15", "10"), // 25 - 15 = 10
                Arguments.of("7", "-3", "10"), // 7 - (-3) = 10
                Arguments.of("25", "-15", "40"), // 25 - (-15) = 40
                Arguments.of("25", "25", "0"), // 25 - 25 = 0
                Arguments.of("10.5", "5.2", "5.3"),
                Arguments.of("4000000000", "2000000000", "2000000000"),
                Arguments.of("4000000000000000000000000000", "2000000000000000000000000000", "2000000000000000000000000000"),
                Arguments.of(
                        "4000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
                ),


                //n1 < n2:
                Arguments.of("-10", "5", "-15"), // (-10) - 5 = -15
                Arguments.of("-100", "50", "-150"), // (-100) - 50 = -150
                Arguments.of("-7", "3", "-10"), // (-7) - 3 = -10
                Arguments.of("-25", "15", "-40"), // (-25) - 15 = -40
                Arguments.of("-7", "-3", "-4"), // (-7) - (-3) = -4
                Arguments.of("-25", "-15", "-10"),
                Arguments.of("-10", "-5", "-5"), // (-10) - (-5) = -5
                Arguments.of("-100", "-50", "-50"),// (-100) - (-50) = -50
                Arguments.of("-100", "-100", "0"),// (-100) - (-100) = 0
                Arguments.of("-10.5", "-10", "-0.5"),
                Arguments.of("2000000000", "4000000000", "-2000000000"),
                Arguments.of("2000000000000000000000000000", "4000000000000000000000000000", "-2000000000000000000000000000"),
                Arguments.of(
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "4000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "-2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
                ),


                //n1 == n2
                Arguments.of("0", "0", "0"),        //0 0 0
                Arguments.of("10", "10", "0"),
                Arguments.of("4000000000000000000000000000", "4000000000000000000000000000", "0"),
                Arguments.of(
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "0"
                )
        );
    }

    private static Stream<Arguments> mathSubtractExceptionData() {
        return Stream.of(
                Arguments.of("", "55", Exception.class),
                Arguments.of("five", "55", Exception.class),
                Arguments.of("50", "potato", Exception.class)
        );
    }
}
