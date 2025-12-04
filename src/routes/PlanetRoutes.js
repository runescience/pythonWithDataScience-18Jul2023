
import express from "express";
import { AppDataSource } from "../config/database.js";
import { Planet } from "../entity/Planet.js";
import { generateId } from "../entity/utils/idGenerator.js";
import fs from "fs";

const router = express.Router();

// GET all planets
router.get("/", async (req, res) => {
    try {
        const planetRepository = AppDataSource.getRepository(Planet);
        const planets = await planetRepository.find();
        res.json(planets);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error fetching planets: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error fetching planets", error: error.message });
    }
});

// GET planet by ID
router.get("/:planet_id", async (req, res) => {
    try {
        const planetRepository = AppDataSource.getRepository(Planet);
        const planet = await planetRepository.findOne({
            where: { planet_id: req.params.planet_id },
        });

        if (!planet) {
            const logMessage = `${new Date().toISOString()} - Planet not found: ${req.params.planet_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Planet not found" });
        }

        res.json(planet);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error fetching planet: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error fetching planet", error: error.message });
    }
});

// POST - Create new planet
router.post("/", async (req, res) => {
    try {
        const planetRepository = AppDataSource.getRepository(Planet);
        const newPlanet = planetRepository.create({
            planet_id: generateId(),
            Itemtag: req.body.Itemtag,
            Owner: req.body.Owner,
            GameID: req.body.GameID,
            Name: req.body.Name,
            Size: req.body.Size,
            Atmosphere: req.body.Atmosphere,
            NativeRace: req.body.NativeRace,
            Oretype: req.body.Oretype,
            isCaptured: req.body.isCaptured,
        });

        const savedPlanet = await planetRepository.save(newPlanet);
        const logMessage = `${new Date().toISOString()} - Planet created: ${savedPlanet.planet_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(201).json(savedPlanet);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error creating planet: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error creating planet", error: error.message });
    }
});

// PUT - Update planet
router.put("/:planet_id", async (req, res) => {
    try {
        const planetRepository = AppDataSource.getRepository(Planet);
        const planet = await planetRepository.findOne({
            where: { planet_id: req.params.planet_id },
        });

        if (!planet) {
            const logMessage = `${new Date().toISOString()} - Planet not found for update: ${req.params.planet_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Planet not found" });
        }

        planetRepository.merge(planet, {
            Itemtag: req.body.Itemtag,
            Owner: req.body.Owner,
            GameID: req.body.GameID,
            Name: req.body.Name,
            Size: req.body.Size,
            Atmosphere: req.body.Atmosphere,
            NativeRace: req.body.NativeRace,
            Oretype: req.body.Oretype,
            isCaptured: req.body.isCaptured,
        });

        const updatedPlanet = await planetRepository.save(planet);
        const logMessage = `${new Date().toISOString()} - Planet updated: ${updatedPlanet.planet_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.json(updatedPlanet);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error updating planet: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error updating planet", error: error.message });
    }
});

// DELETE planet
router.delete("/:planet_id", async (req, res) => {
    try {
        const planetRepository = AppDataSource.getRepository(Planet);
        const planet = await planetRepository.findOne({
            where: { planet_id: req.params.planet_id },
        });

        if (!planet) {
            const logMessage = `${new Date().toISOString()} - Planet not found for deletion: ${req.params.planet_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Planet not found" });
        }

        await planetRepository.remove(planet);
        const logMessage = `${new Date().toISOString()} - Planet deleted: ${req.params.planet_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.json({ message: "Planet deleted successfully" });
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error deleting planet: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error deleting planet", error: error.message });
    }
});

export default router;
