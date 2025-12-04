import { EntitySchema } from "typeorm";
import { generateShortId } from "./idGenerator.js";
import { v4 as uuidv4 } from "uuid";

export function createBaseEntity(
    name,
    tableName,
    idField,
    additionalColumns = {},
) {
    return new EntitySchema({
        name: name,
        tableName: tableName,
        columns: {
            [idField]: {
                type: "varchar",
                primary: true,
                length: 8,
                nullable: false,
                generated: false,
            },
            author: {
                type: "varchar",
                nullable: true,
            },
            contact: {
                type: "varchar",
                nullable: true,
            },

            description: {
                type: "varchar",
                nullable: true,
            },
            created_on: {
                type: "datetime",
                createDate: true,
            },
            updated_on: {
                type: "datetime",
                updateDate: true,
            },
            is_active: {
                type: "boolean",
                default: true,
            },
            ...additionalColumns,
        },
    });
}
