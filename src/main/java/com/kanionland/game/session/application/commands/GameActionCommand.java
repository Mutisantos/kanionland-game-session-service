package com.kanionland.game.session.application.commands;

import com.kanionland.game.session.application.commands.states.actions.Action;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameActionCommand {

  private UUID sessionId;
  private String username;
  private String description;
  private String character;
  private Action action;

  public ActionType getType() {
    return action != null ? action.getType() : null;
  }
}
