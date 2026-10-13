package com.client.core_financial_processing.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Modernized Response DTO for legacy COBOL module: CSUTLDTC
 * Mainframe Source: app/cbl/CSUTLDTC.cbl
 */
@Schema(description = "Response payload for CsutldtcService domain operations")
public record CsutldtcResponse(
    @Schema(description = "Executed Business Rule Identifier", example = "BR-CSUTLDTC-0001")
    String ruleId,

    @Schema(description = "Operation Status (SUCCESS, DECLINED_INSUFFICIENT_FUNDS, VALIDATION_ERROR)", example = "SUCCESS")
    String status,

    @Schema(description = "Human-readable execution summary or failure rationale", example = "Transaction processed successfully")
    String message,

    @Schema(description = "Resulting Account Balance", example = "1250.75")
    BigDecimal newBalance,

    @Schema(description = "Transaction Reference Identifier", example = "TXN-73910")
    String transactionReference,

    @Schema(description = "Execution Timestamp")
    Instant timestamp
) {
    public CsutldtcResponse(String ruleId, String status, String message, BigDecimal newBalance, String transactionReference) {
        this(ruleId, status, message, newBalance, transactionReference, Instant.now());
    }

    public static CsutldtcResponse success(String ruleId, String message, BigDecimal balance, String ref) {
        return new CsutldtcResponse(ruleId, "SUCCESS", message, balance, ref, Instant.now());
    }

    public static CsutldtcResponse declined(String ruleId, String message, BigDecimal balance, String ref) {
        return new CsutldtcResponse(ruleId, "DECLINED_INSUFFICIENT_FUNDS", message, balance, ref, Instant.now());
    }

    public static CsutldtcResponse validationError(String ruleId, String message) {
        return new CsutldtcResponse(ruleId, "VALIDATION_ERROR", message, BigDecimal.ZERO, "ERR", Instant.now());
    }
}
