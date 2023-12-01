import {ChangePassword} from "./ChangePassword";
import {Result} from "../../shared/dto/Result";
import {AccountCommandHandler} from "../spi/AccountCommandHandler";
import {InMemoryAccountCommandService} from "../../../../app/dashboard/services/inMemory/inMemory-account-command.service";
import {FakeUserContextHolder} from "../../../../app/dashboard/services/inMemory/FakeUserContextHolder";
import {UserContextHolder} from "../../shared/interfaces/UserContextHolder";
import {ChangePasswordFormData} from "../dto/ChangePasswordFormData";
import {Message} from "../../shared/dto/Message";


describe("ChangePasswordCommand", () => {
    let changePassword: ChangePassword;
    let accountCommandHandler: AccountCommandHandler;
    let userContextHolder: UserContextHolder
    beforeEach(() => {
        userContextHolder = new FakeUserContextHolder();
        accountCommandHandler = new InMemoryAccountCommandService();
        changePassword = new ChangePassword(accountCommandHandler, userContextHolder);
    })

    it("A user send change password request", () => {
        const request: ChangePasswordFormData = {oldPassword: "251200", newPassword: "Mot2$asse", confirmPassword: "Mot2$asse"};
        changePassword.execute(request).subscribe((result: Result<Message>) => {
            expect(result.isOk()).toBeTrue();
        })
    })
    it("A user send change password request but it fails", () => {
        const request: ChangePasswordFormData = {oldPassword: "incorrectPassword", newPassword: "Mot2$asse", confirmPassword: "Mot2$asse"};
        changePassword.execute(request).subscribe((result: Result<Message>) => {
            expect(result.isOk()).toBeFalse();
            expect(result.getError().message).toBe("invalid password");
        })
    })
})