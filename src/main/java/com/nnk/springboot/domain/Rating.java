package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "rating")
public class Rating implements Serializable {
    // TODO: Map columns in data table RATING with corresponding java fields

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(max=125, message = "Maximum length = 125 characters")
    @NotBlank(message = "MoodysRating is mandatory")
    private String moodysRating;

    @Size(max=125, message = "Maximum length = 125 characters")
    @NotBlank(message = "SandPRating is mandatory")
    private String sandPRating;

    @Size(max=125, message = "Maximum length = 125 characters")
    @NotBlank(message = "FitchRating is mandatory")
    private String fitchRating;

    @Digits(message = "OrderNumber must be integer number", integer = 4, fraction = 0)
    private Integer orderNumber;

    public Rating() {
    }

    public Rating(@Size(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "MoodysRating is mandatory") String moodysRating, @Size(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "SandPRating is mandatory") String sandPRating, @Size(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "FitchRating is mandatory") String fitchRating, @Digits(message = "OrderNumber must be integer number", integer = 4, fraction = 0) Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}

