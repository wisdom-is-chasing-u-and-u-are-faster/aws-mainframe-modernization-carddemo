package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: CSUTLDWY
 * @citation app/cpy/CSUTLDWY.cpy
 * 16 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Csutldwy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: WS-EDIT-DATE-CC X(2) (line 6)
     * PIC clause: X(2)
     */
    @JsonProperty("WS_EDIT_DATE_CC")
    private String wsEditDateCc;

    /**
     * Original: WS-EDIT-DATE-YY X(2) (line 11)
     * PIC clause: X(2)
     */
    @JsonProperty("WS_EDIT_DATE_YY")
    private String wsEditDateYy;

    /**
     * Original: WS-EDIT-DATE-MM X(2) (line 16)
     * PIC clause: X(2)
     */
    @JsonProperty("WS_EDIT_DATE_MM")
    private String wsEditDateMm;

    /**
     * Original: WS-EDIT-DATE-DD X(2) (line 25)
     * PIC clause: X(2)
     */
    @JsonProperty("WS_EDIT_DATE_DD")
    private String wsEditDateDd;

    /**
     * Original: WS-EDIT-DATE-BINARY S9(9) (line 37)
     * PIC clause: S9(9)
     */
    @JsonProperty("WS_EDIT_DATE_BINARY")
    private Integer wsEditDateBinary;

    /**
     * Original: WS-CURRENT-DATE-YYYYMMDD X(8) (line 39)
     * PIC clause: X(8)
     */
    @JsonProperty("WS_CURRENT_DATE_YYYYMMDD")
    private String wsCurrentDateYyyymmdd;

    /**
     * Original: WS-CURRENT-DATE-BINARY S9(9) (line 42)
     * PIC clause: S9(9)
     */
    @JsonProperty("WS_CURRENT_DATE_BINARY")
    private Integer wsCurrentDateBinary;

    /**
     * Original: WS-EDIT-YEAR-FLG X(01) (line 46)
     * PIC clause: X(01)
     */
    @JsonProperty("WS_EDIT_YEAR_FLG")
    private String wsEditYearFlg;

    /**
     * Original: WS-EDIT-MONTH X(01) (line 50)
     * PIC clause: X(01)
     */
    @JsonProperty("WS_EDIT_MONTH")
    private String wsEditMonth;

    /**
     * Original: WS-EDIT-DAY X(01) (line 54)
     * PIC clause: X(01)
     */
    @JsonProperty("WS_EDIT_DAY")
    private String wsEditDay;

    /**
     * Original: WS-DATE-FORMAT X(08) (line 58)
     * PIC clause: X(08)
     */
    @JsonProperty("WS_DATE_FORMAT")
    private String wsDateFormat;

    /**
     * Original: WS-SEVERITY X(04) (line 61)
     * PIC clause: X(04)
     */
    @JsonProperty("WS_SEVERITY")
    private String wsSeverity;

    /**
     * Original: WS-MSG-NO X(04) (line 66)
     * PIC clause: X(04)
     */
    @JsonProperty("WS_MSG_NO")
    private String wsMsgNo;

    /**
     * Original: WS-RESULT X(15) (line 71)
     * PIC clause: X(15)
     */
    @JsonProperty("WS_RESULT")
    private String wsResult;

    /**
     * Original: WS-DATE X(10) (line 76)
     * PIC clause: X(10)
     */
    @JsonProperty("WS_DATE")
    private String wsDate;

    /**
     * Original: WS-DATE-FMT X(10) (line 81)
     * PIC clause: X(10)
     */
    @JsonProperty("WS_DATE_FMT")
    private String wsDateFmt;

}
