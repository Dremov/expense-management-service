package com.dremov.expensemanagementservice;

import com.dremov.expensemanagementservice.domain.Expense;
import com.dremov.expensemanagementservice.domain.ExpenseRequestBody;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseManagementController {

    private final ExpenseManagementService expenseManagementService;

    public ExpenseManagementController(ExpenseManagementService expenseManagementService) {
        this.expenseManagementService = expenseManagementService;
    }

    @PostMapping("/expense")
    public ResponseEntity<Void> createExpense(@RequestBody ExpenseRequestBody requestBody) {
        expenseManagementService.createExpense(buildExpenseDTO(requestBody));

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    private Expense buildExpenseDTO(ExpenseRequestBody requestBody) {

        return Expense.builder()
                .expenseCategory(requestBody.getExpenseCategory())
                .expenseName(requestBody.getExpenseName())
                .price(requestBody.getPrice())
                .build();
    }
}
