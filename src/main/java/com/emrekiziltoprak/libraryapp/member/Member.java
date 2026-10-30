package com.emrekiziltoprak.libraryapp.member;

import com.emrekiziltoprak.libraryapp.utils.EmailValidator;
import com.emrekiziltoprak.libraryapp.utils.Utils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

//domain entity
@Entity
@Table(name = "member")
@Getter
public class Member {

    @Column
    private LocalDateTime createdAt;

    @Id
    private UUID id;

    @Column
    private String fullName;

    @Column
    private String email;

    protected Member () {}

    public Member(String fullName, String email) {

        boolean isEmailValid = EmailValidator.isValid(email);

        if(!isEmailValid) throw new IllegalArgumentException("Email is invalid");

        Utils.requireNotBlank(fullName, "fullName");

        this.fullName = fullName;
        this.createdAt = LocalDateTime.now();
        this.email = email.toLowerCase(Locale.ROOT).trim();
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Member{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Member m)) return false;
        return Objects.equals(this.id, m.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    public void changeName(String name){
        Utils.requireNotBlank(name, "name");
        this.fullName = name;
    }
    public void changeEmail(String email){
        boolean isValidmail = EmailValidator.isValid(email);
        if(!isValidmail) throw new IllegalArgumentException("Email is invalid");

        this.email = email.toLowerCase(Locale.ROOT).trim();

    }


}
