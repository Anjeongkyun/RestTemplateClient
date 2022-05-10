//package com.daon.host.web.entity;
//
//import lombok.Getter;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.Column;
//import javax.persistence.EntityListeners;
//import javax.persistence.MappedSuperclass;
//import java.time.LocalDateTime;
//
//@Getter
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public class BaseTime {
//
//    @CreationTimestamp
//    @Column(name = "ins_date")
//    private LocalDateTime createDate;
//
//    @UpdateTimestamp
//    @Column(name = "upd_date")
//    private LocalDateTime modifiedDate;
//}
