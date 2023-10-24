package be.intecbrussel.mathtest.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class BasicMathServiceImpl implements BasicMathService{
    @Override
    public String add(String number1, String number2) {
        if (!number1.matches("^-?\\d*\\.?\\d+$") || !number2.matches("^-?\\d*\\.?\\d+$")) {
            throw new RuntimeException("Invalid number Strings.");
        }

        BigDecimal n1, n2, sum;
        n1 = new BigDecimal(number1);
        n2 = new BigDecimal(number2);
        sum = n1.add(n2);
        return sum.stripTrailingZeros().toPlainString();
    }

    @Override
    public String subtract(String number1, String number2) {
        return null;
    }

    @Override
    public String multiply(String number1, String number2) {
        return null;
    }

    @Override
    public String divide(String number1, String number2) {
        return null;
    }
}
