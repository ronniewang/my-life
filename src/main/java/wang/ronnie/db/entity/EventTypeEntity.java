package wang.ronnie.db.entity;

import javax.persistence.*;

/**
 * Created by ronnie on 2016/4/22.
 */
@Entity
@Table(name = "t_event_type")
public class EventTypeEntity {

    private int typeId;

    private String typeDescription;

    private Integer parentTypeId;

    private boolean hasChildren;

    @Id
    @Column(name = "type_id", nullable = false)
    public int getTypeId() {

        return typeId;
    }

    public void setTypeId(int typeId) {

        this.typeId = typeId;
    }

    @Basic
    @Column(name = "type_description", nullable = false, length = 255)
    public String getTypeDescription() {

        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {

        this.typeDescription = typeDescription;
    }

    @Basic
    @Column(name = "parent_type_id", nullable = true)
    public Integer getParentTypeId() {

        return parentTypeId;
    }

    public void setParentTypeId(Integer parentTypeId) {

        this.parentTypeId = parentTypeId;
    }

    @Basic
    @Column(name = "has_children", nullable = false)
    public boolean isHasChildren() {

        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {

        this.hasChildren = hasChildren;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventTypeEntity that = (EventTypeEntity) o;

        if (typeId != that.typeId) return false;
        if (hasChildren != that.hasChildren) return false;
        if (typeDescription != null ? !typeDescription.equals(that.typeDescription) : that.typeDescription != null)
            return false;
        if (parentTypeId != null ? !parentTypeId.equals(that.parentTypeId) : that.parentTypeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = typeId;
        result = 31 * result + (typeDescription != null ? typeDescription.hashCode() : 0);
        result = 31 * result + (parentTypeId != null ? parentTypeId.hashCode() : 0);
        result = 31 * result + (hasChildren ? 1 : 0);
        return result;
    }
}
