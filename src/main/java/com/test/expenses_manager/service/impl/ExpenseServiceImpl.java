package com.test.expenses_manager.service.impl;

import com.test.expenses_manager.exception.ResourceNotFoundException;
import com.test.expenses_manager.mapper.ExpenseMapper;
import com.test.expenses_manager.dto.ExpenseDTO;
import com.test.expenses_manager.repository.ExpensesJpaRepository;
import com.test.expenses_manager.model.Expense;
import com.test.expenses_manager.enums.ExpenseStatus;
import com.test.expenses_manager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpensesJpaRepository expensesJpaRepository;
    private final ExpenseMapper expenseMapper;

    @Autowired
    public ExpenseServiceImpl(ExpensesJpaRepository expensesJpaRepository, ExpenseMapper expenseMapper) {
        this.expensesJpaRepository = expensesJpaRepository;
        this.expenseMapper = expenseMapper;
    }

    @Override
    public List<ExpenseDTO> getMonthExpenses() {
        LocalDate startOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        List<Expense> expenses = expensesJpaRepository.findByFechaInicioBetween(startOfMonth, endOfMonth);
        if (expenses.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron gastos en el mes.");
        }

        return  expenses.stream()
                .map(expenseMapper::expenseToExpenseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDTO createExpense(ExpenseDTO gastoDTO) {
        Expense expense = expenseMapper.expenseDTOToExpense(gastoDTO);
        expense.setEstado(ExpenseStatus.PENDIENTE);
        Expense saved = expensesJpaRepository.save(expense);
        return expenseMapper.expenseToExpenseDTO(saved);
    }

    @Override
    public ExpenseDTO markAsPay(Long id) {
        return expensesJpaRepository.findById(id)
                .map(expense -> {
                    expense.setEstado(ExpenseStatus.PAGADO);
                    return expenseMapper.expenseToExpenseDTO(expensesJpaRepository.save(expense));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Gasto con ID " + id + " no encontrado."));
    }


    public Map<String, List<ExpenseDTO>> getExpensesByStatus(List<ExpenseStatus> status) {
        List<Expense> expenses = expensesJpaRepository.findByEstadoIn(status);
        if (expenses.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron gastos con el estado especificado.");
        }
        return expenses.stream()
                .map(expenseMapper::expenseToExpenseDTO)
                .collect(Collectors.groupingBy(expenseDTO -> expenseDTO.getEstado().name()));
    }


    @Override
    public Double calculateExpenseAvgByPeriod(String periodo) {
        LocalDate startDate;
        LocalDate endDate = LocalDate.now();

        startDate = switch (periodo.toLowerCase()) {
            case "dia" -> endDate;
            case "semana" -> endDate.minusWeeks(1);
            case "quincena" -> endDate.minusWeeks(2);
            default -> throw new IllegalArgumentException("Periodo no válido");
        };

        List<Expense> expenses = expensesJpaRepository.findByFechaInicioBetween(startDate, endDate);
        if (expenses.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron gastos en el periodo especificado.");
        }
        return expenses.stream()
                .mapToDouble(Expense::getMonto)
                .average()
                .orElse(0.0);
    }
}
