import { Result } from "../../shared/dto/Result";
import { InMemoryTeamCommandHandler } from "../../../../app/dashboard/services/inMemory/InMemoryTeamCommandHandler";
import { TeamCommandHandler } from "../spi/TeamCommandHandler";
import { FireTeammate } from "./FireTeammate";

describe("FireTeammate", () => {
    let fireTeammate: FireTeammate;
    let commandHandler: InMemoryTeamCommandHandler;

    beforeEach(() => {
        commandHandler = new InMemoryTeamCommandHandler()
        fireTeammate = new FireTeammate(commandHandler);
    })

    it("A manager fire a teammate", () => {
        fireTeammate.execute("teammateId").subscribe((result: Result<string>) => {
            expect(result.isOk()).toBeTrue();
            expect(commandHandler.hasBeenCalled("fireTeammate"))
            .toEqual("teammateId")
        })
    });

    it("A manager fire a teammate but fails", () => {
        fireTeammate.execute("failure").subscribe((result: Result<string>) => {
            expect(result.getError().message).toBe("bad request");
        })
    })
})