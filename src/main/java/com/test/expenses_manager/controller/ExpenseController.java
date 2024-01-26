package com.test.expenses_manager.controller;

import com.test.expenses_manager.dto.ExpenseDTO;
import com.test.expenses_manager.enums.ExpenseStatus;
import com.test.expenses_manager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expenses")
@Validated
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    @GetMapping("/month")
    public ResponseEntity<List<ExpenseDTO>> getMonthExpenses() {
        return ResponseEntity.ok(expenseService.getMonthExpenses());
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok(expenseService.createExpense(expenseDTO));
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<ExpenseDTO> markAsPay(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.markAsPay(id));
    }

    @GetMapping("/state")
    public ResponseEntity<Map<String, List<ExpenseDTO>>> getExpensesByState(@RequestParam List<ExpenseStatus> status) {
        return ResponseEntity.ok(expenseService.getExpensesByStatus(status));
    }

    @GetMapping("/avg/{period}")
    public ResponseEntity<Double> getExpenseAvgByPeriod(@PathVariable String period) {
        return ResponseEntity.ok(expenseService.calculateExpenseAvgByPeriod(period));
    }
}
