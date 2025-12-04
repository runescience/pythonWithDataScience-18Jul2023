import express from "express";
import "reflect-metadata";
import { AppDataSource } from "../src/config/database.js"; // Corrected import
import TeamsRoutes from "../src/routes/TeamRoutes.js";
import PartStatusRoutes from "../src/routes/PartStatusRoutes.js";
import InventoryRoutes from "../src/routes/InventoryRoutes.js";
import CacheRoutes from "../src/routes/CacheRoutes.js";
import cors from "cors";
import fs from "fs";

// Enable CORS for all routes
const app = express();
app.use(
    cors({
        origin: "http://localhost:4200", // Your Angular app's URL
        methods: ["GET", "POST", "PUT", "DELETE", "OPTIONS"],
        allowedHeaders: ["Content-Type", "Authorization"],
    })
);

app.use(express.json());

// Request logging middleware
app.use((req, res, next) => {
    const logMessage = `${new Date().toISOString()} - ${req.method} ${req.url} - Status: ${res.statusCode}\n`;
    fs.appendFileSync("app.log", logMessage, { flags: "a" });
    next();
});

AppDataSource.initialize()
    .then(() => {
        console.log("Database initialized");
        // Start the server only after the database is initialized
        app.listen(3000, "0.0.0.0", () => { // Changed to 0.0.0.0
            console.log("Server running on port 3000");
        });
    })
    .catch((error) => {
        console.error("Error during Data Source initialization:", error);
    });

// Use routes
app.use("/teams", TeamsRoutes);
app.use("/partstatus", PartStatusRoutes);
app.use("/inventory", InventoryRoutes);
app.use("/cache", CacheRoutes);

// teamid:555,
// teamname:blue, 
// author:joel,
// updated_on:"12/12/2025",
// is_active:true, contact:"rob",
// description:"no description today"


