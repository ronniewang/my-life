package wang.ronnie.db.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ronnie on 2016/4/22.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_user")
public class UserEntity {

    private long id;

    private String email;

    private String mobilePhone;

    private String password;

    private String username;

    private Boolean isEmailValid;

    private Boolean isMobilePhoneValid;

    private Date createdTime;

    private Date lastModifiedTime;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    @Basic
    @Column(name = "mobile_phone", nullable = true, length = 255)
    public String getMobilePhone() {

        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {

        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    @Basic
    @Column(name = "is_email_valid", nullable = true)
    public Boolean getEmailValid() {

        return isEmailValid;
    }

    public void setEmailValid(Boolean emailValid) {

        isEmailValid = emailValid;
    }

    @Basic
    @Column(name = "is_mobile_phone_valid", nullable = true)
    public Boolean getMobilePhoneValid() {

        return isMobilePhoneValid;
    }

    public void setMobilePhoneValid(Boolean mobilePhoneValid) {

        isMobilePhoneValid = mobilePhoneValid;
    }

    @Basic
    @CreatedDate
    @Column(name = "created_time", nullable = false)
    public Date getCreatedTime() {

        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {

        this.createdTime = createdTime;
    }

    @Basic
    @LastModifiedDate
    @Column(name = "last_modified_time", nullable = false)
    public Date getLastModifiedTime() {

        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {

        this.lastModifiedTime = lastModifiedTime;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (isEmailValid != null ? !isEmailValid.equals(that.isEmailValid) : that.isEmailValid != null) return false;
        if (isMobilePhoneValid != null ? !isMobilePhoneValid.equals(that.isMobilePhoneValid) : that.isMobilePhoneValid != null)
            return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (lastModifiedTime != null ? !lastModifiedTime.equals(that.lastModifiedTime) : that.lastModifiedTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (isEmailValid != null ? isEmailValid.hashCode() : 0);
        result = 31 * result + (isMobilePhoneValid != null ? isMobilePhoneValid.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (lastModifiedTime != null ? lastModifiedTime.hashCode() : 0);
        return result;
    }
}
