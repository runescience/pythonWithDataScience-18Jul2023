import pandas as pd
import numpy as np
import csv

version = "v.0.3.10"



######################################
# makeShipParts 
# input: startItems - df,  owner - string
######################################
def makeShipParts(startItems, owner):
  ll=[]
  for row in startItems.itertuples():
    univ = { 'owner':owner, 'abbrev':row.Abbr, 'quan':row.Start, 'hp':row.HP, 'dam':0}
    ll.append(univ)    
  return ll

######################################
# copyUniveToPartsCsv output 
######################################
def copyUniveToPartsCsv():
  # create parts list as a subset of   
  startItems = univ[['Abbr', 'Start', 'HP', 'Name' ]].copy()

  #keep items taht are greater than 0 Start
  startItems = startItems[startItems['Start'] != 0]

  # add columnName to the index
  startItems.index.rename('Ind', inplace=True)
  startItems.to_csv("./data/ship_starts.csv")

  #Make array of ship Parts
  xxList =makeShipParts(startItems, "SHIP0003")
  return xxList


######################################
# copyUniveToPartsCsv output 
######################################
def copyUniveToPartsList(owner="SHIP000XXXX"):
  # create parts list as a subset of   
  startItems = univ[['Abbr', 'Start', 'HP', 'Name' ]].copy()

  #keep items taht are greater than 0 Start
  startItems = startItems[startItems['Start'] != 0]

  # add columnName to the index
  startItems.index.rename('Ind', inplace=True)
  startItems.to_csv("./data/ship_starts.csv") #, mode='a', header=False)

  ll=[]
  for row in startItems.itertuples():
    rr = { 'owner':owner, 'abbrev':row.Abbr, 'quan':row.Start, 'hp':row.HP, 'dam':0}
    ll.append(rr)    
  return ll


######################################
#  main()
######################################
univ = pd.read_csv('./data/complexUniv.csv')  #, index_col=6)
parts = pd.read_csv('./data/ship_starts.csv')  #, index_col=6)

print("= = = = = = = = = = = = = =")
## get the new ship part list
## turn it into a data frame
## move framework parts to inventory file

partsList = copyUniveToPartsList("SHIP00007755") # or: copyUniveToPartsCsv()
partsList2 = copyUniveToPartsList("SHIP00004698") # or: copyUniveToPartsCsv()
partsList3 = copyUniveToPartsList("SHIP00009025") # or: copyUniveToPartsCsv()
partsList4 = copyUniveToPartsList("SHIP00001281") # or: copyUniveToPartsCsv()

print(partsList,"\n--------------")
print(partsList2,"\n--------------")
print(partsList3,"\n--------------")
print(partsList4,"\n--------------")



# concatenating df1 and df2 along rows
#vertical_concat = pd.concat([df1, df2], axis=0)
 
# concatenating df3 and df4 along columns
#horizontal_concat = pd.concat([df3, df4], axis=1)

partsDf = pd.DataFrame(partsList)

partsDf = pd.concat([partsDf, pd.DataFrame(partsList2)], axis=0)
partsDf = pd.concat([partsDf, pd.DataFrame(partsList3)], axis=0)
partsDf = pd.concat([partsDf, pd.DataFrame(partsList4)], axis=0)

partsDf.to_csv("./data/inventory.csv", mode='a', index=False, header=False)

#print("\n----output---\n",partsDf,"\n--------------")

df = pd.read_csv('./data/UniverseMap2.csv', header=None, 
                 index_col=False) #, squeeze=True)#.to_dict()

df.fillna('', inplace=True)
#print(df)
##------------------------------------

df.to_csv("./data/map.csv",header=False,index=False)

exit(33)

##------------------------------------




#numpy_array = df.to_numpy()
#print(type(numpy_array))
#print(numpy_array)

exit(33)

#substring = 'belt'
#mask = dict_from_csv.applymap(lambda x: substring in x.lower() if #isinstance(x,str) else False).to_numpy()
#indices = np.argwhere(mask) 
#print("\n----output---\n",indices,"\n--------------")




