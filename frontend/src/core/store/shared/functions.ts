import { Todo } from "src/core/application/todo/dto/Todo";


export const addItemOnList = <T> (list: T[]): (item: T) => T[] => {
    return (item) => list.concat(item);
}

export type ListPredicate<T> = (item: T, id: string) => boolean;

export const filterList = <T> (list: T[], fn: ListPredicate<T>): (id: string) => T[] => {
    return (id: string) => list.filter((item)=> fn(item, id))
}

export const deleteById = <T extends {id: string}> (item: T, id: string) => item.id !== id;

export const byId = <T extends {id: string}> (item: T, id: string) => item.id === id;

export const assertNotNull = <T> (value: T | null): T => {
    if(!value) throw new Error("Invalid state: state part must be initialized");
    return value;
}

type ListItemUpdate<T extends {id: string}> = (item: T, id: string) => (transform: OperatorFn<T>) => T
type OperatorFn<T> = (value: T) => T
export const mapOnList = <T extends {id: string}> (list: T[], fn: ListItemUpdate<T>): (transform: OperatorFn<T>, id: string) => T[] => {
    return (transform: OperatorFn<T>, id: string) => list.map((item) => fn(item, id)(transform))
}

export const updateById = <T extends {id: string}>(item: T, id: string) => (transform: OperatorFn<T>) => {
    if(item.id === id) item = transform(item);
    return item;
}

