package com.kanionland.game.session.application.commands.states.actions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kanionland.game.session.application.commands.ActionType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JacksonXmlRootElement(localName = "diceRollAction")
public class DiceRollAction implements Action {

  @JacksonXmlProperty(localName = "stat")
  private final String stat;
  @JacksonXmlProperty(localName = "challengeValue")
  private final int challengeValue;
  @JacksonXmlProperty(localName = "rollValue")
  private final int rollValue;
  @JacksonXmlProperty(localName = "statValue")
  private final int statValue;
  @JacksonXmlProperty(localName = "fateRollValues")
  private final List<Integer> fateRollValues;

  @Override
  public ActionType getType() {
    return ActionType.DICE_ROLL;
  }
}
