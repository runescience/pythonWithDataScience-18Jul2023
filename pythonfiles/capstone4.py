import sys

"""
Review your current functions and identify repetitive code
Modify your code to replace repetitive sections with loops

Modify your code to remove any hard coded IP addresses and CIDR
Use command line arguments to pass your code the IP Address and CIDR
import the sys module
"""

"""
    Constants
"""
_WORD_LENGTH  = 32
_BYTE_LENGTH = 8
_MASK_VALUE = 0xff
_BIT_ON = '1'
_BINARY_BASE = 2
_SEPARATOR = "."
_USAGE = "execution info:  progname  ip_address as {###.###.###.###}, cidr as {###} "
_IP_ARG = 1
_CIDR_ARG = 2
_IP_PARTS = 4
_NUM_PARAMS = 3

"""
    valid ip, comes in 4 parts separated by dots.
    each part has to be a digit
"""
def validate_ip(string):
    array = string.split(".")

    # not for pieces?  Bail
    if (len(array) != _IP_PARTS):
        return False;

    #parts are not digits? Bail!
    for part in array:
        if not part.isdigit():
            return False
    return True


"""
    check num arguments
    validate rawip
    validate rawcidr
    if we fail we dont return
"""
def validate_args(rawip, rawcidr):

    #check number arg =3
    if len(sys.argv) != _NUM_PARAMS:
        print(_USAGE)
        exit()

    #check the ip, then check the cidr
    if not (validate_ip(rawip) and  rawcidr.isdigit()):
        print(_USAGE)
        exit()
    return


"""
     make an ip number out of a
     standard formated dot separated ip address
"""
def make_ip_num(ip_str):

    # initializing variables
	#convert string to integer.  Dot separated string to integer.
    ip_num = 0 #final inp number
    cntr = 3  #count 3 to 0.
    mylist = ip_str.split('.')

    # a number shifted by zero bits is still a number
    # so the last iteration will be fine
    for item in mylist:
        item = (int(item) << cntr * _BYTE_LENGTH)
        cntr -= 1;
        ip_num += item

    return ip_num


"""
   take an ip_num and make it into
   a dot separated ip readable ip address
   ip_num is a number
"""
def make_ip_str(ip_num):

    # initializing variables
    octets = [0, 0, 0, 0]
    shiftcntr = 3
    itemcntr = 0

    #loops!
    for item in octets:
        item = (ip_num >> _BYTE_LENGTH * shiftcntr) & _MASK_VALUE
        octets[itemcntr] = str(item)
        shiftcntr -= 1
        itemcntr += 1

    #all together now!
    ip_str = _SEPARATOR.join(octets)

    return ip_str

"""
    Calculate the various ip numbers
    ip_address is a string
    cidr is an integer
"""
def calculate(ip_address, cidr):

    # initializing variables
    tupple = [0,0,0,0,0]
    ip_num = make_ip_num(ip_address)
    host_bits = _WORD_LENGTH - int(cidr)

    #create the mask based on the number of host bits
    mask = int('1' * (host_bits), 2)

    net_id = (ip_num >> host_bits) << host_bits
    first_host = net_id + 1
    broadcast = net_id  | mask
    last_host = broadcast - 1

    tupple[0] = ip_num
    tupple[1] = net_id
    tupple[2] = first_host
    tupple[3] = last_host
    tupple[4] = broadcast

    return tupple

def pretty_print(my_tuple):
    #tupple with 5 integers
    #label and string value
    print("[0] ip_num:",    str(my_tuple[0]))
    print("[1] net_id:",    str(my_tuple[1]))
    print("[2] first_host:", str(my_tuple[2]))
    print("[3] last_host:", str(my_tuple[3]))
    print("[4] broadcast:", str(my_tuple[4]))
    return



validate_args(sys.argv[_IP_ARG], sys.argv[_CIDR_ARG] )

pretty_print(calculate(sys.argv[_IP_ARG], sys.argv[_CIDR_ARG] ))
