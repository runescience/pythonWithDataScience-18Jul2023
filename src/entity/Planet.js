
import { EntitySchema } from "typeorm";

export const Planet = new EntitySchema({
    name: "Planet",
    tableName: "planet",
    columns: {
        planet_id: {
            type: "varchar",
            primary: true,
            length: 8,
            nullable: false,
        },
        Itemtag: {
            type: "varchar",
            length: 16,
            nullable: true,
        },
        Owner: {
            type: "varchar",
            length: 16,
            nullable: true,
        },
        GameID: {
            type: "varchar",
            length: 12,
            nullable: true,
        },
        Name: {
            type: "varchar",
            length: 16,
            nullable: true,
        },
        Size: {
            type: "int",
            nullable: true,
            default: 0,
        },
        Atmosphere: {
            type: "int",
            nullable: true,
            default: 0,
        },
        NativeRace: {
            type: "varchar",
            length: 4,
            nullable: true,
        },
        Oretype: {
            type: "varchar",
            length: 4,
            nullable: true,
        },
        isCaptured: {
            type: "boolean",
            nullable: true,
            default: false,
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
