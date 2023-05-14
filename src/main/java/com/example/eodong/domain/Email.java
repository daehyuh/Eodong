package com.example.eodong.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EMAIL")
public class Email {
    @Id
    @Column(name = "email_idx")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "Email_seq")
    @SequenceGenerator(name="Email_seq", sequenceName = "Email_seq", initialValue = 0, allocationSize = 1)
    private Long emailIdx;
    @Column(name = "email_number")
    private String emailNumber;
    @Column(name = "email_mail")
    private String emailMail;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "email_date")
    private Date emailDate;

    public Long getEmailIdx() {
        return emailIdx;
    }

    public void setEmailIdx(Long emailIdx) {
        this.emailIdx = emailIdx;
    }

    public String getEmailNumber() {
        return emailNumber;
    }

    public void setEmailNumber(String emailNumber) {
        this.emailNumber = emailNumber;
    }

    public String getEmailMail() {
        return emailMail;
    }

    public void setEmailMail(String emailMail) {
        this.emailMail = emailMail;
    }

    public Date getEmailDate() {
        return emailDate;
    }

    public void setEmailDate(Date emailDate) {
        this.emailDate = emailDate;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailIdx=" + emailIdx +
                ", emailNumber='" + emailNumber + '\'' +
                ", emailMail='" + emailMail + '\'' +
                ", emailDate=" + emailDate +
                '}';
    }
}
