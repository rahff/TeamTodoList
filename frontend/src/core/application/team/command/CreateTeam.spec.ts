import { Result } from "src/core/application/shared/dto/Result";
import { CreateTeamFormData } from "../dto/CreateTeamFormData";
import { CreateTeam } from "./CreateTeam";
import { InMemoryTeamCommandHandler } from "../../../../app/dashboard/services/inMemory/InMemoryTeamCommandHandler";
import { IDProvider } from "src/core/application/shared/interfaces/IDProvider";
import { TestIDProvider } from "../../todo/in-memory/testIdProvider";
import { Team } from "../dto/Team";


describe("CreateTeam", () => {
    let createTeam: CreateTeam;
    let commandHandler: InMemoryTeamCommandHandler;
    let idProvider: IDProvider
    beforeEach(() => {
        idProvider = new TestIDProvider()
        commandHandler = new InMemoryTeamCommandHandler();
        createTeam = new CreateTeam(commandHandler, idProvider);
    })

    it("A manager create a team", () => {
        const formData: CreateTeamFormData = {name: "Team1", teammates: []}
        createTeam.execute(formData).subscribe((result: Result<Team, Error>) => {
            expect(result.isOk()).toBeTrue();
            expect(commandHandler.hasBeenCalled("createTeam"))
            .toEqual({name: "Team1", teammates: [], id: "generatedId"})
        })
    })

    it("A manager create a team but it fails", () => {
        const formData: CreateTeamFormData = {name: "will fail", teammates: []}
        createTeam.execute(formData).subscribe((result: Result<Team, Error>) => {
            expect(result.isOk()).toBeFalsy();
            expect(result.getError().message).toBe("bad request");
        })
    })
})