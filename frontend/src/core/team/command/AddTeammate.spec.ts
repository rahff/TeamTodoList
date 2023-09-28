import { Result } from "src/core/shared/dto/Result";
import { InMemoryTeamCommandHandler } from "../in-memory/InMemoryTeamCommandHandler";
import { AddTeammate } from "./AddTeammate";
import { AddTeammateRequest } from "../dto/AddTeammateRequest";



describe("AddTeammate", () => {
    let addTeammate: AddTeammate;
    let commandHandler: InMemoryTeamCommandHandler;

    beforeEach(() => {
        commandHandler = new InMemoryTeamCommandHandler();
        addTeammate = new AddTeammate(commandHandler);
    })

    it("A manager add teammate on a team", () => {
        const request: AddTeammateRequest = { teamId: "teamId", teammateId: "teammateId"};
        addTeammate.execute(request).subscribe((result: Result) => {
            expect(result.isOk()).toBeTrue();
            expect(commandHandler.hasBeenCalled("addTeammateOnTeam")).toEqual({ teamId: "teamId", teammateId: "teammateId"})
        })
    });
    
    it("A manager add teammate on a team but it fails", () => {
        const request: AddTeammateRequest = { teamId: "failure", teammateId: "teammateId"};
        addTeammate.execute(request).subscribe((result: Result) => {
            expect(result.isError()).toBe("bad request");
        })
    })
})