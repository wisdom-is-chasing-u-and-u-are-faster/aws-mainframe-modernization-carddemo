package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: CSMSG01Y
 * @citation app/cpy/CSMSG01Y.cpy
 * 2 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Csmsg01y implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: CCDA-MSG-THANK-YOU X(50) (line 18)
     * PIC clause: X(50)
     */
    @JsonProperty("CCDA_MSG_THANK_YOU")
    private String ccdaMsgThankYou;

    /**
     * Original: CCDA-MSG-INVALID-KEY X(50) (line 20)
     * PIC clause: X(50)
     */
    @JsonProperty("CCDA_MSG_INVALID_KEY")
    private String ccdaMsgInvalidKey;

}
