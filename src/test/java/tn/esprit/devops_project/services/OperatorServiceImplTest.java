package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OperatorServiceImplTest {

    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllOperators() {
        List<Operator> operators = new ArrayList<>();
        // Add some sample operators to the list
        operators.add(new Operator());
        operators.add(new Operator());
        when(operatorRepository.findAll()).thenReturn(operators);
        List<Operator> result = operatorService.retrieveAllOperators();
        assertEquals(2, result.size());
    }

    @Test
    void addOperator() {
        Operator operator = new Operator();
        when(operatorRepository.save(operator)).thenReturn(operator);
        Operator result = operatorService.addOperator(operator);
        assertEquals(operator, result);
    }

    @Test
    void deleteOperator() {
        Long id = 1L;
        operatorService.deleteOperator(id);
        verify(operatorRepository, times(1)).deleteById(id);
    }

    @Test
    void updateOperator() {
        Operator operator = new Operator();
        when(operatorRepository.save(operator)).thenReturn(operator);
        Operator result = operatorService.updateOperator(operator);
        assertEquals(operator, result);
    }

    @Test
    void retrieveOperator() {
        Long id = 1L;
        Operator operator = new Operator();
        when(operatorRepository.findById(id)).thenReturn(Optional.of(operator));
        Operator result = operatorService.retrieveOperator(id);
        assertEquals(operator, result);
    }
}
