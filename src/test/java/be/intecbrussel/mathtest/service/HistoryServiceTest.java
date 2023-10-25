//package be.intecbrussel.mathtest.service;
//
//import be.intecbrussel.mathtest.model.Operation;
//import be.intecbrussel.mathtest.model.OperationType;
//import be.intecbrussel.mathtest.repository.OperationRepository;
//import jakarta.inject.Inject;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
////@SpringBootTest
//public class HistoryServiceTest {
//    @Mock
//    private OperationRepository operationRepository;
//
//    @InjectMocks
//    private HistoryService historyService;
//
//    private List<Operation> operations;
//
//    @BeforeEach
//    private void fillOperations() {
//        operations = new ArrayList<>();
//        operations.add(new Operation().setData("15", "2", "17", OperationType.ADD));
//        operations.add(new Operation().setData("15", "2", "13", OperationType.SUBTRACT));
//        operations.add(new Operation().setData("15", "2", "30", OperationType.MULTIPLY));
//        operations.add(new Operation().setData("15", "2", "7.5", OperationType.DIVIDE));
//    }
//
//    private static Stream<Arguments> historyData() {
//        return Stream.of(
//                Arguments.of(OperationType.ADD, "10", "12", "-2", new Operation().setData("12", "-2", "10", OperationType.ADD)),
//                Arguments.of(OperationType.SUBTRACT, "14", "12", "-2", new Operation().setData("12", "-2", "14", OperationType.SUBTRACT)),
//                Arguments.of(OperationType.MULTIPLY, "-24", "12", "-2", new Operation().setData("12", "-2", "-24", OperationType.MULTIPLY)),
//                Arguments.of(OperationType.DIVIDE, "-6", "12", "-2", new Operation().setData("12", "-2", "-6", OperationType.DIVIDE))
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("historyData")
//    public void testAddingHistory(OperationType operationType, String result, String n1, String n2, Operation expectedToBeAdded){
//        historyService.addNewCalculation(operationType,result,n1,n2);
//        Mockito.verify(operationRepository).save(expectedToBeAdded);
//    }
//
//    @Test
//    public void testGetHistory() {
//        Mockito.when(operationRepository.findAll()).thenReturn(operations);
//        List<Operation> serviceOperations = historyService.retrieveOperations();
//        Mockito.verify(operationRepository.findAll());
//        Assertions.assertEquals(operations, serviceOperations);
//    }
//
//    private static Stream<Arguments> amountData() {
//        return Stream.of(
//                Arguments.of(4, 4),
//                Arguments.of(6, 4),
//                Arguments.of(2, 2),
//                Arguments.of(0, 0),
//                Arguments.of(-1, 0)
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("amountData")
//    public void testAmountRetrieved(int input, int expectedListSize){
//        Mockito.when(operationRepository.findTopNOperations(expectedListSize)).thenReturn(operations.subList(0, expectedListSize));
//        List<Operation> serviceOperation = historyService.retrieveLastOperation(input);
//        Mockito.verify(operationRepository.findTopNOperations(expectedListSize));
//        Assertions.assertEquals(expectedListSize, serviceOperation.size());
//    }
//
//}
