package com.greentechpay.mssearch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "services", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Cacheable
//@org.hibernate.annotations.Cache(region = "services", usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppService {
    @Id
    private Integer id;
    private String name;
    @Column(name = "min_amount")
    private Integer minAmount;
    @Column(name = "max_amount")
    private Integer maxAmount;
    @Column(name = "show_service")
    private Boolean showService;
    @Column(name = "desc_id")
    private String descId;
    private String currency;
    @Column(name = "local_fee_rate")
    private Integer localFeeRate;
    @Column(name = "bank_fee_rate")
    private Integer bankFeeRate;
    @Column(name = "create_date")
    private Date createDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;
    @Column(name = "has_debt")
    private Boolean hasDebt;
    @Column(name = "vendor_id")
    private Integer vendorId;
    @Column(name = "vendor_fee_rate")
    private Integer vendorFeeRate;
    @Column(name = "external_service_code")
    private String externalServiceRate;
    @Column(name = "is_smart_card")
    private Boolean isSmartCard;
}
