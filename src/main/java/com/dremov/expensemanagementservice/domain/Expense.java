package com.dremov.expensemanagementservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    String expenseName;
    String expenseCategory;
    Price price;
}
