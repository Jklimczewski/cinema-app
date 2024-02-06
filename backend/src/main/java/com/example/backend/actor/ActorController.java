package com.example.backend.actor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actors")
public class ActorController {
  private final ActorService actorService;
  private final ActorMapper actorMapper;

  public ActorController(ActorService actorService, ActorMapper actorMapper) {
    this.actorService = actorService;
    this.actorMapper = actorMapper;
  }

  @GetMapping("")
  public List<ActorReadDto> getAllActors() {
    return actorService.getAll()
        .stream()
        .map(actorMapper::toReadDto)
        .toList();
  }

  @GetMapping("/{actorId}")
  public ActorReadDto getActorById(@PathVariable Integer actorId) {
    return actorMapper.toReadDto(actorService.getById(actorId));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ActorReadDto create(@RequestBody ActorWriteDto writeDto) {
    Actor actor = Actor.builder()
        .name(writeDto.name())
        .surrname(writeDto.surrname())
        .build();
    return actorMapper.toReadDto(actorService.save(actor));
  }

  @PutMapping("/{actorId}")
  public ActorReadDto update(
      @PathVariable Integer actorId,
      @RequestBody ActorWriteDto writeDto) {
    Actor entityToUpdate = actorMapper.fromWriteDto(writeDto);
    Actor editedEntity = actorService.update(actorId, entityToUpdate);

    return actorMapper.toReadDto(editedEntity);
  }

  @DeleteMapping("/{actorId}/delete")
  public void delete(@PathVariable Integer actorId) {
    actorService.deleteById(actorId);
  }
}
