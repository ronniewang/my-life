package wang.ronnie.service.impl;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void add(MyLifeEventEntity lifeEventEntity) {

        Preconditions.checkNotNull(lifeEventEntity);
        lifeEventRepository.save(lifeEventEntity);
    }
}
