# onbank
Proyecto Final de ADA. Web service de tipo REST
Proyecto Final ADA Banco Digital (OnBank)

Planteamiento del problema

Se necesita una API que le permita al cliente crear una cuenta bancaria,hacer transferencias,pagos,consultas y extracción de dinero de manera fácil y a través de una página web, sin necesidad que el cliente se dirija a una sucursal, solo con sus datos personales tales como 
Nombre completo
DNI
fecha de nacimiento
Mail

Objetivos  

Permitir que el cliente pueda extraer fondos de su cuenta correspondiente y en la divisa que él/ella decida sin tarjeta física
Optimizar la gestión de transferencias o consultas brindándole al cliente la facilidad de realizarlo en la página web. 
Facilitar al cliente la creación de una cuenta bancaria.
Realizar pagos y depositos de manera simplificada. 

Requisitos

El sistema debe ser una API de tipo Rest
Patrón de diseño MVC
Uso de base de datos relacionales gestionado por MYSQL
Uso del framework Springboot,Spring DATA JPA,Hibernate
Uso de herramientas como POSTMAN, IDE(INTELLIJ IDEA) 


Reglas de negocio

Se Podrá crear una nueva cuenta donde se le solicitará al nuevo cliente: 
-) Nombre completo
-)DNI 
-) Fecha de nacimiento 
-) Teléfono 
-)Email
-)clave

Al culminar el alta del cliente se le asignará una cuenta por defecto AHORRO en PESOS con un monto de $0.00 ( luego podrá añadir una cuenta extra en dolares corriente/ahorro)

El cliente puede realizar un pago proporcionando el monto,la moneda y la fecha.Antes de realizar el débito de su cuenta, se tendrá que validar por cada cliente asociado al pago: 
	
	en la cuenta del cliente debemos validar que la moneda del pago sea la misma moneda de la cuenta a debitar.
	
	el monto del pago sea menor o igual al monto disponible en la cuenta

Se devolverá el monto que debitó junto con el código de respuesta     correspondiente.

En caso de que algunas de estas validaciones fallen devolverá un BAD REQUEST junto con el mensaje personalizado


Se podrá realizar un depósito proporcionando el monto a depositar,el DNI del cliente, el número de cuenta(idCuenta) y la moneda que esta depositando. 
Antes de realizar el depósito se tendrá que validar: 

Buscamos el ID de la cuenta proporcionada.
Validamos que el dni del cliente proporcionado sea igual al dni de la cuenta buscada por id.
Validamos que la moneda de la cuenta del cliente sea igual a la moneda del depósito.

Se guarda el Depósito y se devuelve la cuenta actualizada con el código de respuesta correspondiente

En caso de que algunas de estas validaciones fallen devolverá un BAD REQUEST junto con el mensaje personalizado

Extraer dinero a través de una orden de extracción

Se podrá realizar una orden de extracción recibiendo el monto a extraer,el id de la cuenta y la fecha realizada

Antes de debitar el monto en la cuenta del cliente se tendrá que validar lo siguiente: 
Buscamos en el repositorio una cuenta con el id dado.
Validamos que la moneda de la cuenta sea igual a la moneda proporcionada.
Validamos que el monto de la cuenta sea mayor o igual al monto de la extracción de dinero.
	Al finalizar el proceso se devolverá el código de respuesta correspondiente,          la cuenta que fue debitada  

En caso de que algunas de estas validaciones fallen devolverá un BAD REQUEST junto con el mensaje personalizado.

Diccionario de datos

Tabla Customer:
 
-) costumer_id : identificador único del cliente (int, autoincrementable,not null,primary key) 
		-) costumer_dni : Documento único del cliente ( varchar50, not null) 
		-) fullname : Nombre completo del cliente (varchar 100)
		-)Date_birth: fecha de nacimiento del cliente (Date)
		-)email: email del cliente (varchar100)
		-)password: clave del cliente (int) 

	
Tabla Account:
-) id_account: Número identificador de la cuenta (int,autoincrementable,not null,primary key)
		-) currency(FK): tipo de divisa de la cuenta(int,not null,llave foránea         referenciando a la tabla currency) 
	-)type_account(FK): tipo de cuenta que puede manejar esta cuenta puede ser ahorro o corriente(int,not null, llave foránea referenciando a la tabla type_account) 
   	-)amount: representa la cantidad de fondos que va a tener disponible el cliente en esta cuenta (double,not null)  

Tabla Customer_account: 
	tabla que representa la relación entre cliente y cuenta(n:n) así podremos saber que cuenta tiene cada cliente
-) id_customer(FK) : representa el id del cliente que va a tener una nueva cuenta(int, llave foránea referenciando a la tabla customer) 
-) id_account(FK): representa el id de la cuenta que va a tener cada cliente ( int,llave foránea referenciando a la tabla account) 

Tabla type_account: 
 	Tabla que representa el tipo de cuenta que puede tener una cuenta,entre las opciones que puede llegar a ser: corriente/ahorro. 
	-) id_type: número único que identifica el tipo de cuenta(primary key, not null,int,autoincrementable) 
	-) type_name: nombre del tipo de cuenta que va a representar ej. “ahorro”,”corriente” ( varchar50,not null)

Tabla Currency:
Representa a la moneda que se puede manejar dentro de la cuenta, por ejemplo pesos/dolares
	-)id_currency: número único que va a pertencer al tipo de moneda ingresado(primary key,not null,autoincremental) 
-) currency: nombre de la moneda a ingresar (varchar50, not null) 

Tabla phone_number:
Tabla donde almacena números telefónicos de cada cliente un cliente puede tener varios números telefónicos(1:n)

	-)id (PK) : número único de identificación de cada número telefónico(int,autoincremental,primary key,not null)

-) customer_id (FK): hace referencia al id del cliente correspondiente 
(int llave foránea haciendo referencia a la tabla de phone_number) 

-) phone_number: número telefónico ( varchar10) 

TABLAS QUE REPRESENTA ACCIONES QUE EL CLIENTE PUEDE REALIZAR

Tabla Payment:
Tabla que representa la acción del cliente al realizar pagos.
muchos clientes pueden realizar muchos pago (n:n) 
-) id_payment: número único del pago realizado (primary key,int,autoincrementable) 
-) amount: monto exacto a pagar (double) 
-)date_payment: fecha que se realiza el pago ( DATE )
-) currency(FK): divisa a la que esta relacionada el pago (clave foránea,not null,int) 

Tabla payment_customer:
Tabla donde se relaciona las dos tablas principales: payment y customer,asi poder saber qué pago se hizo por cada cliente

-) id_payment(FK): numero único que hace referencia a la llave primaria de la tabla payment (llave foránea,int,not null) 

-) id_costumer(FK): numero unico que hace referencia al cliente que realizara el pago ( llave foranea,int,not null) 


Tabla Extraction_order
Tabla que representara la orden de extracción que permitirá extraer efectivo a una cuenta 

-) id_extraction: número único para identificar cada order extraccion (int,primary key,not null,autoincrementable) 

-) id_account(FK): campo que hace referencia a la tabla account para especificar a que cuenta pertenece la orden de extracción ( int,llave foranea)  
-)amount_extraction: monto que se debitará (double)
-)date_extraction: fecha en que se realiza la orden de extracción(DATE)

Tabla Transfer:
Tabla que almacenara los datos de una transferencia entre cuentas 
-) id_transfer(PK): número único que identificara cara transferencia (primary key,not null,autoincrementable) 
-) id_customer(FK): se refiere al id o número único del cliente que REALIZA la transferencia ( llave foránea haciendo referencia a la tabla customer,int) 
-) id_account(FK): se refiere al id o número único de la cuenta que va a RECIBIR la transferencia de fondos( llave foránea haciendo referencia a la tabla account) 
-) amount: campo que representa el monto a transferir (double,not null) 
-) date_transfer: representa la fecha que se realiza la tranferencia (DATE)  	


Tabla Deposit: 
Tabla que representa un depósito a una cuenta
-) id(PK): representa el número unido e identificador de cada depósito(int,not null, autoincremental,primary key) 
-) amount: representa el monto a ser depositado (double,not null) 
-) id_account(FK): hace referencia a la tabla ACCOUNT (id_account) (int,not null) 
-)id_currency(FK) : hace referencia a la moneda que sera ese deposito, tabla CURRENCY(id_currency)
-) id_cliente: hace referencia al DNI del cliente de la cuenta que le pertenece (varchar30,not null) 






DIAGRAMA RELACIONAL 



