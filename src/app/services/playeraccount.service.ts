
import { PlayerAccount } from '../models/playeraccount.interface';

export class PlayerAccountService {
    private apiUrl = 'http://0.0.0.0:3000/playeraccount';

    async getAllPlayerAccounts(): Promise<PlayerAccount[]> {
        const response = await fetch(this.apiUrl);
        if (!response.ok) {
            throw new Error('Failed to fetch player accounts');
        }
        return response.json();
    }

    async getPlayerAccountById(id: string): Promise<PlayerAccount> {
        const response = await fetch(`${this.apiUrl}/${id}`);
        if (!response.ok) {
            throw new Error('Failed to fetch player account');
        }
        return response.json();
    }

    async createPlayerAccount(playerAccount: Omit<PlayerAccount, 'playeraccount_id' | 'created_on' | 'updated_on'>): Promise<PlayerAccount> {
        const response = await fetch(this.apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(playerAccount),
        });
        if (!response.ok) {
            throw new Error('Failed to create player account');
        }
        return response.json();
    }

    async updatePlayerAccount(id: string, playerAccount: Partial<PlayerAccount>): Promise<PlayerAccount> {
        const response = await fetch(`${this.apiUrl}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(playerAccount),
        });
        if (!response.ok) {
            throw new Error('Failed to update player account');
        }
        return response.json();
    }

    async deletePlayerAccount(id: string): Promise<void> {
        const response = await fetch(`${this.apiUrl}/${id}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error('Failed to delete player account');
        }
    }
}
