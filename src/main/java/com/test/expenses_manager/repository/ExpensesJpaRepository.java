package com.test.expenses_manager.repository;

import com.test.expenses_manager.model.Expense;
import com.test.expenses_manager.enums.ExpenseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpensesJpaRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByFechaInicioBetween(LocalDate startOfMonth, LocalDate endOfMonth);

    List<Expense> findByEstadoIn(List<ExpenseStatus> status);
}
