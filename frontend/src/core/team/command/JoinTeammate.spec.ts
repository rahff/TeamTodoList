import { IDProvider } from "src/core/shared/interfaces/IDProvider";
import { TestIDProvider } from "src/core/todo/in-memory/testIdProvider";
import { Result } from "src/core/shared/dto/Result";
import { JoinTeammate } from "./JoinTeammate";
import { CreateTeammateFormData } from "../dto/CreateTeammateFormData";
import { InMemoryTeamCommandHandler } from "../in-memory/InMemoryTeamCommandHandler";



describe("JoinTeammate", () => {
    let joinTeammate: JoinTeammate;
    let commandHandler: InMemoryTeamCommandHandler;
    let idProvider: IDProvider;
    beforeEach(() => {
        idProvider = new TestIDProvider();
        commandHandler = new InMemoryTeamCommandHandler();
        joinTeammate = new JoinTeammate(commandHandler, idProvider);
    });

    it("A manager join a new teammate in the organization", () => {
        const formData: CreateTeammateFormData = {name: "Jonh Doe", email: "johndoe@gmail.com"};
        joinTeammate.execute(formData).subscribe((result: Result) => {
            expect(result.isOk()).toBeTrue();
            expect(commandHandler.hasBeenCalled("joinTeammate")).toEqual({name: "Jonh Doe", email: "johndoe@gmail.com", id: idProvider.generate()})
        })
    });

    it("A manager join a new teammate in the organization but it fail", () => {
        const formData: CreateTeammateFormData = {name: "failure", email: "johndoe@gmail.com"};
        joinTeammate.execute(formData).subscribe((result: Result) => {
            expect(result.isError()).toBe("bad request");
        })
    })
})