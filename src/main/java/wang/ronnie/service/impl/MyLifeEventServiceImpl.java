package wang.ronnie.service.impl;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wang.ronnie.db.entity.MyLifeEventEntity;
import wang.ronnie.db.repository.MyLifeEventRepository;
import wang.ronnie.service.MyLifeEventService;

/**
 * Created by ronnie on 2016/4/25.
 *
 * @author ronnie
 */
@Service
public class MyLifeEventServiceImpl implements MyLifeEventService {

    @Autowired
    private MyLifeEventRepository lifeEventRepository;

    /**
     * 加入一个新event
     *
     * @param lifeEventEntity not null
     * @throws IllegalArgumentException 如果事件时间有冲突的话，抛此异常
     */
    @Override
    public void add(MyLifeEventEntity lifeEventEntity) {

        Preconditions.checkNotNull(lifeEventEntity);
        Page<MyLifeEventEntity> queries = lifeEventRepository.findAll(new PageRequest(0, 1, Sort.Direction.DESC, "eventEndTime"));
        for (MyLifeEventEntity query : queries) {
            if (lifeEventEntity.getEventEndTime().after(query.getEventEndTime())) {
                lifeEventRepository.save(lifeEventEntity);
                return;
            }
            throw new IllegalArgumentException("这个时间已经干了别的事了");
        }
    }
}
