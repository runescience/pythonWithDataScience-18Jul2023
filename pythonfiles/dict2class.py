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
  #print("===> length of ftc dict full:", len(ftc_dict_full))

  startlist =[]
  print("\n======for keys in dict=========>")
  for key in ftc_dict_full:
    obj = ftc_dict_full[key]
    if obj["startquan"]>0:
      rr={ 'abbr':key, 'quan': obj["startquan"], 'name': obj["name"], 'hp': obj["hp"]}
      print("rr:",rr)
      startlist.append(rr)

  return startlist


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

  #print_dict_keys( ftc_dict_full)
  print("$$$"*3)
  return ftc_dict_full


class Dict2Class(object):
  # Turns a dictionary into a class
  def __init__(self, my_dict):
    for key in my_dict:
      setattr(self, key, my_dict[key])
      print(key,my_dict[key])


# Driver Code
if __name__ == "__main__":
  rr = readtopandas(file_path)
  ftc_dict_full = rr
  startlist = get_startquant()

  result = Dict2Class(ftc_dict_full)

  # printing the result
  print("After Converting Dictionary to Class : ")

  print(result.AD)
  print(type(result))
