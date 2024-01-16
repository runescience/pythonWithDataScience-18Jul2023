class Start_objects(object):
    """ Here we will see the actual logic behind various pieces of Python
    language e.g. instances, variables, method and @property etc.
    Also, we will see the combine usage of these pieces to complete a
    design with Agile methodology.
    """
    

    def printMe(self):
      print("abbrev:", self.abbrev, " name:", self.name, " cost:", 
            self.cost, " startquan:", self.startquan)
  
    def __init__(self, abbrev, name, cost, startquan):
        """ init is not the constructor, but the initializer which
        initialize the instance variable

        self : is the instance

        __init__ takes the instance 'self' and populates it with the radius,
        metal, date etc. and store in a dictionary.

        self.radius, self.metal etc. are the instance variable which
        must be unique.
        """

        self.abbrev = abbrev
        self.name = name
        self.cost = cost
        self.startquan = startquan
      

dict_from_list={}

columns=["abbrev", "name", "cost", "startquan"]

rows1=["pe","people",3, 200]
zipped1 = zip(columns,rows1)
zipdict1 = dict(zipped1)
dict_from_list["pe"] = zipdict1


while True:
    try:
        tup=next(zipped1)
        print(tup[0],"->", tup[1])
    except StopIteration:
        break



rows2=["sh","shields",1000, 1]
zipped2 = zip(columns,rows2)
zipdict2 = dict(zipped2)
dict_from_list["sh"] = zipdict2

rows3=["en","engines",10000, 3]
zipped3 = zip(columns,rows3)
zipdict3 = dict(zipped3)
dict_from_list["en"] = zipdict3



print ("dict_from_list:",dict_from_list)
print("dict len:", len(dict_from_list))
print("dict keys:", dict_from_list.keys())

print("dict values:", dict_from_list["pe"].values())
print("dict values:", dict_from_list["sh"].values())
print("dict values:", dict_from_list["en"].values())



list = []
indexes = ["pe","sh","en"]

for item in dict_from_list:
  print("==>item", item)
  list.append( Start_objects( *dict_from_list["pe"].values()) )

for item in list:
  item.printMe()

#print("==>list.append:", *dict_from_list["AA"].values())
#list.append( Start_objects( *dict_from_list["AA"].values()) )
#list.append( Start_objects( dict_from_list["BB"].values()) )
#list.append( Start_objects( dict_from_list["CC"].values()) )

ll = dict_from_list["CC"]
print ("\ntype:",type(ll))
print ("\n\nll.keys():",ll.keys())




