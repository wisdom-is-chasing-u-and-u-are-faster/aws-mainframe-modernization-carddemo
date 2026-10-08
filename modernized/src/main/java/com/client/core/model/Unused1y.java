package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: UNUSED1Y
 * @citation app/cpy/UNUSED1Y.cpy
 * 6 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Unused1y implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: UNUSED-ID X(08) (line 2)
     * PIC clause: X(08)
     */
    @JsonProperty("UNUSED_ID")
    private String unusedId;

    /**
     * Original: UNUSED-FNAME X(20) (line 3)
     * PIC clause: X(20)
     */
    @JsonProperty("UNUSED_FNAME")
    private String unusedFname;

    /**
     * Original: UNUSED-LNAME X(20) (line 4)
     * PIC clause: X(20)
     */
    @JsonProperty("UNUSED_LNAME")
    private String unusedLname;

    /**
     * Original: UNUSED-PWD X(08) (line 5)
     * PIC clause: X(08)
     */
    @JsonProperty("UNUSED_PWD")
    private String unusedPwd;

    /**
     * Original: UNUSED-TYPE X(01) (line 6)
     * PIC clause: X(01)
     */
    @JsonProperty("UNUSED_TYPE")
    private String unusedType;

    /**
     * Original: UNUSED-FILLER X(23) (line 7)
     * PIC clause: X(23)
     */
    @JsonProperty("UNUSED_FILLER")
    private String unusedFiller;

}
