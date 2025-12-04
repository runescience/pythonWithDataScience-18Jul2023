
import { Universe } from '../models/universe.interface';

const API_URL = 'http://localhost:3000/universe';

export class UniverseService {
    async getAllUniverses(): Promise<Universe[]> {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error('Failed to fetch universes');
        }
        return response.json();
    }

    async getUniverseById(universe_id: string): Promise<Universe> {
        const response = await fetch(`${API_URL}/${universe_id}`);
        if (!response.ok) {
            throw new Error('Failed to fetch universe');
        }
        return response.json();
    }

    async createUniverse(universe: Omit<Universe, 'universe_id' | 'created_on' | 'updated_on'>): Promise<Universe> {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(universe),
        });
        if (!response.ok) {
            throw new Error('Failed to create universe');
        }
        return response.json();
    }

    async updateUniverse(universe_id: string, universe: Partial<Universe>): Promise<Universe> {
        const response = await fetch(`${API_URL}/${universe_id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(universe),
        });
        if (!response.ok) {
            throw new Error('Failed to update universe');
        }
        return response.json();
    }

    async deleteUniverse(universe_id: string): Promise<void> {
        const response = await fetch(`${API_URL}/${universe_id}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error('Failed to delete universe');
        }
    }
}
