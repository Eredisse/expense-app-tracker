package com.personalproject.expenseapptracker.repo;

import com.personalproject.expenseapptracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Long> {
}
