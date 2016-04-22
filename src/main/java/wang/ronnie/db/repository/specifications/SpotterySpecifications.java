package wang.ronnie.db.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import wang.ronnie.db.entity.MyLifeEventEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class SpotterySpecifications {

    public static Specification<MyLifeEventEntity> startDateTimeBetween(final Date startDate, final Date endDate) {

        return new Specification<MyLifeEventEntity>() {

            @Override
            public Predicate toPredicate(Root<MyLifeEventEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                return cb.and(
                        cb.greaterThanOrEqualTo(root.<Date>get("eventStartTime"), startDate),
                        cb.lessThan(root.<Date>get("eventEndTime"), endDate));
            }

        };
    }
}