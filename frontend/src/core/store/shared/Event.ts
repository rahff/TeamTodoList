import { AnyAction } from "@reduxjs/toolkit";

export interface EventWithPayload<T> extends AnyAction {
    payload: T
}