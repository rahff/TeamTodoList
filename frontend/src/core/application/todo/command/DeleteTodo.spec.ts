import { Result } from "src/core/application/shared/dto/Result";
import { DeleteTodo } from "./DeleteTodo";
import { InMemoryTodoCommandHandler } from "src/app/dashboard/services/inMemory/InMemoryTodoCommandHandler";
import { DeleteTodoRequest } from "../dto/CreateTodoListFormData";


describe("DeleteTodo", () => {
    let deleteTodo: DeleteTodo;
    let dataSource: InMemoryTodoCommandHandler;

    beforeEach(() => {
        dataSource = new InMemoryTodoCommandHandler();
        deleteTodo = new DeleteTodo(dataSource);
    })

    it("A manager delete a todo", () => {
        const deleteTodoRequest: DeleteTodoRequest = {todoId: "todoId", todoListId: "todoListId"};
        deleteTodo.execute(deleteTodoRequest).subscribe((result: Result<string>) => {
            expect(result.isOk()).toBeTrue();
            expect(dataSource.hasBeenCalled("deleteTodo")).toEqual(deleteTodoRequest)
        })
    });

    it("A manager delete a todo but it fails", () => {
        const deleteTodoRequest: DeleteTodoRequest = {todoId: "failure", todoListId: "todoListId"};
        deleteTodo.execute(deleteTodoRequest).subscribe((result: Result<string>) => {
            expect(result.getError().message).toBe("bad request")
        })
    })
})