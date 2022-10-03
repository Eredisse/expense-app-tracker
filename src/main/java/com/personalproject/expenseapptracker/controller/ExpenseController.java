package com.personalproject.expenseapptracker.controller;

import com.personalproject.expenseapptracker.model.Expense;
import com.personalproject.expenseapptracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping()
    public Page<Expense> getAllExpenses(Pageable page) {
        return expenseService.getAllExpenses(page);
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping
    public void deleteExpenseById(@RequestParam Long id) {
        expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Expense saveExpense(@Valid @RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@RequestBody Expense expense, @PathVariable Long id) {
        return expenseService.updateExpense(id, expense);
    }

    @GetMapping("/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page) {
        return expenseService.getByCategory(category, page);
    }

    @GetMapping("/name")
    public List<Expense> getExpensesByNameContaining(@RequestParam String keyword, Pageable page) {
        return expenseService.getByNameContaining(keyword, page);
    }

    @GetMapping("/date")
    public List<Expense> getExpensesByDateBetween(@RequestParam(required = false) Date startDate,
                                                  @RequestParam(required = false) Date endDate,
                                                  Pageable page) {
        return expenseService.getByDate(startDate, endDate, page);
    }
}
