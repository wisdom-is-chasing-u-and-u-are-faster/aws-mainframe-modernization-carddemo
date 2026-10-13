package com.client.core_financial_processing.service;

import com.client.runtime.ProgramRegistry;
import com.client.runtime.ProgramService;
import com.client.core_financial_processing.dto.CsutldtcRequest;
import com.client.core_financial_processing.dto.CsutldtcResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CsutldtcService Business Domain Service Tests")
class CsutldtcServiceTest {

    @Mock
    private ProgramRegistry registry;

    @InjectMocks
    private CsutldtcService service;

    @BeforeEach
    void setUp() {
        // Setup initial test fixture state
    }

    @Test
    @DisplayName("Verify module implements ProgramService and has valid metadata")
    void testModuleMetadata() {
        assertTrue(service instanceof ProgramService);
        assertEquals("CSUTLDTC", service.programId());
        assertEquals("CSUTLDTC", service.getModuleId());
        assertNotNull(service.sourcePath());
        assertNotNull(service.getSourcePath());
        assertNotNull(service.rules());
        assertNotNull(service.newState());
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
        assertTrue(response.message().contains("successfully") || response.reasonCode() == 0);
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
        assertEquals("DECLINED", response.status());
        assertTrue(response.message().toLowerCase().contains("overlimit") || response.message().toLowerCase().contains("overdraft") || response.reasonCode() == 102);
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
    @Test
    @DisplayName("CardDemo CSUTLDTC Ground Truth: Strict calendar validation rejects invalid date 2026-02-30")
    void testCardDemoDateValidationRejection() {
        CsutldtcRequest req = new CsutldtcRequest(
            "00000000001",
            BigDecimal.ZERO,
            "DATE_VAL",
            "TXN-DATE-VAL",
            null,
            null,
            null,
            null,
            null,
            null,
            "2026-02-30",
            "YYYY-MM-DD",
            null,
            Map.of()
        );
        CsutldtcResponse resp = service.validateDate(req);
        assertEquals("VALIDATION_ERROR", resp.status());
        assertEquals(12, resp.severity());
        assertTrue(resp.message().contains("invalid") || resp.message().contains("Datevalue"));
    }

    @Test
    @DisplayName("CardDemo CSUTLDTC Ground Truth: Strict calendar validation accepts valid date 2026-04-15")
    void testCardDemoDateValidationSuccess() {
        CsutldtcRequest req = new CsutldtcRequest(
            "00000000001",
            BigDecimal.ZERO,
            "DATE_VAL",
            "TXN-DATE-VAL",
            null,
            null,
            null,
            null,
            null,
            null,
            "2026-04-15",
            "YYYY-MM-DD",
            null,
            Map.of()
        );
        CsutldtcResponse resp = service.validateDate(req);
        assertEquals("SUCCESS", resp.status());
        assertEquals(0, resp.severity());
    }
}
