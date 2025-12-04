
import { EntitySchema } from "typeorm";

export const Cache = new EntitySchema({
    name: "Cache",
    tableName: "cache",
    columns: {
        cache_id: {
            type: "varchar",
            primary: true,
            length: 8,
            nullable: false,
        },
        Owner: {
            type: "varchar",
            length: 24,
            nullable: true,
        },
        TaskForceTag: {
            type: "varchar",
            length: 24,
            nullable: true,
        },
        Shortname: {
            type: "varchar",
            length: 24,
            nullable: true,
        },
        Quantity: {
            type: "int",
            nullable: true,
            default: 0,
        },
        created_on: {
            type: "datetime",
            createDate: true,
        },
        updated_on: {
            type: "datetime",
            updateDate: true,
        },
    },
});
