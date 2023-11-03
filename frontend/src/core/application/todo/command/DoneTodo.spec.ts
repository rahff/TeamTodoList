import { Result } from "src/core/application/shared/dto/Result";
import { DoneTodo } from "./DoneTodo";
import { InMemoryTodoCommandHandler } from "src/app/dashboard/services/inMemory/InMemoryTodoCommandHandler";

describe("DoneTodo", () => {
    let doneTodo: DoneTodo;
    let dataSource: InMemoryTodoCommandHandler;

    beforeEach(() => {
        dataSource = new InMemoryTodoCommandHandler();
        doneTodo = new DoneTodo(dataSource);
    })

    it(" A teammate done a todo", () => {
        const doneTodoId = "todoId";
        doneTodo.execute(doneTodoId).subscribe((result: Result<string>) => {
            expect(result.isOk()).toBeTrue();
            expect(dataSource.hasBeenCalled("doneTodo")).toEqual(doneTodoId);
        })
    })

    it(" A teammate done a todo but it fails", () => {
        const doneTodoId = "failure";
        doneTodo.execute(doneTodoId).subscribe((result: Result<string>) => {
            expect(result.getError().message).toEqual("bad request");
        })
    })
})