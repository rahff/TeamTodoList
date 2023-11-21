export const DATA_SOURCE = {
    IN_MEMORY: 0,
    SERVER: 1
} as const

export type DataSource = typeof DATA_SOURCE