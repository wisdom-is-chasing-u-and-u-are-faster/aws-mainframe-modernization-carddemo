package com.client.core_financial_processing.service;

import com.client.runtime.ProgramRegistry;
import com.client.runtime.ProgramService;
import com.client.core_financial_processing.dto.Cbact01cRequest;
import com.client.core_financial_processing.dto.Cbact01cResponse;
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
@DisplayName("Cbact01cService Business Domain Service Tests")
class Cbact01cServiceTest {

    @Mock
    private ProgramRegistry registry;

    @InjectMocks
    private Cbact01cService service;

    @BeforeEach
    void setUp() {
        // Setup initial test fixture state
    }

    @Test
    @DisplayName("Verify module implements ProgramService and has valid metadata")
    void testModuleMetadata() {
        assertTrue(service instanceof ProgramService);
        assertEquals("CBACT01C", service.programId());
        assertEquals("CBACT01C", service.getModuleId());
        assertNotNull(service.sourcePath());
        assertNotNull(service.getSourcePath());
        assertNotNull(service.rules());
        assertNotNull(service.newState());
    }

    @Test
    @DisplayName("Happy path: execute business rule with valid debit request")
    void testExecuteRule_Success() {
        Cbact01cRequest request = new Cbact01cRequest(
            "00000000001",
            new BigDecimal("150.00"),
            "DEBIT",
            "TXN-1001",
            Map.of()
        );
        Cbact01cResponse response = service.executeCbact01c0001(request);

        assertNotNull(response);
        assertEquals("SUCCESS", response.status());
        assertNotNull(response.newBalance());
        assertTrue(response.message().contains("successfully") || response.reasonCode() == 0);
    }

    @Test
    @DisplayName("Overdraft guard: reject transaction exceeding overdraft limits")
    void testExecuteRule_OverdraftDeclined() {
        Cbact01cRequest request = new Cbact01cRequest(
            "00000000001",
            new BigDecimal("999999.00"),
            "DEBIT",
            "TXN-OVERDRAFT",
            Map.of()
        );
        Cbact01cResponse response = service.executeCbact01c0001(request);

        assertNotNull(response);
        assertEquals("DECLINED", response.status());
        assertTrue(response.message().toLowerCase().contains("overlimit") || response.message().toLowerCase().contains("overdraft") || response.reasonCode() == 102);
    }

    @Test
    @DisplayName("Validation guard: reject negative transaction amount")
    void testExecuteRule_NegativeAmountValidation() {
        Cbact01cRequest request = new Cbact01cRequest(
            "00000000001",
            new BigDecimal("-50.00"),
            "DEBIT",
            "TXN-NEG",
            Map.of()
        );
        Cbact01cResponse response = service.executeCbact01c0001(request);

        assertNotNull(response);
        assertEquals("VALIDATION_ERROR", response.status());
    }

    @Test
    @DisplayName("Resilience: handle null request gracefully with baseline default")
    void testExecuteRule_NullRequestHandling() {
        Cbact01cResponse response = service.executeCbact01c0001((Cbact01cRequest) null);

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
        var result = service.executeCbact01c0001(payload);

        assertNotNull(result);
        assertEquals("SUCCESS", result.status());
    }
    @Test
    @DisplayName("CardDemo CBACT01C: Account record lookup success")
    void testAccountLookupSuccess() {
        Cbact01cRequest req = Cbact01cRequest.defaultBaseline();
        Cbact01cResponse resp = service.lookupAccount(req);
        assertEquals("SUCCESS", resp.status());
        assertEquals(0, resp.reasonCode());
    }

    @Test
    @DisplayName("CardDemo CBACT01C: Missing account record rejected with code 20")
    void testAccountLookupMissingId() {
        Cbact01cRequest req = new Cbact01cRequest("00000000000", BigDecimal.ZERO, "STANDARD", "REF-ERR", Map.of());
        Cbact01cResponse resp = service.lookupAccount(req);
        assertEquals("DECLINED", resp.status());
        assertEquals(20, resp.reasonCode());
    }
}
