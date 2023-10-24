package be.intecbrussel.mathtest.service;

import be.intecbrussel.mathtest.model.Operation;
import be.intecbrussel.mathtest.model.OperationType;

import java.util.List;

public interface HistoryService {
    void addNewCalculation(OperationType operationType, String result, String... input);
    List<Operation> retrieveOperations();
    List<Operation> retrieveLastOperation(int amount);
}
