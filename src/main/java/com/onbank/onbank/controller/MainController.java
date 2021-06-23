package com.onbank.onbank.controller;

import com.onbank.onbank.model.*;


import com.onbank.onbank.model.Currency;
import com.onbank.onbank.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/onbank")
public class MainController {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ICurrencyRepository currencyRepository;

    @Autowired
    private ITypeAccountRepository typeAccountRepository;

    @Autowired
    private IExtractionOrderRepository extractionOrderRepository;

    @Autowired
    private IPhoneNumberRepository phoneNumberRepository;

    @Autowired
    private IDepositRepository depositRepository;

    @Autowired
    private IPaymentRepository paymentRepository;

    @Autowired
    private ITransferRepository transferRepository;

    // ***************************************GET*******************************************************************
    //Traer todos los clientes
    @GetMapping(path = "/customers")
    @ResponseBody
    ResponseEntity<Customer> getAllCustomer() {
        Iterable<Customer> customerList = customerRepository.findAll();
        return new ResponseEntity(customerList, HttpStatus.OK);
    }

    //Traer todos las cuentas
    @GetMapping(path = "/accounts")
    @ResponseBody
    Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    //Traer todos los tipos de cuentas
    @GetMapping(path = "/typeaccounts")
    @ResponseBody
    Iterable<TypeAccount> getAllTypeAccounts() {
        return typeAccountRepository.findAll();
    }

    //Traer todos los tipos de moneda
    @GetMapping(path = "/currency")
    @ResponseBody
    Iterable<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }

    //Traer todos los telefonos
    @GetMapping(path = "/phonenumbers")
    @ResponseBody
    Iterable<PhoneNumber> getAllPhoneNumbers() {
        return phoneNumberRepository.findAll();
    }

    //Traer todas las ordenes de extraccion
    @GetMapping(path = "/extractionorders")
    Iterable<ExtractionOrder> getAllExtractionOrders() {
        return extractionOrderRepository.findAll();
    }

    //Traer todas las transferencias
    @GetMapping(path = "/transfer")
    Iterable<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    //Traer customer por id
    @GetMapping(path = "/customer/{id}")
    ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return new ResponseEntity(customer, HttpStatus.FOUND);
        }
        return new ResponseEntity("ID NO VALIDO", HttpStatus.BAD_REQUEST);
    }

    //Traer cuenta por id
    @GetMapping(path = "/account/{id}")
    ResponseEntity<Account> getAccountById(@PathVariable("id") int id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return new ResponseEntity(account, HttpStatus.FOUND);
        }
        return new ResponseEntity("ID NO VALIDO", HttpStatus.BAD_REQUEST);
    }

    //Traer tipo de cuenta por id
    @GetMapping(path = "/typeaccount/{id}")
    ResponseEntity<TypeAccount> getTypeAccountById(@PathVariable("id") int id) {
        Optional<TypeAccount> typeAccount = typeAccountRepository.findById(id);
        if (typeAccount.isPresent()) {
            return new ResponseEntity(typeAccount, HttpStatus.FOUND);
        }
        return new ResponseEntity("ID NO VALIDO", HttpStatus.BAD_REQUEST);
    }

    //Traer moneda por id
    @GetMapping(path = "/currency/{id}")
    ResponseEntity<Currency> getCurrencyById(@PathVariable("id") int id) {
        Optional<Currency> currency = currencyRepository.findById(id);
        if (currency.isPresent()) {
            return new ResponseEntity(currency, HttpStatus.FOUND);
        }
        return new ResponseEntity("ID NO VALIDO", HttpStatus.BAD_REQUEST);
    }

    //Traer por id  payment
    @GetMapping(path = "/payment/{id}")
    ResponseEntity<Payment> getPaymentById(@PathVariable("id") int id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            return new ResponseEntity(payment, HttpStatus.FOUND);
        }
        return new ResponseEntity("ID NO VALIDO", HttpStatus.BAD_REQUEST);
    }

    ///Traer orden de extraccion por cuenta
    @GetMapping(path = "/extractionorder/account/{idAccount}")
    ResponseEntity<ExtractionOrder> getExtractionOrderByAccount(@PathVariable("idAccount") int idAccount) {
        List<ExtractionOrder> orderList = extractionOrderRepository.OrderList(idAccount);

        if (orderList.isEmpty()) {
            return new ResponseEntity("NO HAY ORDER DE EXTRACCION ASOCIADO A ESTA CUENTA", HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity(orderList, HttpStatus.FOUND);
    }

    //Traer depositos
    @GetMapping(path = "/deposit")
    List<Deposit> getDepositAll() {
        return (List<Deposit>) depositRepository.findAll();
    }

    /////Consulta personalizada traer los clientes LIKE
    @GetMapping(path = "/customer/like/{param}")
    ResponseEntity getCustomerLike(@PathVariable("param") String param) {
        List<Customer> customers = customerRepository.getAllCustomerByLike(param);

        if (customers.isEmpty() != true) {
            return new ResponseEntity<>(customers, HttpStatus.FOUND);
        } else {
            return new ResponseEntity("NO HAY CLIENTE PARECIDO A " + param, HttpStatus.BAD_REQUEST);
        }
    }


    //////////////*********************** POST****************************** /////
    //Creamos un cliente nuevo con una cuenta de ahorro en pesos con saldo 0.00 por default y devolvemos el cliente creado
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            Account newAccount = new Account();// creamos nueva cuenta
            Optional<Currency> currency1 = currencyRepository.findById(1); // asignamos por default la moneda en pesos
            Optional<TypeAccount> typeAccount = typeAccountRepository.findById(1); //buscamos tipo de cuenta para que sea de ahorro
            //Asignamos a una cuenta nueva, un tipo de cuenta AHORRO y en moneda PESOS
            newAccount.setTypeAccount(typeAccount.get());//Seteamos a la nueva cuenta el nuevo tipo de cuenta buscado anteriormente
            newAccount.setCurrency(currency1.get());//seteamos a la nueva cuenta el tipo de moneda buscado anteriormente
            newAccount.setAmount(0.00);//Seteamos el monto inicial
            List<Account> accounts = new ArrayList<>();
            accounts.add(newAccount);
            customer.setAccounts(accounts);
            Customer newCustomer = customerRepository.save(customer);//Guardamos el cliente
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /// Creamos un nuevo telefono
    @PostMapping(path = "/newphonenumber", consumes = "application/json", produces = "application/json")
    //Guardamos nuevo telefono
    ResponseEntity<PhoneNumber> newPhoneNumber(@RequestBody PhoneNumber phoneNumber) {
        try {
            Customer customer = customerRepository.findById(phoneNumber.getCostumer().getCustomerId()).get();
            if (customer != null) {
                phoneNumber.setCostumer(customer);
                phoneNumberRepository.save(phoneNumber);
                return new ResponseEntity("Guardado con exito " + phoneNumber, HttpStatus.CREATED);
            } else {
                return new ResponseEntity("no existe un cliente con el id " +
                        customer.getCustomerId(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Creamos una nueva orden de extraccion
    @PostMapping(path = "/customer/{id}/amount/{amount}/account/{id_account}/currency/{idCurrency}/extractionorder")
    ResponseEntity<Account> newOrderExtraction(@PathVariable("id") int id, @PathVariable("amount") double amount,
                                               @PathVariable("id_account") int idAccount,
                                               @PathVariable("idCurrency") int idCurrency) {
        try {
            Customer customer = customerRepository.findById(id).get();
            List<Account> accountList = customer.getAccounts(); //buscamos todas las cuentas del cliente
            for (Account account : accountList) {
                if (account.getIdAccount() == idAccount) { //el numero de cuenta ingresado corresponde a una cuenta del cliente?
                    if (account.getCurrency().getIdCurrency() == idCurrency) { //el tipo de moneda es igual al de la cuenta?
                        if (account.getAmount() >= amount) { // el monto es igual o menor al monto de la cuenta?
                            ExtractionOrder newOrder = new ExtractionOrder();// creamos una nueva orden de extraccion
                            newOrder.setAmountExtraction(amount); // seteamos el monto por el ingresado
                            newOrder.setIdAccount(account);// seteamos el id de la cuenta del cliente
                            newOrder.setDateExtraction(new Date());//seteamos el date al momento de crear la orden de extraccion
                            account.setAmount(account.getAmount() - newOrder.getAmountExtraction());// seteamos el monto de la cuenta
                            extractionOrderRepository.save(newOrder);// guardamos la orden de la extraccion
                            return new ResponseEntity<Account>(account, HttpStatus.FOUND);
                        }
                        return new ResponseEntity("revisar el monto a extraer es mayor al monto disponible",
                                HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity("La moneda colocada no corresponde al tipo de moneda de la cuenta",
                            HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity("El numero de cuenta no corresponde al cliente",
                        HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity("Hubo un problema de:" + e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Intente de nuevo", HttpStatus.BAD_REQUEST);
    }

    //Creamos un deposito que se sumará a la cuenta proporcionada,devuelve la cuenta con la el deposito hecho
    @PostMapping(path = "/deposit", consumes = "application/json", produces = "application/json")
    ResponseEntity<Account> createDeposit(@RequestBody Deposit deposit) {
        //Buscamos por ID la cuenta del deposito
        Account account = accountRepository.findById(deposit.getAccount().getIdAccount()).get();
        try {
            int idCurrencyByAccount = account.getCurrency().getIdCurrency();//Buscamos el id de la moneda
            try {
                //comparamos si el id de la moneda de la cuenta es igual al id de la moneda del deposito
                if (deposit.getCurrency().getIdCurrency() == idCurrencyByAccount) {
                    account.setAmount(account.getAmount() + deposit.getAmount());//seteamos el monto de ese cuenta sumandola
                    try { //Iteramos la lista de clientes que puede tener esa cuenta
                        for (Customer cust : account.getCustomer()) {
                            //consultamos si el dni de la cuenta es igual al dni colocado en el deposito
                            if (cust.getCostumerDni().equalsIgnoreCase(deposit.getDniClient())) {
                                depositRepository.save(deposit);
                            }
                        }
                    } catch (Exception e) {
                        return new ResponseEntity("Hubo un problema de:" + e.getMessage(), HttpStatus.BAD_REQUEST);
                    }
                }
            } catch (Exception e) {
                return new ResponseEntity("Hubo un problema de:" + e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity("Hubo un problema de:" + e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Account>(account, HttpStatus.CREATED);

    }

    //Creamos un pago devolvemos la cuenta con el nuevo monto
    @PostMapping(path = "/payment", consumes = "application/json", produces = "application/json")
    ResponseEntity createPayment(@RequestBody Payment payment) {
        try {
            //El pago puede tener varios clientes buscamos,iteramos la lista de clientes,por cada cliente del pago:
            for (Customer customer : payment.getCustomerList()) {
                //validamos que el cliente exista en el repositorio
                Integer idCustomer = customer.getCustomerId();
                Customer cFound = customerRepository.findById(idCustomer).get();
                if (cFound != null) {
                    //cada cliente puede tener varias cuentas,las iteramos
                    for (Account acc : cFound.getAccounts()) {
                        //Validamos que la moneda del pago sea igual a la moneda de la cuenta
                        //y que el monto de la cuenta tiene que ser mayor o igual al pago
                        if (acc.getCurrency().getIdCurrency() == payment.getCurrency().getIdCurrency()
                                && acc.getAmount().doubleValue() >= payment.getAmount().doubleValue()) {
                            acc.setAmount(acc.getAmount().doubleValue() - payment.getAmount().doubleValue()); //seteamos el monto de la cuenta
                            paymentRepository.save(payment);// guardamos
                            return new ResponseEntity("Se debito correctamente el monto de:"
                                    + payment.getAmount(), HttpStatus.CREATED);
                        } else {
                            return new ResponseEntity("Ups algo salio mal", HttpStatus.BAD_REQUEST);
                        }
                    }
                } else {
                    return new ResponseEntity("EL CLIENTE NO CONTIENE UNA CUENTA", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity("ERROR DEL TIPO" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Intente de nuevo", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/transfer",
            consumes = "application/json", produces = "application/json")
    ResponseEntity<GeneralResponse> createTransfer(@RequestBody Transfer transfer) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            //buscamos en el repo el cliente con el dni de la transferencia
            Customer customer = customerRepository.getCustomerByDni(transfer.getIdCustomer().getCostumerDni());
            //buscamos la cuenta en el repo a traves del id de la cuenta dada en la transferencia
            Account account = accountRepository.findById(transfer.getIdAccount().getIdAccount()).get();
            if (customer != null && account != null) {
                for (Account accCustomer : customer.getAccounts()) {
                    //Validamos que el tipo de moneda de la cuenta del cliente a debitar sea el mismo a la cuenta a recibir
                    if (accCustomer.getCurrency().getIdCurrency() == account.getCurrency().getIdCurrency()) {
                        //Validamos que el monto disponible sea igual o mayor a la transferencia
                        if (accCustomer.getAmount() >= transfer.getAmount()) {
                            //restamos el monto en la cuenta a debitar
                            accCustomer.setAmount(accCustomer.getAmount() - transfer.getAmount());
                            //seteamos el monto de la cuenta a recibir
                            account.setAmount(account.getAmount() + transfer.getAmount());
                            transferRepository.save(transfer);
                        } else {
                            generalResponse.setCode(HttpStatus.BAD_REQUEST.value());
                            generalResponse.setMessage("EL MONTO DE LA TRANSFERENCIA ES MAYOR A LA DISPONIBLE");
                            return ResponseEntity.badRequest().body(generalResponse);
                        }
                    } else {
                        generalResponse.setCode(HttpStatus.BAD_REQUEST.value());
                        generalResponse.setMessage("EL TIPO DE MONEDA NO COINCIDE");
                        return ResponseEntity.badRequest().body(generalResponse);
                    }
                }
            } else {
                generalResponse.setCode(HttpStatus.BAD_REQUEST.value());
                generalResponse.setMessage("ERROR,REVISAR EL DNI DEL CLIENTE Y/O EL ID DE LA CUENTA");
                return ResponseEntity.badRequest().body(generalResponse);
            }
        } catch (Exception e) {
            return new ResponseEntity("ERROR DEL TIPO" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        generalResponse.setCode(HttpStatus.ACCEPTED.value());
        generalResponse.setMessage("TRANSFERENCIA HECHA CON EXITO! con el id: " + transfer.getIdTransfer());
        return ResponseEntity.accepted().body(generalResponse);
    }


    ////////////////////////DELETE ////////////////
    // eliminamos un pago devolvemos el id del pago eliminado y hacemos el reintegro del importe del pago
    @DeleteMapping(path = "/payment/{id}")
    @ResponseBody
    ResponseEntity<GeneralResponse> deleteById(@PathVariable("id") int id) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            Payment payment = paymentRepository.findById(id).get();
            for (Customer customer : payment.getCustomerList()) { //Buscamos los clientes que hicieron el pago
                for (Account acc : customer.getAccounts()) {// de esos clientes buscamos sus cuentas
                    //si la moneda del pago es igual a la moneda de la cuenta eliminamos
                    if (acc.getCurrency().getIdCurrency() == payment.getCurrency().getIdCurrency()) {
                        acc.setAmount(acc.getAmount() + payment.getAmount()); //Seteamos el monto de la cuenta
                        paymentRepository.deleteById(id); //eliminamos el pago
                    } else {
                        generalResponse.setCode(HttpStatus.BAD_REQUEST.value());
                        generalResponse.setMessage("error al eliminar");
                        return ResponseEntity.badRequest().body(generalResponse);
                    }
                }
            }
            generalResponse.setCode(HttpStatus.ACCEPTED.value());
            generalResponse.setMessage(HttpStatus.ACCEPTED.getReasonPhrase() + "Pago eliminado con el id: " + id);
            return ResponseEntity.accepted().body(generalResponse);
        } catch (Exception e) {
            generalResponse.setCode(HttpStatus.CONFLICT.value());
            generalResponse.setMessage(HttpStatus.CONFLICT.getReasonPhrase() + " " + e.getMessage());
            return ResponseEntity.badRequest().body(generalResponse);
        }

    }

    //Eliminamos un cliente devolvemos un generalResponse con el id
    @DeleteMapping(path = "/customer/{id}")
    @ResponseBody
    ResponseEntity<GeneralResponse> deleteCustomerById(@PathVariable("id") int id) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            customerRepository.deleteById(id);
            generalResponse.setCode(HttpStatus.ACCEPTED.value());
            generalResponse.setMessage(HttpStatus.ACCEPTED.getReasonPhrase() + " cliente eliminado con el id: " + id);
            return ResponseEntity.accepted().body(generalResponse);
        } catch (Exception e) {
            generalResponse.setCode(HttpStatus.CONFLICT.value());
            generalResponse.setMessage(HttpStatus.CONFLICT.getReasonPhrase() + " " + e.getMessage());
            return ResponseEntity.badRequest().body(generalResponse);
        }

    }

    /////////////////////////////////////////UPDATE /////////////////////////////////////
    //Actualiza el dni del cliente buscado por id devolvemos el id del cliente actualizado
    @PutMapping(path = "/customer/{id}/update/dni/{dni}")
    @ResponseBody
    ResponseEntity<GeneralResponse> updateCustomerById(@PathVariable("id") int id,
                                                       @PathVariable("dni") String dni) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            Customer customer = customerRepository.findById(id).get();
            customer.setCostumerDni(dni);
            customerRepository.save(customer);
            generalResponse.setCode(HttpStatus.ACCEPTED.value());
            generalResponse.setMessage(HttpStatus.ACCEPTED.getReasonPhrase() + " DNI del cliente actualizado  con el id: "
                    + id);
            return ResponseEntity.accepted().body(generalResponse);
        } catch (Exception e) {
            generalResponse.setCode(HttpStatus.CONFLICT.value());
            generalResponse.setMessage(HttpStatus.CONFLICT.getReasonPhrase() + " " + e.getMessage());
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }

    //Actualizamos a traves del id el password devolvemos el id del cliente actualizado
    @PutMapping(path = "/customer/{id}/update/password/{password}")
    @ResponseBody
    ResponseEntity<GeneralResponse> updatePasswordById(@PathVariable("id") int id,
                                                       @PathVariable("password") int password) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            Customer customer = customerRepository.findById(id).get();
            customer.setPassword(password);
            customerRepository.save(customer);
            generalResponse.setCode(HttpStatus.ACCEPTED.value());
            generalResponse.setMessage(HttpStatus.ACCEPTED.getReasonPhrase() + " password del cliente actualizado con el id: "
                    + id);
            return ResponseEntity.accepted().body(generalResponse);
        } catch (Exception e) {
            generalResponse.setCode(HttpStatus.CONFLICT.value());
            generalResponse.setMessage(HttpStatus.CONFLICT.getReasonPhrase() + " " + e.getMessage());
            return ResponseEntity.badRequest().body(generalResponse);
        }
    }
}










