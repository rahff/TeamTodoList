
type UnaryOperator<T> = (value: T) => T;
interface Item {id: string};
type ListItemUpdate<T extends Item> = (item: T, id: string) => (transform: UnaryOperator<T>) => T

export const addItemOnList = <T> (list: T[]): (item: T) => T[] => {
    return (item) => list.concat(item);
}

export const addItemsOnList = <T> (list: T[]): (items: T[]) => T[] => {
    return (items) => list.concat(items);
}

export type ListPredicate<T> = (item: T, id: string) => boolean;

export const filterList = <T> (list: T[], fn: ListPredicate<T>): (id: string) => T[] => {
    return (id: string) => list.filter((item)=> fn(item, id))
}

export const deleteById = <T extends Item> (item: T, id: string) => item.id !== id;

export const byId = <T extends Item> (item: T, id: string) => item.id === id;

export const assertNotNull = <T> (value: T | null): T => {
    if(!value) throw new Error("Invalid state: state part must be initialized");
    return value;
}

export const mapOnList = <T extends Item> (list: T[], fn: ListItemUpdate<T>): (transform: UnaryOperator<T>, id: string) => T[] => {
    return (transform: UnaryOperator<T>, id: string) => list.map((item) => fn(item, id)(transform))
}

export const updateById = <T extends Item>(item: T, id: string) => (transform: UnaryOperator<T>) => {
    if(item.id === id) item = transform(item);
    return item;
}

