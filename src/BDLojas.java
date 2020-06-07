import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BDLojas implements Serializable {
    private Map<String, Loja> lojas;
    private Set<String> codigos;

    /**
     * Construtores
     */

    public BDLojas(){
        this.lojas = new HashMap<>();
        this.codigos = new TreeSet<>();
    }

    public BDLojas(Map<String, Loja> loja, Set<String> codigos){
        setLojas(loja);
        setCodigos(codigos);
    }

    public BDLojas(BDLojas r){
        setLojas(r.getLojas());
        setCodigos(r.getCodigos());
    }

    //Getters

    public Map<String, Loja> getLojas() {
        return this.lojas.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().clone()));
    }

    public Set<String> getCodigos() {
        return this.codigos.stream().collect(Collectors.toSet());
    }

    //Setters
    public void setCodigos(Set<String> codigos) {
        this.codigos = new TreeSet<>();
        for(String s: codigos) this.codigos.add(s);
    }

    public void setLojas(Map<String, Loja> lojas) {
        this.lojas = new HashMap<>();
        lojas.entrySet().forEach(e -> this.lojas.put(e.getKey(), e.getValue().clone()));
    }


    public BDLojas clone(){
        return new BDLojas(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Total de Lojas: ").append("\n");
        sb.append(this.lojas);

        return sb.toString();
    }

    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        BDLojas r = (BDLojas) obj;
        return this.lojas.equals(r.getLojas());
    }

    /**
     * Método que verifica se uma loja existe
     * @param v é a loja que se pretende saber se existe
     * @return
     */

    public boolean existe(Loja v){
        return this.lojas.keySet().contains(v.getEmail());
    }

    /**
     * Método que adiciona uma encomenda a uma loja
     * @param e é a encomenda
     * @param j é a loja
     */
    public void updateLoja(Encomenda e, Loja j){
        j.addEncomenda(e);
        this.lojas.put(j.getEmail(), j);
    }

    /**
     * Método que remove uma encomenda de uma loja
     * @param e é a encomenda
     * @param j é a loja
     */

    public void updateLoja2(Encomenda e ,Loja j){
        j.removeEncomenda(e);
        this.lojas.put(j.getEmail(), j);
    }

    /**
     * Método que verifica se um código de uma loja existe
     * @param s é o código a verificar
     * @return
     */

   public boolean existeCodigo(String s){
        return this.codigos.contains(s);
    }

    /**
     * Método que adiciona uma loja
     * @param l
     */

    public void add(Loja l){
        this.lojas.put(l.getEmail(), l.clone());
    }

    /**
     * Método que tenta efetuar o login de uma loja no sistema
     * @param email
     * @param password
     * @return
     */
    public Loja tryLogin(String email, String password){
        Loja aux = this.lojas.get(email);
        if(aux == null) System.out.println("Não existe essa loja");
        else{
            if(aux.getPassword().equals(password)){
                System.out.println("Login feito com sucesso");
                return aux;
            }
            else{
                System.out.println("Password incorreta");
                return null;
            }
        }
        return aux;
    }

    /**
     * Método que imprime as lojas, indicando a distância a uma determinado utilizador doméstico
     * @param u é o utilizador que serve de referência
     * @return
     */
    public String listLojasUser(Utilizador u){
        StringBuilder sb = new StringBuilder();
        sb.append("LISTA DE LOJAS\n");
        for(String s: this.lojas.keySet()){
            Loja j = this.lojas.get(s);
            double dist = DistanceCalculator.distance(u.getLatitude(), j.getLatitude(), u.getLongitude(), j.getLongitude());
            sb.append(this.lojas.get(s).getCodigo() + " --> " + this.lojas.get(s).getNome() + " ----> DIST:  " + dist +" KMS" +"\n");
        }
        return sb.toString();
    }

    /**
     * Nétodo que imprime as lojas, indicando a distância a um determinado voluntário
     * @param u
     * @return
     */

    public String listLojasVol(Voluntario u){
        StringBuilder sb = new StringBuilder();
        sb.append("LISTA DE LOJAS\n");
        int count = 0;
        for(String s: this.lojas.keySet()) {
            Loja j = this.lojas.get(s);
            double dist = DistanceCalculator.distance(u.getLatitude(), j.getLatitude(), u.getLongitude(), j.getLongitude());
            if (dist <= u.getRaio_acao()) {
                sb.append(this.lojas.get(s).getCodigo() + " --> " + this.lojas.get(s).getNome() + " ----> DIST: " + dist + " KMS" + "\n");
                count++;
            }
        }
        if(count == 0) sb.append("Não existem lojas no seu raio de ação");
        return sb.toString();
    }

    /**
     * Método que devolve o email de uma loja, quando lhe é fornecido o seu código
     * @param cod
     * @return
     * @throws LojaNotFoundException
     */

    public String getEmail(String cod) throws LojaNotFoundException{
        for(String s: this.lojas.keySet()){
            if(this.lojas.get(s).getCodigo().equals(cod)) return this.lojas.get(s).getEmail();
        }
        throw new LojaNotFoundException();
    }
}
