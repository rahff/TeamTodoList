import { Result } from "src/core/application/shared/dto/Result";
import { InMemoryTeamCommandHandler } from "../../../../app/dashboard/services/inMemory/InMemoryTeamCommandHandler";
import { AddTeammateOnTeam } from "./AddTeammateOnTeam";
import { AddTeammateOnTeamRequest } from "../dto/AddTeammateOnTeamRequest";
import { Teammate } from "../dto/Teammate";



describe("AddTeammate", () => {
    let addTeammate: AddTeammateOnTeam;
    let commandHandler: InMemoryTeamCommandHandler;

    beforeEach(() => {
        commandHandler = new InMemoryTeamCommandHandler();
        addTeammate = new AddTeammateOnTeam(commandHandler);
    })

    it("A manager add teammate on a team", () => {
        const request: AddTeammateOnTeamRequest = { teamId: "teamId", teammateIds: ["teammateId"]};
        addTeammate.execute(request).subscribe((result: Result<Teammate[]>) => {
            expect(result.isOk()).toBeTrue();
            expect(commandHandler.hasBeenCalled("addTeammateOnTeam"))
            .toEqual({ teamId: "teamId", teammateIds: ["teammateId"]})
        })
    });
    
    it("A manager add teammate on a team but it fails", () => {
        const request: AddTeammateOnTeamRequest = { teamId: "failure", teammateIds: ["teammateId"]};
        addTeammate.execute(request).subscribe((result: Result<Teammate[]>) => {
            expect(result.getError().message).toBe("bad request");
        })
    })
})