public class TrazAqui{

    public static void main(String[] args) {
        Transportes t = new Transportes();
        EmpresaTransportes e = new EmpresaTransportes();
        Encomenda enc = new Encomenda();
        Voluntarios v = new Voluntarios();
        Lojas l = new Lojas();
        LinhaEncomenda le = new LinhaEncomenda();
        Utilizador user = new Utilizador();
        EncomendasAceites ea = new EncomendasAceites();

        Parse ler = new Parse();
        ler.parse();

    }
}
