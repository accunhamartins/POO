import java.io.Serializable;

public class BDGeral implements Serializable {
    private BDVoluntarios voluntarios;
    private BDUtilizador utilizadores;
    private BDLojas lojas;
    private BDTransportes transportes;


    public BDGeral(){
        this.voluntarios = new BDVoluntarios();
        this.utilizadores = new BDUtilizador();
        this.lojas = new BDLojas();
        this.transportes = new BDTransportes();
    }

    public BDGeral(BDVoluntarios v, BDUtilizador u, BDLojas l, BDTransportes t){
        this.voluntarios = v.clone();
        this.utilizadores = u.clone();
        this.lojas = l.clone();
        this.transportes = t.clone();
    }

    public BDGeral(BDGeral b){
        this.voluntarios = b.getVoluntarios();
        this.utilizadores = b.getUtilizadores();
        this.lojas = b.getLojas();
        this.transportes = b.getTransportes();
    }


    public BDLojas getLojas() {
        return this.lojas.clone();
    }

    public BDTransportes getTransportes() {
        return this.transportes.clone();
    }

    public BDUtilizador getUtilizadores() {
        return this.utilizadores.clone();
    }

    public BDVoluntarios getVoluntarios() {
        return this.voluntarios.clone();
    }

    public void addVoluntario(Voluntario v){
        if(this.voluntarios.existe(v)){
            System.out.println("Já existe esse voluntário");
        }
        else this.voluntarios.add(v);
    }

    public void addUser(Utilizador u){
        if(this.utilizadores.existe(u)){
            System.out.println("Já existe esse utilizador");
        }
        else this.utilizadores.add(u);
    }

    public void addTransporte(EmpresaTransportes t){
        if(this.transportes.existe(t)){
            System.out.println("Já existe essa empresa de transportes");
        }
        else this.transportes.add(t);

    }

    public void addLoja(Loja l){
        if(this.lojas.existe(l))
            System.out.println("Já existe esse voluntário");
        else this.lojas.add(l);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("=================== TOTAL DE UTILIZADORES RGISTADOS NO SISTEMA ===================");
        sb.append("-------------Utilizadores----------------------- \n");
        sb.append(this.utilizadores.toString() + "\n");
        sb.append("-------------Empresas de transportes------------ \n");
        sb.append(this.transportes.toString() + "\n");
        sb.append("-------------Voluntários------------------------ \n");
        sb.append(this.voluntarios.toString() + "\n");
        sb.append("-------------Lojas------------------------------\n");
        sb.append(this.lojas.toString() + "\n");

        return sb.toString();
    }

    public String userString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-------------Utilizadores----------------------- \n");
        sb.append(this.utilizadores.toString() + "\n");

        return sb.toString();
    }

    public String transporteString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-------------Empresas de transportes------------ \n");
        sb.append(this.transportes.toString() + "\n");

        return sb.toString();
    }

    public String voluntarioString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-------------Voluntários------------------------ \n");
        sb.append(this.voluntarios.toString() + "\n");

        return sb.toString();
    }

    public String lojaString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-------------Lojas------------------------------\n");
        sb.append(this.lojas.toString() + "\n");

        return sb.toString();
    }


    public void updateLoja(Encomenda e, Loja j){
        this.lojas.updateLoja(e, j);
    }


    public BDGeral clone(){
        return new BDGeral(this);
    }

}
