package com.dremov.expensemanagementservice;

import com.dremov.expensemanagementservice.domain.Expense;
import com.dremov.expensemanagementservice.domain.ExpenseRequestBody;
import com.dremov.expensemanagementservice.domain.Price;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseManagementController.class)
class ExpenseManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseManagementService expenseManagementServiceMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldReturn201WhenRequestIsValid() throws Exception {
        var gson = new Gson();
        var expenseBody = ExpenseRequestBody.builder().build();
        var testBody = gson.toJson(expenseBody);

        mockMvc.perform(post("/expense")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testBody)
                .characterEncoding("utf-8")
        ).andExpect(status().isCreated());
    }

    @Test
    public void shouldCallServiceWhenRequestIsValid() throws Exception {
        var gson = new Gson();
        var testName = "";
        var testPrise = Price.builder()
                .value(10)
                .build();
        var expenseBody = ExpenseRequestBody.builder()
                .expenseName(testName)
                .price(testPrise)
                .build();
        var testBody = gson.toJson(expenseBody);
        var expectedExpenseDTO = Expense.builder()
                .expenseName(testName)
                .price(testPrise)
                .build();

        mockMvc.perform(post("/expense")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testBody)
                .characterEncoding("utf-8")
        );

        verify(expenseManagementServiceMock).createExpense(expectedExpenseDTO);
    }
}