package com.test.expenses_manager.service;

import com.test.expenses_manager.dto.ExpenseDTO;
import com.test.expenses_manager.enums.ExpenseStatus;

import java.util.List;
import java.util.Map;

public interface ExpenseService {

    List<ExpenseDTO> getMonthExpenses();

    ExpenseDTO createExpense(ExpenseDTO expenseDTO);

    ExpenseDTO markAsPay(Long id);

    Map<String, List<ExpenseDTO>> getExpensesByStatus(List<ExpenseStatus> status);

    Double calculateExpenseAvgByPeriod(String period);
}
