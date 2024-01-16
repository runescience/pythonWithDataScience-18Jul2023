import csv

file_path = r'../data/map.csv'

def read_map(filename):
  with open(filename, newline='', encoding='utf-8') as f:
    reader = csv.reader(f, delimiter=',')
    tumble = []
    output = []

    for row in reader:
      # remove empty strings from list row
      while ("" in row):
        row.remove("")

      # append each list to the big string
      if len(row) > 0:
        tumble = tumble + row

    my_list = []
    for stz in tumble:
      location, eventz = stz.split(":")
      xx, yy = location.split(",")
      print(xx, ",", yy, "<==", eventz)
      object = {'xx': xx, 'yy': yy, 'event': eventz}
      my_list.append(eventz)
      output.append(object)
    print("=============> list to set", set(my_list))

    # open file in write mode
    with open(r'./data/mapglossary.txt', 'w') as fp:
      for item in sorted(set(my_list)):
        # write each item on a new line
        fp.write("%s\n" % item)
      print('Done')

  return output



list = read_map(file_path)
print(len(list))
