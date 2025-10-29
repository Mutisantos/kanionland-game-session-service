package com.kanionland.game.session.application.ports.input;

import com.kanionland.game.session.application.commands.GameActionCommand;
import java.util.UUID;

public interface GameSessionPort {

    void startSession(String playerId);
    void sendGameAction(GameActionCommand action);
    void endSession(UUID sessionId);

}
