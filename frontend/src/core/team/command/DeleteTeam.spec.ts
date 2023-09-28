import { Result } from "src/core/shared/dto/Result";
import { InMemoryTeamCommandHandler } from "../in-memory/InMemoryTeamCommandHandler";
import { DeleteTeam } from "./DeleteTeam";

describe("DeleteTeam", () => {
    let deleteTeam: DeleteTeam;
    let commandHandler: InMemoryTeamCommandHandler;

    beforeEach(() => {
        commandHandler = new InMemoryTeamCommandHandler();
        deleteTeam = new DeleteTeam(commandHandler);
    })

    it("A manager delete a team", () => {
        const teamToDeleteId = "teamId";
        deleteTeam.execute(teamToDeleteId).subscribe((result: Result) => {
            expect(result.isOk()).toBeTrue();
            expect(commandHandler.hasBeenCalled("deleteTeam")).toEqual(teamToDeleteId);
        })
    })

    it("A manager delete a team but it fails", () => {
        const teamToDeleteId = "failure";
        deleteTeam.execute(teamToDeleteId).subscribe((result: Result) => {
            expect(result.isError()).toBe("bad request");
        })
    })
})