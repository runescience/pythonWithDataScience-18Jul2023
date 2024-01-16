from datetime import datetime
from datetime import date
import datetime 
# import adder
#import testrack

#from <file> import <method>
#from testrack import adder


mydebug = True

if mydebug == True:
  print("Debug:aaa")

if mydebug == True:
  print("Debug:bbb")

if mydebug == True:
  print("Debug:ccc")

if mydebug == True:
  print("Debug:ddd")

if mydebug == True:
  print("Debug:001")

# xxx = adder.adder(6, 1)
# print ("xxx", xxx)

if mydebug == True:
  print("Debug:002")

def getObjectCount():
  filename = '../data/ObjectCounter.txt'
  fileObject  = open(filename,'r')
  ObjectCounter = fileObject.read()
  fileObject.close
  return ObjectCounter

def getNextObjNumber():
  filename = '../data/ObjectCounter.txt'
  fileObject  = open(filename,'r')
  ObjectCounter = fileObject.read()
  fileObject.close
  iobj = int(float(ObjectCounter))
  #print("type iobj", type(iobj))
  #print("val iobj", iobj)
  iobj+=1
  #print("val iobj", iobj)

  fileObject  = open(filename,'w+')
  fileObject.write(str(iobj))
  fileObject.close()
  return str(iobj)


def makeNewObjectTag(type):
  newObj = type+getNextObjNumber().zfill(8)
  logMe('transaction', "makeNewObject:" + newObj )
  return newObj


def makeShip(owner,xx,yy):
  ll = createUniversObject(xx,yy,owner,"SHIP")
  logMe('debug', "makeShip:"+ll['object'] )
  return ll


def makePlanet(owner,xx,yy):
  ll = createUniversObject(xx,yy,owner,"PLANET")
  return ll

def makePlayer(owner,xx,yy):
  ll = createUniversObject(00,00,owner,"PLAYER")
  print("player:",ll)
  return ll


def makeCache(owner,xx,yy):
  ll = createUniversObject(xx,yy,owner,"CACHE")
  return ll


def makeWormHole(owner,xx,yy):
  ll = createUniversObject(xx,yy,owner,"HOLE")
  return ll


def makeComponent(owner,xx,yy):
    ll = createUniversObject(xx,yy,owner,"COMP")
    return ll

def createNewObjects(tag,owner,someInt):
  ss = makeNewObjectTag(tag)
  return ss

def storeInLong(xx,yy):
  zz = (xx << 8) + yy
  #print(zz)
  logMe('transaction', "storeInLong:" + str(xx) + ":"+ str(yy) )
  return zz

def unstoreLong(input):
  aa = (input >> 8)
  bb = aa << 8
  cc = input - bb
  print (aa,cc)
  location = {'xx':aa, 'yy':cc}
  logMe('transaction', "unstoreLong:" + str(aa) + ":"+ str(cc) )
  return location

def printUsers(someList):
  for user in someList:
    print("hello user "+ user)

def printLocations(somelist):
  for item in somelist:
    print("in universe, item:", item['object'], " owner:", item['owner'], " is at ", item['xx'], ",", item['yy'])
    
    #stuff = "in universe, item:"+ item['object']+ " owner:"+ item['owner']+ " is at ", item['xx'], ",", item['yy']        
    #print("stuff type:", stuff)
    #logMe("debug",stuff)


def logMe(level, text):
  filename = 'journal.txt'
  fileObject  = open(filename,'a')

  datestring = datetime.datetime.today().strftime('%Y-%m-%d %H:%M:%S ')
  
  fileObject.write(datestring + "  Level:" + level + "  Text:" + text)
  fileObject.write("\n")
  fileObject.close


def createUniversObject(xx,yy,owner,object):
  universeObject = {'xx':xx, 'yy':yy, 'long':storeInLong(xx,yy), 'owner':owner, 'object':makeNewObjectTag(object) }

  logMe('transaction', "createUniversObject" + ":" + owner + ":"+ object)
  logMe('debug', "createUniversObject" + ":"  + object + " at location:" + str(xx) + ","+ str(yy))
  return universeObject




if mydebug == True:
  print("Debug:003")


users = ['xia', 'don', 'brisbane', 'joelly' ]
logMe('debug', 'sunshine')


if mydebug == True:
  print("Debug:004")

#print ("iobj:", getNextObjNumber().zfill(8)) #iobj: 00000075


# printUsers(users)
# print("\n")
# users.sort
# print("Sorted the names. Here they are:\n")
# printUsers(users)
logMe('debug', "startprogram\n===============" )

print("there are:",getObjectCount()," items in the universe")

if mydebug == True:
  print("Debug:005")

jj = []
ll = makePlayer('Rich',1,5)
jj.append(ll)
#print("print:returned from create:", ll)

owner = ll["object"]

ll = makeCache(owner,1,5)
jj.append(ll)
print("==>print:owned cache!!!:", ll)


ll = makeCache('PLAYER0001',1,5)
jj.append(ll)
#print("print:returned from create:", ll)

ll = makeShip(owner,17,6 )
#print("print:returned from create:", ll)
jj.append(ll)


ll = makeCache("MIKE",12,45)
#print("print:returned from create:", ll)
jj.append(ll)
ll = makePlanet("RedRaven",85,15)
#print("print:returned from create:", ll)
jj.append(ll)

ll = makeShip("Bizmark",5,5)
#print("print:returned from create:", ll)
jj.append(ll)

printLocations(jj)

print("there are:", getObjectCount()," items in the universe")


