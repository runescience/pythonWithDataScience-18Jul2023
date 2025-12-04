import { EntitySchema } from "typeorm";

export const QuestionType = new EntitySchema({
    name: "QuestionType",
    tableName: "question_types",
    columns: {
        question_type_id: {
            type: "varchar",
            primary: true,
            length: 8,
            nullable: false,
            generated: false,
        },
        type: {
            type: "varchar",
            nullable: false,
            unique: true,
        },
        has_regex: {
            type: "boolean",
            default: false,
        },
        regex_str: {
            type: "varchar",
            nullable: true,
        },
        has_options: {
            type: "boolean",
            default: false,
        },
        options_str: {
            type: "varchar",
            nullable: true,
        },
        has_supplemental: {
            type: "boolean",
            default: false,
        },
        supplemental_str: {
            type: "varchar",
            nullable: true,
        },
        // BaseEntity fields
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
    },
    relations: {
        questions: {
            target: "Question",
            type: "one-to-many",
            inverseSide: "question_type",
        },
    },
});