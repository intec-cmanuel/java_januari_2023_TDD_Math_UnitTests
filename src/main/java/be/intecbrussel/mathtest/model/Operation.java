package be.intecbrussel.mathtest.model;

import jakarta.persistence.*;

@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String number1;
    private String number2;
    private String result;

    @Enumerated
    private OperationType operationType;

    public String getNumber1() {
        return number1;
    }

    public String getNumber2() {
        return number2;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public String getResult() {
        return result;
    }

    public Operation setData(String number1, String number2, String result, OperationType operationType) {
        this.number1 = number1;
        this.number2 = number2;
        this.result = result;
        this.operationType = operationType;
        return this;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", number1=" + number1 +
                ", number2=" + number2 +
                '}';
    }
}
