import { Result } from "src/core/application/shared/dto/Result";
import { DeleteTodo } from "./DeleteTodo";
import { InMemoryTodoCommandHandler } from "src/app/dashboard/services/inMemory/InMemoryTodoCommandHandler";


describe("DeleteTodo", () => {
    let deleteTodo: DeleteTodo;
    let dataSource: InMemoryTodoCommandHandler;

    beforeEach(() => {
        dataSource = new InMemoryTodoCommandHandler();
        deleteTodo = new DeleteTodo(dataSource);
    })

    it("A manager delete a todo", () => {
        const deletedTodoId = "todoId";
        deleteTodo.execute(deletedTodoId).subscribe((result: Result<string>) => {
            expect(result.isOk()).toBeTrue();
            expect(dataSource.hasBeenCalled("deleteTodo")).toEqual(deletedTodoId)
        })
    });

    it("A manager delete a todo but it fails", () => {
        const deletedTodoId = "failure";
        deleteTodo.execute(deletedTodoId).subscribe((result: Result<string>) => {
            expect(result.getError().message).toBe("bad request")
        })
    })
})