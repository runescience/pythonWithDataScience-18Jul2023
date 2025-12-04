
import express from "express";
import { AppDataSource } from "../config/database.js";
import { Ship } from "../entity/Ship.js";
import { generateId } from "../entity/utils/idGenerator.js";
import fs from "fs";

const router = express.Router();

// GET all ships
router.get("/", async (req, res) => {
    try {
        const shipRepository = AppDataSource.getRepository(Ship);
        const ships = await shipRepository.find();
        res.json(ships);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error fetching ships: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error fetching ships", error: error.message });
    }
});

// GET ship by ID
router.get("/:ship_id", async (req, res) => {
    try {
        const shipRepository = AppDataSource.getRepository(Ship);
        const ship = await shipRepository.findOne({
            where: { ship_id: req.params.ship_id },
        });

        if (!ship) {
            const logMessage = `${new Date().toISOString()} - Ship not found: ${req.params.ship_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Ship not found" });
        }

        res.json(ship);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error fetching ship: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error fetching ship", error: error.message });
    }
});

// POST - Create new ship
router.post("/", async (req, res) => {
    try {
        const shipRepository = AppDataSource.getRepository(Ship);
        const newShip = shipRepository.create({
            ship_id: generateId(),
            Owner: req.body.Owner,
            Shipname: req.body.Shipname,
            Race: req.body.Race,
            Alliances: req.body.Alliances,
            Energy: req.body.Energy,
            Funds: req.body.Funds,
        });

        const savedShip = await shipRepository.save(newShip);
        const logMessage = `${new Date().toISOString()} - Ship created: ${savedShip.ship_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(201).json(savedShip);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error creating ship: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error creating ship", error: error.message });
    }
});

// PUT - Update ship
router.put("/:ship_id", async (req, res) => {
    try {
        const shipRepository = AppDataSource.getRepository(Ship);
        const ship = await shipRepository.findOne({
            where: { ship_id: req.params.ship_id },
        });

        if (!ship) {
            const logMessage = `${new Date().toISOString()} - Ship not found for update: ${req.params.ship_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Ship not found" });
        }

        shipRepository.merge(ship, {
            Owner: req.body.Owner,
            Shipname: req.body.Shipname,
            Race: req.body.Race,
            Alliances: req.body.Alliances,
            Energy: req.body.Energy,
            Funds: req.body.Funds,
        });

        const updatedShip = await shipRepository.save(ship);
        const logMessage = `${new Date().toISOString()} - Ship updated: ${updatedShip.ship_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.json(updatedShip);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error updating ship: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error updating ship", error: error.message });
    }
});

// DELETE ship
router.delete("/:ship_id", async (req, res) => {
    try {
        const shipRepository = AppDataSource.getRepository(Ship);
        const ship = await shipRepository.findOne({
            where: { ship_id: req.params.ship_id },
        });

        if (!ship) {
            const logMessage = `${new Date().toISOString()} - Ship not found for deletion: ${req.params.ship_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Ship not found" });
        }

        await shipRepository.remove(ship);
        const logMessage = `${new Date().toISOString()} - Ship deleted: ${req.params.ship_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.json({ message: "Ship deleted successfully" });
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error deleting ship: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error deleting ship", error: error.message });
    }
});

export default router;
