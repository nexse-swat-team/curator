package com.nexse.swat.curator.persistence;

import com.nexse.swat.curator.persistence.domain.ChannelData;
import com.nexse.swat.curator.persistence.domain.Newsletter;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
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
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class SpringContextTest {
    @PersistenceContext
    transient EntityManager entityManager;

    @Test
    public void channelInsertTest(){
        ChannelData channelData = new ChannelData();
        channelData.setFromUser("Test");
        channelData.setSource("source");
        channelData.persist();
        Assert.assertTrue(ChannelData.countChannelData() >= 1l);
    }

    @Test
    public void channelFetchingTest(){
            ChannelData channelData = new ChannelData();
            channelData.setFromUser("Test");
            channelData.setSource("source");
            channelData.persist();
            channelData.flush();
            List<ChannelData> channelDataList = ChannelData.findAllChannelData();
            Assert.assertTrue(channelDataList.size() >= 1);
    }

    @Test
    public void newsletterFetchingTest(){
        Newsletter newsletter = new Newsletter();
        newsletter.setCreatedAt(new Date());
        newsletter.persist();
        newsletter.flush();
        List<Newsletter> newsletterList = Newsletter.findAllNewsletter();
        Assert.assertTrue(newsletterList.size() >= 1);
    }
}
