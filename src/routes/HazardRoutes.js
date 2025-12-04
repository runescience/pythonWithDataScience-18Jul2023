
import express from "express";
import { AppDataSource } from "../config/database.js";
import { Hazard } from "../entity/Hazard.js";
import { generateId } from "../entity/utils/idGenerator.js";
import fs from "fs";

const router = express.Router();

// GET all hazards
router.get("/", async (req, res) => {
    try {
        const hazardRepository = AppDataSource.getRepository(Hazard);
        const hazards = await hazardRepository.find();
        res.json(hazards);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error fetching hazards: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error fetching hazards", error: error.message });
    }
});

// GET hazard by ID
router.get("/:hazard_id", async (req, res) => {
    try {
        const hazardRepository = AppDataSource.getRepository(Hazard);
        const hazard = await hazardRepository.findOne({
            where: { hazard_id: req.params.hazard_id },
        });

        if (!hazard) {
            const logMessage = `${new Date().toISOString()} - Hazard not found: ${req.params.hazard_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Hazard not found" });
        }

        res.json(hazard);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error fetching hazard: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error fetching hazard", error: error.message });
    }
});

// POST - Create new hazard
router.post("/", async (req, res) => {
    try {
        const hazardRepository = AppDataSource.getRepository(Hazard);
        const newHazard = hazardRepository.create({
            hazard_id: generateId(),
            Owner: req.body.Owner,
            ItemTag: req.body.ItemTag,
            Longname: req.body.Longname,
            Shortname: req.body.Shortname,
            Comment: req.body.Comment,
            Rating: req.body.Rating,
        });

        const savedHazard = await hazardRepository.save(newHazard);
        const logMessage = `${new Date().toISOString()} - Hazard created: ${savedHazard.hazard_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(201).json(savedHazard);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error creating hazard: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error creating hazard", error: error.message });
    }
});

// PUT - Update hazard
router.put("/:hazard_id", async (req, res) => {
    try {
        const hazardRepository = AppDataSource.getRepository(Hazard);
        const hazard = await hazardRepository.findOne({
            where: { hazard_id: req.params.hazard_id },
        });

        if (!hazard) {
            const logMessage = `${new Date().toISOString()} - Hazard not found for update: ${req.params.hazard_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Hazard not found" });
        }

        hazardRepository.merge(hazard, {
            Owner: req.body.Owner,
            ItemTag: req.body.ItemTag,
            Longname: req.body.Longname,
            Shortname: req.body.Shortname,
            Comment: req.body.Comment,
            Rating: req.body.Rating,
        });

        const updatedHazard = await hazardRepository.save(hazard);
        const logMessage = `${new Date().toISOString()} - Hazard updated: ${updatedHazard.hazard_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.json(updatedHazard);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error updating hazard: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error updating hazard", error: error.message });
    }
});

// DELETE hazard
router.delete("/:hazard_id", async (req, res) => {
    try {
        const hazardRepository = AppDataSource.getRepository(Hazard);
        const hazard = await hazardRepository.findOne({
            where: { hazard_id: req.params.hazard_id },
        });

        if (!hazard) {
            const logMessage = `${new Date().toISOString()} - Hazard not found for deletion: ${req.params.hazard_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Hazard not found" });
        }

        await hazardRepository.remove(hazard);
        const logMessage = `${new Date().toISOString()} - Hazard deleted: ${req.params.hazard_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.json({ message: "Hazard deleted successfully" });
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error deleting hazard: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error deleting hazard", error: error.message });
    }
});

export default router;
