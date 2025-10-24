package com.kanionland.game.session.application.adapters.output.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game_sessions")
public class GameSessionJpaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "player_id", nullable = false)
    private String playerId;
    
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private GameState state;
    
    public enum GameState {
        STARTED, COMPLETED, ABANDONED
    }
}
