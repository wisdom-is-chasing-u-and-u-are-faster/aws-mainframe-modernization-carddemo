package com.client.core_financial_processing.service;

import com.client.core_financial_processing.dto.CsutldtcRequest;
import com.client.core_financial_processing.dto.CsutldtcResponse;
import com.client.core_financial_processing.model.Csutldtc;
import com.client.core_financial_processing.repository.CsutldtcRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CsutldtcService Business Domain Service Tests")
class CsutldtcServiceTest {

    @Mock
    private CsutldtcRepository repository;

    @InjectMocks
    private CsutldtcService service;

    @BeforeEach
    void setUp() {
        // Setup initial test fixture state
    }

    @Test
    @DisplayName("Verify module metadata and mainframe source path")
    void testModuleMetadata() {
        assertNotNull(service.getModuleId());
        assertEquals("CSUTLDTC", service.getModuleId());
        assertNotNull(service.getSourcePath());
    }

    @Test
    @DisplayName("Happy path: execute business rule with valid debit request")
    void testExecuteRule_Success() {
        CsutldtcRequest request = new CsutldtcRequest(
            "00000000001",
            new BigDecimal("150.00"),
            "DEBIT",
            "TXN-1001",
            Map.of()
        );
        CsutldtcResponse response = service.executeCsutldtc0283(request);

        assertNotNull(response);
        assertEquals("SUCCESS", response.status());
        assertNotNull(response.newBalance());
        assertTrue(response.message().contains("successfully"));
    }

    @Test
    @DisplayName("Overdraft guard: reject transaction exceeding overdraft limits")
    void testExecuteRule_OverdraftDeclined() {
        CsutldtcRequest request = new CsutldtcRequest(
            "00000000001",
            new BigDecimal("999999.00"),
            "DEBIT",
            "TXN-OVERDRAFT",
            Map.of()
        );
        CsutldtcResponse response = service.executeCsutldtc0283(request);

        assertNotNull(response);
        assertEquals("DECLINED_INSUFFICIENT_FUNDS", response.status());
        assertTrue(response.message().toLowerCase().contains("overdraft"));
    }

    @Test
    @DisplayName("Validation guard: reject negative transaction amount")
    void testExecuteRule_NegativeAmountValidation() {
        CsutldtcRequest request = new CsutldtcRequest(
            "00000000001",
            new BigDecimal("-50.00"),
            "DEBIT",
            "TXN-NEG",
            Map.of()
        );
        CsutldtcResponse response = service.executeCsutldtc0283(request);

        assertNotNull(response);
        assertEquals("VALIDATION_ERROR", response.status());
    }

    @Test
    @DisplayName("Resilience: handle null request gracefully with baseline default")
    void testExecuteRule_NullRequestHandling() {
        CsutldtcResponse response = service.executeCsutldtc0283((CsutldtcRequest) null);

        assertNotNull(response);
        assertNotNull(response.status());
    }

    @Test
    @DisplayName("Backward compatibility: handle legacy Map payload")
    void testExecuteRule_LegacyMapPayload() {
        Map<String, Object> payload = Map.of(
            "accountId", "00000000001",
            "amount", 50.0
        );
        var result = service.executeCsutldtc0283(payload);

        assertNotNull(result);
        assertEquals("SUCCESS", result.status());
    }
}
