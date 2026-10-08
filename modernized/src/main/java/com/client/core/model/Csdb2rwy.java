package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: CSDB2RWY
 * @citation app/app-transaction-type-db2/cpy/CSDB2RWY.cpy
 * 8 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Csdb2rwy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: WS-DISP-SQLCODE ----9 (line 22)
     * PIC clause: ----9
     */
    @JsonProperty("WS_DISP_SQLCODE")
    private String wsDispSqlcode;

    /**
     * Original: WS-DUMMY-DB2-INT S9(4) (line 23)
     * PIC clause: S9(4)
     */
    @JsonProperty("WS_DUMMY_DB2_INT")
    private Integer wsDummyDb2Int;

    /**
     * Original: WS-DB2-PROCESSING-FLAG X(1) (line 25)
     * PIC clause: X(1)
     */
    @JsonProperty("WS_DB2_PROCESSING_FLAG")
    private String wsDb2ProcessingFlag;

    /**
     * Original: WS-DB2-CURRENT-ACTION X(72) (line 28)
     * PIC clause: X(72)
     */
    @JsonProperty("WS_DB2_CURRENT_ACTION")
    private String wsDb2CurrentAction;

    /**
     * Original: WS-DSNTIAC-MESG-LEN S9(4) (line 34)
     * PIC clause: S9(4)
     */
    @JsonProperty("WS_DSNTIAC_MESG_LEN")
    private Integer wsDsntiacMesgLen;

    /**
     * Original: WS-DSNTIAC-LRECL S9(4) (line 41)
     * PIC clause: S9(4)
     */
    @JsonProperty("WS_DSNTIAC_LRECL")
    private Integer wsDsntiacLrecl;

    /**
     * Original: WS-DSNTIAC-ERR-MSG X(10) (line 43)
     * PIC clause: X(10)
     */
    @JsonProperty("WS_DSNTIAC_ERR_MSG")
    private String wsDsntiacErrMsg;

    /**
     * Original: WS-DSNTIAC-ERR-CD-X X(02) (line 44)
     * PIC clause: X(02)
     */
    @JsonProperty("WS_DSNTIAC_ERR_CD_X")
    private String wsDsntiacErrCdX;

}
