package com.xunmao.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActorServiceTest {

    @Test
    public void shouldListActors() {
        ApplicationContext context = new ClassPathXmlApplicationContext("daos.xml", "services.xml");

        ActorService actorService = context.getBean("actorService", com.xunmao.demo.service.ActorService.class);

        actorService.listActors().forEach(System.out::println);
    }

    @Test
    public void shouldListActorsWithLimit() {
        ApplicationContext context = new ClassPathXmlApplicationContext("daos.xml", "services.xml");

        ActorService actorService = context.getBean("actorService", com.xunmao.demo.service.ActorService.class);

        Map<String, Integer> parameterMap = new HashMap<>();
        parameterMap.put("startIndex", 0);
        parameterMap.put("pageSize", 5);

        actorService.listActorsWithLimit(parameterMap).forEach(System.out::println);
    }

    @Test
    public void shouldListActorsLike() {
        ApplicationContext context = new ClassPathXmlApplicationContext("daos.xml", "services.xml");

        ActorService actorService = context.getBean("actorService", com.xunmao.demo.service.ActorService.class);

        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("firstName", "%NI%");
        parameterMap.put("lastName", "%ER%");

        actorService.listActorsLike(parameterMap).forEach(System.out::println);
    }
}
