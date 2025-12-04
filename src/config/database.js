import { DataSource } from "typeorm";

import { Team } from "../entity/Team.js";
import { PartStatus } from "../entity/PartStatus.js";
import { Inventory } from "../entity/Inventory.js";
import { Cache } from "../entity/Cache.js";
import { GameDefinition } from "../entity/GameDefinition.js";
import { Hazard } from "../entity/Hazard.js";
import { Planet } from "../entity/Planet.js";


export const AppDataSource = new DataSource({
    type: "sqlite",
    database: "teams.sqlite",
    synchronize: true,
    logging: false,
    entities: [
        Team,
        PartStatus,
        Inventory,
        Cache,
        GameDefinition,
        Hazard,
        Planet,
    ],
});
