import java.util.HashMap;

public class TrazAqui{

    public static void main(String[] args) {
        Transportes t = new Transportes();
        EmpresaTransportes e = new EmpresaTransportes();
        Encomenda enc = new Encomenda();
        Voluntario v = new Voluntario();
        Loja l = new Loja();
        LinhaEncomenda le = new LinhaEncomenda();
        Utilizador user = new Utilizador();
        MenuLogin menu = new MenuLogin();

        Parse ler = new Parse();
        ler.parse();

        HashMap<String,UtilizadorSistema> users = new HashMap<>(ler.getUsers());
        HashMap<String,String> passwords = new HashMap<>(ler.getPasswords());

        System.out.println(users.toString());
        System.out.println(passwords.toString());

        //menu.showMenus();


    }
}
