package com.greentechpay.mssearch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_history", schema = "public")
public class PaymentHistory {

    @Id
    private String id;
    private BigDecimal amount;
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "payment_date")
    private Timestamp paymentDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus paymentStatus;
    @Column(name = "merchant_id")
    private Integer merchantId;
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "try_count")
    private Integer tryCount;
    @Column(name = "external_payment_id")
    private String externalPaymentId;
    @Column(name = "sender_request_id")
    private String senderRequestId;
    private String currency;
    @Column(name = "currency_out")
    private String currencyOut;
    @Column(name = "amount_out")
    private BigDecimal amountOut;
    @Column(name = "service_id")
    private Integer serviceId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
    @Column(name = "to_user")
    private String toUser;
    @Column(name = "date")
    private Date date;
    @Column(name = "transfer_type")
    @Enumerated(EnumType.STRING)
    private TransferType transferType;
}
