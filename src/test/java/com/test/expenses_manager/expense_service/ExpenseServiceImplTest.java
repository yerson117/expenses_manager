package com.test.expenses_manager.expense_service;

import org.jeasy.random.EasyRandom;
import com.test.expenses_manager.dto.ExpenseDTO;
import com.test.expenses_manager.mapper.ExpenseMapper;
import com.test.expenses_manager.repository.ExpensesJpaRepository;
import com.test.expenses_manager.model.Expense;
import com.test.expenses_manager.enums.ExpenseStatus;
import com.test.expenses_manager.service.impl.ExpenseServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceImplTest {

    @Mock
    private ExpensesJpaRepository expensesJpaRepository;

    @Mock
    private ExpenseMapper expenseMapper;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    public void testGetMonthExpenses() {



        List<Expense> expenses = Collections.singletonList(getExpense()); // Crea una lista de gastos de prueba
        when(expensesJpaRepository.findByFechaInicioBetween(any(LocalDate.class), any(LocalDate.class))).thenReturn(expenses);
        when(expenseMapper.expenseToExpenseDTO(any(Expense.class))).thenReturn(getExpenseDTO());

        List<ExpenseDTO> result = expenseService.getMonthExpenses();

        assertFalse(result.isEmpty());
        verify(expensesJpaRepository).findByFechaInicioBetween(any(LocalDate.class), any(LocalDate.class));
        verify(expenseMapper).expenseToExpenseDTO(any(Expense.class));
    }


    @Test
    public void testCreateExpense() {
        ExpenseDTO dto = getExpenseDTO(); // Asumiendo que tienes un constructor vacío o valores preestablecidos
        Expense expense = getExpense(); // Similar para Expense

        when(expenseMapper.expenseDTOToExpense(dto)).thenReturn(expense);
        when(expensesJpaRepository.save(expense)).thenReturn(expense);
        when(expenseMapper.expenseToExpenseDTO(expense)).thenReturn(dto);

        ExpenseDTO result = expenseService.createExpense(dto);

        assertNotNull(result);
        verify(expensesJpaRepository).save(expense);
        verify(expenseMapper).expenseDTOToExpense(dto);
        verify(expenseMapper).expenseToExpenseDTO(expense);
    }


    @Test
    public void testMarkAsPay() {
        Expense expense = getExpense(); // Configura un objeto Expense
        when(expensesJpaRepository.findById(anyLong())).thenReturn(Optional.of(expense));
        when(expensesJpaRepository.save(any(Expense.class))).thenReturn(expense);
        when(expenseMapper.expenseToExpenseDTO(any(Expense.class))).thenReturn(getExpenseDTO());

        ExpenseDTO result = expenseService.markAsPay(1L);

        assertNotNull(result);
        verify(expensesJpaRepository).findById(anyLong());
        verify(expensesJpaRepository).save(any(Expense.class));
        verify(expenseMapper).expenseToExpenseDTO(any(Expense.class));
    }

    @Test
    public void testGetExpensesByStatus() {
        List<Expense> expenses = Collections.singletonList(getExpense()); // Crea una lista de gastos de prueba
        when(expensesJpaRepository.findByEstadoIn(anyList())).thenReturn(expenses);
        when(expenseMapper.expenseToExpenseDTO(any(Expense.class))).thenReturn(getExpenseDTO());

        Map<String, List<ExpenseDTO>> result = expenseService.getExpensesByStatus(Collections.singletonList(ExpenseStatus.PAGADO));

        assertFalse(result.isEmpty());
        verify(expensesJpaRepository).findByEstadoIn(anyList());
        verify(expenseMapper, times(expenses.size())).expenseToExpenseDTO(any(Expense.class));
    }

    @Test
    public void testCalculateExpenseAvgByPeriod() {
        List<Expense> expenses = Collections.singletonList(getExpense());
        when(expensesJpaRepository.findByFechaInicioBetween(any(LocalDate.class), any(LocalDate.class))).thenReturn(expenses);

        Double result = expenseService.calculateExpenseAvgByPeriod("semana");

        assertNotNull(result);
        verify(expensesJpaRepository).findByFechaInicioBetween(any(LocalDate.class), any(LocalDate.class));
    }

    public Expense getExpense() {
        return easyRandom.nextObject(Expense.class);
    }

    public ExpenseDTO getExpenseDTO() {
        return easyRandom.nextObject(ExpenseDTO.class);
    }
}
