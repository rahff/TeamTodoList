package org.example.persistance.mappers.team;

import org.example.persistance.entities.team.Teammate;

import java.util.List;
import java.util.stream.Collectors;

public class TeammateMapper {

  public static List<Teammate> fromStrings(List<String> refs){
    return refs.stream().map(Teammate::new).collect(Collectors.toList());
  }

  private static Teammate fromString(String ref){
    return new Teammate(ref);
  }
}
