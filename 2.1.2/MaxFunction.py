from random import uniform, random
import math
import time


def anneal(x,y):
    old_cost = function(x,y)
    T = 1.0
    T_min = 0.00001
    alpha = 0.9
    maximo=0
    while T > T_min:
        i = 1
        while i <= 1000:
            x_new=x+uniform(-1,1)*1
            y_new=y+uniform(-1,1)*1
            new_cost = function(x_new,y_new)
            delta_e = new_cost-old_cost
            if delta_e>0:
                #print("to no if")
                x=x_new
                y=y_new
                old_cost = new_cost
            else:  
                ap = acceptance_probability(old_cost, new_cost, T)
                rand = random()
                if (ap > rand and delta_e<-0.00000001):
                    new_cost = function(x_new, y_new)
                    old_cost = new_cost
                    x = x_new
                    y = y_new
            i += 1
            if old_cost>maximo:
                maximo = old_cost
                x_max = x
                y_max = y
        T = T*alpha
    return x, y, old_cost

def function(x,y):
    return (4*(math.exp(-(x**2+y**2)))+
                math.exp(-((x-5)**2+(y-5)**2))+
                math.exp(-((x+5)**2+(y-5)**2))+
                math.exp(-((x-5)**2+(y+5)**2))+
                math.exp(-((x+5)**2+(y+5)**2)))

def acceptance_probability(old_cost, new_cost, T):
    return math.exp((new_cost-old_cost)/T)

x=uniform(-1,1)*20
y=uniform(-1,1)*20
print("Resultado: ")
print(anneal(x,y)[2])
time.sleep(5)