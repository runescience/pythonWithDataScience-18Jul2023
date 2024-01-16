from csv import reader


file_name =  r'./data/ftc5data.csv'

with open(file_name, "r") as csv_file:
    csv_reader = reader(csv_file)
    header = next(csv_reader)

    #print("Header:")
    #print(", ".join(header))
    #print("Values:")

    for row in csv_reader:
       print("row:", row)

    #setattr( obj,header,key)