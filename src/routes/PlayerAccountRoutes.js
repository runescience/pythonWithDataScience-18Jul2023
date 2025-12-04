
import express from "express";
import { AppDataSource } from "../config/database.js";
import { PlayerAccount } from "../entity/PlayerAccount.js";
import { generateId } from "../entity/utils/idGenerator.js";
import fs from "fs";

const router = express.Router();

// GET all player accounts
router.get("/", async (req, res) => {
    try {
        const playerAccountRepository = AppDataSource.getRepository(PlayerAccount);
        const playerAccounts = await playerAccountRepository.find();
        res.json(playerAccounts);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error fetching player accounts: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error fetching player accounts", error: error.message });
    }
});

// GET player account by ID
router.get("/:playeraccount_id", async (req, res) => {
    try {
        const playerAccountRepository = AppDataSource.getRepository(PlayerAccount);
        const playerAccount = await playerAccountRepository.findOne({
            where: { playeraccount_id: req.params.playeraccount_id },
        });

        if (!playerAccount) {
            const logMessage = `${new Date().toISOString()} - Player account not found: ${req.params.playeraccount_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Player account not found" });
        }

        res.json(playerAccount);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error fetching player account: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error fetching player account", error: error.message });
    }
});

// POST create new player account
router.post("/", async (req, res) => {
    try {
        const playerAccountRepository = AppDataSource.getRepository(PlayerAccount);
        const newPlayerAccount = playerAccountRepository.create({
            playeraccount_id: generateId(),
            ...req.body,
        });

        const savedPlayerAccount = await playerAccountRepository.save(newPlayerAccount);
        const logMessage = `${new Date().toISOString()} - Player account created: ${savedPlayerAccount.playeraccount_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(201).json(savedPlayerAccount);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error creating player account: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error creating player account", error: error.message });
    }
});

// PUT update player account
router.put("/:playeraccount_id", async (req, res) => {
    try {
        const playerAccountRepository = AppDataSource.getRepository(PlayerAccount);
        const playerAccount = await playerAccountRepository.findOne({
            where: { playeraccount_id: req.params.playeraccount_id },
        });

        if (!playerAccount) {
            const logMessage = `${new Date().toISOString()} - Player account not found for update: ${req.params.playeraccount_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Player account not found" });
        }

        playerAccountRepository.merge(playerAccount, req.body);
        const updatedPlayerAccount = await playerAccountRepository.save(playerAccount);
        const logMessage = `${new Date().toISOString()} - Player account updated: ${req.params.playeraccount_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.json(updatedPlayerAccount);
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error updating player account: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error updating player account", error: error.message });
    }
});

// DELETE player account
router.delete("/:playeraccount_id", async (req, res) => {
    try {
        const playerAccountRepository = AppDataSource.getRepository(PlayerAccount);
        const playerAccount = await playerAccountRepository.findOne({
            where: { playeraccount_id: req.params.playeraccount_id },
        });

        if (!playerAccount) {
            const logMessage = `${new Date().toISOString()} - Player account not found for deletion: ${req.params.playeraccount_id}\n`;
            fs.appendFileSync("app.log", logMessage, { flags: "a" });
            return res.status(404).json({ message: "Player account not found" });
        }

        await playerAccountRepository.remove(playerAccount);
        const logMessage = `${new Date().toISOString()} - Player account deleted: ${req.params.playeraccount_id}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.json({ message: "Player account deleted successfully" });
    } catch (error) {
        const logMessage = `${new Date().toISOString()} - Error deleting player account: ${error.message}\n`;
        fs.appendFileSync("app.log", logMessage, { flags: "a" });
        res.status(500).json({ message: "Error deleting player account", error: error.message });
    }
});

export default router;
