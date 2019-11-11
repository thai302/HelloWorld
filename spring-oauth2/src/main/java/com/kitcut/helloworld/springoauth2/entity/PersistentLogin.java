package com.kitcut.helloworld.springoauth2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "persistent_logins")
public class PersistentLogin {

    @Id
    @Column(name = "Series", length = 64, nullable = false)
    private String series;

    @Column(name = "Username", length = 64, nullable = false)
    private String userName;

    @Column(name = "Token", length = 64, nullable = false)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Last_Used", nullable = false)
    private Date lastUsed;
}
