import sys
import random
import string
import numpy as np
#from random import randrange


### set sampling parameters ###
N = 100  ## number of records
if len(sys.argv)>1: N = int(sys.argv[1])

### rate of target (fixed at 25%) ###
rate = 0.25

### print header and simulate data ###
print("id,target,f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12")
for i in range(0,N):
  f = np.random.normal(0, 3, 12)
  id = ''.join(random.choice(string.digits + string.ascii_uppercase) for _ in range(15))
  print("%s,%d,%s" % ( id,
    np.random.choice(np.arange(0, 2), p=[1-rate,rate]),
    ",".join(map(str,f))))
