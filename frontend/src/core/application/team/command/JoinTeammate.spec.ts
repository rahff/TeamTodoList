import { IDProvider } from "src/core/application/shared/interfaces/IDProvider";
import { Result } from "src/core/application/shared/dto/Result";
import { JoinTeammate } from "./JoinTeammate";
import { CreateTeammateFormData } from "../dto/CreateTeammateFormData";
import { InMemoryTeamCommandHandler } from "../../../../app/dashboard/services/inMemory/InMemoryTeamCommandHandler";
import { Teammate } from "../../../model/team/Teammate";
import { TestIDProvider } from "../../todo/in-memory/testIdProvider";



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
        joinTeammate.execute(formData).subscribe((result: Result<Teammate>) => {
            expect(result.isOk()).toBeTrue();
            expect(commandHandler.hasBeenCalled("joinTeammate"))
            .toEqual({name: "Jonh Doe", email: "johndoe@gmail.com", id: idProvider.generate()})
        })
    });

    it("A manager join a new teammate in the organization but it fail", () => {
        const formData: CreateTeammateFormData = {name: "failure", email: "johndoe@gmail.com"};
        joinTeammate.execute(formData).subscribe((result: Result<Teammate>) => {
            expect(result.getError().message).toBe("bad request");
        })
    })
})