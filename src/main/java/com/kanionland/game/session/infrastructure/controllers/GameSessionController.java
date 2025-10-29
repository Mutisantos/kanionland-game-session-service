package com.kanionland.game.session.infrastructure.controllers;

import com.kanionland.game.session.application.commands.GameActionCommand;
import com.kanionland.game.session.application.mappers.GameActionMapper;
import com.kanionland.game.session.application.ports.input.GameSessionPort;
import com.kanionland.game.session.infrastructure.requests.GameActionRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game-sessions")
@RequiredArgsConstructor
public class GameSessionController {

  private final GameSessionPort gameSessionService;
  private final GameActionMapper mapper;

  @PostMapping
  public ResponseEntity<Void> startSession(@RequestParam String playerId) {
    gameSessionService.startSession(playerId);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{sessionId}")
  public ResponseEntity<Void> generateAction(@PathVariable String sessionId,
      @Valid @RequestBody GameActionRequest request) {
    final GameActionCommand command = mapper.toCommand(request, UUID.fromString(sessionId));
    gameSessionService.sendGameAction(command);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{sessionId}/end")
  public ResponseEntity<Void> endSession(@PathVariable UUID sessionId) {
    gameSessionService.endSession(sessionId);
    return ResponseEntity.ok().build();
  }
}
