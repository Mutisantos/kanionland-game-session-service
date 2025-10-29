package com.kanionland.game.session.application.commands.states.actions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kanionland.game.session.application.commands.ActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JacksonXmlRootElement(localName = "travelAction")
public class TravelAction implements Action {

  @JacksonXmlProperty(localName = "destination")
  private final String destination;
  @JacksonXmlProperty(localName = "travelTime")
  private final int travelTime;

  @Override
  public ActionType getType() {
    return ActionType.TRAVEL;
  }
}
