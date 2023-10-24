package be.intecbrussel.mathtest.service;

import be.intecbrussel.mathtest.repository.OperationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Stream;

@SpringBootTest
public class BasicMathServiceTest {
    @Autowired
    private BasicMathService basicMathService;

    private static Stream<Arguments> mathAddData() {
        return Stream.of(
                Arguments.of("10", "5", "15"),
                Arguments.of("5", "10", "15"),
                Arguments.of("0", "0", "0"),
                Arguments.of("0", "55", "55"),
                Arguments.of("25", "0", "25"),
                Arguments.of("2000000000", "2000000000", "4000000000")
        );
    }

    private static Stream<Arguments> mathSubtractData() {
        return Stream.of(
                Arguments.of("10", "5", "5"),
                Arguments.of("5", "10", "-5"),
                Arguments.of("0", "0", "0"),
                Arguments.of("0", "55", "-55"),
                Arguments.of("25", "0", "25"),
                Arguments.of("2000000000", "2000000000", "0")
        );
    }

    private static Stream<Arguments> mathMultiplyData() {
        return Stream.of(
                Arguments.of("10", "5", "50"),
                Arguments.of("5", "10", "50"),
                Arguments.of("0", "0", "0"),
                Arguments.of("0", "55", "0"),
                Arguments.of("25", "0", "0"),
                Arguments.of("2000000000", "2000000000", "4000000000000000000")
        );
    }

    private static Stream<Arguments> mathDivideData() {
        return Stream.of(
                Arguments.of("10", "5", "2"),
                Arguments.of("5", "10", "0.5"),
                Arguments.of("0", "0", ArithmeticException.class),
                Arguments.of("0", "55", "0"),
                Arguments.of("25", "0", ArithmeticException.class),
                Arguments.of("2000000000", "2000000000", "1")
        );
    }

    @ParameterizedTest
    @MethodSource("mathAddData")
    public void testBasicAddition(String n1, String n2, String result) {
        String res = basicMathService.add(n1, n2);
        Assertions.assertEquals(result, res);
    }

    @ParameterizedTest
    @MethodSource("mathSubtractData")
    public void testBasicSubtract(String n1, String n2, String result) {
        String res = basicMathService.subtract(n1, n2);
        Assertions.assertEquals(result, res);
    }

    @ParameterizedTest
    @MethodSource("mathMultiplyData")
    public void testBasicMultiply(String n1, String n2, String result) {
        String res = basicMathService.multiply(n1, n2);
        Assertions.assertEquals(result, res);
    }

    @ParameterizedTest
    @MethodSource("mathDivideData")
    public void testBasicDivision(String n1, String n2, Object result) {
        if (result instanceof String) {
            Object res = basicMathService.divide(n1, n2);
            Assertions.assertEquals(res, result);
        } else if (result instanceof Exception){
            Assertions.assertThrows((Class<? extends Exception>) result, () -> basicMathService.divide(n1, n2));
        } else {
            Assertions.fail();
        }
    }
}
