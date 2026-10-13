package com.client.core_financial_processing.controller;

import com.client.core_financial_processing.dto.Cbact03cRequest;
import com.client.core_financial_processing.dto.Cbact03cResponse;
import com.client.core_financial_processing.service.Cbact03cService;
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

@WebMvcTest(Cbact03cController.class)
@DisplayName("Cbact03cController REST API Endpoints Tests")
class Cbact03cControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Cbact03cService service;

    @Test
    @DisplayName("GET /api/v1/core_financial_processing/cbact03c/health returns HTTP 200 and status ACTIVE")
    void testHealthEndpoint() throws Exception {
        when(service.getModuleId()).thenReturn("CBACT03C");
        when(service.getSourcePath()).thenReturn("app/cbl/CBACT03C.cbl");

        mockMvc.perform(get("/api/v1/core_financial_processing/cbact03c/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("ACTIVE"))
            .andExpect(jsonPath("$.moduleId").value("CBACT03C"));
    }

    @Test
    @DisplayName("POST /api/v1/core_financial_processing/cbact03c/cbact03c0016 returns HTTP 200")
    void testActionEndpoint_Success() throws Exception {
        Cbact03cResponse mockResponse = Cbact03cResponse.success(
            "BR-CBACT03C-0001",
            "Rule executed successfully",
            new BigDecimal("850.00"),
            "TXN-1001"
        );
        when(service.executeCbact03c0016(any(Cbact03cRequest.class))).thenReturn(mockResponse);

        String jsonPayload = "{\"accountId\": \"00000000001\", \"amount\": 150.00, \"transactionType\": \"DEBIT\"}";

        mockMvc.perform(post("/api/v1/core_financial_processing/cbact03c/cbact03c0016")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("SUCCESS"))
            .andExpect(jsonPath("$.newBalance").value(850.00));
    }
}
