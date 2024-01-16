
player_map = {}

file_path = r'../data/map.csv'


def get_player_info(phrase, maxlen):
  data = ""
  while (len(data) > maxlen or len(data) < 1):
    data = input(phrase + ":")
    if len(data) <= maxlen:
      return data


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




# Define the actions for each choice we want to offer.
def view_player():
  username = get_player_info("get player info", 25)
  ll = player_map.get(username, 'NO KEY')
  print("\n",username,":",ll,"\n")

def add_ship(xx,yy,owner,tag):
  for items in start_items:
    print (items)



def add_player():
  print("\nHere are some running shoes. Run fast!\n")
  username = get_player_info("username", 25)
  shipname = get_player_info("shipname", 25)
  race = get_player_info("race(hum,elf,dwa,hob,oth:", 3)
  xx = get_player_info("xx:", 5)
  yy = get_player_info("yy:", 5)

  add_ship(xx,yy,"owner1234","tag1234")

  player = {'username': username, 'shipname': shipname, 'race': race, 'xx': xx, 'yy': yy}

  player_map.update({username: player})

  return player


def remove_player():
  print("\nHere's a map. Can you leave a trip plan for us?\n")


def process_turn():
  print("process turn")


def build_map():
  print("build map")


def print_player_map():
  print("========{",len(player_map), "users in play}=======>\n")
  for item in player_map:
    print("in player_map:", item)

def show_map():
  print("========{ heres the map }=======>\n")
  print("========{ . . . . . . }=======>\n")
  print("========{ . . . . o . }=======>\n")
  print("========{ . . . . . . }=======>\n")



# Give the user some context.
print("\n========================================================")
print("\nWelcome to the nature center. What would you like to do?")

# Set an initial value for choice other than the value for 'quit'.
choice = ''

# Start a loop that runs until the user enters the value for 'quit'.


while choice != 'q':
  # Give all the choices in a series of print statements.
  print("\n[1] view player OR [2] add player.")
  print("[3] remove player OR [4] list players")
  print("[5] process turn OR  [6] build map")
  print("[7] show map")

  print("[q] Enter q to quit.")

  # Ask for the user's choice.
  choice = input("\nWhat would you like to do? ")

  # Respond to the user's choice.
  if choice == '1':
    view_player()
  elif choice == '2':
    print(add_player())
  elif choice == '3':
    remove_player()
  elif choice == '4':
    print_player_map()
  elif choice == '5':
    build_map()
  elif choice == '6':
    build_map()
  elif choice == '5':
    process_turn()
  elif choice == '7':
    show_map()

  elif choice == 'q':
    print("\nThanks for playing. See you later.\n")
  else:
    print("\nI don't understand that choice, please try again.\n")

# Print a message that we are all finished.
print("Thanks again, bye now.")

