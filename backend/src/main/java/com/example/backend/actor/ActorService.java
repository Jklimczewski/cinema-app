package com.example.backend.actor;

import java.util.List;

public interface ActorService {
  List<Actor> getAll();

  Actor getById(Integer id);

  Actor save(Actor actor);
}
