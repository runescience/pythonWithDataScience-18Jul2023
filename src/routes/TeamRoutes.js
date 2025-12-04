import { Router } from "express";
import { Team } from "../entity/Team.js";
import { v4 as uuidv4 } from "uuid";
import { AppDataSource } from "../config/database.js";
import fs from "fs";

const router = Router();

// Helper function to log messages to a file
const logToFile = (message) => {
    const logMessage = `${new Date().toISOString()} - ${message}\n`;
    fs.appendFileSync("app.log", logMessage, { flags: "a" });
};

// Get all teams
router.get("/", async (req, res) => {
    try {
        const teams = await AppDataSource.manager.find(Team);
        res.json(teams);
        logToFile(`Fetched teams: ${teams.length} found`);
    } catch (error) {
        logToFile(`Error fetching teams: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Create new team
router.post("/", async (req, res) => {
    const { teamname, description, contact, is_active } = req.body;

    try {
        await AppDataSource.manager.transaction(async (transactionalEntityManager) => {
            const team = {
                team_id: uuidv4().substring(0, 8),
                teamname,
                description,
                contact,
                is_active: is_active !== undefined ? is_active : true
            };

            const savedTeam = await transactionalEntityManager.save(Team, team);
            logToFile(`Created team: ${JSON.stringify(savedTeam)}`);
            res.json(savedTeam);
        });
    } catch (error) {
        logToFile(`Error creating team: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Get team by ID
router.get("/:team_id", async (req, res) => {
    const { team_id } = req.params;
    try {
        const team = await AppDataSource.manager.findOne(Team, {
            where: { team_id }
        });

        if (!team) {
            logToFile(`Team not found with ID: ${team_id}`);
            return res.status(404).json({ error: "Team not found" });
        }

        logToFile(`Fetched team: ${team_id}`);
        res.json(team);
    } catch (error) {
        logToFile(`Error fetching team ${team_id}: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Update team
router.put("/:team_id", async (req, res) => {
    const { team_id } = req.params;
    const { teamname, description, contact, is_active } = req.body;

    try {
        await AppDataSource.manager.transaction(async (transactionalEntityManager) => {
            const team = await transactionalEntityManager.findOne(Team, {
                where: { team_id }
            });

            if (!team) {
                logToFile(`Team not found for update: ${team_id}`);
                return res.status(404).json({ error: "Team not found" });
            }

            team.teamname = teamname || team.teamname;
            team.description = description !== undefined ? description : team.description;
            team.contact = contact !== undefined ? contact : team.contact;
            team.is_active = is_active !== undefined ? is_active : team.is_active;

            const updatedTeam = await transactionalEntityManager.save(Team, team);
            logToFile(`Updated team: ${team_id}`);
            res.json(updatedTeam);
        });
    } catch (error) {
        logToFile(`Error updating team ${team_id}: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

// Delete team

// Delete team
router.delete("/:team_id", async (req, res) => {
    const { team_id } = req.params;

    logToFile(`Team deleting: ${team_id}`);

    try {
        await AppDataSource.manager.transaction(async (transactionalEntityManager) => {
            const team = await transactionalEntityManager.findOne(Team, {
                where: { team_id }
            });

            if (!team) {
                logToFile(`Team not found for deletion: ${team_id}`);
                return res.status(404).json({ error: "Team not found" });
            }
            else {
                logToFile(`Team  found for deletion: ${team_id}`);
            }

            await transactionalEntityManager.remove(Team, team);
            logToFile(`Deleted team: ${team_id}`);
            res.status(204).send();
        });
    } catch (error) {
        logToFile(`Error deleting team ${team_id}: ${error.message}`);
        res.status(500).json({ error: error.message });
    }
});

export default router;
