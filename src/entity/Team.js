import { Entity, Column } from "typeorm";

import { EntitySchema } from "typeorm";

export const Team = new EntitySchema({
    name: "Team",
    tableName: "teams",
    columns: {
        team_id: {
            type: "varchar",
            primary: true,
            length: 8,
            nullable: false,
        },
        teamname: {
            type: "varchar",
            length: 100,
            nullable: false,
            unique: true,
        },
        author: {
            type: "varchar",
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
        is_active: {
            type: "boolean",
            default: true,
        },
        contact: {
            type: "text",
            nullable: true,
        },
        description: {
            type: "varchar",
            nullable: true,
        },
    },
});
