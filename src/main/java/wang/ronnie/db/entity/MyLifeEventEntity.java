package wang.ronnie.db.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ronnie on 2016/4/22.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_my_life_event")
public class MyLifeEventEntity {

    private Long id;

    private Date eventStartTime;

    private Date eventEndTime;

    private String eventDescription;

    private String eventResources;

    private int eventType;

    private Long createdBy;

    private Long lastModifiedBy;

    private Date createdTime;

    private Date lastModifiedTime;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "event_start_time", nullable = false)
    public Date getEventStartTime() {

        return eventStartTime;
    }

    public void setEventStartTime(Date eventStartTime) {

        this.eventStartTime = eventStartTime;
    }

    @Basic
    @Column(name = "event_end_time", nullable = false)
    public Date getEventEndTime() {

        return eventEndTime;
    }

    public void setEventEndTime(Date eventEndTime) {

        this.eventEndTime = eventEndTime;
    }

    @Basic
    @Column(name = "event_description", nullable = false, columnDefinition = "longtext")
    public String getEventDescription() {

        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {

        this.eventDescription = eventDescription;
    }

    @Basic
    @Column(name = "event_resources", columnDefinition = "longtext")
    public String getEventResources() {

        return eventResources;
    }

    public void setEventResources(String eventResources) {

        this.eventResources = eventResources;
    }

    @Basic
    @Column(name = "event_type", nullable = false)
    public int getEventType() {

        return eventType;
    }

    public void setEventType(int eventType) {

        this.eventType = eventType;
    }

    @Basic
    @CreatedBy
    @Column(name = "created_by", nullable = false)
    public Long getCreatedBy() {

        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {

        this.createdBy = createdBy;
    }

    @Basic
    @LastModifiedBy
    @Column(name = "last_modified_by", nullable = false)
    public Long getLastModifiedBy() {

        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {

        this.lastModifiedBy = lastModifiedBy;
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

        MyLifeEventEntity that = (MyLifeEventEntity) o;

        if (id != that.id) return false;
        if (eventType != that.eventType) return false;
        if (createdBy != that.createdBy) return false;
        if (lastModifiedBy != that.lastModifiedBy) return false;
        if (eventStartTime != null ? !eventStartTime.equals(that.eventStartTime) : that.eventStartTime != null)
            return false;
        if (eventEndTime != null ? !eventEndTime.equals(that.eventEndTime) : that.eventEndTime != null) return false;
        if (eventDescription != null ? !eventDescription.equals(that.eventDescription) : that.eventDescription != null)
            return false;
        if (eventResources != null ? !eventResources.equals(that.eventResources) : that.eventResources != null)
            return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (lastModifiedTime != null ? !lastModifiedTime.equals(that.lastModifiedTime) : that.lastModifiedTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = 1;
        result = 31 * result + (eventStartTime != null ? eventStartTime.hashCode() : 0);
        result = 31 * result + (eventEndTime != null ? eventEndTime.hashCode() : 0);
        result = 31 * result + (eventDescription != null ? eventDescription.hashCode() : 0);
        result = 31 * result + (eventResources != null ? eventResources.hashCode() : 0);
        result = 31 * result + eventType;
        result = 31 * result + (int) (createdBy ^ (createdBy >>> 32));
        result = 31 * result + (int) (lastModifiedBy ^ (lastModifiedBy >>> 32));
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (lastModifiedTime != null ? lastModifiedTime.hashCode() : 0);
        return result;
    }
}
