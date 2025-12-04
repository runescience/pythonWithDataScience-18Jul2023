
import express from "express";
import { AppDataSource } from "../config/database.js";
import { Inventory } from "../entity/Inventory.js";
import { generateId } from "../entity/utils/idGenerator.js";
import fs from "fs";

const router = express.Router();

// Logging helper
const logToFile = (message) => {
    const logMessage = `${new Date().toISOString()} - ${message}\n`;
    fs.appendFileSync("app.log", logMessage, { flags: "a" });
};

// Get all inventory items
router.get("/", async (req, res) => {
    try {
        const inventoryItems = await AppDataSource.manager.find(Inventory);
        logToFile(`Retrieved ${inventoryItems.length} inventory items`);
        res.json(inventoryItems);
    } catch (error) {
        logToFile(`Error fetching inventory items: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Get inventory item by ID
router.get("/:inventory_id", async (req, res) => {
    const { inventory_id } = req.params;
    try {
        const inventoryItem = await AppDataSource.manager.findOne(Inventory, {
            where: { inventory_id }
        });
        if (!inventoryItem) {
            logToFile(`Inventory item not found: ${inventory_id}`);
            return res.status(404).json({ error: "Inventory item not found" });
        }
        logToFile(`Retrieved inventory item: ${inventory_id}`);
        res.json(inventoryItem);
    } catch (error) {
        logToFile(`Error fetching inventory item ${inventory_id}: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Create new inventory item
router.post("/", async (req, res) => {
    const { availableSpaces, Cost, Energy, Tech, Advancedgame, Max, Startquan, Name, Abbrev, Comment, Group, Bonus } = req.body;

    try {
        await AppDataSource.manager.transaction(async (transactionalEntityManager) => {
            const newInventoryItem = {
                inventory_id: generateId(),
                availableSpaces: availableSpaces || 0,
                Cost: Cost || 0,
                Energy: Energy || 0,
                Tech: Tech || 0,
                Advancedgame: Advancedgame || 0,
                Max: Max || 0,
                Startquan: Startquan || 0,
                Name,
                Abbrev,
                Comment,
                Group,
                Bonus,
            };

            const savedInventoryItem = await transactionalEntityManager.save(Inventory, newInventoryItem);
            logToFile(`Created inventory item: ${savedInventoryItem.inventory_id}`);
            res.status(201).json(savedInventoryItem);
        });
    } catch (error) {
        logToFile(`Error creating inventory item: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Update inventory item
router.put("/:inventory_id", async (req, res) => {
    const { inventory_id } = req.params;
    const { availableSpaces, Cost, Energy, Tech, Advancedgame, Max, Startquan, Name, Abbrev, Comment, Group, Bonus } = req.body;

    try {
        await AppDataSource.manager.transaction(async (transactionalEntityManager) => {
            const inventoryItem = await transactionalEntityManager.findOne(Inventory, {
                where: { inventory_id }
            });

            if (!inventoryItem) {
                logToFile(`Inventory item not found for update: ${inventory_id}`);
                return res.status(404).json({ error: "Inventory item not found" });
            }

            inventoryItem.availableSpaces = availableSpaces !== undefined ? availableSpaces : inventoryItem.availableSpaces;
            inventoryItem.Cost = Cost !== undefined ? Cost : inventoryItem.Cost;
            inventoryItem.Energy = Energy !== undefined ? Energy : inventoryItem.Energy;
            inventoryItem.Tech = Tech !== undefined ? Tech : inventoryItem.Tech;
            inventoryItem.Advancedgame = Advancedgame !== undefined ? Advancedgame : inventoryItem.Advancedgame;
            inventoryItem.Max = Max !== undefined ? Max : inventoryItem.Max;
            inventoryItem.Startquan = Startquan !== undefined ? Startquan : inventoryItem.Startquan;
            inventoryItem.Name = Name !== undefined ? Name : inventoryItem.Name;
            inventoryItem.Abbrev = Abbrev !== undefined ? Abbrev : inventoryItem.Abbrev;
            inventoryItem.Comment = Comment !== undefined ? Comment : inventoryItem.Comment;
            inventoryItem.Group = Group !== undefined ? Group : inventoryItem.Group;
            inventoryItem.Bonus = Bonus !== undefined ? Bonus : inventoryItem.Bonus;

            const updatedInventoryItem = await transactionalEntityManager.save(Inventory, inventoryItem);
            logToFile(`Updated inventory item: ${inventory_id}`);
            res.json(updatedInventoryItem);
        });
    } catch (error) {
        logToFile(`Error updating inventory item ${inventory_id}: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Delete inventory item
router.delete("/:inventory_id", async (req, res) => {
    const { inventory_id } = req.params;

    try {
        await AppDataSource.manager.transaction(async (transactionalEntityManager) => {
            const result = await transactionalEntityManager.delete(Inventory, { inventory_id });
            
            if (result.affected === 0) {
                logToFile(`Inventory item not found for deletion: ${inventory_id}`);
                return res.status(404).json({ error: "Inventory item not found" });
            }

            logToFile(`Deleted inventory item: ${inventory_id}`);
            res.status(204).send();
        });
    } catch (error) {
        logToFile(`Error deleting inventory item ${inventory_id}: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

export default router;
