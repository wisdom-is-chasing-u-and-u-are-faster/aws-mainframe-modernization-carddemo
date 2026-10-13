package com.client.core_financial_processing.controller;

import com.client.core_financial_processing.dto.Cbtrn02cRequest;
import com.client.core_financial_processing.dto.Cbtrn02cResponse;
import com.client.core_financial_processing.service.Cbtrn02cService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Cbtrn02cController.class)
@DisplayName("Cbtrn02cController REST API Endpoints Tests")
class Cbtrn02cControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Cbtrn02cService service;

    @Test
    @DisplayName("GET /api/v1/core_financial_processing/cbtrn02c/health returns HTTP 200 and status ACTIVE")
    void testHealthEndpoint() throws Exception {
        when(service.getModuleId()).thenReturn("CBTRN02C");
        when(service.getSourcePath()).thenReturn("app/cbl/CBTRN02C.cbl");

        mockMvc.perform(get("/api/v1/core_financial_processing/cbtrn02c/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("ACTIVE"))
            .andExpect(jsonPath("$.moduleId").value("CBTRN02C"));
    }

    @Test
    @DisplayName("POST /api/v1/core_financial_processing/cbtrn02c/cbtrn02c0051 returns HTTP 200")
    void testActionEndpoint_Success() throws Exception {
        Cbtrn02cResponse mockResponse = Cbtrn02cResponse.success(
            "BR-CBTRN02C-0001",
            "Rule executed successfully",
            new BigDecimal("850.00"),
            "TXN-1001"
        );
        when(service.executeCbtrn02c0051(any(Cbtrn02cRequest.class))).thenReturn(mockResponse);

        String jsonPayload = "{\"accountId\": \"00000000001\", \"amount\": 150.00, \"transactionType\": \"DEBIT\"}";

        mockMvc.perform(post("/api/v1/core_financial_processing/cbtrn02c/cbtrn02c0051")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("SUCCESS"))
            .andExpect(jsonPath("$.newBalance").value(850.00));
    }
}
