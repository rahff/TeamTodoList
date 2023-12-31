
import { Result } from "src/core/application/shared/dto/Result";
import { AddTodo } from "./AddTodo";
import { CreateTodoFormData } from "../dto/CreateTodoFormData";
import { IDProvider } from "src/core/application/shared/interfaces/IDProvider";
import { TestIDProvider } from "../in-memory/testIdProvider";
import { Todo } from "../../../model/todo/Todo";
import { InMemoryTodoCommandHandler } from "src/app/dashboard/services/inMemory/InMemoryTodoCommandHandler";



describe("AddTodo", () => {
    let addTodo: AddTodo;
    let todoDataSource: InMemoryTodoCommandHandler;
    let idProvider: IDProvider
    beforeEach(() => {
        idProvider = new TestIDProvider()
        todoDataSource = new InMemoryTodoCommandHandler();
        addTodo = new AddTodo(todoDataSource, idProvider);
    })

    it("A manager add todo in todo list", () => {
        const formData: CreateTodoFormData = {description: "Do something", deadline: new Date(2022, 10, 12).toISOString(), todoListId: "listId"};
        addTodo.execute(formData).subscribe((result: Result<Todo>) => {
            expect(result.isOk()).toBeTrue();
            expect(todoDataSource.hasBeenCalled("addTodo"))
            .toEqual({description: "Do something", deadline: new Date(2022, 10, 12).toISOString(), todoListId: "listId", id: "generatedId"})
        })
    });

    it("A manager add todo in todo list but it fails", () => {
        const formData: CreateTodoFormData = {description: "will fail", deadline: new Date(2022, 10, 12).toISOString(), todoListId: "listId"};
        addTodo.execute(formData).subscribe((result: Result<Todo>) => {
            expect(result.isOk()).toBeFalsy();
            expect(result.getError().message).toBe("bad request");
        })
    })
})