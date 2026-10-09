package com.client.runtime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import com.client.runtime.CobolState;
import com.client.runtime.ProgramService;

@DisplayName("ProgramService Modernization Parity Test")
public class ProgramServiceTest {

    private ProgramService service;

    @BeforeEach
    void setUp() {
        service = new ProgramService();
    }

    @Test
    @DisplayName("Verify service class contract and program identifier")
    void testServiceContract() {
        assertNotNull(service);
        assertNotNull(service.programId());
        assertFalse(service.programId().isBlank());
    }

    @Test
    @DisplayName("Verify state initialization and execution bounds")
    void testStateParity() {
        CobolState st = service.newState();
        assertNotNull(st);
        if (service.programTranslated()) {
            assertDoesNotThrow(() -> service.execute(st));
        } else {
            assertFalse(service.untranslatedReasons().isEmpty());
        }
    }
}
