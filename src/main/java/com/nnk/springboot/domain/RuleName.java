package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Class materializing a RuleName entity.
 */
@Entity
@Table(name = "rulename")
public class RuleName implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(max = 125, message = "Maximum length = 125 characters")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Size(max = 125, message = "Maximum length = 125 characters")
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Size(max = 125, message = "Maximum length = 125 characters")
    @NotBlank(message = "Json is mandatory")
    private String json;

    @Size(max = 512, message = "Maximum length = 512 characters")
    @NotBlank(message = "Template is mandatory")
    private String template;

    @Size(max = 125, message = "Maximum length = 125 characters")
    @NotBlank(message = "SqlStr is mandatory")
    private String sqlStr;

    @Size(max = 125, message = "Maximum length = 125 characters")
    @NotBlank(message = "SqlPart is mandatory")
    private String sqlPart;

    public RuleName() {
    }

    /**
     * Constructs a new RuleName with the mandatory fields.
     *
     * @param name        the name of the RuleName
     * @param description the description of the RuleName
     * @param json        the json of the RuleName
     * @param template    the template of the RuleName
     * @param sqlStr      the sqlStr of the RuleName
     * @param sqlPart     the sqlPart of the RuleName
     */
    public RuleName(@Size(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "Name is mandatory") String name, @Size(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "Description is mandatory") String description, @Size(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "Json is mandatory") String json, @Size(max = 512, message = "Maximum length = 512 characters") @NotBlank(message = "Template is mandatory") String template, @Size(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "SqlStr is mandatory") String sqlStr, @Size(max = 125, message = "Maximum length = 125 characters") @NotBlank(message = "SqlPart is mandatory") String sqlPart) {
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
