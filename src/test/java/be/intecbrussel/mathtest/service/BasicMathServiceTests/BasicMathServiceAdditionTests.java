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
public class BasicMathServiceAdditionTests {
    @Autowired
    private BasicMathService basicMathService;

    @ParameterizedTest
    @MethodSource("mathAddData")
    public void testAdditions_ReturningStrings(String n1, String n2, String expectedResult) {
        String res = basicMathService.add(n1, n2);
        Assertions.assertEquals(expectedResult, res);
    }

    @ParameterizedTest
    @MethodSource("mathAddExceptionData")
    public void testAdditions_ThrowingException(String n1, String n2, Class<Exception> expectedResult) {
        Assertions.assertThrows(expectedResult, () -> basicMathService.add(n1, n2));
    }

    private static Stream<Arguments> mathAddData() {
        return Stream.of(
                Arguments.of("10", "5", "15"),
                Arguments.of("5", "10", "15"),
                Arguments.of("0", "0", "0"),
                Arguments.of("0", "0", "0"),
                Arguments.of("0", "55", "55"),
                Arguments.of("0", "55", "55"),
                Arguments.of("-50", "-40", "-90"),
                Arguments.of("2.5", "4.5", "7"),
                Arguments.of("3.2", "4.4", "7.6"),
                Arguments.of("3.2", "4.4", "7.6"),
                Arguments.of("0.000000000000001", "0.999999999999999", "1"),
                Arguments.of("0", "55", "55"),
                Arguments.of("25", "0", "25"),
                Arguments.of("-25", "12", "-13"),
                Arguments.of("25", "-12", "13"),
                Arguments.of("2000000000", "2000000000", "4000000000"),
                Arguments.of("2000000000000000000000000000", "2000000000000000000000000000", "4000000000000000000000000000"),
                Arguments.of(
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                        "4000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
                )
        );
    }

    private static Stream<Arguments> mathAddExceptionData() {
        return Stream.of(
                Arguments.of("", "55", Exception.class),
                Arguments.of("five", "55", Exception.class),
                Arguments.of("50", "potato", Exception.class)
        );
    }

}
