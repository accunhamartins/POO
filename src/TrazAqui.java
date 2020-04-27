public class TrazAqui{

    public static void main(String[] args) {
        Transportes t = new Transportes();
        EmpresaTransportes e = new EmpresaTransportes();
        Encomenda enc = new Encomenda();
        Voluntario v = new Voluntario();
        Loja l = new Loja();
        LinhaEncomenda le = new LinhaEncomenda();
        Utilizador user = new Utilizador();
        EncomendasAceites ea = new EncomendasAceites();
        RegistosUsers ru = new RegistosUsers();
        RegistosLojas rl = new RegistosLojas();
        RegistosTransportes rt = new RegistosTransportes();
        RegistosVoluntarios rv = new RegistosVoluntarios();

        Parse ler = new Parse();
        ler.parse();


        ru.setUsers(ler.getRu().getUsers());
        rv.setVoluntarios(ler.getRv().getVoluntarios());
        rl.setLojas(ler.getRl().getLojas());
        rt.setTransportes(ler.getRt().getTransportes());
        ea.setAceites(ler.getEa().getAceites());

        System.out.println(ru.toString());
        System.out.println("\n");
        System.out.println(rv.toString());
        System.out.println("\n");
        System.out.println(rl.toString());
        System.out.println("\n");
        System.out.println(rt.toString());
        System.out.println("\n");
        System.out.println(ea.toString());
        System.out.println("\n");
        System.out.println("done!");

    }
}
