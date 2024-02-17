
class Questions:
      def __init__(self,guruh,savol_raqami,savol):
            self.guruh = guruh
            self.savol_raqami = savol_raqami
            self.savol = savol


def n1_1(x,y) : 
    return pow(x,y)

def n1_2() : 
    viloyat = input("Viloyatni kiriting : \n")
    tuman = input("Tumanni kiriting : \n")
    mahalla = input("Mahallani kiriting : \n")
    kocha = input("ko`chani kiriting : \n")
    print(f'\n "{viloyat} viloyati, {tuman} tumani, {mahalla} mahallasi, {kocha} ko`chasi"')

def n1_3() :
    cars = ['bmw','mercedes benz', 'volvo', 'general motors', 'tesla', 'audi']
    print("\n",sorted(cars)) 

def n1_4() :
     kinolar = []
     for i in range(5) :
            kinolar.append(input(f"{i+1} chi kinoni kiriting : "))
     print("\n",kinolar)

def n1_5() :
     cars = ['toyota', 'mazda', 'hyundai', 'gm', 'kia']
     cars2 = []
     for i in cars : 
        if i != 'gm' :
             cars2.append(i.title())
        else : 
             cars2.append(i.upper())
     print("\n",cars2)

def n1_6(name) :
     otam = {'ismi' : input("Ismni kiriting : "),'tugilgan_yili' : input("Tug`ilgan yilini kiriting : "),
                    'shahri' : input('Tug`ilgan viloyatini kiriting : '),'manzili' : input('Manzilini kiriting : ')}
     print('\n',f'{name}ning ismi {otam["ismi"]}, {otam["tugilgan_yili"]}-yilda, {otam["shahri"]} viloyatida tugilgan')

def n1_7() : 
     ism = input("ismingizni kiriting : \n")
     yosh = input("yoshingizni kiriting : \n")
     yosh = 2024-int(yosh)
     print(f"\nismi {ism}, {yosh}-yil")

def n2_1() : 
     son = 22 % 4
     print("\nQoldiq : ",son)

def n2_2() : 
     yil = input("Tug'ilgan yilingizni kiriting : \n")
     oy = input("Tug'ilgan oyningizni kiriting : \n")
     kun = input("Tug'ilgan kuningizni kiriting : \n")
     print("\n",f'"Men {yil}-yil {kun}-{oy}da tug`ulgan man"')

def n2_3() :
     cars = ['bmw','mercedes benz', 'volvo', 'general motors', 'tesla', 'audi']
     cars.reverse()
     print("\n",cars) 

def n2_4() :
     mevalar = []
     for i in range(5) : 
          mevalar.append(input(f"{i+1} chi mevani kiriting: \n"))
     print('\n',mevalar)

def n2_5() : 
     login = input("Loginni kiriting : \n")
     ism = input("Ismingizni kiriting : \n")
     if login.lower() == 'admin' : 
          print(f'"Xush kelibsiz {login.title()}"')
     else : 
          print('\n',f'"Xush kelibsiz, {ism}!"')

def n2_7() :
     son = int(input("Ixtiyoriy son kiriting : \n"))
     print('\n',f"Sonning kvadrati : {son*son}.\nSonning kubi : {son*son*son}")

def n3_1() : 
     tomonlar = 125
     print('\n',f"Kvadratning perimetri : {tomonlar*4}\nKvadratning yuzi : {tomonlar*tomonlar}")

def n3_2() : 
    ism = input("Ismingizni kiriting : \n")
    familiya = input("Faliyangizni kiriting : \n")
    sharif = input("Sharifingizni kiriting : \n")
    print('\n',f'"Mening to`liq ismim {ism} {familiya} {sharif}"')

def n3_3() :
     numbers = [12, 98, 34, 65, 34, 76, 11]
     print('\n',sorted(numbers,reverse=True)) 

def n3_4() :
     list = []
     count = int(input("bugun necha kishi bilan suhbat qildingiz?\n"))
     
     for i in range(count) :
            list.append(input(f'{i+1} - suhbat qilgan odamingiz kim edi : '))
     print('\n',list)

def n3_5() :
     num = int(input("Birinchi sonni kiriting : "))
     num2 = int(input("Ikkinchi sonni kiriting : "))
     if num == num2 : 
          print('\n','Sonlar teng')
     else : print('\n','Sonlar teng emas')    

def n3_7(x) :
        if x % 2 == 0 : 
             return f'\n{x} soni juft son'
        else : return f'\n{x} soni toq son'

def n4_1() :
     PI = 3.14
     yuza = float(12/2)
     print("\n Doiraning yuzasi : ",(yuza*yuza)*PI)

def n4_2() :
     email = input('Emailni kiriting : \n')
     telefon = input('Telefon raqamingizni kiriting : \n')
     manzil = input('Manzilingizni kiriting : \n')
     print("\n",f'“Meni electron pochta manzilim ‘{email}’, telefon raqamim: {telefon}, hozirda men {manzil} shahrida istiqomat qilaman”')

def n4_3() :
     numbers = [12, 98, 34, 65, 34, 76, 11]
     print("\nMin : ",f"{min(numbers)}")

def n4_4() :
     
     for i in range(10,100) : 
          if(i % 2 == 1) : 
               print(f'{i} = ',i**3)

def n4_5(x) : 
     if x < 0 : 
          print(f"\n {x} manfiy son")
     else : print(f"\n {x} musbat son")
     
def n4_7(x,y) :
      if x == y :
           return f"\nSonlar teng!"  
      elif x > y : 
           return f"\n{x} soni katta"
      else : 
           return f"\n{y} soni katta"
     

def n5_1() :
     print('\nNum : ',7 // 8)

def n5_2() :
     mashina = input('Mashina nomi : ')
     rang = input('Mashina rangi : ')
     yili = input('Mashina yili : ')
     print('\n',f'"Men {yili}-yilda chiqgan {rang} rangli {mashina} oldim"')


def n5_3() :
     numbers = [12, 98, 34, 65, 34, 76, 11]
     print("\nMax : ",f"{max(numbers)}")


def n5_4() :
     print('\n')
     for i in range(1,11) : 
          print(f'{i} = {i * i}')

def n5_5(x) :
     if x > 0 : 
          return f"{x*x}"
     else : return "Musbat son kiriting"

def n5_7(x,y) :
     return f"\n ^ {pow(x,y)}"


def n6_1(x,y) :
     return print('\nNum : ',x%y)

def n6_2() :
     universitet = input("universitet nomi : ")
     fakultet = input("fakultet nomi : ")
     guruh = input("guruh nomi : ") 
     print(f'\n"Men {universitet} universiteti, {fakultet} fakulteti, {guruh}-guruhda o’qiyman"')

def n6_3() :
     list = []
     count = int(input("bugun necha kishi bilan uchrashdingiz?\n"))
     
     for i in range(count) :
            list.append(input(f'{i+1} - suhbat qilgan odamingiz kim edi : '))
     print('\n',list)


def n6_4(x) :
     if x % 2 == 0 : 
          return f"{x*x}"
     else : return "Musbat son kiriting"

def n7_2() : 
     kompyuter = input("Kompyuter nomi : ")
     o_xotira = input("Kompyuter operativ xotirasi : ")
     v_xotira = input("Kompyuter video xotirasi : ")
     print(f'"Men {o_xotira} GB operativ xotirali, {v_xotira}GB video xotiraga ega ‘{kompyuter}’ noutbook sotib oldim"')

def n7_3() : 
     cars = ['bmw','mercedes benz', 'volvo', 'general motors', 'tesla', 'audi']
     save = [] 
     for i in cars : 
          if cars.index(i) > 1 and cars.index(i) < 6 : 
               save.append(i)
     print('\n',save)

def n7_4() : 
     sonlar = [2, 1, 2, 3, 4]
     count = 0
     for i in sonlar :
            if i % 2 == 0 : 
                count = count+1
     print("Juft : ",count) 

def n7_5(x,y) : 
     if x+y > 10 and x+y < 20 : 
          return "\n Taqiqlangan oraliq"
     else : return "\n Taqiqlanmagan oraliq"


def n7_7(x,y) : 
     if x%2 == 0 and y%2 == 0 : 
          return "\n Juft sonlar"
     elif x%2 == 0 and y%2 != 0 : 
          return f"\n{x} juft son, {y} toq son"
     else : 
          return f"\n{y} juft son, {x} toq son"
     
def n8_3() :
    juft = []
    for i in range(120,1201) : 
         if(i % 2 == 0) : juft.append(i)
    print('\n',juft)


def n8_4() :
     sonlar = [2, 1, 2, 3, 4]
     count = 0
     for i in sonlar : 
          if(i % 2 == 1) : 
               count = count+1
     print('\n',f'Toq sonlar : {count}')


def n8_5(x,y) :
     if y == 6 or x == 6 : 
          return "\n Ajoyib raqam"
     else : "Kiritilgan sonlar Ichida 6 mavjud emas"

def n8_7(x,y) : 
     if x%2 == 0 and y%2 == 0 : 
          return "\n Juft sonlar"
     elif x%2 == 0 and y%2 != 0 : 
          return f"\n{x} juft son, {y} toq son"
     else : 
          return f"\n{y} juft son, {x} toq son"

def n1_1_1() : 
     print("****************************","\nMasalani Yechimi:\n")
     print("\nx = 5\ny = 4\nprint(pow(x,y))\n")
     print("****************************")

def n1_1_2() : 
     print("****************************","\nMasalani Yechimi:\n")
     print('viloyat = input("Viloyatni kiriting :")\ntuman = input("Tumanni kiriting :")\nmahalla = input("Mahallani kiriting :")\nkocha = input("ko`chani kiriting :")\nprint(f"{viloyat} viloyati, {tuman} tumani, {mahalla} mahallasi, {kocha} ko`chasi")')
     print("****************************")


list = []
list.append(Questions('1','1','\n5 ning 4-darajasini toping\n'))
list.append(Questions('1','2','\n(string metodlaridan foydalaning)(kocha, mahalla, tuman, viloyat) qiymatinifoydalanuvchidan so`rang va quydagi ko`rinishda konsolga chiqazing:“Andijon viloyati, Asaka tumani, Sherdor mahallasi, Navoiy ko’chasi”\n'))
list.append(Questions('1','3',"\ncars = ['bmw','mercedes benz', 'volvo', 'general motors', 'tesla', 'audi'] berilgan ro’yhatni alifbo ketma-ketligida tartib ekranga chiqazing\n"))
list.append(Questions('1','4','\n(for sikl operatoridan foydalaning)Foydalanuvchidan 5 ta eng sevimli kinolarini kiritshni so`rang, va kinolar degan ro`yxatga saqlab oling. Natijani konsolga chiqaring.\n'))
list.append(Questions('1','5',"\ncars = ['toyota', 'mazda', 'hyundai', 'gm', 'kia'] degan ro`yxat tuzing, ro`yxat elementlarining birinchi harfini katta qilib konsolga chqaring. GM uchun ikkala harfni katta qiling.\n"))
list.append(Questions('1','6','\n(Dictionary dan foydlaning) otam degan lug`at yarating va lug`atga shu inson haqida kamida 3 ta m`alumot kiriting (ismi, tu`gilgan yili, shahri, manzili va hokazo). Lug`atdagi ma`lumotni matn shaklida konsolga chiqaring : Otamning ismi Mavlutdin, 1954-yilda, Samarqand viloyatida tug`ilgan\n'))
list.append(Questions('1','7','\nFoydalanuvchi ismi va yoshini so`rab, uning tug`ilgan yilini hisoblaydigan funksiya yozing.\n'))
list.append(Questions('2','1','\n22 ni 4 ga bo`lganda qancha qoldiq qoladi?.\n'))
list.append(Questions('2','2','\n(string metodlaridan foydalaning) (tug’ulgan yil, oy, kun) qiymatini foydalanuvchidan so`rang va quydagi ko’rinishda konsolga chiqazing: “Men 1987-yil 12-dekabrda tug`ulgan man.”\n'))
list.append(Questions('2','3',"\ncars = ['bmw','mercedes benz', 'volvo', 'general motors', 'tesla', 'audi'] berilgan ro’yhatni teskari tartibda ekranga chiqazing.\n"))
list.append(Questions('2','4','\n(for sikl operatoridan foydalaning) Foydalanuvchidan 5 ta eng sevimli mevalarini kiritshni so`rang, va mevalar degan ro`yxatga saqlab oling.Natijani konsolga chiqaring.\n'))
list.append(Questions('2','5','\nFoydalanuvchi login ismini so`rang. Agar login admin bo`lsa, "Xush kelibsiz, Admin. Foydalanuvchilar ro`yxatini ko`rasizmi?" xabarini konsolga chiqaring. Aks holda, "Xush kelibsiz, {foydalanuvchi_ismi}!" matnini konsolga chiqaring..\n'))
list.append(Questions('2','6','\n(Dictionary dan foydlaning) onam degan lug`at yarating va lug`atga shu inson haqida kamida 3 ta ma`lumot kiriting (ismi, tu`gilgan yili, shahri, manzili va hokazo). Lug`atdagi ma`lumotni matn shaklida konsolga chiqaring : Onamning ismi Matluba, 1954-yilda, Samarqand viloyatida tug`ilgan.\n'))
list.append(Questions('2','7','\nFoydalanuvchidan son olib, uning kvadrati va kubini konsolga chiqaruvchi funksiya yozing..\n'))
list.append(Questions('3','1','\nTomonlari 125 ga teng kvadratning yuzi va perimetrini toping.\n'))
list.append(Questions('3','2','\n(string metodlaridan foydalaning) (ism, familya, sharif) qiymatini foydalanuvchidan so`rang va quydagi ko’rinishda konsolga chiqazing: “Mening to’liq ismim Abdullayev Abdulla Abdullayevich”.\n'))
list.append(Questions('3','3','\nnumbers = [12, 98, 34, 65, 34, 76, 11] berilgan ro’yhatni kamayish taribida ekranga chiqazing.\n'))
list.append(Questions('3','4','\n(for sikl operatoridan foydalaning)Foydalanuvchidan bugun nechta odam bilan uchrashganini (suhbatlashganini) so`rang, va har bir suhbatlashgan odamning ismini birma-bir so`rab ro`yxatga yozing. Ro`yxatni konsolga chiqaring.\n'))
list.append(Questions('3','5','\n.Foydalanuvchidan 2 ta son kiritishni so`rang. Agar ikki son bir-biriga teng bo`lsa, "Sonlar teng" ekan degan yozuvni konsolga chiqaring.\n'))
list.append(Questions('3','6','\n(Dictionary dan foydlaning) ukam degan lug`at yarating va lug`atga shu inson haqida kamida 3 ta m`alumot kiriting (ismi, tu`gilgan yili, shahri, manzili va hokazo). Lug`atdagi ma`lumotni matn shaklida konsolga chiqaring : Ukamning ismi Mavlutdin, 1954-yilda, Samarqand viloyatida tug`ilgan\n'))
list.append(Questions('3','7','\nFoydalanuvchidan son olib, son juft yoki toqligini konsolga chiqaruvchi funksiya yozing.\n'))
list.append(Questions('4','1','\nDiametri 12 ga teng bo`lgan doiraning yuzini toping ( PI=3.14 deb oling)\n'))
list.append(Questions('4','2','\n(string metodlaridan foydalaning) (email, telefon nomer, manzil) qiymatini foydalanuvchidan so`rang va quydagi ko’rinishda konsolga chiqazing: “Meni electron pochta manzilim ‘google@.com’, telefon raqamim: +998999855612, hozirda men Toshkent shahrida istiqomat qilaman”\n'))
list.append(Questions('4','3','\nnumbers = [12, 98, 34, 65, 34, 76, 11] berilgan ro’yhatdan eng kichik elementni ekranga chiqazing (min() funksiyasidan foydalaning)\n'))
list.append(Questions('4','4','\n(for sikl operatoridan foydalaning)10 dan 100 gacha bo`lgan toq sonlar ro`yxatini tuzing. Ro`yxatning xar bir elementining kubini yangi qatordan konsolga chiqaring.\n'))
list.append(Questions('4','5','\nFoydalanuvchidan istalgan son kiritishni so`rang. Agar son manfiy bo`lsa konsolga "Manfiy son", agar musbat bo`lsa "Musbat son" degan xabarni chiqaring\n'))
list.append(Questions('4','6','\n(Dictionary dan foydlaning) opam degan lug`at yarating va lug`atga shu inson haqida kamida 3 ta m`alumot kiriting (ismi, tu`gilgan yili, shahri, manzili va hokazo). Lug`atdagi ma`lumotni matn shaklida konsolga chiqaring : Opamning ismi Osiyo, 1954-yilda, Samarqand viloyatida tug`ilgan\n'))
list.append(Questions('4','7','\nFoydalanuvchidan ikkita son olib, ulardan kattasini konsolga chiqaruvchi funksiya yozing. Agar sonlar teng bo`lsa "Sonlar teng" degan xabarni chiqaring.\n'))
list.append(Questions('5','1','\n7 ni 8 ga bo’lgandagi butun qismini toping\n'))
list.append(Questions('5','2','\n(string metodlaridan foydalaning) (moshina, rang, chiqgan yili) qiymatini foydalanuvchidan so`rang va quydagi ko’rinishda konsolga chiqazing: “Men 2022-yilda chiqgan qora rangli Malibu oldim”\n'))
list.append(Questions('5','3','\nnumbers = [12, 98, 34, 65, 34, 76, 11] berilgan ro’yhatdan eng katta elementni ekranga chiqzing (max() funksiyasidan foydalaning)\n'))
list.append(Questions('5','4','\n(for sikl operatoridan foydalaning)1 dan 10 gacha bo’lgan sonlar kvadratini chiqazuvchi dastur tuzing\n'))
list.append(Questions('5','5','\nFoydalanuvchidan son kiritishni so`rang, agar son musbat bo`lsa uning kvadratini hisoblab konsolga chiqaring. Agar son manfiy bo`lsa, "Musbat son kiriting" degan xabarni chiqaring.\n'))
list.append(Questions('5','6','\n(Dictionary dan foydlaning) singlim degan lug`at yarating va lug`atga shu inson haqida kamida 3 ta m`alumot kiriting (ismi, tu`gilgan yili, shahri, manzili va hokazo). Lug`atdagi ma`lumotni matn shaklida konsolga chiqaring: Singlimning ismi Mohira, 1954-yilda, Samarqand viloyatida tug`ilgan\n'))
list.append(Questions('5','7','\nFoydalanuvchidan x va y sonlarini olib, 𝑥^𝑦 konsolga chiqaruvchi funksiya yozing.\n'))
list.append(Questions('6','1','\n22 ni 4 ga bo`lganda qancha qoldiq qoladi?\n'))
list.append(Questions('6','2','\n(string metodlaridan foydalaning) (universitet nomi, fakultet nomi, guruh nomi) qiymatini foydalanuvchidan so`rang va quydagi ko’rinishda konsolga chiqazing:“Men PDP universiteti, ATT fakulteti, 705-guruhda o’qiyman”\n'))
list.append(Questions('6','3','\n(for sikl operatoridan foydalaning)Foydalanuvchidan bugun nechta odam bilan uchrashganini (suhbatlashganini) so`rang, va har bir suhbatlashgan odamning ismini birma-bir so`rab ro`yxatga yozing. Ro`yxatni konsolga chiqaring.\n'))
list.append(Questions('6','4','\nFoydalanuvchidan son kiritishni so`rang, agar son juft bo`lsa uning kvadratini hisoblab konsolga chiqaring. Agar son toq bo`lsa, "Musbat son kiriting" degan xabarni chiqaring.\n'))
list.append(Questions('6','5','\n(Dictionary dan foydlaning) bobom degan lug`at yarating va lug`atga shu inson haqida kamida 3 ta m`alumot kiriting (ismi, tu`gilgan yili, shahri, manzili va hokazo). Lug`atdagi ma`lumotni matn shaklida konsolga chiqaring : Bobom ismi Mavlutdin, 1954-yilda, Samarqand viloyatida tug`ilgan\n'))
list.append(Questions('6','6','\nFoydalanuvchidan x va y sonlarini olib, 𝑦^𝑥 konsolga chiqaruvchi funksiya yozing.\n'))
list.append(Questions('7','1','\n52 ni 3 ga bo`lganda qancha qoldiq qoladi?.\n'))
list.append(Questions('7','2','\n(string metodlaridan foydalaning) (kompyuter, operativ xotirasi, video xotirasi) qiymatini foydalanuvchidan so`rang va quydagi ko’rinishda konsolga chiqazing: “Men 8 GB operativ xotirali, 4GB video xotiraga ega ‘Dell’ noutbook sotib oldim”.\n'))
list.append(Questions('7','3',"\ncars = ['bmw','mercedes benz', 'volvo', 'general motors', 'tesla', 'audi'] berilgan ro’yhatdan 2-va 6-elementlar oralig’idagi elementlardan iborat ro’yhatni konsolga chiqazing\n"))
list.append(Questions('7','4','\n(for sikl operatoridan foydalaning) sonlar = [2, 1, 2, 3, 4] berilgan ro’yhatda juft sonlar miqdorini aniqlovchi dastur tuzing.\n'))
list.append(Questions('7','5','\nFoydalanuvchidan 2 ta int tipiga oid son kiritishini so’rang. Agar kiritilgan sonlar yig’indisi 10 dan 19 oralig’idagi son bo’lsa ekranga “Taqiqlangan oraliq” aks holda “Taqiqlanmagan oraliq” degan yozuv chiqsin.\n'))
list.append(Questions('7','6','\n(Dictionary dan foydlaning) buvim degan lug`at yarating va lug`atga shu inson haqida kamida 3 ta m`alumot kiriting (ismi, tu`gilgan yili, shahri,manzili va hokazo). Lug`atdagi ma`lumotni matn shaklida konsolga chiqaring: buvimning ismi Muattar, 1954-yilda, Samarqand viloyatida tug`ilgan.\n'))
list.append(Questions('7','7','\nFoydalanuvchidan x va y sonlarini olib, kiritilgan sonlarni juft ekanligiga tekshirib konsolga chiqaruvchi funksiya yozing.\n'))
list.append(Questions('8','1','\n37 ni 6 ga bo`lganda qancha qoldiq qoladi?.\n'))
list.append(Questions('8','2','\n(string metodlaridan foydalaning) (kocha, mahalla, tuman, viloyat) qiymatini foydalanuvchidan so`rang va quydagi ko’rinishda konsolga chiqazing:“Samarqand viloyati, Narpay tumani, Bog’iston mahallasi, Gulobod ko’chasi”.\n'))
list.append(Questions('8','3','\n120 dan 1200 gacha bo`lgan juft sonlar ro`yxatini tuzing.\n'))
list.append(Questions('8','4','\n(for sikl operatoridan foydalaning) sonlar = [2, 1, 2, 3, 4] berilgan ro’yhatda toq sonlar miqdorini aniqlovchi dastur tuzing.\n'))
list.append(Questions('8','5','\nFoydalanuvchidan 2 ta int tipiga oid son kiritishini so’rang. Agar kiritilgan sonlardan biri 6 ga teng bo’lsa ekranga “Ajoyib raqam” aks holda “Kiritilgan sonlar Ichida 6 mavjud emas” degan habar chiqasin.\n'))
list.append(Questions('8','6','\n(Dictionary dan foydlaning) otam degan lug`at yarating va lug`atga shu inson haqida kamida 3 ta m`alumot kiriting (ismi, tu`gilgan yili, shahri, manzili va hokazo). Lug`atdagi ma`lumotni matn shaklida konsolga chiqaring : Otamning ismi Mavlutdin, 1954-yilda, Samarqand viloyatida tug`ilgan.\n'))
list.append(Questions('8','7','\nFoydalanuvchidan x va y sonlarini olib, kiritilgan sonlarni toq ekanligiga tekshirib konsolga chiqaruvchi funksiya yozing.\n'))


start = input("Start Enter!!!")
while start != "stop" :
    start += input("\nguruh raqamini kiriting : ")
    start += input("savol raqamini kiriting : ")
    for i in list : 
      if i.guruh+i.savol_raqami == start : 
          print(i.savol)
          start = input('Menu: \n\n1 = ha\n2 = yoq\n3 = yechimni\n\n')
          if start == '1' : 
              if i.guruh == '1' and i.savol_raqami == '1' : 
                  print("\nbeshning to`rtinchi darajasi: ",n1_1(5,4),"\n")
              elif i.guruh == '1' and i.savol_raqami == '2' : n1_2()
              elif i.guruh == '1' and i.savol_raqami == '3' : n1_3()
              elif i.guruh == '1' and i.savol_raqami == '4' : n1_4()
              elif i.guruh == '1' and i.savol_raqami == '5' : n1_5()
              elif i.guruh == '1' and i.savol_raqami == '6' : n1_6("Otam")
              elif i.guruh == '1' and i.savol_raqami == '7' : n1_7()
              elif i.guruh == '2' and i.savol_raqami == '1' : n2_1()
              elif i.guruh == '2' and i.savol_raqami == '2' : n2_2()
              elif i.guruh == '2' and i.savol_raqami == '3' : n2_3()
              elif i.guruh == '2' and i.savol_raqami == '4' : n2_4()
              elif i.guruh == '2' and i.savol_raqami == '5' : n2_5()
              elif i.guruh == '2' and i.savol_raqami == '6' : n1_6("Onam")
              elif i.guruh == '2' and i.savol_raqami == '7' : n2_7()
              elif i.guruh == '3' and i.savol_raqami == '1' : n3_1()
              elif i.guruh == '3' and i.savol_raqami == '2' : n3_2()
              elif i.guruh == '3' and i.savol_raqami == '3' : n3_3()
              elif i.guruh == '3' and i.savol_raqami == '4' : n3_4()
              elif i.guruh == '3' and i.savol_raqami == '5' : n3_5()
              elif i.guruh == '3' and i.savol_raqami == '6' : n1_6("Ukam")
              elif i.guruh == '3' and i.savol_raqami == '7' : print(n3_7(int(input("Son kiriting : \n"))))
              elif i.guruh == '4' and i.savol_raqami == '1' : n4_1()
              elif i.guruh == '4' and i.savol_raqami == '2' : n4_2()
              elif i.guruh == '4' and i.savol_raqami == '3' : n4_3()
              elif i.guruh == '4' and i.savol_raqami == '4' : n4_4()
              elif i.guruh == '4' and i.savol_raqami == '5' : n4_5(int(input("0 dan katta yoki kichik bo'lgan son kiriting : ")))
              elif i.guruh == '4' and i.savol_raqami == '6' : n1_6("Opam")
              elif i.guruh == '4' and i.savol_raqami == '7' : 
                   print(n4_7(int(input("1 - sonni kiriting : ")),int(input("2 - sonni kiriting : "))))
              elif i.guruh == '5' and i.savol_raqami == '1' : n5_1()
              elif i.guruh == '5' and i.savol_raqami == '2' : n5_2()
              elif i.guruh == '5' and i.savol_raqami == '3' : n5_3()
              elif i.guruh == '5' and i.savol_raqami == '4' : n5_4()
              elif i.guruh == '5' and i.savol_raqami == '5' : print("\n",n5_5(int(input("Son kiriting : \n"))))
              elif i.guruh == '5' and i.savol_raqami == '6' : n1_6("Singlim")
              elif i.guruh == '5' and i.savol_raqami == '7' : print(n5_7(int(input("x : sonini kiriting : ")),int(input("y : sonini kiriting : "))))
              elif i.guruh == '6' and i.savol_raqami == '1' : n6_1(22,4)
              elif i.guruh == '6' and i.savol_raqami == '2' : n6_2()
              elif i.guruh == '6' and i.savol_raqami == '3' : n6_3()
              elif i.guruh == '6' and i.savol_raqami == '4' : print("\n",n6_4(int(input("Son kiriting : \n"))))
              elif i.guruh == '6' and i.savol_raqami == '5' : n1_6("Bobom")
              elif i.guruh == '6' and i.savol_raqami == '6' : print(n5_7(int(input("x : sonini kiriting : ")),int(input("y : sonini kiriting : "))))
              elif i.guruh == '7' and i.savol_raqami == '1' : n6_1(52,3)
              elif i.guruh == '7' and i.savol_raqami == '2' : n7_2()
              elif i.guruh == '7' and i.savol_raqami == '3' : n7_3()
              elif i.guruh == '7' and i.savol_raqami == '4' : n7_4()
              elif i.guruh == '7' and i.savol_raqami == '5' : print(n7_5(int(input("1 - son :")),int(input("2 - son :"))))
              elif i.guruh == '7' and i.savol_raqami == '6' : n1_6("Buvim")
              elif i.guruh == '7' and i.savol_raqami == '7' : print(n7_7(int(input("1 - sonni kiriting : ")),int(input("2 - sonni kiriting : "))))
              elif i.guruh == '8' and i.savol_raqami == '1' : n6_1(37,6)
              elif i.guruh == '8' and i.savol_raqami == '2' : n1_2()
              elif i.guruh == '8' and i.savol_raqami == '3' : n8_3()
              elif i.guruh == '8' and i.savol_raqami == '4' : n8_4()
              elif i.guruh == '8' and i.savol_raqami == '5' : n8_5(print(int(input("1-sonni kiriting : ")),int(input("2-sonni kiriting : "))))
              elif i.guruh == '8' and i.savol_raqami == '6' : n1_6("Otam")
              elif i.guruh == '8' and i.savol_raqami == '7' : print(n8_7(int(input("1 - sonni kiriting : ")),int(input("2 - sonni kiriting : "))))
          elif start == '3' : 
               if i.guruh == '1' and i.savol_raqami == '1' : 
                    n1_1_1()
               elif i.guruh == '1' and i.savol_raqami == '2' : n1_1_2()

    start = ""   