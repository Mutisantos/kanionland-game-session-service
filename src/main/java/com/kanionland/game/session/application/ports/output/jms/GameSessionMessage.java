package com.kanionland.game.session.application.ports.output.jms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kanionland.game.domain.model.GameState;
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
public class GameSessionMessage {
    private UUID id;
    private String playerId;
    private GameState state;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
