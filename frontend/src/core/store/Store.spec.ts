import { TodoList } from "../todo/dto/TodoList";
import { TodoListDetails } from "../todo/dto/TodoListDetails";
import { newPersonnalTodo, newTeamTodoList, newTodo, teammateTodoListDetails, teammateTodoListDetailsAfterAddingOneTodo, todoListsForTeamRef, todoListsForTeamRefAfterAddingOne, todoListsForTeamRefAfterDeleteFirst, todoListsForTeammateRef, todoListsForTeammateRefAfterAddingOne } from "../todo/in-memory/data/data.todo";
import { createStore } from "./Store"
import { addPersonnalTodoListAction, addTeamTodoListAction, addTodoAction, deleteTeamTodoListAction, setTeamTodoListsAction } from "./todo/Actions";
import { TodoListDetailsSelector, TeamTodoListsSelector, PersonnalTodoListsSelector } from "./todo/TeamTodoListSelector";



describe("TodoLists Store slice", () => {
    const INITIAL_GLOBAL_STATE = {todoLists: {teamLists: todoListsForTeamRef, personnalLists: todoListsForTeammateRef, details: teammateTodoListDetails}}
    it("dispatch setTeamTodoLists", () => {
        const store = createStore();
        const selector = new TeamTodoListsSelector(store)
        const action = setTeamTodoListsAction(todoListsForTeamRef)
        store.dispatch(action);
        selector.getListOfTodoList().subscribe((list: TodoList[]) => {
            expect(list).toEqual(todoListsForTeamRef)
        })
    })

    it("dispatch deleteTeamTodoList", () => {
        const store = createStore(INITIAL_GLOBAL_STATE);
        const selector = new TeamTodoListsSelector(store)
        const action = deleteTeamTodoListAction("qsdfoliuytredfghjk")
        store.dispatch(action);
        selector.getListOfTodoList().subscribe((list: TodoList[]) => {
            expect(list).toEqual(todoListsForTeamRefAfterDeleteFirst)
        })
    })

    it("dispatch addTodo", () => {
        const store = createStore(INITIAL_GLOBAL_STATE);
        const selector = new TodoListDetailsSelector(store);
        const action = addTodoAction(newTodo);
        store.dispatch(action);
        selector.getTodoListDetails().subscribe((details: TodoListDetails | null) => {
            expect(details).toEqual(teammateTodoListDetailsAfterAddingOneTodo);
        })
    })

    it("dispatch addTeamTodoList", () => {
        const store = createStore(INITIAL_GLOBAL_STATE);
        const selector = new TeamTodoListsSelector(store);
        const action = addTeamTodoListAction(newTeamTodoList);
        store.dispatch(action);
        selector.getListOfTodoList().subscribe((list: TodoList[]) => {
            expect(list).toEqual(todoListsForTeamRefAfterAddingOne);
        })
    })

    it("dispatch addPersonnalTodoList", () => {
        const store = createStore(INITIAL_GLOBAL_STATE);
        const selector = new PersonnalTodoListsSelector(store);
        const action = addPersonnalTodoListAction(newPersonnalTodo);
        store.dispatch(action);
        selector.getListOfTodoList().subscribe((list: TodoList[]) => {
            expect(list).toEqual(todoListsForTeammateRefAfterAddingOne);
        })
    })
})