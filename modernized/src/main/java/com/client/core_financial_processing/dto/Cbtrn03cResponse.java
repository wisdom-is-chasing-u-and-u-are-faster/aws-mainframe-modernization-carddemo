package com.client.core_financial_processing.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Modernized Response DTO for legacy COBOL module: CBTRN03C
 * Mainframe Source: app/cbl/CBTRN03C.cbl
 */
@Schema(description = "Response payload for Cbtrn03cService domain operations")
public record Cbtrn03cResponse(
    @Schema(description = "Executed Business Rule Identifier", example = "BR-CBTRN03C-0001")
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
    public Cbtrn03cResponse(String ruleId, String status, String message, BigDecimal newBalance, String transactionReference) {
        this(ruleId, status, message, newBalance, transactionReference, Instant.now());
    }

    public static Cbtrn03cResponse success(String ruleId, String message, BigDecimal balance, String ref) {
        return new Cbtrn03cResponse(ruleId, "SUCCESS", message, balance, ref, Instant.now());
    }

    public static Cbtrn03cResponse declined(String ruleId, String message, BigDecimal balance, String ref) {
        return new Cbtrn03cResponse(ruleId, "DECLINED_INSUFFICIENT_FUNDS", message, balance, ref, Instant.now());
    }

    public static Cbtrn03cResponse validationError(String ruleId, String message) {
        return new Cbtrn03cResponse(ruleId, "VALIDATION_ERROR", message, BigDecimal.ZERO, "ERR", Instant.now());
    }
}
