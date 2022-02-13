package com.sut.personalmedicalcartbackend.model;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String password;
    private String email;
    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(id, user.id)
                .append(name, user.name)
                .append(surname, user.surname)
                .append(patronymic, user.patronymic)
                .append(password, user.password)
                .append(email, user.email)
                .append(phoneNumber, user.phoneNumber)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(surname)
                .append(patronymic)
                .append(password)
                .append(email)
                .append(phoneNumber)
                .toHashCode();
    }
}
