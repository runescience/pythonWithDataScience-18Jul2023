
import express from "express";
import { AppDataSource } from "../config/database.js";
import { Universe } from "../entity/Universe.js";

const router = express.Router();

// Get all universes
router.get("/", async (req, res) => {
    try {
        const universeRepository = AppDataSource.getRepository(Universe);
        const universes = await universeRepository.find();
        res.json(universes);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// Get universe by ID
router.get("/:universe_id", async (req, res) => {
    try {
        const universeRepository = AppDataSource.getRepository(Universe);
        const universe = await universeRepository.findOne({
            where: { universe_id: req.params.universe_id },
        });

        if (!universe) {
            return res.status(404).json({ message: "Universe not found" });
        }

        res.json(universe);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// Create new universe
router.post("/", async (req, res) => {
    try {
        const universeRepository = AppDataSource.getRepository(Universe);
        const universe = universeRepository.create(req.body);
        const result = await universeRepository.save(universe);
        res.status(201).json(result);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
});

// Update universe
router.put("/:universe_id", async (req, res) => {
    try {
        const universeRepository = AppDataSource.getRepository(Universe);
        const universe = await universeRepository.findOne({
            where: { universe_id: req.params.universe_id },
        });

        if (!universe) {
            return res.status(404).json({ message: "Universe not found" });
        }

        universeRepository.merge(universe, req.body);
        const result = await universeRepository.save(universe);
        res.json(result);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
});

// Delete universe
router.delete("/:universe_id", async (req, res) => {
    try {
        const universeRepository = AppDataSource.getRepository(Universe);
        const result = await universeRepository.delete(req.params.universe_id);

        if (result.affected === 0) {
            return res.status(404).json({ message: "Universe not found" });
        }

        res.json({ message: "Universe deleted successfully" });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

export default router;
