package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "rulename")
public class RuleName implements Serializable {
    // TODO: Map columns in data table RULENAME with corresponding java fields

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Length(max=125, message = "Maximum length = 125 characters")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Length(max=125, message = "Maximum length = 125 characters")
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Length(max=125, message = "Maximum length = 125 characters")
    @NotBlank(message = "Json is mandatory")
    private String json;

    @Length(max=512, message = "Maximum length = 125 characters")
    @NotBlank(message = "Template is mandatory")
    private String template;

    @Length(max=125, message = "Maximum length = 125 characters")
    @NotBlank(message = "SqlStr is mandatory")
    private String sqlStr;

    @Length(max=125, message = "Maximum length = 125 characters")
    @NotBlank(message = "SqlPart is mandatory")
    private String sqlPart;

    public RuleName() {
    }

    public RuleName(@Length(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "Name is mandatory") String name, @Length(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "Description is mandatory") String description, @Length(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "Json is mandatory") String json, @Length(max = 512, message = "Maximum length = 125 characters") @NotBlank(message = "Template is mandatory") String template, @Length(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "SqlStr is mandatory") String sqlStr, @Length(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "SqlPart is mandatory") String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }
}
