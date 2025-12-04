import { DataSource } from "typeorm";

import { Team } from "../entity/Team.js";


export const AppDataSource = new DataSource({
    type: "sqlite",
    database: "teams.sqlite",
    synchronize: true,
    logging: false,
    entities: [
        Team,

    ],
});
