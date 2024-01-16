import csv
import json
import pprint
import collections
import ast


ddict = {}
with open('../data/ftc5data.csv', 'r') as file:
  reader = csv.DictReader(file, skipinitialspace=True)
  
  ddict = list(reader)
  
with open('hal.json', 'w') as f:
  json.dump(ddict, f)

#   # print(ddict['name'])
#   # print(ddict['name'].split(' '))
