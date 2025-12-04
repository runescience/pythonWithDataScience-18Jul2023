
import express from "express";
import { AppDataSource } from "../config/database.js";
import { GameDefinition } from "../entity/GameDefinition.js";
import { generateShortId } from "../entity/utils/idGenerator.js";
import fs from "fs";

const router = express.Router();

// Get all game definitions
router.get("/", async (req, res) => {
    try {
        const gameDefinitionRepository = AppDataSource.getRepository(GameDefinition);
        const gameDefinitions = await gameDefinitionRepository.find();
        res.json(gameDefinitions);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error fetching game definitions: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error fetching game definitions", error: error.message });
    }
});

// Get a single game definition by ID
router.get("/:gamedefinition_id", async (req, res) => {
    try {
        const gameDefinitionRepository = AppDataSource.getRepository(GameDefinition);
        const gameDefinition = await gameDefinitionRepository.findOne({
            where: { gamedefinition_id: req.params.gamedefinition_id },
        });

        if (!gameDefinition) {
            const logMessage = `${new Date().toISOString()} - Game definition not found: ${req.params.gamedefinition_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Game definition not found" });
        }

        res.json(gameDefinition);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error fetching game definition: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error fetching game definition", error: error.message });
    }
});

// Create a new game definition
router.post("/", async (req, res) => {
    try {
        const gameDefinitionRepository = AppDataSource.getRepository(GameDefinition);
        const newGameDefinition = gameDefinitionRepository.create({
            gamedefinition_id: generateShortId(),
            ...req.body,
        });

        const savedGameDefinition = await gameDefinitionRepository.save(newGameDefinition);
        const logMessage = `${new Date().toISOString()} - Game definition created: ${savedGameDefinition.gamedefinition_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(201).json(savedGameDefinition);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error creating game definition: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error creating game definition", error: error.message });
    }
});

// Update a game definition
router.put("/:gamedefinition_id", async (req, res) => {
    try {
        const gameDefinitionRepository = AppDataSource.getRepository(GameDefinition);
        const gameDefinition = await gameDefinitionRepository.findOne({
            where: { gamedefinition_id: req.params.gamedefinition_id },
        });

        if (!gameDefinition) {
            const logMessage = `${new Date().toISOString()} - Game definition not found for update: ${req.params.gamedefinition_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Game definition not found" });
        }

        gameDefinitionRepository.merge(gameDefinition, req.body);
        const updatedGameDefinition = await gameDefinitionRepository.save(gameDefinition);
        const logMessage = `${new Date().toISOString()} - Game definition updated: ${updatedGameDefinition.gamedefinition_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.json(updatedGameDefinition);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error updating game definition: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error updating game definition", error: error.message });
    }
});

// Delete a game definition
router.delete("/:gamedefinition_id", async (req, res) => {
    try {
        const gameDefinitionRepository = AppDataSource.getRepository(GameDefinition);
        const gameDefinition = await gameDefinitionRepository.findOne({
            where: { gamedefinition_id: req.params.gamedefinition_id },
        });

        if (!gameDefinition) {
            const logMessage = `${new Date().toISOString()} - Game definition not found for deletion: ${req.params.gamedefinition_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Game definition not found" });
        }

        await gameDefinitionRepository.remove(gameDefinition);
        const logMessage = `${new Date().toISOString()} - Game definition deleted: ${req.params.gamedefinition_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.json({ message: "Game definition deleted successfully" });
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error deleting game definition: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error deleting game definition", error: error.message });
    }
});

export default router;
