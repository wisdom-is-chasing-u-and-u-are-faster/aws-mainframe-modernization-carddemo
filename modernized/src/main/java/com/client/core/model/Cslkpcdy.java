package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: CSLKPCDY
 * @citation app/cpy/CSLKPCDY.cpy
 * 4 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cslkpcdy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: WS-US-PHONE-AREA-CODE-TO-EDIT XXX (line 24)
     * PIC clause: XXX
     */
    @JsonProperty("WS_US_PHONE_AREA_CODE_TO_EDIT")
    private String wsUsPhoneAreaCodeToEdit;

    /**
     * Original: US-STATE-CODE-TO-EDIT X(2) (line 1012)
     * PIC clause: X(2)
     */
    @JsonProperty("US_STATE_CODE_TO_EDIT")
    private String usStateCodeToEdit;

    /**
     * Original: US-STATE-AND-FIRST-ZIP2 X(4) (line 1072)
     * PIC clause: X(4)
     */
    @JsonProperty("US_STATE_AND_FIRST_ZIP2")
    private String usStateAndFirstZip2;

    /**
     * Original: LAST-3-OF-ZIP X(3) (line 1314)
     * PIC clause: X(3)
     */
    @JsonProperty("LAST_3_OF_ZIP")
    private String last3OfZip;

}
