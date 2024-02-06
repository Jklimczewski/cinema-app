package com.example.backend.actor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {

  private final ActorRepository actorRepository;

  public ActorServiceImpl(ActorRepository actorRepository) {
    this.actorRepository = actorRepository;
  }

  @Override
  public Actor save(Actor actor) {
    return actorRepository.save(actor);
  }

  @Override
  public Actor getById(Integer id) {
    return actorRepository.findById(id).get();
  }

  @Override
  public List<Actor> getAll() {
    return actorRepository.findAll();
  }

  @Override
  public Actor update(Integer id, Actor actor) {
    Actor actorToUpdate = getById(id);
    actorToUpdate.update(actor);
    return actorRepository.save(actorToUpdate);
  }

  @Override
  public void deleteById(Integer id) {
    Actor actorToDelete = getById(id);
    actorRepository.delete(actorToDelete);
  }

}
