package com.greentechpay.mssearch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CacheConcurrencyStrategy;


import java.util.List;

@Entity
@Table(name = "categories", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Data
//@Cacheable
//@org.hibernate.annotations.Cache(region = "categories", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category {
    @Id
    private Integer id;
    private String name;
    @Column(name = "desc_id")
    private String decId;
    @OneToMany(mappedBy = "categoryId", fetch = FetchType.EAGER)
    private List<AppService> appServiceList;
}
