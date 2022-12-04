package com.decagon.recruitmentportal.entities;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Document("applicants")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Applicant implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @Indexed(unique = true)
    private String uuid;

    private String emailAddress;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @PrePersist
    private void setCreatedAt() {
        createdDate = new Date();
    }

    @PreUpdate
    private void setUpdatedAt() {
        updatedDate = new Date();
    }

}