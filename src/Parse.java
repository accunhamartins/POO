import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Parse {
    private BDGeral baseGeral;
    private EncomendasAceites ea;
    private List<Encomenda> encomendas;


    public Parse(){
       this.baseGeral = new BDGeral();
       this.encomendas = new ArrayList<>();
       this.ea = new EncomendasAceites();
    }

    public Parse(BDGeral bd, List<Encomenda> encomendas, EncomendasAceites ea){
        this.baseGeral = bd.clone();
        this.ea = ea.clone();
        setEncomendas(encomendas);

    }

    public Parse (Parse a){
        this.baseGeral = a.getBaseGeral();
        this.ea = a.getEa();
        setEncomendas(a.getEncomendas());
    }

    public BDGeral getBaseGeral() {
        return baseGeral.clone();
    }


    public List<Encomenda> getEncomendas() {
        return this.encomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public void setEncomendas(List<Encomenda> encomendas) {
        this.encomendas = new ArrayList<>();
        for(Encomenda e: encomendas) this.encomendas.add(e.clone());
    }



    public EncomendasAceites getEa() {
        return new EncomendasAceites(this.ea.getAceites());
    }

    public void setEa(EncomendasAceites ea) {
        this.ea = new EncomendasAceites();
        this.ea.setAceites(ea.getAceites());
    }

    public void parse(){
        List<String> ler = lerFicheiro("logs.txt");
        String[] linhaPartida;
        for (String linha : ler) {
            linhaPartida = linha.split(":", -1);
            switch(linhaPartida[0]){
                case "Utilizador":
                    Utilizador u = parseUtilizador(linhaPartida[1]);
                    this.baseGeral.addUser(u);
                    break;
                case "Loja":
                    Loja l = parseLojas(linhaPartida[1]);
                    this.baseGeral.addLoja(l);
                    break;
                case "Transportadora":
                    EmpresaTransportes t = parseEmpresaTransportes(linhaPartida[1]);
                    this.baseGeral.addTransporte(t);
                    break;
                case "Voluntario":
                    Voluntario v = parseVoluntarios(linhaPartida[1]);
                    this.baseGeral.addVoluntario(v);
                    break;
                case "Encomenda":
                    Encomenda e = parseEncomenda(linhaPartida[1]);
                    this.encomendas.add(e.clone());
                    break;
                case "Aceite":
                    this.ea = parseEncomendasAceites(linhaPartida[1], ea);
                    break;
                default:
                    System.out.println("Linha inválida.");
                    break;
            }
        }
        addEncomendas(this.encomendas);
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
      return new Loja(email, password, codigo, nome, 20.0, latitude, longitude, new ArrayList<>());
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
        return new EmpresaTransportes(email, password,codigo, nome, nif, custo_km, " ", latitude, longitude, raioDeAcao, new ArrayList<>(), 0, true);
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

    public void addEncomendas(List<Encomenda> encomendas){
        for(Encomenda e: encomendas){
            for(Loja j: this.baseGeral.getLojas().getLojas().values()){
                if(e.getCodigo_loja().equals(j.getCodigo())){
                    j.addEncomenda(e);
                    this.baseGeral.updateLoja(e, j);
                }
            }
        }
    }

}
