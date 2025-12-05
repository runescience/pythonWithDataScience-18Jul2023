
import { EntitySchema } from "typeorm";

export const Inventory = new EntitySchema({
    name: "Inventory",
    tableName: "inventory",
    columns: {
        Abbrev: {
            type: "varchar",
            primary: true,
            length: 10,
            nullable: false,
        },
        inventory_id: {
            type: "varchar",
            length: 10,
            nullable: true,
        },
        availableSpaces: {
            type: "int",
            nullable: true,
            default: 0,
        },
        Cost: {
            type: "int",
            nullable: true,
            default: 0,
        },
        Energy: {
            type: "int",
            nullable: true,
            default: 0,
        },
        Tech: {
            type: "int",
            nullable: true,
            default: 0,
        },
        Advancedgame: {
            type: "int",
            nullable: true,
            default: 0,
        },
        Max: {
            type: "int",
            nullable: true,
            default: 0,
        },
        Startquan: {
            type: "int",
            nullable: true,
            default: 0,
        },
        Name: {
            type: "varchar",
            length: 100,
            nullable: true,
        },
        Comment: {
            type: "text",
            nullable: true,
        },
        Group: {
            type: "varchar",
            length: 50,
            nullable: true,
        },
        Bonus: {
            type: "varchar",
            length: 100,
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
