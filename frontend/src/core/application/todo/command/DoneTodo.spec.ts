import { Result } from "src/core/application/shared/dto/Result";
import { DoneTodo } from "./DoneTodo";
import { InMemoryTodoCommandHandler } from "src/app/dashboard/services/inMemory/InMemoryTodoCommandHandler";
import {DoneTodoRequest} from "../dto/DoneTodoRequest";

describe("DoneTodo", () => {
    let doneTodo: DoneTodo;
    let dataSource: InMemoryTodoCommandHandler;

    beforeEach(() => {
        dataSource = new InMemoryTodoCommandHandler();
        doneTodo = new DoneTodo(dataSource);
    })

    it(" A teammate done a todo", () => {
        const doneTodoRequest: DoneTodoRequest = {todoId: "todoId", todoListId: "1"};
        doneTodo.execute(doneTodoRequest).subscribe((result: Result<string>) => {
            expect(result.isOk()).toBeTrue();
            expect(dataSource.hasBeenCalled("doneTodo")).toEqual(doneTodoRequest);
        })
    })

    it(" A teammate done a todo but it fails", () => {
        const doneTodoRequest: DoneTodoRequest = {todoId: "failure", todoListId: "1"};
        doneTodo.execute(doneTodoRequest).subscribe((result: Result<string>) => {
            expect(result.getError().message).toEqual("bad request");
        })
    })
})