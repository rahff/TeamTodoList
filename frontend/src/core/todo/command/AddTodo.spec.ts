
import { Result } from "src/core/shared/dto/Result";
import { InMemoryTodoCommandHandler } from "../in-memory/InMemoryTodoCommandHandler";
import { AddTodo } from "./AddTodo";
import { CreateTodoFormData } from "../dto/CreateTodoFormData";
import { IDProvider } from "src/core/shared/interfaces/IDProvider";
import { TestIDProvider } from "../in-memory/testIdProvider";



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
        const formData: CreateTodoFormData = {description: "Do something", deadline: new Date(2022, 10, 12), ref: "teamId"};
        addTodo.execute(formData).subscribe((result: Result) => {
            expect(result.isOk()).toBeTrue();
            expect(todoDataSource.hasBeenCalled("addTodo"))
            .toEqual({description: "Do something", deadline: new Date(2022, 10, 12), ref: "teamId", id: "generatedId"})
        })
    });

    it("A manager add todo in todo list but it fails", () => {
        const formData: CreateTodoFormData = {description: "will fail", deadline: new Date(2022, 10, 12), ref: "teamId"};
        addTodo.execute(formData).subscribe((result: Result) => {
            expect(result.isOk()).toBeFalsy();
            expect(result.isError()).toBe("bad request");
        })
    })
})