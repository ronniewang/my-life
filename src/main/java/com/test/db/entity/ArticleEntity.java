package com.test.db.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ronnie wang on 16/12/27.
 */
@Entity
@Table(name = "t_article")
public class ArticleEntity {

    private Long id;

    private String name;

    private String content;

    private Date createdTime;

    private Date lastModifiedTime;

    private Integer readTimes;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, columnDefinition = "char")
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Basic
    @Column(name = "read_times", nullable = false)
    public Integer getReadTimes() {

        return readTimes;
    }

    public void setReadTimes(Integer readTimes) {

        this.readTimes = readTimes;
    }

    @Basic
    @Column(name = "content", nullable = false, length = -1, columnDefinition = "text")
    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    @Basic
    @Column(name = "created_time", nullable = false)
    public Date getCreatedTime() {

        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {

        this.createdTime = createdTime;
    }

    @Basic
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

        ArticleEntity that = (ArticleEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (lastModifiedTime != null ? !lastModifiedTime.equals(that.lastModifiedTime) : that.lastModifiedTime != null)
            return false;
        return readTimes != null ? readTimes.equals(that.readTimes) : that.readTimes == null;

    }

    @Override
    public int hashCode() {

        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (lastModifiedTime != null ? lastModifiedTime.hashCode() : 0);
        result = 31 * result + (readTimes != null ? readTimes.hashCode() : 0);
        return result;
    }
}
