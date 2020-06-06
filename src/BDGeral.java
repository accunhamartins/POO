import java.io.*;
import java.util.Map;

public class BDGeral implements Serializable {
    private BDVoluntarios voluntarios;
    private BDUtilizador utilizadores;
    private BDLojas lojas;
    private BDTransportes transportes;
    private BDProdutos produtos;


    public BDGeral(){
        this.voluntarios = new BDVoluntarios();
        this.utilizadores = new BDUtilizador();
        this.lojas = new BDLojas();
        this.transportes = new BDTransportes();
        this.produtos = new BDProdutos();
    }

    public BDGeral(BDVoluntarios v, BDUtilizador u, BDLojas l, BDTransportes t, BDProdutos p){
        this.voluntarios = v.clone();
        this.utilizadores = u.clone();
        this.lojas = l.clone();
        this.transportes = t.clone();
        this.produtos = p.clone();
    }

    public BDGeral(BDGeral b){
        this.voluntarios = b.getVoluntarios();
        this.utilizadores = b.getUtilizadores();
        this.lojas = b.getLojas();
        this.transportes = b.getTransportes();
        this.produtos = b.getProdutos();
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

    public BDProdutos getProdutos() { return this.produtos.clone();}

    public void addVoluntario(Voluntario v){
        if(this.voluntarios.existe(v)){
            System.out.println("Já existe esse voluntário");
        }
        else this.voluntarios.add(v);
    }

    public void addVoluntarioDisponivel(Voluntario v){
        this.voluntarios.add(v);
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

    public void addProduto(LinhaEncomenda p){
        if (!this.produtos.existe(p.getDescricao())) this.produtos.add(p);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("=================== TOTAL DE UTILIZADORES REGISTADOS NO SISTEMA ===================");
        sb.append("-------------Utilizadores----------------------- \n");
        sb.append(this.utilizadores.toString() + "\n");
        sb.append("-------------Empresas de transportes------------ \n");
        sb.append(this.transportes.toString() + "\n");
        sb.append("-------------Voluntários------------------------ \n");
        sb.append(this.voluntarios.toString() + "\n");
        sb.append("-------------Lojas------------------------------\n");
        sb.append(this.lojas.toString() + "\n");
        sb.append("-------------Produtos------------------------------\n");
        sb.append(this.produtos.toString() + "\n");

        return sb.toString();
    }

    public void updateLoja(Encomenda e, Loja j){
        this.lojas.updateLoja(e, j);
    }

    public void updateLoja2(Encomenda e ,Loja j){
        this.lojas.updateLoja2(e,j);
    }

    public void updateUser(Encomenda e, Utilizador j){
        this.utilizadores.updateUser(e, j);
    }

    public void updateVoluntario2(Voluntario v){
        this.voluntarios.updateVoluntario2(v);
    }

    public void updateVoluntario(Double classificacao, Voluntario v){
        this.voluntarios.updateVoluntario(v, classificacao);
    }

    public void updateTransportes(Double classificacao, EmpresaTransportes e){
        this.transportes.updateTransporte(e, classificacao);
    }

    public BDGeral clone(){
        return new BDGeral(this);
    }

    public Utilizador loginUser(String email, String password) throws UserNotFoundException{
        Utilizador aux;
        aux = this.utilizadores.tryLogin(email, password);
        if(aux == null) throw new UserNotFoundException();
        else return aux;
    }

    public Voluntario loginVoluntario(String email, String password) throws VoluntarioNotFoundException{
        Voluntario aux;
        aux = this.voluntarios.tryLogin(email, password);
        if(aux == null) throw new VoluntarioNotFoundException();
        else return aux;
    }

    public Loja loginLoja(String email, String password) throws LojaNotFoundException{
        Loja aux;
        aux = this.lojas.tryLogin(email, password);
        if(aux == null) throw new LojaNotFoundException();
        else return aux;
    }

    public EmpresaTransportes loginEmpresa(String email, String password) throws TransporteNotFoundException{
        EmpresaTransportes aux;
        aux = this.transportes.tryLogin(email, password);
        if(aux == null) throw new TransporteNotFoundException();
        else return aux;
    }

    public void gravarFicheiro(String filename) throws IOException, FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public BDGeral lerFicheiro(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        BDGeral d = (BDGeral) ois.readObject();
        ois.close();
        return d;
    }

}
