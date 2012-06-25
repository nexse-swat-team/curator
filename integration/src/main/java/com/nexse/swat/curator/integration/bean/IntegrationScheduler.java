package com.nexse.swat.curator.integration.bean;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: germano
 * Date: 25/06/12
 * Time: 11:48
 * To change this template use File | Settings | File Templates.
 */
@Service
public class IntegrationScheduler {
    @Produce(uri="direct:process")
    private ProducerTemplate producerTemplate;

    private static final Logger logger =
            LoggerFactory.getLogger(IntegrationScheduler.class);


    @Scheduled(fixedDelay = 5*60*60*1000)
    public void work() {
        logger.info("Starting a new integration poll");
        producerTemplate.sendBody("Trigger message: "+ Calendar.getInstance().getTime());
    }



}
