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


    @Column(nullable = false, length = 50)
    private String emailAddress;

    @Column(nullable = false, length = 50)
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(nullable = false, length = 50)
    private String DOB;
    @Column(nullable = false, length = 50)
    private String stateOfOrigin;
    @Column(nullable = false, length = 50)
    private Long phoneNumber;
    @Column(nullable = false, length = 50)
    private String stateOfResidence;


//Education
@Column(nullable = false, length = 25)
    private String higestQualification;
    @Column(nullable = false, length = 25)
    private String courseOfStudy;
    @Column(nullable = false, length = 50)
    private String School;
    @Column(nullable = false, length = 25)
    private String gradeAchieved;
    @Column(nullable = false, length = 25)
    private String NYSCStatus;

    //Others
    @Column(nullable = false, length = 50)
    private String resumptionDateandLocation;
    @Column(nullable = false, length = 50)
    private String howDidYouHearAboutUs;
    @Column(nullable = false, length = 50)
    private String paymentOption;
    @Column(nullable = false, length = 50)
    private String linkedInProfileLink;

    //Programming Experience
    @Column(nullable = false, length = 50)
    private int yearsOfExperience;
    @Column(nullable = false, length = 50)
    private String describeYourProgrammingContributionToTheProduct;
    @Column(nullable = false, length = 20)
    private Boolean haveYouContributedtoAnyProjectUsingYourProgrammingExperience;

    //Achievements & Leadership
    @Column(nullable = false, length = 60)
    private String whatDoYouConsiderAsYourMostOutstandingAchievements;
    @Column(nullable = false, length = 50)
    private Boolean haveYouHeldAnyLeadershipPositionInThePast;
    @Column(nullable = false, length = 50)
    private String describeTheLeadershipPositionAndHowYouPlayedThem;

    //Commitment to the Decagon Mission
    @Column(nullable = false, length = 500)
    private String tellUsWhyYouAreAnIdealCandidateForOurSoftwareEngineeringProgram;


    @Column(length = 5000)
    private  String  description;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 50)
    private String middleName;

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