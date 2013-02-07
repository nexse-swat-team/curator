package com.nexse.swat.curator.integration;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.junit4.CamelSpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 26/05/12
 * Time: 15:13
 * To change this template use File | Settings | File Templates.
 */


@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/camel-context.xml")
public class CamelTest{
    @Produce(uri="direct:process")
    ProducerTemplate producerTemplate;

    @Test
    public void twitterBeanTest(){
        producerTemplate.sendBody("");

    }

}
