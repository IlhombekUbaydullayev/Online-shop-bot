class b:
    HEADER = '\033[95m'
    OKBLUE = '\033[94m'
    OKCYAN = '\033[96m'
    OKGREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'

print(b.HEADER + "Salom")
print(b.OKBLUE + "Salom")
print(b.OKCYAN + "Salom")
print(b.OKGREEN + "Salom")
print(b.UNDERLINE + "Salom")
print(b.WARNING + "Salom")
print(b.ENDC + "Salom")
print(b.BOLD + "Salom")









# 7 ni 8 ga bo’lgandagi butun qismini toping 

# def bolish(x,y) :
#     return x//y 

# print(b.OKBLUE+"\nbutun qismi : ",b.UNDERLINE,bolish(7,8),'\n',b.FAIL)
    




                  # 8-GURUH  


# 37 ni 6 ga bo'lganda qancha qoldiq qoladi

# def residual(x,y) :
#      return print('\nNum : ',x%y)

# print(b.OKBLUE)
# residual(37,6)
# print(b.ENDC)
    





# (string metodlaridan foydalaning) (kocha, mahalla, tuman, viloyat) qiymatini
# foydalanuvchidan so'rang va quydagi ko’rinishda konsolga chiqazing:
# “Samarqand viloyati, Narpay tumani, Bog’iston mahallasi, Gulobod ko’chasi”
    
# def reference() : 
#     viloyat = input("\nViloyatni kiriting : ")
#     tuman = input("Tumanni kiriting : ")
#     mahalla = input("Mahallani kiriting : ")
#     kocha = input("ko`chani kiriting : ")
#     print(b.OKBLUE)
#     print(f'\n "{viloyat} viloyati, {tuman} tumani, {mahalla} mahallasi, {kocha} ko`chasi"')
#     print(b.ENDC)

# reference()
    



# 120 dan 1200 gacha bo'lgan juft sonlar ro'yxatini tuzing

# def couple() :
#     juft = []
#     for i in range(120,1201) : 
#          if(i % 2 == 0) : juft.append(i)
#     print('\n',juft)

# print(b.OKCYAN)
# couple()
# print(b.ENDC)





# (for sikl operatoridan foydalaning) sonlar = [2, 1, 2, 3, 4] berilgan ro’yhatda
# toq sonlar miqdorini aniqlovchi dastur tuzing

# def odd_numbers() :
#      sonlar = [2, 1, 2, 3, 4]
#      count = 0
#      for i in sonlar : 
#           if(i % 2 == 1) : 
#                count = count+1
#      print('\n',f'Toq sonlar : {count}')
# print(b.OKCYAN)     
# odd_numbers()
# print(b.ENDC)




# Foydalanuvchidan 2 ta int tipiga oid son kiritishini so’rang. Agar kiritilgan
# sonlardan biri 6 ga teng bo’lsa ekranga “Ajoyib raqam” aks holda “Kiritilgan
# sonlar Ichida 6 mavjud emas” degan habar chiqasin

# def perfect_number(x,y) :
#      if y == 6 or x == 6 : 
#            return "\n Ajoyib raqam"
#      else : return "\nKiritilgan sonlar Ichida 6 mavjud emas"

# num = -1
# while num != 0 : 
#      print(b.OKGREEN)
#      print(perfect_number(int(input("1-sonni kiriting : ")),int(input("2-sonni kiriting : "))))
#      print(b.ENDC)
     


# (Dictionary dan foydlaning) otam degan lug'at yarating va lug'atga shu inson
# haqida kamida 3 ta m'alumot kiriting (ismi, tu'gilgan yili, shahri, manzili va
# hokazo). Lug'atdagi ma'lumotni matn shaklida konsolga chiqaring : 
# Otamning ismi Mavlutdin, 1954-yilda, Samarqand viloyatida tug'ilgan

# def dictionary(name) :
#      otam = {'ismi' : input("\nIsmni kiriting : "),
#             'tugilgan_yili' : input("Tug`ilgan yilini kiriting : "),
#             'shahri' : input('Tug`ilgan viloyatini kiriting : '),
#             'manzili' : input('Manzilini kiriting : ')}
#      print('\n',f'{name}ning ismi {otam["ismi"]}, {otam["tugilgan_yili"]}-yilda,'+
#             f'{otam["shahri"]} viloyatining {otam["manzili"]} tumanida tugilgan')
# print(b.OKCYAN)
# dictionary("Otam")
# print(b.ENDC)


# Foydalanuvchidan x va y sonlarini olib, kiritilgan sonlarni toq ekanligiga tekshirib konsolga chiqaruvchi funksiya yozing.
    
# def odd_or_pair(x,y) : 
#      if x%2 == 1 and y%2 == 1 : 
#           return " toq sonlar"
#      elif x%2 == 0 and y%2 != 0 : 
#           return f"{x} juft son, {y} toq son"
#      else : 
#           return f"{y} juft son, {x} toq son"
# n = 1
# while n != -1 :
#      print(b.OKCYAN)
#      print(odd_or_pair(int(input("1 - sonni kiriting : ")),int(input("2 - sonni kiriting : "))))
#      print(b.ENDC)






                    #    5 - GURUH




# (string metodlaridan foydalaning) (moshina, rang, chiqgan yili) qiymatini
# foydalanuvchidan so'rang va quydagi ko’rinishda konsolga chiqazing:
# “Men 2022-yilda chiqgan qora rangli Malibu oldim”

# def n5_2() :
#      mashina = input('\nMashina nomi : ')
#      rang = input('Mashina rangi : ')
#      yili = input('Mashina yili : ')
#      print(b.OKBLUE,'\n',f'"Men {yili}-yilda chiqgan {rang} rangli {mashina} oldim"','\n',b.ENDC)

# n5_2()
    











# numbers = [12, 98, 34, 65, 34, 76, 11] berilgan ro’yhatdan eng katta
# elementni ekranga chiqzing (max() funksiyasidan foydalaning)
    
# def max_number() :
#     numbers = [12, 98, 34, 65, 34, 76, 11]
#     print(b.OKBLUE,"\nMax : ",f"{max(numbers)}",b.ENDC,'\n')

# max_number()


# (for sikl operatoridan foydalaning)1 dan 10 gacha bo’lgan sonlar kvadratini
# chiqazuvchi dastur tuzing

# def one_from_n() :
#      print('\n')
#      for i in range(1,11) : 
#           print(b.BOLD,f'{i} = {i * i}')

# one_from_n()

# print('\n',b.ENDC)
    



# def positive(x) :
#      if x == 0 : 
#          return b.OKCYAN+f'{x} dan katta yoki kichik son kiriting!'+b.ENDC
#      elif x > 0 : 
#          return b.OKBLUE+f"{x*x}"+b.ENDC                        #Foydalanuvchidan son kiritishni so'rang,
#      else :                                                     #agar son musbat bo'lsa uning kvadratini
#          return b.WARNING + "Musbat son kiriting" + b.ENDC      #hisoblab konsolga chiqaring. Agar son manfiy
#                                                                 #bo'lsa, "Musbat son kiriting" degan xabarni
# son = 1                                                         #chiqaring.
# while son != -10000 :
#     son = int(input("\nSon kiriting : "))
#     print(positive(son))
    




# (Dictionary dan foydlaning) singlim degan lug'at yarating va lug'atga shu inson haqida kamida 3 ta m'alumot kiriting (ismi, 
#tu'gilgan yili, shahri,manzili va hokazo). Lug'atdagi ma'lumotni matn shaklida konsolga chiqaring: Singlimning ismi Mohira,
#1954-yilda, Samarqand viloyatida tug'ilgan

# def dictionary(name) :
#      singlim = {'ismi' : input("Ismni kiriting : "),
#                 'tugilgan_yili' : input("Tug`ilgan yilini kiriting : "),
#                 'shahri' : input('Tug`ilgan viloyatini kiriting : '),
#                 'manzili' : input('Manzilini kiriting : ')}
#      print('\n',f'{name}ning ismi {singlim["ismi"]}, {singlim["tugilgan_yili"]}-yilda, {singlim["shahri"]} viloyatining, '+  
#            f'{singlim["manzili"]} tumanida tug`ilgan'
#           )
     
# print(b.OKBLUE)
# dictionary('Singlim')
# print(b.ENDC)




# Foydalanuvchidan x va y sonlarini olib, 𝑥^𝑦 konsolga chiqaruvchi funksiya yozing.
    
# def degree(x,y) :
#      return f"\n ^ {pow(x,y)}"

# x = int(input("\nx : sonini kiriting : "))
# y = int(input("y : sonini kiriting : "))

# print(b.OKBLUE)
# print(degree(x,y))
# print(b.ENDC)