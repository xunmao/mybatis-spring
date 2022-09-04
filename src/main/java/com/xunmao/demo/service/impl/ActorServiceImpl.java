package com.xunmao.demo.service.impl;

import java.util.List;
import java.util.Map;

import com.xunmao.demo.dao.ActorMapper;
import com.xunmao.demo.pojo.Actor;
import com.xunmao.demo.service.ActorService;

public class ActorServiceImpl implements ActorService {

    private ActorMapper actorMapper;

    public void setActorMapper(ActorMapper actorMapper) {
        this.actorMapper = actorMapper;
    }

    @Override
    public void addActor(Actor actor) {
        actorMapper.addActor(actor);
    }

    @Override
    public void deleteActor(int actorId) {
        actorMapper.deleteActor(actorId);
    }

    @Override
    public Actor findActorById(int actorId) {
        return actorMapper.findActorById(actorId);
    }

    @Override
    public List<Actor> listActors() {
        return actorMapper.listActors();
    }

    @Override
    public List<Actor> listActorsByLastNameLike(String value) {
        return actorMapper.listActorsByLastNameLike(value);
    }

    @Override
    public List<Actor> listActorsLike(Map<String, String> parameterMap) {
        return actorMapper.listActorsLike(parameterMap);
    }

    @Override
    public List<Actor> listActorsWithLimit(Map<String, Integer> parameterMap) {
        return actorMapper.listActorsWithLimit(parameterMap);
    }

    @Override
    public void updateActor(Actor actor) {
        actorMapper.updateActor(actor);
    }

    @Override
    public void updateActorWithMap(Map<String, Object> parameterMap) {
        actorMapper.updateActorWithMap(parameterMap);
    }
}
