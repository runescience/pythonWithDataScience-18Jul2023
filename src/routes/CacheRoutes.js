
import { Router } from "express";
import { Cache } from "../entity/Cache.js";
import { v4 as uuidv4 } from "uuid";
import { AppDataSource } from "../config/database.js";
import fs from "fs";

const router = Router();

// Helper function to log messages to a file
function logToFile(message) {
    const logMessage = `${new Date().toISOString()} - ${message}\n`;
    fs.appendFileSync("app.log", logMessage, { flags: "a" });
}

// Get all cache items
router.get("/", async (req, res) => {
    try {
        const cacheItems = await AppDataSource.manager.find(Cache);
        res.json(cacheItems);
    } catch (error) {
        logToFile(`Error fetching cache items: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Get cache item by ID
router.get("/:cache_id", async (req, res) => {
    const { cache_id } = req.params;
    try {
        const cacheItem = await AppDataSource.manager.findOne(Cache, {
            where: { cache_id }
        });
        if (!cacheItem) {
            logToFile(`Cache item not found: ${cache_id}`);
            return res.status(404).json({ error: "Cache item not found" });
        }
        res.json(cacheItem);
    } catch (error) {
        logToFile(`Error fetching cache item ${cache_id}: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Create new cache item
router.post("/", async (req, res) => {
    const { Owner, TaskForceTag, Shortname, Quantity } = req.body;

    try {
        await AppDataSource.manager.transaction(async (transactionalEntityManager) => {
            const newCacheItem = {
                cache_id: uuidv4().substring(0, 8),
                Owner,
                TaskForceTag,
                Shortname,
                Quantity: Quantity || 0,
            };

            const savedCacheItem = await transactionalEntityManager.save(Cache, newCacheItem);
            logToFile(`Created new cache item: ${savedCacheItem.cache_id}`);
            res.status(201).json(savedCacheItem);
        });
    } catch (error) {
        logToFile(`Error creating cache item: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Update cache item
router.put("/:cache_id", async (req, res) => {
    const { cache_id } = req.params;
    const { Owner, TaskForceTag, Shortname, Quantity } = req.body;

    try {
        await AppDataSource.manager.transaction(async (transactionalEntityManager) => {
            const cacheItem = await transactionalEntityManager.findOne(Cache, {
                where: { cache_id }
            });

            if (!cacheItem) {
                logToFile(`Cache item not found for update: ${cache_id}`);
                return res.status(404).json({ error: "Cache item not found" });
            }

            cacheItem.Owner = Owner !== undefined ? Owner : cacheItem.Owner;
            cacheItem.TaskForceTag = TaskForceTag !== undefined ? TaskForceTag : cacheItem.TaskForceTag;
            cacheItem.Shortname = Shortname !== undefined ? Shortname : cacheItem.Shortname;
            cacheItem.Quantity = Quantity !== undefined ? Quantity : cacheItem.Quantity;

            const updatedCacheItem = await transactionalEntityManager.save(Cache, cacheItem);
            logToFile(`Updated cache item: ${cache_id}`);
            res.json(updatedCacheItem);
        });
    } catch (error) {
        logToFile(`Error updating cache item ${cache_id}: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Delete cache item
router.delete("/:cache_id", async (req, res) => {
    const { cache_id } = req.params;

    try {
        await AppDataSource.manager.transaction(async (transactionalEntityManager) => {
            const cacheItem = await transactionalEntityManager.findOne(Cache, {
                where: { cache_id }
            });

            if (!cacheItem) {
                logToFile(`Cache item not found for deletion: ${cache_id}`);
                return res.status(404).json({ error: "Cache item not found" });
            }

            await transactionalEntityManager.remove(Cache, cacheItem);
            logToFile(`Deleted cache item: ${cache_id}`);
            res.json({ message: "Cache item deleted successfully" });
        });
    } catch (error) {
        logToFile(`Error deleting cache item ${cache_id}: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

export default router;
