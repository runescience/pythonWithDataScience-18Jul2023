
import express from "express";
import { AppDataSource } from "../config/database.js";
import { PartStatus } from "../entity/PartStatus.js";
import { generateId } from "../entity/utils/idGenerator.js";
import fs from "fs";

const router = express.Router();

// Logging helper
const logToFile = (message) => {
    const logMessage = `${new Date().toISOString()} - ${message}\n`;
    fs.appendFileSync("app.log", logMessage, { flags: "a" });
};

// Get all part statuses
router.get("/", async (req, res) => {
    try {
        const partStatuses = await AppDataSource.manager.find(PartStatus);
        logToFile(`Retrieved ${partStatuses.length} part statuses`);
        res.json(partStatuses);
    } catch (error) {
        logToFile(`Error fetching part statuses: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Get part status by ID
router.get("/:partstatus_id", async (req, res) => {
    const { partstatus_id } = req.params;
    try {
        const partStatus = await AppDataSource.manager.findOne(PartStatus, {
            where: { partstatus_id }
        });
        if (!partStatus) {
            logToFile(`Part status not found: ${partstatus_id}`);
            return res.status(404).json({ error: "Part status not found" });
        }
        logToFile(`Retrieved part status: ${partstatus_id}`);
        res.json(partStatus);
    } catch (error) {
        logToFile(`Error fetching part status ${partstatus_id}: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Create new part status
router.post("/", async (req, res) => {
    const { Owner, TaskForceTag, Shortname, Quantity, Damage, Active } = req.body;

    try {
        await AppDataSource.manager.transaction(async (transactionalEntityManager) => {
            const newPartStatus = {
                partstatus_id: generateId(),
                Owner,
                TaskForceTag,
                Shortname,
                Quantity: Quantity || 0,
                Damage: Damage || 0,
                Active: Active !== undefined ? Active : 1,
            };

            const savedPartStatus = await transactionalEntityManager.save(PartStatus, newPartStatus);
            logToFile(`Created part status: ${savedPartStatus.partstatus_id}`);
            res.status(201).json(savedPartStatus);
        });
    } catch (error) {
        logToFile(`Error creating part status: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Update part status
router.put("/:partstatus_id", async (req, res) => {
    const { partstatus_id } = req.params;
    const { Owner, TaskForceTag, Shortname, Quantity, Damage, Active } = req.body;

    try {
        await AppDataSource.manager.transaction(async (transactionalEntityManager) => {
            const partStatus = await transactionalEntityManager.findOne(PartStatus, {
                where: { partstatus_id }
            });

            if (!partStatus) {
                logToFile(`Part status not found for update: ${partstatus_id}`);
                return res.status(404).json({ error: "Part status not found" });
            }

            partStatus.Owner = Owner !== undefined ? Owner : partStatus.Owner;
            partStatus.TaskForceTag = TaskForceTag !== undefined ? TaskForceTag : partStatus.TaskForceTag;
            partStatus.Shortname = Shortname !== undefined ? Shortname : partStatus.Shortname;
            partStatus.Quantity = Quantity !== undefined ? Quantity : partStatus.Quantity;
            partStatus.Damage = Damage !== undefined ? Damage : partStatus.Damage;
            partStatus.Active = Active !== undefined ? Active : partStatus.Active;

            const updatedPartStatus = await transactionalEntityManager.save(PartStatus, partStatus);
            logToFile(`Updated part status: ${partstatus_id}`);
            res.json(updatedPartStatus);
        });
    } catch (error) {
        logToFile(`Error updating part status ${partstatus_id}: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Delete part status
router.delete("/:partstatus_id", async (req, res) => {
    const { partstatus_id } = req.params;

    try {
        await AppDataSource.manager.transaction(async (transactionalEntityManager) => {
            const result = await transactionalEntityManager.delete(PartStatus, { partstatus_id });
            
            if (result.affected === 0) {
                logToFile(`Part status not found for deletion: ${partstatus_id}`);
                return res.status(404).json({ error: "Part status not found" });
            }

            logToFile(`Deleted part status: ${partstatus_id}`);
            res.status(204).send();
        });
    } catch (error) {
        logToFile(`Error deleting part status ${partstatus_id}: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

export default router;
