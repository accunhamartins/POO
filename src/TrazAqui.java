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
        ler.parse(ru, rl, rt, rv, ea);

    }
}
