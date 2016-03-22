# PJS4
PJS4 OklmRoom [OKRM]

Serveur Wamp + BDD MySql + HeidiSQL : https://drive.google.com/open?id=0B2jx_FennHRJdTRqZk0xaGNCM3c
#___
Appli Java : Java - JEE (Momo et Veusty)
___
•	Connexion ✔

•	Inscription ✔

•	Réservation orange

•	Réservation rouge

•	Servlet Serveur/Client ✔

•	Timer Refresh Liste Salle (gestion refresh la nuit)

•	Lié à la BDD MySql ✔
#___
STRUCTURE Base de Données : SQL
___
•	Users :
___
int IdUser

string Lastname

string Firstname

string Pseudo

string Password

string Right (1:élèves - 2:prof - 3:admin)

int Reservations  (< 7 / jour)
#___
•	Blocks :
___
int IdBlock

string Name

#___
•	Floors :
___
int IdFloor

string NameBlock (foreign Key Block(Name))

#___
•	Rooms :
___
int IdRoom

string NbRoom

int NbSeat

Bool Reserved

string Screen

string Processor

string Ram

int FloorId (foreign Key Floor(IdFloor))
#___
•	Reservations :
___
int IdReservation

int UserId (foreign Key User(IdUser))

int RoomId (foreign Key Room(IdRoom))

int SlotId (foreign Key Slot(IdSlot))

date Day
#___
•	Slots :
___
int IdSlot

time Start

time End
#___
STRUCTURE Site Web : HTML – JSP (Jules Nicolol)
___
#___
•	login :
___
signIn.jsp (choisir la date)
#___
•	user :
___
promoteUser.jsp

signUp.jsp

dashboard.jsp

listReservations.jsp
#___
•	index :
___
index.jsp
#___
•	reservation :
___
listRooms.jsp (choisir la date)
#___
• includes :
___
header.jsp

footer.jsp
#___
• error :
___
404.jsp

500.jsp
#___
•	"commande" => "reserve"

•	"annuler" => "cancel"

•	"lister" => "listRooms"

•	choice DEVIENT SOIT choiceRoom SOIT choiceSlot !

•	Page Planning par salle A DEFINIR
___
#___
Appli Android : (Anis JJ Massine)
___

/!\ BASE DE DONNEE SQL
