package com.client.core_financial_processing.controller;

import com.client.core_financial_processing.dto.Cbact04cRequest;
import com.client.core_financial_processing.dto.Cbact04cResponse;
import com.client.core_financial_processing.service.Cbact04cService;
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

@WebMvcTest(Cbact04cController.class)
@DisplayName("Cbact04cController REST API Endpoints Tests")
class Cbact04cControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Cbact04cService service;

    @Test
    @DisplayName("GET /api/v1/core_financial_processing/cbact04c/health returns HTTP 200 and status ACTIVE")
    void testHealthEndpoint() throws Exception {
        when(service.getModuleId()).thenReturn("CBACT04C");
        when(service.getSourcePath()).thenReturn("app/cbl/CBACT04C.cbl");

        mockMvc.perform(get("/api/v1/core_financial_processing/cbact04c/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("ACTIVE"))
            .andExpect(jsonPath("$.moduleId").value("CBACT04C"));
    }

    @Test
    @DisplayName("POST /api/v1/core_financial_processing/cbact04c/cbact04c0023 returns HTTP 200")
    void testActionEndpoint_Success() throws Exception {
        Cbact04cResponse mockResponse = Cbact04cResponse.success(
            "BR-CBACT04C-0001",
            "Rule executed successfully",
            new BigDecimal("850.00"),
            "TXN-1001"
        );
        when(service.executeCbact04c0023(any(Cbact04cRequest.class))).thenReturn(mockResponse);

        String jsonPayload = """
            {
                "accountId": "00000000001",
                "amount": 150.00,
                "transactionType": "DEBIT",
                "referenceNumber": "TXN-1001"
            }
            """;

        mockMvc.perform(post("/api/v1/core_financial_processing/cbact04c/cbact04c0023")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("SUCCESS"))
            .andExpect(jsonPath("$.newBalance").value(850.00));
    }
}
