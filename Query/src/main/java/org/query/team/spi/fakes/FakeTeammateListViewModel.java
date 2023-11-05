package org.query.team.spi.fakes;



import org.query.team.model.Teammate;
import org.query.team.model.TeammateListViewModel;

import java.util.List;
import java.util.Optional;

public class FakeTeammateListViewModel {

    public static TeammateListViewModel get(){
        return new TeammateListViewModel(
                List.of(
                        new Teammate("teammateId1", "teammateName1", "teammateName1@gmail.com", Optional.of("teamId1")),
                        new Teammate("teammateId2", "teammateName2", "teammateName2@gmail.com", Optional.of("teamId1")),
                        new Teammate("teammateId3", "teammateName3", "teammateName3@gmail.com", Optional.of("teamId2")),
                        new Teammate("teammateId4", "teammateName4", "teammateName4@gmail.com", Optional.of("teamId2"))
                ));
    }
}
