package com.test.service.impl;

import com.test.db.entity.MyLifeEventEntity;
import com.test.db.repository.MyLifeEventRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Date;

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
    public void testAdd_addNewEvent_shouldCallMyLifeEventRepositoryFindAndSave() {

        final Date now = new Date();
        MyLifeEventEntity entity = new MyLifeEventEntity();
        entity.setEventEndTime(now);
        when(myLifeEventRepository.findAll(isA(PageRequest.class))).thenAnswer(new Answer<Page<MyLifeEventEntity>>() {

            @Override
            public Page<MyLifeEventEntity> answer(InvocationOnMock invocationOnMock) throws Throwable {

                MyLifeEventEntity query = new MyLifeEventEntity();
                long beforeNow = now.getTime() - 10000L;
                query.setEventEndTime(new Date(beforeNow));
                return new PageImpl<>(Arrays.asList(query));
            }
        });
        when(myLifeEventRepository.save(isA(MyLifeEventEntity.class))).thenAnswer(new Answer<MyLifeEventEntity>() {

            @Override
            public MyLifeEventEntity answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new MyLifeEventEntity();
            }
        });
        myLifeEventService.add(entity);
        verify(myLifeEventRepository).save(isA(MyLifeEventEntity.class));
        verify(myLifeEventRepository).findAll(isA(PageRequest.class));
        verifyNoMoreInteractions(myLifeEventRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd_addIllegalEvent_shouldThrowIllegalArgumentException() {

        final Date now = new Date();
        MyLifeEventEntity entity = new MyLifeEventEntity();
        entity.setEventEndTime(now);
        when(myLifeEventRepository.findAll(isA(PageRequest.class))).thenAnswer(new Answer<Page<MyLifeEventEntity>>() {

            @Override
            public Page<MyLifeEventEntity> answer(InvocationOnMock invocationOnMock) throws Throwable {

                MyLifeEventEntity query = new MyLifeEventEntity();
                Date afterNow = new Date(now.getTime() + 10000L);
                query.setEventEndTime(afterNow);
                return new PageImpl<>(Arrays.asList(query));
            }
        });
        myLifeEventService.add(entity);
    }

    @Test(expected = NullPointerException.class)
    public void testAdd_addNullEvent_shouldThrowNullPointerException() {

        MyLifeEventEntity entity = null;
        myLifeEventService.add(entity);
    }
}