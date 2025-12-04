
import { EntitySchema } from "typeorm";
import { generateId } from "./utils/idGenerator.js";

export const Ship = new EntitySchema({
    name: "Ship",
    tableName: "ship",
    columns: {
        ship_id: {
            primary: true,
            type: "varchar",
            length: 8,
            generated: false,
        },
        Owner: {
            type: "varchar",
            length: 16,
            nullable: true,
        },
        Shipname: {
            type: "varchar",
            length: 16,
            nullable: true,
        },
        Race: {
            type: "varchar",
            length: 12,
            nullable: true,
        },
        Alliances: {
            type: "varchar",
            length: 255,
            nullable: true,
        },
        Energy: {
            type: "float",
            nullable: true,
        },
        Funds: {
            type: "integer",
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
    },
});
