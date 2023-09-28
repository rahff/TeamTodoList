import { AnyAction } from "@reduxjs/toolkit";

export interface ActionWithPayload<T> extends AnyAction {
    payload: T
}