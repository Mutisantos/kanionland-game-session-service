package com.kanionland.game.session.application.adapters.input.rest;

import com.kanionland.game.session.application.ports.input.GameSessionService;
import com.kanionland.game.session.domain.model.GameSession;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game-sessions")
@RequiredArgsConstructor
public class GameSessionController {

  private final GameSessionService gameSessionService;

  @PostMapping
  public ResponseEntity<Void> startSession(@RequestParam String playerId) {
    gameSessionService.startSession(playerId);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{sessionId}/end")
  public ResponseEntity<Void> endSession(@PathVariable UUID sessionId) {
    gameSessionService.endSession(sessionId);
    return ResponseEntity.ok().build();
  }
}
