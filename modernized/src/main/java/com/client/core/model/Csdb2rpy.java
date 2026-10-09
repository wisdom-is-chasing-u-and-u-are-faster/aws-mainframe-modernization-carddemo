package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized JPA Entity generated from Copybook: CSDB2RPY
 * @citation app/app-transaction-type-db2/cpy/CSDB2RPY.cpy
 * Generated entity schema with primary key and record payload.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "csdb2rpy")
public class Csdb2rpy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @JsonProperty("ID")
    private String id;

    @Column(name = "record_data", length = 4000)
    @JsonProperty("RECORD_DATA")
    private String recordData;

}
