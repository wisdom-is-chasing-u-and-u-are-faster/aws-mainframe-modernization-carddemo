package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: CSMSG02Y
 * @citation app/cpy/CSMSG02Y.cpy
 * 4 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Csmsg02y implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: ABEND-CODE X(4) (line 22)
     * PIC clause: X(4)
     */
    @JsonProperty("ABEND_CODE")
    private String abendCode;

    /**
     * Original: ABEND-CULPRIT X(8) (line 24)
     * PIC clause: X(8)
     */
    @JsonProperty("ABEND_CULPRIT")
    private String abendCulprit;

    /**
     * Original: ABEND-REASON X(50) (line 26)
     * PIC clause: X(50)
     */
    @JsonProperty("ABEND_REASON")
    private String abendReason;

    /**
     * Original: ABEND-MSG X(72) (line 28)
     * PIC clause: X(72)
     */
    @JsonProperty("ABEND_MSG")
    private String abendMsg;

}
