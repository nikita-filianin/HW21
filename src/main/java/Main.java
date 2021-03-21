import entity.Accounts;
import entity.Clients;
import entity.Statuses;
import service.AccountService;
import service.ClientService;
import service.StatusService;

public class Main {
    public static void main(String[] args) {

        Clients client = new Clients();
// INSERT
//        client.setName("Test");
//        client.setEmail("test@mail.com");
//        client.setPhone(380958089010L);
//        client.setAbout("test text");
//        ClientService.save(client);
// UPDATE
//        client.setName("New test");
//        client.setId(12);
//        ClientService.update(client);
// DELETE
//        client.setId(16);
//        ClientService.delete(client);
// FIND_BY_PHONE
//        System.out.println(ClientService.findByPhone(380958089097L));


        Statuses status = new Statuses();
// INSERT
//        status.setAlias("NEW TEST");
//        status.setDescription("TEST");
//        StatusService.save(status);
// UPDATE
//        status.setAlias("NEW TEST");
//        status.setDescription("TEST");
//        status.setId(2);
//        StatusService.update(status);
// DELETE
//        status.setId(4);
//        StatusService.delete(status);


        Accounts account = new Accounts();
// INSERT
//        account.setClient_id(3);
//        account.setNumber("UA TEST NUMBER");
//        account.setValue(1923.3);
//        AccountService.save(account);
// UPDATE
//        account.setClient_id(5);
//        account.setNumber("UA ANOTHER");
//        account.setValue(2000);
//        account.setId(9);
//        AccountService.update(account);
// DELETE
//        account.setId(11);
//        AccountService.delete(account);
//        System.out.println(AccountService.getAll());

        System.out.println(StatusService.getClientAndStatus());
        System.out.println(AccountService.findByValue(200));
        System.out.println(AccountService.getClientByIdMatch());
        System.out.println(StatusService.getClientAndStatus());

    }
}