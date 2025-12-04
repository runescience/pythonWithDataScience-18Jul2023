
import sqlite3 from 'sqlite3';
import fs from 'fs';

function printSchema() {
    const db = new sqlite3.Database('teams.sqlite', sqlite3.OPEN_READONLY, (err) => {
        if (err) {
            console.error('Error opening database:', err.message);
            return;
        }
        console.log('Connected to the teams.sqlite database.');
    });

    db.serialize(() => {
        db.all("SELECT name FROM sqlite_master WHERE type='table'", [], (err, tables) => {
            if (err) {
                console.error('Error fetching tables:', err.message);
                return;
            }

            let schemaOutput = 'Database Schema:\n\n';
            let tableCount = 0;
            let completedTables = 0;

            tables.forEach((table) => {
                const tableName = table.name;
                let tableOutput = `Table: ${tableName}\nColumns:\n`;

                // Get columns first
                db.all(`PRAGMA table_info(${tableName})`, [], (err, columns) => {
                    if (err) {
                        console.error(`Error fetching schema for table ${tableName}:`, err.message);
                        return;
                    }

                    columns.forEach((column) => {
                        tableOutput += `  - ${column.name} (${column.type}) ${column.notnull ? 'NOT NULL' : ''} ${column.pk ? 'PRIMARY KEY' : ''}\n`;
                    });

                    // Get foreign keys for this table
                    db.all(`PRAGMA foreign_key_list(${tableName})`, [], (err, foreignKeys) => {
                        tableOutput += 'Foreign Keys:\n';
                        if (!err && foreignKeys.length > 0) {
                            foreignKeys.forEach((fk) => {
                                tableOutput += `  - ${fk.from} references ${fk.table}(${fk.to})\n`;
                            });
                        } else {
                            tableOutput += '  - None\n';
                        }
                        
                        tableOutput += '\n';
                        schemaOutput += tableOutput;
                        
                        completedTables++;
                        if (completedTables === tables.length) {
                            fs.writeFileSync('schema.txt', schemaOutput);
                            console.log('Schema printed to schema.txt');
                            db.close((err) => {
                                if (err) {
                                    console.error('Error closing database:', err.message);
                                }
                            });
                        }
                    });
                });
            });
        });
    });
}

printSchema();
