import { DataSource } from "typeorm";

import { Team } from "../entity/Team.js";
import { PartStatus } from "../entity/PartStatus.js";
import { Inventory } from "../entity/Inventory.js";


export const AppDataSource = new DataSource({
    type: "sqlite",
    database: "teams.sqlite",
    synchronize: true,
    logging: false,
    entities: [
        Team,
        PartStatus,
        Inventory,
    ],
});
