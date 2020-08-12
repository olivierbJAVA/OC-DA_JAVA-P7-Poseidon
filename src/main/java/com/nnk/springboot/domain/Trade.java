package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "trade")
public class Trade implements Serializable {
    // TODO: Map columns in data table TRADE with corresponding java fields

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tradeId;

    @Length(max=30, message = "Maximum length = 30 characters")
    @NotBlank(message = "Account is mandatory")
    private String account;

    @Length(max=30, message = "Maximum length = 30 characters")
    @NotBlank(message = "Type is mandatory")
    private String type;

    @Digits(message = "BuyQuantity must be digits number", integer = 6, fraction = 0)
    @NotNull(message = "BuyQuantity must not been null")
    private Double buyQuantity;

    @Digits(message = "SellQuantity must be digits number", integer = 6, fraction = 0)
    private Double sellQuantity;

    @Digits(message = "BuyPrice must be digits number", integer = 3, fraction = 2)
    private Double buyPrice;

    @Digits(message = "SellPrice must be digits number", integer = 3, fraction = 2)
    private Double sellPrice;

    private String benchmark;

    private Timestamp tradeDate;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String security;

    @Length(max=10, message = "Maximum length = 10 characters")
    private String status;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String trader;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String book;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String creationName;

    private Timestamp creationDate;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String revisionName;

    private Timestamp revisionDate;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String dealName;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String dealType;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String sourceListId;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String side;

    public Trade() {
    }

    public Trade(@Length(max = 30, message = "Maximum length = 30 characters") @NotBlank(message = "Account is mandatory") String account, @Length(max = 30, message = "Maximum length = 30 characters") @NotBlank(message = "Type is mandatory") String type, @Digits(message = "BuyQuantity must be digits number", integer = 6, fraction = 0) @NotNull(message = "BuyQuantity must not been null") Double buyQuantity) {
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public Double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(Double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public Timestamp getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
