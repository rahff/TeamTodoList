import { Observable } from "rxjs";
import { TodoListDetailsViewModel } from "src/core/store/todoList-details/TodoListDetailsState";
import { TodoListsViewModel } from "src/core/store/todo-lists/TodoListsState";
import { Injectable } from "@angular/core";
import { TodoQueryHandler } from "src/core/application/todo/spi/TodoQueryHandler";
import { Todo } from "src/core/model/todo/Todo";
import { TodoList } from "src/core/model/todo/TodoList";
import { todoListsForTeamRef, todoListsForTeammateRef, teammateTodoListDetails, todosFromTeammateTodoList } from "src/core/application/todo/in-memory/data/data.todo";



@Injectable({
    providedIn: "root"
})
export class InMemoryTodoQueryHandler implements TodoQueryHandler {

    private todoLists!: {todoListsForTeamRef: TodoList[], todoListsForTeammateRef: TodoList[]};

    public constructor(){
        this.todoLists = {todoListsForTeamRef, todoListsForTeammateRef}
    }
    
    public getTodoListByRef(ref: string): Observable<TodoListsViewModel> {
        return new Observable((observable) => {
            setTimeout(() => {
                if(ref === "teamId"){
                    observable.next({viewModel: {lists: this.todoLists.todoListsForTeamRef}});
                }else{
                    observable.next({viewModel:{ lists: this.todoLists.todoListsForTeammateRef}});
                }
            }, 500);
        })
    }

    public getTodoListDetailById(id: string): Observable<TodoListDetailsViewModel> {
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