public class TrazAqui{

    public static void main(String[] args) {
        Transportes t = new Transportes();
        EmpresaTransportes e = new EmpresaTransportes();
        Encomenda enc = new Encomenda();
        Voluntario v = new Voluntario();
        Loja l = new Loja();
        LinhaEncomenda le = new LinhaEncomenda();
        Utilizador user = new Utilizador();

        Parse ler = new Parse();
        ler.parse();


        RegistosUsers ru = new RegistosUsers(ler.getRu());
        RegistosVoluntarios  rv = new RegistosVoluntarios(ler.getRv());
        RegistosLojas rl = new RegistosLojas(ler.getRl());
        RegistosTransportes rt = new RegistosTransportes(ler.getRt());
        EncomendasAceites ea = new EncomendasAceites(ler.getEa());

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
