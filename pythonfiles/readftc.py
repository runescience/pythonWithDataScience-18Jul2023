import csv
import pandas as pd

file_path = r'./data/ftc5data.csv'

parvevous = {}

ftc_dict_full = {}

def write_csv():
  import csv

  header = ['name', 'area', 'country_code2', 'country_code3']
  data = ['Afghanistan', 652090, 'AF', 'AFG']

  with open('countries.csv', 'w', encoding='UTF8') as f:
    writer = csv.writer(f)

    # write the header
    writer.writerow(header)

    # write the data
    writer.writerow(data)


def get_startquant():
  #----- print the dictinary, look at the keys.

  #first entry is a header abbr,name,hp,max,comment
  #so skip it
  print(type(ftc_dict_full))
  print("===> length of ftc dict full:", len(ftc_dict_full))

  startlist =[]
  print("\n======for keys in dict=========>")
  for key in ftc_dict_full:
    obj = ftc_dict_full[key]
    # print("obj:",obj)
    #for key in obj:
    #print(key,":",obj["startquan"])
    if obj["startquan"]>0:
      rr={ 'abbr':key, 'quan': obj["startquan"], 'name': obj["name"], 'hp': obj["hp"]}
      print("rr:",rr)
      startlist.append(rr)




    #if val.get['startquan'] > 0 :
    #  print(key, " start value = ", val.get("startquan"))


  '''
  print(ll)
  print(ll.keys())
  print(ll.get("CB"), "\n\n")

  ftc = ll.get("T1")
  print(ftc['name'], "lets us do a  one to beam up")
  '''

def for_rec_in_dict():
  print("\n======(1 recs in dict, must be key)=====>")
  ii = 0
  print("\n\n======rec in ftc_dict_full=========>")
  for rec in ftc_dict_full:
    ii+=1
    if ii >5:
      break
    print("rec", rec, " rec", type(rec) )
    val = rec
    print(type(val), val)

  print("\n\n======( 2)for keys in dict=========>")
  ii = 0
  for key in ftc_dict_full:
    print(key, '->', ftc_dict_full[key])

  print("\n\n======( 3) items in dict=========>")
  ii = 0
  for item in ftc_dict_full.items():
    ii+=1
    if ii > 3:
      break
    print(item)

  print("\n\n======( 4) key value in dict=========>")
  ii = 0
  for key, value in ftc_dict_full.items():
    ii+=1
    if ii > 3:
      break
    print(key, '->', value)


def print_dict_keys(ll):
  #----- print the dictinary, look at the keys.
  print(ll)
  print(ll.keys())
  print(ll.get("CB"), "\n\n")

  ftc = ll.get("T1")
  print(ftc['name'], "lets us do a  one to beam up")





def readtopandas(filename):


  # get the csv into a framework and fill the Nans with blanks.
  pandafw = pd.read_csv(filename, index_col='abbr', skipinitialspace=True)
  pandafw.fillna('', inplace=True)

  #convert it to a dictionary, with an index.
  df = pandafw.to_dict(orient='index')
  ftc_dict_full = df  # .copy()

  print_dict_keys( ftc_dict_full)
  print("$$$"*3)
  return ftc_dict_full


def readtocsv(filename):
  with open(file_path) as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    for row in csv_reader:
      print("print row==>",row)
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

rr = readtopandas(file_path)
ftc_dict_full = rr
get_startquant()


# readtocsv(file_path)


# print("ii:",len(parvevous))
