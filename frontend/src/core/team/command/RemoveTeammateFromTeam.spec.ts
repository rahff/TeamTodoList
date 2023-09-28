import { Result } from "src/core/shared/dto/Result";
import { RemoveTeammateFromTeamRequest } from "../dto/RemoveTeammateFromTeamRequest";
import { RemoveTeammateFromTeam } from "./RemoveTeammateFromTeam";
import { InMemoryTeamCommandHandler } from "../in-memory/InMemoryTeamCommandHandler";


describe("RemoveTeammateFromTeam", () => {
    let removeTeammateFromTeam: RemoveTeammateFromTeam;
    let commandHandler: InMemoryTeamCommandHandler;

    beforeEach(() => {
        commandHandler = new InMemoryTeamCommandHandler();
        removeTeammateFromTeam = new RemoveTeammateFromTeam(commandHandler);
    })

    it("A manager remove ateammate from a team", () => {
        const request: RemoveTeammateFromTeamRequest = {teamId: "teamId", teammateId: "teammateId"};
        removeTeammateFromTeam.execute(request).subscribe((result: Result) => {
            expect(result.isOk()).toBeTrue();
            expect(commandHandler.hasBeenCalled("removeTeammateFromTeam")).toEqual(request);
        })
    })

    it("A manager remove ateammate from a team but it fails", () => {
        const request: RemoveTeammateFromTeamRequest = {teamId: "failure", teammateId: "teammateId"};
        removeTeammateFromTeam.execute(request).subscribe((result: Result) => {
            expect(result.isError()).toBe("bad request");
        })
    })
})