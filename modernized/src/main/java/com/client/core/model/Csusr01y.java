package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: CSUSR01Y
 * @citation app/cpy/CSUSR01Y.cpy
 * 6 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Csusr01y implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: SEC-USR-ID X(08) (line 18)
     * PIC clause: X(08)
     */
    @JsonProperty("SEC_USR_ID")
    private String secUsrId;

    /**
     * Original: SEC-USR-FNAME X(20) (line 19)
     * PIC clause: X(20)
     */
    @JsonProperty("SEC_USR_FNAME")
    private String secUsrFname;

    /**
     * Original: SEC-USR-LNAME X(20) (line 20)
     * PIC clause: X(20)
     */
    @JsonProperty("SEC_USR_LNAME")
    private String secUsrLname;

    /**
     * Original: SEC-USR-PWD X(08) (line 21)
     * PIC clause: X(08)
     */
    @JsonProperty("SEC_USR_PWD")
    private String secUsrPwd;

    /**
     * Original: SEC-USR-TYPE X(01) (line 22)
     * PIC clause: X(01)
     */
    @JsonProperty("SEC_USR_TYPE")
    private String secUsrType;

    /**
     * Original: SEC-USR-FILLER X(23) (line 23)
     * PIC clause: X(23)
     */
    @JsonProperty("SEC_USR_FILLER")
    private String secUsrFiller;

}
