
import { EntitySchema } from "typeorm";

export const PlayerAccount = new EntitySchema({
    name: "PlayerAccount",
    tableName: "playeraccount",
    columns: {
        playeraccount_id: {
            type: "varchar",
            primary: true,
            length: 8,
            nullable: false,
        },
        Firstname: {
            type: "varchar",
            length: 16,
            nullable: true,
        },
        Lastname: {
            type: "varchar",
            length: 16,
            nullable: true,
        },
        Email: {
            type: "varchar",
            length: 50,
            nullable: true,
        },
        UID: {
            type: "varchar",
            length: 16,
            nullable: true,
        },
        Password: {
            type: "varchar",
            length: 16,
            nullable: true,
        },
        SecretAnswer: {
            type: "varchar",
            length: 16,
            nullable: true,
        },
        SecretQuestion: {
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
