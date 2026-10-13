package com.client.core_financial_processing.controller;

import com.client.core_financial_processing.dto.Cbact01cRequest;
import com.client.core_financial_processing.dto.Cbact01cResponse;
import com.client.core_financial_processing.service.Cbact01cService;
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

@WebMvcTest(Cbact01cController.class)
@DisplayName("Cbact01cController REST API Endpoints Tests")
class Cbact01cControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Cbact01cService service;

    @Test
    @DisplayName("GET /api/v1/core_financial_processing/cbact01c/health returns HTTP 200 and status ACTIVE")
    void testHealthEndpoint() throws Exception {
        when(service.getModuleId()).thenReturn("CBACT01C");
        when(service.getSourcePath()).thenReturn("app/cbl/CBACT01C.cbl");

        mockMvc.perform(get("/api/v1/core_financial_processing/cbact01c/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("ACTIVE"))
            .andExpect(jsonPath("$.moduleId").value("CBACT01C"));
    }

    @Test
    @DisplayName("POST /api/v1/core_financial_processing/cbact01c/cbact01c0001 returns HTTP 200")
    void testActionEndpoint_Success() throws Exception {
        Cbact01cResponse mockResponse = Cbact01cResponse.success(
            "BR-CBACT01C-0001",
            "Rule executed successfully",
            new BigDecimal("850.00"),
            "TXN-1001"
        );
        when(service.executeCbact01c0001(any(Cbact01cRequest.class))).thenReturn(mockResponse);

        String jsonPayload = """
            {
                "accountId": "00000000001",
                "amount": 150.00,
                "transactionType": "DEBIT",
                "referenceNumber": "TXN-1001"
            }
            """;

        mockMvc.perform(post("/api/v1/core_financial_processing/cbact01c/cbact01c0001")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("SUCCESS"))
            .andExpect(jsonPath("$.newBalance").value(850.00));
    }
}
