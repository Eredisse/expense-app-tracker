package com.personalproject.expenseapptracker.service;

import com.personalproject.expenseapptracker.model.Expense;
import com.personalproject.expenseapptracker.repo.ExpenseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExpenseService {

    private final ExpenseRepo expenseRepo;

    public Page<Expense> getAllExpenses(Pageable page) {
        return expenseRepo.findAll(page);
    }

    public Expense getExpenseById(Long id) {
        return expenseRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Expense not found for id")
        );
    }

    public void deleteExpenseById(Long id) {
        expenseRepo.deleteById(id);
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepo.save(expense);
    }

    public Expense updateExpense(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);
        existingExpense.setName(expense.getName()!= null ? expense.getName() : existingExpense.getName());
        existingExpense.setDescription(expense.getDescription()!= null ? expense.getDescription() : existingExpense.getDescription());
        existingExpense.setCategory(expense.getCategory()!= null ? expense.getCategory() : existingExpense.getCategory());
        existingExpense.setAmount(expense.getAmount()!= null ? expense.getAmount() : existingExpense.getAmount());
        existingExpense.setDate(expense.getDate()!= null ? expense.getDate() : existingExpense.getDate());

        return expenseRepo.save(existingExpense);
    }


}
