
import { EntitySchema } from "typeorm";

export const PartStatus = new EntitySchema({
    name: "PartStatus",
    tableName: "partstatus",
    columns: {
        partstatus_id: {
            type: "varchar",
            primary: true,
            length: 8,
            nullable: false,
        },
        Owner: {
            type: "varchar",
            length: 100,
            nullable: true,
        },
        TaskForceTag: {
            type: "varchar",
            length: 100,
            nullable: true,
        },
        Shortname: {
            type: "varchar",
            length: 100,
            nullable: true,
        },
        Quantity: {
            type: "int",
            nullable: true,
            default: 0,
        },
        Damage: {
            type: "int",
            nullable: true,
            default: 0,
        },
        Active: {
            type: "int",
            nullable: true,
            default: 1,
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
