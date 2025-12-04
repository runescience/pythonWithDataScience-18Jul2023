
import { EntitySchema } from "typeorm";

export const GameDefinition = new EntitySchema({
    name: "GameDefinition",
    tableName: "gamedefinition",
    columns: {
        gamedefinition_id: {
            type: "varchar",
            primary: true,
            length: 8,
            nullable: false,
        },
        GameID: {
            type: "int",
            nullable: true,
            default: 0,
        },
        GameName: {
            type: "varchar",
            length: 100,
            nullable: true,
        },
        GameType: {
            type: "varchar",
            length: 100,
            nullable: true,
        },
        GamePassword: {
            type: "varchar",
            length: 100,
            nullable: true,
        },
        MaxPlayers: {
            type: "int",
            nullable: true,
            default: 0,
        },
        StartTech: {
            type: "int",
            nullable: true,
            default: 0,
        },
        MaxTech: {
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
