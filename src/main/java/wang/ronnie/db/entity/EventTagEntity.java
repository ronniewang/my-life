package wang.ronnie.db.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ronnie on 2016/4/25.
 *
 * @author ronnie
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_event_tag")
public class EventTagEntity {

    private Long id;

    private Long userId;

    private String tag;

    private Date createdTime;

    private Date lastModifiedTime;

    private Long createdBy;

    private Long lastModifiedBy;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public Long getUserId() {

        return userId;
    }

    public void setUserId(Long userId) {

        this.userId = userId;
    }

    @Column(name = "tag", nullable = false)
    public String getTag() {

        return tag;
    }

    public void setTag(String tag) {

        this.tag = tag;
    }

    @CreatedDate
    @Column(name = "created_time", nullable = true)
    public Date getCreatedTime() {

        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {

        this.createdTime = createdTime;
    }

    @LastModifiedDate
    @Column(name = "last_modified_time", nullable = true)
    public Date getLastModifiedTime() {

        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {

        this.lastModifiedTime = lastModifiedTime;
    }

    @CreatedBy
    @Column(name = "created_by", nullable = true)
    public Long getCreatedBy() {

        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {

        this.createdBy = createdBy;
    }

    @LastModifiedBy
    @Column(name = "last_modified_by", nullable = true)
    public Long getLastModifiedBy() {

        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {

        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventTagEntity that = (EventTagEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (lastModifiedTime != null ? !lastModifiedTime.equals(that.lastModifiedTime) : that.lastModifiedTime != null)
            return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (lastModifiedBy != null ? !lastModifiedBy.equals(that.lastModifiedBy) : that.lastModifiedBy != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (lastModifiedTime != null ? lastModifiedTime.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
        return result;
    }
}
