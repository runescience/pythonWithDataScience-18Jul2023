import pandas as pd
version = "v.0.3.8"
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
#  main()
######################################
univ = pd.read_csv('./data/complexUniv.csv')  #, index_col=6)
parts = pd.read_csv('./data/ship_starts.csv')  #, index_col=6)

print("= = = = = = = = = = = = = =")
partsList = copyUniveToPartsCsv()

partsDf = pd.DataFrame(partsList)
print(partsDf)



