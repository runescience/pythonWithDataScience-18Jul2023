
import { v4 as uuidv4 } from "uuid";

export function generateShortId() {
    return uuidv4().substring(0, 8);
}

export function generateId() {
    return uuidv4().substring(0, 8);
}
