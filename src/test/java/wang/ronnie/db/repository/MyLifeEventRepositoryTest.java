package wang.ronnie.db.repository;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import wang.ronnie.db.entity.MyLifeEventEntity;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by ronnie on 2016/4/22.
 *
 * @author ronnie
 */
public class MyLifeEventRepositoryTest {
    @Test
    public void testGetMyLifeEvent() {

        RestTemplate restTemplate = new RestTemplate();
//        MyLifeEventEntity entity = restTemplate.getForObject("http://localhost:8081/my-life/events/1", MyLifeEventEntity.class);
//        assertTrue(entity != null);
    }

    @Test
    public void testPostMyLifeEvent() {

        RestTemplate restTemplate = new RestTemplate();
        MyLifeEventEntity myLifeEventEntity = new MyLifeEventEntity();
        long myId = 1L;
        String eventDescription = "test";
        Date eventStartTime = new Date(2016, 04, 22, 7, 15, 10);
        Date eventEndTime = new Date(2016, 04, 22, 8, 10, 10);
        myLifeEventEntity.setEventDescription(eventDescription);
        myLifeEventEntity.setEventStartTime(eventStartTime);
        myLifeEventEntity.setEventEndTime(eventEndTime);
        myLifeEventEntity.setCreatedBy(myId);
        myLifeEventEntity.setLastModifiedBy(myId);
        myLifeEventEntity.setCreatedTime(new Date());
        myLifeEventEntity.setLastModifiedTime(new Date());
//        ResponseEntity<MyLifeEventEntity> newEntity = restTemplate.postForEntity("http://localhost:8081/my-life/events", myLifeEventEntity, MyLifeEventEntity.class);
    }
}