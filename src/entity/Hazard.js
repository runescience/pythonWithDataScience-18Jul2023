
import { EntitySchema } from "typeorm";

export const Hazard = new EntitySchema({
    name: "Hazard",
    tableName: "hazard",
    columns: {
        hazard_id: {
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
        ItemTag: {
            type: "varchar",
            length: 24,
            nullable: true,
        },
        Longname: {
            type: "varchar",
            length: 16,
            nullable: true,
        },
        Shortname: {
            type: "varchar",
            length: 4,
            nullable: true,
        },
        Comment: {
            type: "varchar",
            length: 24,
            nullable: true,
        },
        Rating: {
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
