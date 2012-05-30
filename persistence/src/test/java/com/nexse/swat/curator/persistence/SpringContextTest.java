package com.nexse.swat.curator.persistence;

import com.nexse.swat.curator.persistence.domain.ChannelData;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 24/05/12
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/curator/persistence-context.xml")
public class SpringContextTest {
    @PersistenceContext
    transient EntityManager entityManager;

    @Test
    public void insertTest(){
        ChannelData channelData = new ChannelData();
        channelData.setFromUser("Test");
        channelData.setSource("source");
        channelData.persist();

        Assert.assertTrue(ChannelData.countChannelData() >= 1l);
    }

    @Test
    public void fetchingTest(){
        ChannelData channelData = new ChannelData();
        channelData.setFromUser("Test");
        channelData.setSource("source");
        channelData.persist();
        List<ChannelData> channelDataList = ChannelData.findAllOrderedChannelData();
        Assert.assertTrue(channelDataList.size() > 1);
    }

}
