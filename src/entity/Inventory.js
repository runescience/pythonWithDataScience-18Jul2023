
import { EntitySchema } from "typeorm";

export const Inventory = new EntitySchema({
    name: "Inventory",
    tableName: "inventory",
    columns: {
        inventory_id: {
            type: "varchar",
            primary: true,
            length: 8,
            nullable: false,
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
        Abbrev: {
            type: "varchar",
            length: 10,
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
