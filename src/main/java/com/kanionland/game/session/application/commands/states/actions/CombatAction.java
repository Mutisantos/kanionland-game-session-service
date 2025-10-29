package com.kanionland.game.session.application.commands.states.actions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kanionland.game.session.application.commands.ActionType;
import com.kanionland.game.session.application.commands.CombatActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JacksonXmlRootElement(localName = "combatAction")
public class CombatAction implements Action {

  @JacksonXmlProperty(localName = "actionType")
  private final CombatActionType actionType;
  @JacksonXmlProperty(localName = "stat")
  private final String stat;
  @JacksonXmlProperty(localName = "target")
  private final String target;
  @JacksonXmlProperty(localName = "value")
  private final int value;

  @Override
  public ActionType getType() {
    return ActionType.COMBAT;
  }
}
