package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: CIPAUSMY
 * @citation app/app-authorization-ims-db2-mq/cpy/CIPAUSMY.cpy
 * 12 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cipausmy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: PA-ACCT-ID S9(11) (line 19)
     * PIC clause: S9(11)
     */
    @JsonProperty("PA_ACCT_ID")
    private Long paAcctId;

    /**
     * Original: PA-CUST-ID 9(09) (line 20)
     * PIC clause: 9(09)
     */
    @JsonProperty("PA_CUST_ID")
    private Integer paCustId;

    /**
     * Original: PA-AUTH-STATUS X(01) (line 21)
     * PIC clause: X(01)
     */
    @JsonProperty("PA_AUTH_STATUS")
    private String paAuthStatus;

    /**
     * Original: PA-ACCOUNT-STATUS X(02) (line 22)
     * PIC clause: X(02)
     */
    @JsonProperty("PA_ACCOUNT_STATUS")
    private String paAccountStatus;

    /**
     * Original: PA-CREDIT-LIMIT S9(09)V99 (line 23)
     * PIC clause: S9(09)V99
     */
    @JsonProperty("PA_CREDIT_LIMIT")
    private BigDecimal paCreditLimit;

    /**
     * Original: PA-CASH-LIMIT S9(09)V99 (line 24)
     * PIC clause: S9(09)V99
     */
    @JsonProperty("PA_CASH_LIMIT")
    private BigDecimal paCashLimit;

    /**
     * Original: PA-CREDIT-BALANCE S9(09)V99 (line 25)
     * PIC clause: S9(09)V99
     */
    @JsonProperty("PA_CREDIT_BALANCE")
    private BigDecimal paCreditBalance;

    /**
     * Original: PA-CASH-BALANCE S9(09)V99 (line 26)
     * PIC clause: S9(09)V99
     */
    @JsonProperty("PA_CASH_BALANCE")
    private BigDecimal paCashBalance;

    /**
     * Original: PA-APPROVED-AUTH-CNT S9(04) (line 27)
     * PIC clause: S9(04)
     */
    @JsonProperty("PA_APPROVED_AUTH_CNT")
    private Integer paApprovedAuthCnt;

    /**
     * Original: PA-DECLINED-AUTH-CNT S9(04) (line 28)
     * PIC clause: S9(04)
     */
    @JsonProperty("PA_DECLINED_AUTH_CNT")
    private Integer paDeclinedAuthCnt;

    /**
     * Original: PA-APPROVED-AUTH-AMT S9(09)V99 (line 29)
     * PIC clause: S9(09)V99
     */
    @JsonProperty("PA_APPROVED_AUTH_AMT")
    private BigDecimal paApprovedAuthAmt;

    /**
     * Original: PA-DECLINED-AUTH-AMT S9(09)V99 (line 30)
     * PIC clause: S9(09)V99
     */
    @JsonProperty("PA_DECLINED_AUTH_AMT")
    private BigDecimal paDeclinedAuthAmt;

}
