package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: CVCRD01Y
 * @citation app/cpy/CVCRD01Y.cpy
 * 9 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cvcrd01y implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: CCARD-AID X(5) (line 3)
     * PIC clause: X(5)
     */
    @JsonProperty("CCARD_AID")
    private String ccardAid;

    /**
     * Original: CCARD-NEXT-PROG X(8) (line 21)
     * PIC clause: X(8)
     */
    @JsonProperty("CCARD_NEXT_PROG")
    private String ccardNextProg;

    /**
     * Original: CCARD-NEXT-MAPSET X(7) (line 23)
     * PIC clause: X(7)
     */
    @JsonProperty("CCARD_NEXT_MAPSET")
    private String ccardNextMapset;

    /**
     * Original: CCARD-NEXT-MAP X(7) (line 24)
     * PIC clause: X(7)
     */
    @JsonProperty("CCARD_NEXT_MAP")
    private String ccardNextMap;

    /**
     * Original: CCARD-ERROR-MSG X(75) (line 28)
     * PIC clause: X(75)
     */
    @JsonProperty("CCARD_ERROR_MSG")
    private String ccardErrorMsg;

    /**
     * Original: CCARD-RETURN-MSG X(75) (line 29)
     * PIC clause: X(75)
     */
    @JsonProperty("CCARD_RETURN_MSG")
    private String ccardReturnMsg;

    /**
     * Original: CC-ACCT-ID X(11) (line 34)
     * PIC clause: X(11)
     */
    @JsonProperty("CC_ACCT_ID")
    private String ccAcctId;

    /**
     * Original: CC-CARD-NUM X(16) (line 37)
     * PIC clause: X(16)
     */
    @JsonProperty("CC_CARD_NUM")
    private String ccCardNum;

    /**
     * Original: CC-CUST-ID X(09) (line 40)
     * PIC clause: X(09)
     */
    @JsonProperty("CC_CUST_ID")
    private String ccCustId;

}
