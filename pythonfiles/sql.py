import sqlite3

from sqlite3 import Error


def create_connection(db_file):
    """ create a database connection to a SQLite database """
    conn = None
    try:
        conn = sqlite3.connect(db_file)
        print(sqlite3.version)
    except Error as e:
        print(e)
    finally:
        if conn:
            conn.close()



def create_mem_connection():
    """ create a database connection to a database that resides
        in the memory
    """
    conn = None;
    try:
        conn = sqlite3.connect(':memory:')
        print(sqlite3.version)
    except Error as e:
        print(e)
    finally:
        if conn:
            conn.close()

def add_universe_table(filename):
  # Connecting to sqlite
  # connection object
  connection_obj = sqlite3.connect(filename)
   
  # cursor object
  cursor_obj = connection_obj.cursor()
   
  # Drop the GEEK table if already exists.
  cursor_obj.execute("DROP TABLE IF EXISTS GEEK")
   
  # Creating table
  universe_table = """ CREATE TABLE universe (
              xx INT,
              yy INT,
              xxyy INT,
              Tag CHAR(25) NOT NULL,
              Owner VARCHAR(255) NOT NULL,
              Abbrev CHAR(2),
              Comment VARCHAR(255) NOT NULL
          ); """
  

  cursor_obj.execute(universe_table)
  print("universe_table is Ready")

  # Close the connection
  connection_obj.close()

def add_inventory_table(filename):
  # Connecting to sqlite
  # connection object
  connection_obj = sqlite3.connect(filename)
   
  # cursor object
  cursor_obj = connection_obj.cursor()
   
  # Drop the GEEK table if already exists.
  cursor_obj.execute("DROP TABLE IF EXISTS GEEK")
   
  # Creating table
  inventory_table = """ CREATE TABLE Inventory (
            Tag CHAR(25) NOT NULL,
            Owner VARCHAR(255) NOT NULL,
            Abbrev CHAR(2),
            Quan INT,
            Dam INT,
            xxyy INT,
            Comment VARCHAR(255) NOT NULL
        ); """

  
  cursor_obj.execute(inventory_table)
  print("inventory_table is Ready")
 
  # Close the connection
  connection_obj.close()

def add_player_table(filename):
  # Connecting to sqlite
  # connection object
  connection_obj = sqlite3.connect(filename)
   
  # cursor object
  cursor_obj = connection_obj.cursor()
   
  # Drop the GEEK table if already exists.
  cursor_obj.execute("DROP TABLE IF EXISTS player")
   
  # Creating table
  inventory_table = """ CREATE TABLE player (
            Tag VARCHAR(25) NOT NULL,
            Name VARCHAR(50) NOT NULL,
            Shipname VARCHAR(50) NOT NULL,
            ShipTag VARCHAR(25) NOT NULL,
            password VARCHAR(24) NOT NULL,
            secretword VARCHAR(24) NOT NULL,
            credits INT,
            status BOOLEAN,
            Comment VARCHAR(255) NOT NULL
        ); """

  cursor_obj.execute(inventory_table)
  print("inventory_table is Ready")
 
  # Close the connection
  connection_obj.close()  


def add_hazzard_table(filename):
  # Connecting to sqlite
  # connection object
  connection_obj = sqlite3.connect(filename)
   
  # cursor object
  cursor_obj = connection_obj.cursor()
   
  # Drop the GEEK table if already exists.
  cursor_obj.execute("DROP TABLE IF EXISTS hazzard")
   
  # Creating table
  inventory_table = """ CREATE TABLE hazzard (
            Abbrev CHAR(4),
            value INT,
            name VARCHAR(25) NOT NULL,
            Comment VARCHAR(255) NOT NULL
        ); """

  cursor_obj.execute(inventory_table)
  print("inventory_table is Ready")
 
  # Close the connection
  connection_obj.close()  


def add_wormhole_table(filename):
  # Connecting to sqlite
  # connection object
  connection_obj = sqlite3.connect(filename)
   
  # cursor object
  cursor_obj = connection_obj.cursor()
   
  # Drop the GEEK table if already exists.
  cursor_obj.execute("DROP TABLE IF EXISTS wormhole")
   
  # Creating table
  inventory_table = """ CREATE TABLE wormhole (
            Tag CHAR(25) NOT NULL,
            Owner VARCHAR(25) NOT NULL,
            OtherTag CHAR(25) NOT NULL,
            alliance VARCHAR(25) NOT NULL, 
            Abbrev CHAR(2),
            Comment VARCHAR(255) NOT NULL
        ); """

  cursor_obj.execute(inventory_table)
  print("inventory_table is Ready")
 
  # Close the connection
  connection_obj.close()  




def drop_map_table(filename):
  #Connecting to sqlite
  conn = sqlite3.connect(filename)
  
  #Creating a cursor object using the cursor() method
  cursor = conn.cursor()
  
  #Doping EMPLOYEE table if already exists
  cursor.execute("DROP TABLE IF EXISTS map")
  print("Table dropped... ")
  
  #Commit your changes in the database
  conn.commit()
  
  #Closing the connection
  conn.close()

def drop_player_table(filename):
  #Connecting to sqlite
  conn = sqlite3.connect(filename)
  
  #Creating a cursor object using the cursor() method
  cursor = conn.cursor()
  
  #Doping EMPLOYEE table if already exists
  cursor.execute("DROP TABLE IF EXISTS player")
  print("Table dropped... ")
  
  #Commit your changes in the database
  conn.commit()
  
  #Closing the connection
  conn.close()
  
def drop_universe_table(filename):
  #Connecting to sqlite
  conn = sqlite3.connect(filename)
  
  #Creating a cursor object using the cursor() method
  cursor = conn.cursor()
  
  #Doping EMPLOYEE table if already exists
  cursor.execute("DROP TABLE IF EXISTS universe")
  print("Table dropped... ")
  
  #Commit your changes in the database
  conn.commit()
  
  #Closing the connection
  conn.close()

    
def drop_hazzard_table(filename):
  #Connecting to sqlite
  conn = sqlite3.connect(filename)
  
  #Creating a cursor object using the cursor() method
  cursor = conn.cursor()
  
  #Doping EMPLOYEE table if already exists
  cursor.execute("DROP TABLE IF EXISTS hazzard")
  print("Table dropped... ")
  
  #Commit your changes in the database
  conn.commit()
  
  #Closing the connection
  conn.close()



  

def drop_inventory_table(filename):
  #Connecting to sqlite
  conn = sqlite3.connect(filename)
  
  #Creating a cursor object using the cursor() method
  cursor = conn.cursor()
  
  #Doping EMPLOYEE table if already exists
  cursor.execute("DROP TABLE IF EXISTS Inventory")
  print("Table dropped... ")
  
  #Commit your changes in the database
  conn.commit()
  
  #Closing the connection
  conn.close()

    


def drop_wormhole_table(filename):
  #Connecting to sqlite
  conn = sqlite3.connect(filename)
  
  #Creating a cursor object using the cursor() method
  cursor = conn.cursor()
  
  #Doping EMPLOYEE table if already exists
  cursor.execute("DROP TABLE IF EXISTS wormhole")
  print("Table dropped... ")
  
  #Commit your changes in the database
  conn.commit()
  
  #Closing the connection
  conn.close()

    





filename = r"./data/pythonsqlite.db"
if __name__ == '__main__':
    create_connection(filename)

if __name__ == '__main__':
    create_mem_connection()


drop_map_table(filename)
drop_universe_table(filename)
add_universe_table(filename)

drop_inventory_table(filename)
add_inventory_table(filename)

drop_hazzard_table(filename)
add_hazzard_table(filename)

drop_player_table(filename)
add_player_table(filename)

drop_wormhole_table(filename)
add_wormhole_table(filename)












