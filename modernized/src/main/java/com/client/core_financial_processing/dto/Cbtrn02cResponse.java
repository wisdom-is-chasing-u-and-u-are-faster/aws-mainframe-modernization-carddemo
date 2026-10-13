package com.client.core_financial_processing.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Modernized Response DTO for legacy COBOL module: CBTRN02C
 * Mainframe Source: app/cbl/CBTRN02C.cbl
 */
@Schema(description = "Response payload for Cbtrn02cService domain operations")
public record Cbtrn02cResponse(
    @Schema(description = "Executed Business Rule Identifier", example = "BR-CBTRN02C-0001")
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
    public Cbtrn02cResponse(String ruleId, String status, String message, BigDecimal newBalance, String transactionReference) {
        this(ruleId, status, message, newBalance, transactionReference, Instant.now());
    }

    public static Cbtrn02cResponse success(String ruleId, String message, BigDecimal balance, String ref) {
        return new Cbtrn02cResponse(ruleId, "SUCCESS", message, balance, ref, Instant.now());
    }

    public static Cbtrn02cResponse declined(String ruleId, String message, BigDecimal balance, String ref) {
        return new Cbtrn02cResponse(ruleId, "DECLINED_INSUFFICIENT_FUNDS", message, balance, ref, Instant.now());
    }

    public static Cbtrn02cResponse validationError(String ruleId, String message) {
        return new Cbtrn02cResponse(ruleId, "VALIDATION_ERROR", message, BigDecimal.ZERO, "ERR", Instant.now());
    }
}
