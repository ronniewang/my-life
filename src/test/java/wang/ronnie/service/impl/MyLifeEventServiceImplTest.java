package wang.ronnie.service.impl;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import wang.ronnie.db.entity.MyLifeEventEntity;
import wang.ronnie.db.repository.MyLifeEventRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by ronnie on 2016/4/25.
 *
 * @author ronnie
 */
@RunWith(MockitoJUnitRunner.class)
public class MyLifeEventServiceImplTest {

    @InjectMocks
    private MyLifeEventServiceImpl myLifeEventService;

    @Mock
    private MyLifeEventRepository myLifeEventRepository;

    @Test
    public void testAdd_addNewEvent_shouldCallMyLifeEventRepositoryOnce() {

        MyLifeEventEntity entity = new MyLifeEventEntity();
        when(myLifeEventRepository.save(isA(MyLifeEventEntity.class))).thenAnswer(new Answer<MyLifeEventEntity>() {

            @Override
            public MyLifeEventEntity answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new MyLifeEventEntity();
            }
        });
        myLifeEventService.add(entity);
        verify(myLifeEventRepository).save(isA(MyLifeEventEntity.class));
        verifyNoMoreInteractions(myLifeEventRepository);
    }

    @Test(expected = NullPointerException.class)
    public void testAdd_addNullEvent_shouldThrowNullPointerException() {

        MyLifeEventEntity entity = null;
        myLifeEventService.add(entity);
    }
}