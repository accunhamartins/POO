import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Parse {
    private Map<String, UtilizadorSistema> users;
    private EncomendasAceites ea;
    private Map<String, String> passwords;


    public Parse(){
       this.users = new HashMap<>();
       this.passwords = new HashMap<>();
       this.ea = new EncomendasAceites();
    }

    public Parse(Map<String, UtilizadorSistema> users, Map<String, String> passwords, EncomendasAceites ea){
        setUsers(users);
        setPasswords(passwords);
        this.ea.setAceites(ea.getAceites());

    }

    public Parse (Parse a){
        setUsers(a.getUsers());
        setPasswords(a.getPasswords());
        setEa(a.getEa());
    }

    public Map<String, UtilizadorSistema> getUsers() {
        return this.users.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue().clone()));
    }

    public EncomendasAceites getEa() {
        return new EncomendasAceites(this.ea.getAceites());
    }

    public void setEa(EncomendasAceites ea) {
        this.ea = new EncomendasAceites();
        this.ea.setAceites(ea.getAceites());
    }

    public void setUsers(Map<String, UtilizadorSistema> users) {
        this.users = new TreeMap<>();
        users.entrySet().forEach(e -> this.users.put(e.getKey(), e.getValue().clone()));
    }

    public Map<String, String> getPasswords() {
        return this.passwords.entrySet().stream().collect(Collectors.toMap(r -> r.getKey(), r -> r.getValue()));
    }

    public void setPasswords(Map<String, String> passwords) {
        this.passwords = new TreeMap<>();
        passwords.entrySet().forEach(e -> this.passwords.put(e.getKey(), e.getValue()));
    }


    public void parse(){
        List<String> ler = lerFicheiro("logs.txt");
        String[] linhaPartida;
        for (String linha : ler) {
            linhaPartida = linha.split(":", -1);
            switch(linhaPartida[0]){
                case "Utilizador":
                    Utilizador u = parseUtilizador(linhaPartida[1]);
                    this.add(u);
                    break;
                case "Loja":
                    Loja l = parseLojas(linhaPartida[1]);
                    this.add(l);
                    break;
                case "Transportadora":
                    EmpresaTransportes t = parseEmpresaTransportes(linhaPartida[1]);
                    this.add(t);
                    break;
                case "Voluntario":
                    Voluntario v = parseVoluntarios(linhaPartida[1]);
                    this.add(v);
                    break;
                case "Encomenda":
                    Encomenda e = parseEncomenda(linhaPartida[1]);
                    break;
                case "Aceite":
                    this.ea = parseEncomendasAceites(linhaPartida[1], ea);
                    break;
                default:
                    System.out.println("Linha inválida.");
                    break;
            }

        }

    }

    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines = new ArrayList<>();
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { System.out.println("Erro ao dar load dos logs."); }
        return lines;
    }

    public EncomendasAceites parseEncomendasAceites(String linha, EncomendasAceites ea){
        List<String> aux = ea.getAceites();
        aux.add(linha);
        ea.setAceites(aux);
        return ea;
    }

    public Loja parseLojas(String input){
      String []campos = input.split(",");
      String codigo = campos[0];
      String nome = campos[1];
      double latitude = Double.parseDouble(campos[2]);
      double longitude = Double.parseDouble(campos[3]);
      String email = codigo + "@gmail.com";
      String password = "12345";
      return new Loja(email, password, codigo, nome, false, 0.0, 0.0, 0.0, latitude, longitude, new ArrayList<>());
    }

    public Utilizador parseUtilizador(String input){
      String []campos = input.split(",");
      String codigo = campos[0];
      String nome = campos[1];
      double latitude = Double.parseDouble(campos[2]);
      double longitude = Double.parseDouble(campos[3]);
      String email = codigo + "@gmail.com";
      String password = "12345";
      return new Utilizador(email, password, codigo, nome, latitude, longitude);
    }

    public Voluntario parseVoluntarios(String input){

      String []campos = input.split(",");
      String codigo = campos[0];
      String nome = campos[1];
      double latitude = Double.parseDouble(campos[2]);
      double longitude = Double.parseDouble(campos[3]);
      double raio_acao = Double.parseDouble(campos[4]);
      String email = codigo + "@gmail.com";
      String password = "12345";
      return new Voluntario(email, password, nome, codigo, false, latitude, longitude, LocalDate.now(), raio_acao, new ArrayList<>());
    }

    public EmpresaTransportes parseEmpresaTransportes(String input){
        String []campos = input.split(",");
        String codigo = campos[0];
        String nome = campos[1];
        double latitude = Double.parseDouble(campos[2]);
        double longitude = Double.parseDouble(campos[3]);
        int nif = Integer.parseInt(campos[4]);
        double raioDeAcao = Double.parseDouble(campos[5]);
        double custo_km = Double.parseDouble(campos[6]);
        String email = codigo + "@gmail.com";
        String password = "12345";
        return new EmpresaTransportes(email, password,codigo, nome, nif, custo_km, " ", latitude, longitude, raioDeAcao, new ArrayList<>(), new ArrayList<>(), 0, false);
    }

    public Encomenda parseEncomenda(String input){
        Map<String, LinhaEncomenda> produtos = new HashMap<>();
        String []campos = input.split("," );
        String codigo = campos[0];
        String codigo_user = campos[1];
        String codigo_loja = campos[2];
        double peso = Double.parseDouble(campos[3]);
        for(int i = 4; i < campos.length; i += 4){
            String aux = campos[i] + "," + campos[i+1] + "," + campos[i+2] + "," + campos[i+3];
            LinhaEncomenda le = parseLinhaEncomenda(aux);
            produtos.put(le.getCodigo(), le.clone());

        }
        return new Encomenda(codigo, codigo_user, codigo_loja, peso, " ", " ", produtos,false);
    }


    public LinhaEncomenda parseLinhaEncomenda(String input){
        String []campos = input.split(",");
        String codigo = campos[0];
        String descricao = campos[1];
        double quantidade = Double.parseDouble(campos[2]);
        double preco = Double.parseDouble(campos[3]);

        return new LinhaEncomenda(codigo, descricao, preco, quantidade, false);
    }

    public void add(UtilizadorSistema u){
        this.users.put(u.getEmail(), u.clone());
        this.passwords.put((u.getEmail()), u.getPassword());
    }
}
