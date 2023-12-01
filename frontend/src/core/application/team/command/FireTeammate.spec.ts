import { Result } from "../../shared/dto/Result";
import { InMemoryTeamCommandHandler } from "../../../../app/dashboard/services/inMemory/InMemoryTeamCommandHandler";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { FireTeammate } from "./FireTeammate";
import {Teammate} from "../../../model/team/Teammate";

describe("FireTeammate", () => {
    let fireTeammate: FireTeammate;
    let commandHandler: InMemoryTeamCommandHandler;
    const getTeammate = (id: string, teamId: string): Teammate => ({id, email: "r@gamil.com", teamId, name: "rahff"})
    beforeEach(() => {
        commandHandler = new InMemoryTeamCommandHandler()
        fireTeammate = new FireTeammate(commandHandler);
    })

    it("A manager fire a teammate", () => {
        const firedTeammate: Teammate = getTeammate("teammateId", "teamId");
        fireTeammate.execute(firedTeammate).subscribe((result: Result<string>) => {
            expect(result.isOk()).toBeTrue();
            expect(commandHandler.hasBeenCalled("fireTeammate"))
            .toEqual(firedTeammate)
        })
    });

    it("A manager fire a teammate but fails", () => {
        const firedTeammate: Teammate = getTeammate("failure", "teamId")
        fireTeammate.execute(firedTeammate).subscribe((result: Result<string>) => {
            expect(result.getError().message).toBe("bad request");
        })
    })
})