import { DataSource } from "typeorm";

import { QuestionType } from "../entity/QuestionType.js";


export const AppDataSource = new DataSource({
    type: "sqlite",
    database: "teams.sqlite",
    synchronize: true,
    logging: false,
    entities: [

        QuestionType,

    ],
});
