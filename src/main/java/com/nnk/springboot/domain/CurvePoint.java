package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "CurveId must not been null")
    private Integer curveId;

    @NotBlank(message = "AsOfDate is mandatory")
    private Timestamp asOfDate;

    @NotNull(message = "Term must not been null")
    private Double term;

    @NotNull(message = "Value must not been null")
    private Double value;

    @NotBlank(message = "Account is mandatory")
    private Timestamp creationDate;

    public CurvePoint() {
    }

    public CurvePoint(@NotNull(message = "CurveId must not been null") Integer curveId, @NotBlank(message = "AsOfDate is mandatory") Timestamp asOfDate, @NotNull(message = "Term must not been null") Double term, @NotNull(message = "Value must not been null") Double value, @NotBlank(message = "Account is mandatory") Timestamp creationDate) {
        this.curveId = curveId;
        this.asOfDate = asOfDate;
        this.term = term;
        this.value = value;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
