package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: CCPAURLY
 * @citation app/app-authorization-ims-db2-mq/cpy/CCPAURLY.cpy
 * 6 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ccpaurly implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: PA-RL-CARD-NUM X(16) (line 19)
     * PIC clause: X(16)
     */
    @JsonProperty("PA_RL_CARD_NUM")
    private String paRlCardNum;

    /**
     * Original: PA-RL-TRANSACTION-ID X(15) (line 20)
     * PIC clause: X(15)
     */
    @JsonProperty("PA_RL_TRANSACTION_ID")
    private String paRlTransactionId;

    /**
     * Original: PA-RL-AUTH-ID-CODE X(06) (line 21)
     * PIC clause: X(06)
     */
    @JsonProperty("PA_RL_AUTH_ID_CODE")
    private String paRlAuthIdCode;

    /**
     * Original: PA-RL-AUTH-RESP-CODE X(02) (line 22)
     * PIC clause: X(02)
     */
    @JsonProperty("PA_RL_AUTH_RESP_CODE")
    private String paRlAuthRespCode;

    /**
     * Original: PA-RL-AUTH-RESP-REASON X(04) (line 23)
     * PIC clause: X(04)
     */
    @JsonProperty("PA_RL_AUTH_RESP_REASON")
    private String paRlAuthRespReason;

    /**
     * Original: PA-RL-APPROVED-AMT +9(10).99 (line 24)
     * PIC clause: +9(10).99
     */
    @JsonProperty("PA_RL_APPROVED_AMT")
    private String paRlApprovedAmt;

}
