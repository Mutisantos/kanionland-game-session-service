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
@RequestMapping("/api/game-sessions")
@RequiredArgsConstructor
public class GameSessionController {

    private final GameSessionService gameSessionService;

    @PostMapping
    public ResponseEntity<GameSession> startSession(@RequestParam String playerId) {
        GameSession session = gameSessionService.startSession(playerId);
        return ResponseEntity
                .created(URI.create("/api/game-sessions/" + session.getId()))
                .body(session);
    }

    @PostMapping("/{sessionId}/end")
    public ResponseEntity<GameSession> endSession(@PathVariable UUID sessionId) {
        GameSession session = gameSessionService.endSession(sessionId);
        return ResponseEntity.ok(session);
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<GameSession> getSession(@PathVariable UUID sessionId) {
        return gameSessionService.getSession(sessionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
