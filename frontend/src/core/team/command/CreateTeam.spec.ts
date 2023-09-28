import { Result } from "src/core/shared/dto/Result";
import { CreateTeamFormData } from "../dto/CreateTeamFormData";
import { CreateTeam } from "./CreateTeam";
import { InMemoryTeamCommandHandler } from "../in-memory/InMemoryTeamCommandHandler";
import { IDProvider } from "src/core/shared/interfaces/IDProvider";
import { TestIDProvider } from "src/core/todo/in-memory/testIdProvider";

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
        const formData: CreateTeamFormData = {name: "Team1", activity: "Marketing"}
        createTeam.execute(formData).subscribe((result: Result) => {
            expect(result.isOk()).toBeTrue();
            expect(commandHandler.hasBeenCalled("createTeam")).toEqual({name: "Team1", activity: "Marketing", id: "generatedId"})
        })
    })

    it("A manager create a team but it fails", () => {
        const formData: CreateTeamFormData = {name: "will fail", activity: "Marketing"}
        createTeam.execute(formData).subscribe((result: Result) => {
            expect(result.isOk()).toBeFalsy();
            expect(result.isError()).toBe("bad request");
        })
    })
})