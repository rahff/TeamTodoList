import { Observable } from "rxjs";
import { TodoList } from "../../../../core/application/todo/dto/TodoList";
import { TodoQueryHandler } from "../../../../core/application/todo/spi/TodoQueryHandler";
import { Todo } from "../../../../core/application/todo/dto/Todo";
import { teammateTodoListDetails, todoListsForTeamRef, todoListsForTeammateRef, todosFromTeammateTodoList } from "../../../../core/application/todo/in-memory/data/data.todo";
import { TodoListDetailsViewModel } from "src/core/store/todoList-details/TodoListDetailsState";
import { TodoListsViewModel } from "src/core/store/todo-lists/TodoListsState";
import { Injectable } from "@angular/core";



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
        if(id === "todoListId") {
            return new Observable((observable) => {
                setTimeout(() => {
                    observable.next(teammateTodoListDetails)
                }, 500);
            })
        }
        throw "404"
    }
    
    public getTodosByListId(id: string): Observable<Todo[]> {
        return new Observable((observable) => {
            setTimeout(() => {
                observable.next(todosFromTeammateTodoList)
            }, 500);
        })
    }
}