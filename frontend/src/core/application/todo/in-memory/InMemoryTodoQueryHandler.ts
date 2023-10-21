import { Observable } from "rxjs";
import { TodoList } from "../dto/TodoList";
import { TodoQueryHandler } from "../spi/TodoQueryHandler";
import { Todo } from "../dto/Todo";
import { teammateTodoListDetails, todoListsForTeamRef, todoListsForTeammateRef, todosFromTeammateTodoList } from "./data/data.todo";
import { TodoListDetailsView } from "src/core/store/todoList-details/TodoListDetailsState";
import { TodoListsView } from "src/core/store/todo-lists/TodoListsState";




export class InMemoryTodoQueryHandler implements TodoQueryHandler {

    private todoLists!: {todoListsForTeamRef: TodoList[], todoListsForTeammateRef: TodoList[]};

    public constructor(){
        this.todoLists = {todoListsForTeamRef, todoListsForTeammateRef}
    }
    
    public getTodoListByRef(ref: string): Observable<TodoListsView> {
        return new Observable((observable) => {
            setTimeout(() => {
                if(ref === "teamId"){
                    observable.next({ lists: this.todoLists.todoListsForTeamRef});
                }else{
                    observable.next({lists: this.todoLists.todoListsForTeammateRef});
                }
            }, 500);
        })
    }

    public getTodoListDetailById(id: string): Observable<TodoListDetailsView> {
        return new Observable((observable) => {
            setTimeout(() => {
                observable.next(teammateTodoListDetails)
            }, 500);
        })
    }
    
    public getTodosByListId(id: string): Observable<Todo[]> {
        return new Observable((observable) => {
            setTimeout(() => {
                observable.next(todosFromTeammateTodoList)
            }, 500);
        })
    }
}