package com.nexse.swat.curator.integration;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 26/05/12
 * Time: 15:13
 * To change this template use File | Settings | File Templates.
 */

public class CamelTest extends CamelSpringTestSupport {
    @Produce(uri="direct:process")
    ProducerTemplate producerTemplate;

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
    }

    @Test
    public void twitterBeanTest(){
        producerTemplate.sendBody("");

    }

}
