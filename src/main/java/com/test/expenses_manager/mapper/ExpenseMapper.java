package com.test.expenses_manager.mapper;

import com.test.expenses_manager.dto.ExpenseDTO;
import com.test.expenses_manager.model.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseMapper {

    ExpenseDTO expenseToExpenseDTO(Expense expense);

    Expense expenseDTOToExpense(ExpenseDTO expenseDTO);
}
