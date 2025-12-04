
export interface Inventory {
  inventory_id?: string;
  availableSpaces?: number;  //1: avail
  Cost?: number; //. 
  Energy?: number;  //3 
  Tech?: number; //4
  Advancedgame?: number; //5 
  Max?: number; //6
  Startquan?: number; //7
  Name?: string; //8
  Abbrev?: string; //9
  Comment?: string; //10
  Group?: string;
  Bonus?: string;
  created_on?: Date;
  updated_on?: Date;
}
