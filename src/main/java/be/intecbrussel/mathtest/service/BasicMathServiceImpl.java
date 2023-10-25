package be.intecbrussel.mathtest.service;

import be.intecbrussel.mathtest.model.Operation;
import be.intecbrussel.mathtest.model.OperationType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * @author Me, all me, not Jonathan!
 */
@Service
public class BasicMathServiceImpl implements BasicMathService {
    @Override
    public String add(String number1, String number2) {
        return calculate(number1, number2, OperationType.ADD);
    }

    @Override
    public String subtract(String number1, String number2) {
        return calculate(number1, number2, OperationType.SUBTRACT);
    }

    @Override
    public String multiply(String number1, String number2) {
        return calculate(number1, number2, OperationType.MULTIPLY);
    }

    @Override
    public String divide(String number1, String number2) {
        return calculate(number1, number2, OperationType.DIVIDE);
    }

    private String calculate(String number1, String number2, OperationType operationType) {
        validateNumberStrings(number1, number2);

        BigDecimal n1, n2, result;
        n1 = new BigDecimal(number1);
        n2 = new BigDecimal(number2);

        result = getOperationResult(n1, n2, operationType);

        if (result == null) {
            throw new UnsupportedOperationException(operationType.name() + " IS NOT SUPPORTED");
        }

        return result.stripTrailingZeros().toPlainString();
    }

    private void validateNumberStrings(String... numbers) {
        for (String number : numbers) {
            if (!number.matches("^-?\\d*\\.?\\d+$")) {
                throw new RuntimeException(number + " is not a number.");
            }
        }
    }

    private BigDecimal getOperationResult(BigDecimal n1, BigDecimal n2, OperationType operationType) {
        switch(operationType){
            case ADD -> {
                return n1.add(n2);
            }
            case SUBTRACT -> {
                return n1.subtract(n2);
            }
            case MULTIPLY -> {
                return n1.multiply(n2);
            }
            case DIVIDE -> {
                return n1.divide(n2, 16, RoundingMode.HALF_UP);
            }
            default -> {
                return null;
            }
        }
    }
}
