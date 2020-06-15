package com.example.demo.model;
import lombok.*;
import javax.persistence.*;



@Entity
@Data
@Table(name = "T_MST_COMPANY", indexes = {@Index(name = "index_one", columnList = "Code", unique = true),
                                            @Index(name = "index_two", columnList = "CodeHRIS", unique = true)})
public class Company {

    @Id
    @Column(nullable = false, length = 3)
    private String code;

    @Column(nullable = false, length = 3)
    private String codeHRIS;

    @Column(length = 70)
    private String name;

    @Column(length = 15)
    private String abbrName;

    @Column(length = 20)
    private String regNo;

    private String logo;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date ActiveDate;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdOn;

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date lastModifiedOn;

    @Column(nullable = false)
    private String lastModifiedBy;


    private String deactivatedBy;


    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date deactivatedOn;


    private String reactivatedBy;


    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date reactivatedOn;
}
