
import { EntitySchema } from "typeorm";
import { generateId } from "./utils/idGenerator.js";

export const Universe = new EntitySchema({
    name: "Universe",
    tableName: "universe",
    columns: {
        universe_id: {
            primary: true,
            type: "varchar",
            generated: false,
            length: 8,
        },
        Owner: {
            type: "varchar",
            length: 16,
            nullable: false,
        },
        xx: {
            type: "int",
            nullable: false,
        },
        yy: {
            type: "int",
            nullable: false,
        },
        xy: {
            type: "bigint",
            nullable: false,
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
    beforeInsert: (event) => {
        if (!event.entity.universe_id) {
            event.entity.universe_id = generateId();
        }
    },
});
