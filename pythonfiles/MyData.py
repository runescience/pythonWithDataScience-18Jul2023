import sqlite3

class MyData:
    def __init__(self, version, max, energy, cost, hp, startquan, tech, abbr, name, comment):
        self.version = version
        self.max = max
        self.energy = energy
        self.cost = cost
        self.hp = hp
        self.startquan = startquan
        self.tech = tech
        self.abbr = abbr
        self.name = name
        self.comment = comment
        
    def print_data(self):
        print("Version:", self.version)
        print("Max:", self.max)
        print("Energy:", self.energy)
        print("Cost:", self.cost)
        print("HP:", self.hp)
        print("Startquan:", self.startquan)
        print("Tech:", self.tech)
        print("Abbr:", self.abbr)
        print("Name:", self.name)
        print("Comment:", self.comment)
        
class Database:
    def __init__(self, db_name):
        self.db_name = db_name
        
    def insert_data(self, data):
        conn = sqlite3.connect(self.db_name)
        c = conn.cursor()
        
        c.execute('CREATE TABLE IF NOT EXISTS mytable (version TEXT, max TEXT, energy TEXT, cost TEXT, hp TEXT, startquan TEXT, tech TEXT, abbr TEXT PRIMARY KEY, name TEXT, comment TEXT)')
        
        query = f"INSERT INTO mytable VALUES ('{data.version}', '{data.max}', '{data.energy}', '{data.cost}', '{data.hp}', '{data.startquan}', '{data.tech}', '{data.abbr}', '{data.name}', '{data.comment}')"
        
        c.execute(query)
        conn.commit()
        conn.close()


    def read_file(filename):
        conn = sqlite3.connect("../data/mydatabase.db")
        c = conn.cursor()
        
        with open(filename, 'r') as file:
            lines = file.readlines()
            
            for line in lines:
                values = line.strip().split(',')
                data = MyData(*values)
                try:
                    c.execute(f"INSERT INTO mytable VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
                              (data.version, data.max, data.energy, data.cost, data.hp, data.startquan, data.tech, data.abbr, data.name, data.comment))
                    conn.commit()
                except Error as e:
                    print(f"Error inserting data: {e}")
        
        conn.close()
    


class MyDataDriver:
    def __init__(self):
        self.db = Database("mydatabase.db")
    
    def write_sample_data(self):
        sample_data = [
            MyData("1.0", "10", "100", "50", "200", "5", "Tech 1", "AB1", "Sample 1", "Sample Comment 1"),
            MyData("2.0", "5", "50", "20", "100", "3", "Tech 2", "AB2", "Sample 2", "Sample Comment 2"),
            MyData("3.0", "8", "80", "40", "150", "4", "Tech 3", "AB3", "Sample 3", "Sample Comment 3")
        ]
        
        for data in sample_data:
            self.db.insert_data(data)
            
    def read_sample_data(self):
        conn = sqlite3.connect("mydatabase.db")
        c = conn.cursor()
        
        c.execute("SELECT * FROM mytable")
        rows = c.fetchall()
        
        for row in rows:
            print(row)
        
        conn.close()



my_data_driver = MyDataDriver()
my_data_driver.write_sample_data()
my_data_driver.read_sample_data()