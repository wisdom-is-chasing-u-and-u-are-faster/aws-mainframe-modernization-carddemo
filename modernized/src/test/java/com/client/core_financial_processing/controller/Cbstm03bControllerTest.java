package com.client.core_financial_processing.controller;

import com.client.core_financial_processing.dto.Cbstm03bRequest;
import com.client.core_financial_processing.dto.Cbstm03bResponse;
import com.client.core_financial_processing.service.Cbstm03bService;
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

@WebMvcTest(Cbstm03bController.class)
@DisplayName("Cbstm03bController REST API Endpoints Tests")
class Cbstm03bControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Cbstm03bService service;

    @Test
    @DisplayName("GET /api/v1/core_financial_processing/cbstm03b/health returns HTTP 200 and status ACTIVE")
    void testHealthEndpoint() throws Exception {
        when(service.getModuleId()).thenReturn("CBSTM03B");
        when(service.getSourcePath()).thenReturn("app/cbl/CBSTM03B.CBL");

        mockMvc.perform(get("/api/v1/core_financial_processing/cbstm03b/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("ACTIVE"))
            .andExpect(jsonPath("$.moduleId").value("CBSTM03B"));
    }

    @Test
    @DisplayName("POST /api/v1/core_financial_processing/cbstm03b/cbstm03b0163 returns HTTP 200")
    void testActionEndpoint_Success() throws Exception {
        Cbstm03bResponse mockResponse = Cbstm03bResponse.success(
            "BR-CBSTM03B-0001",
            "Rule executed successfully",
            new BigDecimal("850.00"),
            "TXN-1001"
        );
        when(service.executeCbstm03b0163(any(Cbstm03bRequest.class))).thenReturn(mockResponse);

        String jsonPayload = "{\"accountId\": \"00000000001\", \"amount\": 150.00, \"transactionType\": \"DEBIT\"}";

        mockMvc.perform(post("/api/v1/core_financial_processing/cbstm03b/cbstm03b0163")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("SUCCESS"))
            .andExpect(jsonPath("$.newBalance").value(850.00));
    }
}
