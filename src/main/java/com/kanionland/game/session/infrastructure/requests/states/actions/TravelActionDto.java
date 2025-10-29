package com.kanionland.game.session.infrastructure.requests.states.actions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelActionDto implements ActionDto {

  private String destination;
  private int travelTime;

  @Override
  public String getType() {
    return "TRAVEL";
  }
}
