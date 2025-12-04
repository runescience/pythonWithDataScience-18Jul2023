
import fs from 'fs';
import fetch from 'node-fetch';

const API_URL = 'http://0.0.0.0:3000/inventory';

// Read and parse the inventory2.txt file
const loadInventoryData = async () => {
    try {
        const fileContent = fs.readFileSync('xxtext/inventory2.txt', 'utf-8');
        const lines = fileContent.split('\n');
        
        let successCount = 0;
        let errorCount = 0;
        
        for (const line of lines) {
            // Skip empty lines or lines that are too short
            if (!line.trim() || line.trim().length < 10) {
                continue;
            }
            
            // Parse comma-separated values
            const parts = line.split(',').map(p => p.trim());
            
            // Skip if we don't have at least 10 fields (the minimum required)
            if (parts.length < 10) {
                console.log(`Skipping line (insufficient fields): ${line}`);
                continue;
            }
            
            // Map the fields according to the format:
            // availableSpaces, Cost, Energy, Tech, Advancedgame, Max, Startquan, Name, Abbrev, Comment
            // Group and Bonus are optional (fields 11 and 12)
            const inventoryItem = {
                availableSpaces: parseInt(parts[0]) || 0,
                Cost: parseInt(parts[1]) || 0,
                Energy: parseInt(parts[2]) || 0,
                Tech: parseInt(parts[3]) || 0,
                Advancedgame: parseInt(parts[4]) || 0,
                Max: parseInt(parts[5]) || 0,
                Startquan: parseInt(parts[6]) || 0,
                Name: parts[7] || '',
                Abbrev: parts[8] || '',
                Comment: parts[9] || '',
                Group: parts[10] || 'NONE',  // Default value if not provided
                Bonus: parts[11] || ''        // Default value if not provided
            };
            
            // Skip if Name is empty or just placeholder characters
            if (!inventoryItem.Name || inventoryItem.Name === 'a') {
                console.log(`Skipping line (invalid name): ${line}`);
                continue;
            }
            
            try {
                const response = await fetch(API_URL, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(inventoryItem)
                });
                
                if (response.ok) {
                    const result = await response.json();
                    successCount++;
                    console.log(`✓ Created: ${inventoryItem.Name} (${inventoryItem.Abbrev}) - ID: ${result.inventory_id}`);
                } else {
                    const error = await response.text();
                    errorCount++;
                    console.error(`✗ Failed to create ${inventoryItem.Name}: ${error}`);
                }
            } catch (error) {
                errorCount++;
                console.error(`✗ Error creating ${inventoryItem.Name}:`, error.message);
            }
        }
        
        console.log('\n=== Summary ===');
        console.log(`Successfully created: ${successCount}`);
        console.log(`Failed: ${errorCount}`);
        
    } catch (error) {
        console.error('Error reading file:', error);
    }
};

// Main execution
console.log('Starting inventory data load...');
console.log('Make sure the backend server is running on port 3000\n');

loadInventoryData();
