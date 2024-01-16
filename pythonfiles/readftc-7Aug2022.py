import csv
import pandas as pd

file_path = r'../data/ftc5data.csv'

parvevous = {}


def readtopandas(filename):
  pandafw = pd.read_csv(filename, index_col='abbr', skipinitialspace=True)
  pandafw.fillna('', inplace=True)
  print(pandafw, "=============================================================")

  df = pandafw.to_dict(orient='index')

  mydict = {}
  mydict = df  # .copy()
  # print (mydict)
  print(mydict.keys())

  print(mydict.get("CB"), "\n\n")

  ll = mydict.get("T1")
  print(ll['name'], "lets us do a  one to beam up")

  '''parvevous = df.to_dict()
  #print("parvevous:",parvevous)
  for row in parvevous:
    print("row:",row)
  print("=======>:", row)
  '''


def readtocsv(filename):
  with open(file_path) as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    for row in csv_reader:
      print(row)
      line_count += 1

    print(f'Processed {line_count} lines.')


def read_ftc(filename):
  with open(filename, newline='', encoding='utf-8') as f:
    reader = csv.reader(f, delimiter=',')
    tumble = []
    output = []

    for row in reader:
      # remove empty strings from list row
      while ("" in row):
        row.remove("")

      print("row:", row)

      output.append(row)

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


# list = read_ftc(file_path)

readtopandas(file_path)

# readtocsv(file_path)


# print("ii:",len(parvevous))
