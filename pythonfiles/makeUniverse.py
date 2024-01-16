import datetime
import testrack
import pandas as pd
import traceback

from datetime import datetime
from datetime import date

# from <file> import <method>
from testrack import adder

logDictionary = []
universeDictionary = []
mapHolder = {}

xxx = testrack.adder(6, 1)
print("testrack:adder()", xxx)


######################################
#
######################################
def addLogObject(logObject):
    logDictionary.append(logObject)


######################################
#
######################################
def printUsers(someList, minty):
    print("===============>\nUserList:\n")
    for user in someList:
        print("hello user " + user)


######################################
# arg info:
# you can call with no arg (defaults to all), or use PLANET, PLAYER, CACHE, SHIP, CACHE
######################################
def printLocations(somelist, score="all"):
    print("===============>\nLocations:\n")
    for item in somelist:
        print("in universe:", item['object'], " owner:", item['owner'], " is at ", item['xx'], ",", item['yy'],
              ", long:", item['long'])

    # print("in universe:", type(item['object']), " owner:", type(item['owner']), " is at ", type(item['xx']), ",", type(item['yy']))


def printMapHolder(somelist, score="all"):
    print("========{dictinary}=======>\n mapholder:\n")
    for item in somelist:
        print("in mapholder:", item)

def putItemInMap(key,value):
    mapHolder.update({key: value})



######################################
#
######################################
######################################
#
######################################
def printLog(somelist, score="all"):
    # if you dont include a test arg then you get all of them.
    # other wise you can use debug or transaction

    print("===============>\nlog:\n")
    for item in somelist:
        print("log date:", item['date'], " level:", item['level'], " msg", item['msg'])


######################################
#
######################################
def logMe(level, msg):
    filename = './data/journal.txt'
    fileObject = open(filename, 'a')

    datestring = datetime.today().strftime('%Y-%m-%d %H:%M:%S ')
    logObject = {'date': datestring, 'level': level, 'msg': msg, }
    addLogObject(logObject)

    fileObject.write(datestring + "  Level:" + level + "  Text:" + msg)
    fileObject.write("\n")
    fileObject.close


######################################
#
######################################
def getObjectCount():
    filename = './data/ObjectCounter.txt'
    fileObject = open(filename, 'r')
    ObjectCounter = fileObject.read()
    fileObject.close
    return ObjectCounter


######################################
#
######################################
def getNextObjNumber():
    filename = './data/ObjectCounter.txt'
    fileObject = open(filename, 'r')
    ObjectCounter = fileObject.read()
    fileObject.close
    iobj = int(float(ObjectCounter))

    # print("val iobj", iobj)
    iobj += 1

    fileObject = open(filename, 'w+')
    fileObject.write(str(iobj))
    fileObject.close()
    return str(iobj)


######################################
#
######################################
def makeNewObjectTag(type):
    newObj = type + getNextObjNumber().zfill(8)
    logMe('transaction', "makeNewObject:" + newObj)
    #traceback.print_stack()
    print()

    return newObj


######################################
#
######################################
def makeShip(owner, xx, yy, shipname="none", alliance="none", password="none", owned=False):
    ll = createUniversObject(xx, yy, owner, "SHIP")
    logMe('debug', "makeShip:" + ll['object'])
    ll["shipname"] = shipname
    ll["alliance"] = alliance
    ll["password"] = password
    return ll


######################################
#
######################################
def makePlayer(owner, xx, yy, username='none', password="none", alliance="none", owned=False):
    ll = createUniversObject(00, 00, owner, "PLAY")
    ll["username"] = username
    ll["alliance"] = alliance
    ll["password"] = password
    ll["owned"] = True
    print("===========>\nplayer:", ll)
    return ll


######################################
#
######################################
def makePlanet(owner, xx, yy, planetname="none", biosphere="neutral", race="nonhuman", stance="neutral", owned=False):
    # race:  dogian, feline, human, nonhuman, empty
    # stance friendly, neutral, hostile
    ll = createUniversObject(xx, yy, owner, "PLNT")
    ll["planetname"] = planetname
    ll["biosphere"] = biosphere
    ll["race"] = race
    ll["stance"] = stance
    ll["password"] = 'none'
    ll["owned"] = owned
    return ll


######################################
#
######################################
def makeCache(owner, xx, yy, abbrev="or", quan=0, hidden=False):
    ll = createUniversObject(xx, yy, owner, "CACH")
    ll["abbrev"] = abbrev
    ll["quan"] = quan
    ll["hidden"] = hidden
    return ll


######################################
#
######################################
def makeWormHole(owner, xx, yy, pair):
    ll = createUniversObject(xx, yy, owner, "HOLE")
    ll["owner"] = owner
    ll['pair'] = pair
    return ll


######################################
#
######################################
def makeBeacon(owner, xx, yy, beacon):
    ll = createUniversObject(xx, yy, owner, "BEAC")
    ll["owner"] = owner
    ll['beacon'] = beacon
    return ll


######################################
#
######################################
def makeComponent(owner, xx, yy, abbrev, quan):
    ll = createUniversObject(xx, yy, owner, "COMP")
    ll["abbrev"] = abbrev
    ll["quan"] = quan
    return ll


######################################
#
######################################
def makeHazard(owner, xx, yy, name, value):
    ll = createUniversObject(xx, yy, owner, "HZRD")
    ll["name"] = name
    ll["value"] = value
    return ll


######################################
#
######################################
def createNewObjects(tag, owner, someInt):
    ss = makeNewObjectTag(tag)
    return ss


######################################
#
######################################
def storeInLong(xx, yy):
    zz = (xx << 8) + yy
    # print(zz)
    logMe('transaction', "storeInLong:" + str(xx) + ":" + str(yy))
    return zz


######################################
#
######################################
def unstoreLong(input):
    aa = (input >> 8)
    bb = aa << 8
    cc = input - bb
    print(aa, cc)
    location = {'xx': aa, 'yy': cc}
    logMe('transaction', "unstoreLong:" + str(aa) + ":" + str(cc))
    return location


######################################
# make all entries
######################################
def makeAllEntries():
    ll = makeCache(owner, 1, 5, "pe", 100, )
    universeDictionary.append(ll)
    print("==>print:owned cache!!!:", ll)

    ll = makeCache('PLAY0001', 1, 5, "or", 2900)
    universeDictionary.append(ll)
    # print("print:returned from create:", ll)

    ll = makeCache("MIKE", 123, 45, )
    # print("print:returned from create:", ll)
    universeDictionary.append(ll)

    ll = makePlanet("RedRaven", 88, 15)
    # print("print:returned from create:", ll)
    universeDictionary.append(ll)

    ll = makeHazard("PLAY0001", 7, 7, "ion", 5)
    # print("print:returned from create:", ll)
    universeDictionary.append(ll)

    ll = makeHazard("PLAY0001", 7, 7, "ion", 5)
    # print("print:returned from create:", ll)
    universeDictionary.append(ll)

    ll = makeBeacon("PLAY0001", 90, 9, "farside-1")
    universeDictionary.append(ll)

    ll = makeBeacon("PLAY0001", 8, 8, "Centarii-2")
    universeDictionary.append(ll)

    ll = makeWormHole("PLAY0001", 66, 9, "a")
    universeDictionary.append(ll)

    ll = makeWormHole("PLAY0001", 14, 9, "a")
    universeDictionary.append(ll)

    ll = makeShip("Bizmark", 5, 5)
    # print("print:returned from create:", ll)
    universeDictionary.append(ll)


######################################
#
######################################
def createUniversObject(xx, yy, owner, object):

    universeObject = {'xx': xx, 'yy': yy, 'long': storeInLong(xx, yy), 'owner': owner,
                      'object': makeNewObjectTag(object)}

    mapOBj = {'xx': xx, 'yy': yy, 'long': universeObject['long'], 'object': universeObject['object'] }

    putItemInMap( universeObject['object'],mapOBj)

    logMe('transaction', "createUniversObject" + ":" + owner + ":" + object)

    logMe('debug', "createUniversObject" + ":" + object + " at location:" + str(xx) + "," + str(yy))

    return universeObject


#############################
#############################


######################################
#
#  main():
######################################
users = ['xia', 'don', 'brisbane', 'joelly']
logMe('debug', 'sunshine')

logMe('debug', "startprogram\n===============")
print("there are:", getObjectCount(), " items in the universe")

###########################
# make player, ship, planet
###########################
player = makePlayer('Rich', 1, 5)
universeDictionary.append(player)

owner = player["object"]
print("\ncreated by makePlayer:", owner, "\n")

ll = makeShip(owner, 17, 6, "BlueBird", "none", "password1", True)
# print("print:returned from create:", ll)
universeDictionary.append(ll)

ll = makePlanet(owner, 17, 6, "Appalachia", "neutral", "human", "neutral", True)
universeDictionary.append(ll)

# Create a DataFrame object
df = pd.DataFrame(universeDictionary)
# display
df.index.rename('Ind', inplace=True)
# print(df)

# df_to_locations(universeDictionary)
printLocations(universeDictionary)
# printLog(logDictionary)
print("there are:", getObjectCount(), " items in the universe")

printMapHolder(mapHolder)

ll = list(mapHolder.keys())

print(type(mapHolder.keys()))

print(type(ll))

print ("ll:",ll)

print("getfrom mapholder by key:", mapHolder.get(ll[1]))

