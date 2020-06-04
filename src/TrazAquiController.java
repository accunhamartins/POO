import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TrazAquiController implements Serializable {
    private BDGeral bd;
    private TrazAquiView view;

    public void setView(TrazAquiView view){this.view = view;}

    public void setBd(BDGeral bd){
        this.bd = bd;
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public BDGeral readFlow() throws IOException, ClassNotFoundException {
        int op;
        Input input = new Input();
        clearScreen();
        this.view.loadMenu();
        do {
            op = input.lerInt();
            switch (op) {
                case 1:
                    Parse ler = new Parse();
                    ler.parse();
                    BDGeral base = new BDGeral(ler.getBaseGeral());
                    System.out.println("DONE!");
                    return base;
                case 2:
                    System.out.println("Insira o ficherio a ler");
                    String aux = Input.lerString();
                    this.bd = new BDGeral();
                    BDGeral base2 = this.bd.lerFicheiro(aux);
                    System.out.println("DONE!");
                    return base2;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (true);
    }

    public void mainFlow() throws IOException, ClassNotFoundException, InterruptedException{
        int op;
        Input input = new Input();
        this.view.printHeader();
        this.view.showMenuInicial();
        do{
            op = input.lerInt();
            switch(op){
                case 0:
                    return;
                case 1:
                    clearScreen();
                    registosFlow();
                    break;
                case 2:
                    clearScreen();
                    loginFlow();
                    break;
                case 3:
                    clearScreen();
                    System.out.println("Insira o nome com que quer gravar");
                    String l = Input.lerString();
                    this.bd.gravarFicheiro(l);
                    System.out.println("DONE");
                    System.out.println("Insira 5 para voltar a imprimir o menu");
                    break;
                case 5:
                    clearScreen();
                    this.view.showMenuInicial();
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
        while (op != 0);
    }

    private void registosFlow(){
        int op;
        Random random = new Random();
        Input input = new Input();
        clearScreen();
        String password = "", nome = "", email = "", local = "";
        Double latitude = 0.0, longitude = 0.0, raio = 0.0 ,tempo_espera = 0.0, custo = 0.0;
        String codigo = "";
        int nif = 0, nrMinimo = 0;
        boolean medico = false;
        this.view.showMenuRegisto();
        do {
            op = input.lerInt();
            switch (op) {
                case 0:
                    clearScreen();
                    this.view.showMenuInicial();
                    return;
                case 1:
                    try {
                        clearScreen();
                        this.view.headRegistoUser();
                        this.view.preenchimentoObrg();
                        this.view.email();
                        email = input.lerString();
                        this.view.password();
                        password = input.lerString();
                        this.view.nome();
                        nome = input.lerString();
                        this.view.latitude();
                        latitude = input.lerDouble();
                        this.view.longitude();
                        longitude = input.lerDouble();
                        codigo = "u" + random.nextInt(99);
                        while (this.bd.getUtilizadores().existeCodigo(codigo)) codigo = "u" + random.nextInt(99);
                    } catch (InputMismatchException e) {
                        input.lerString();
                        System.out.println("Input inválido");
                    }
                    bd.addUser(new Utilizador(email, password, codigo, nome, latitude, longitude, new ArrayList<>()));
                    System.out.println("REGISTO EFETUADO COM SUCESSO");
                    System.out.println("PRIMA 0 PARA VOLTAR AO MENU INICIAL");
                    break;
                case 2:
                    try {
                        clearScreen();
                        this.view.headRegistoVoluntario();
                        this.view.preenchimentoObrg();
                        this.view.email();
                        email = input.lerString();
                        this.view.password();
                        password = input.lerString();
                        this.view.nome();
                        nome = input.lerString();
                        this.view.latitude();
                        latitude = input.lerDouble();
                        this.view.longitude();
                        longitude = input.lerDouble();
                        this.view.raio_acao();
                        raio = input.lerDouble();
                        codigo = "v" + random.nextInt(99);
                        while (bd.getVoluntarios().existeCodigo(codigo)) codigo = "v" + random.nextInt(99);
                    } catch (InputMismatchException e) {
                        input.lerString();
                        System.out.println("Input inválido");
                    }
                    bd.addVoluntario(new Voluntario(email, password, nome, codigo, true, latitude, longitude, LocalDate.now(), raio, new ArrayList<>(), 0, 0));
                    System.out.println("REGISTO EFETUADO COM SUCESSO");
                    System.out.println("PRIMA 0 PARA VOLTAR AO MENU INICIAL");
                    break;
                case 3:
                    try {
                        clearScreen();
                        this.view.headRegistoLoja();
                        this.view.preenchimentoObrg();
                        this.view.email();
                        email = input.lerString();
                        this.view.password();
                        password = input.lerString();
                        this.view.nome();
                        nome = input.lerString();
                        this.view.latitude();
                        latitude = input.lerDouble();
                        this.view.longitude();
                        longitude = input.lerDouble();
                        this.view.tempo_espera();
                        tempo_espera = input.lerDouble();
                        codigo = "l" + random.nextInt(99);
                        while (bd.getLojas().existeCodigo(codigo)) codigo = "l" + random.nextInt(99);
                    } catch (InputMismatchException e) {
                        input.lerString();
                        System.out.println("Input inválido");
                    }
                    bd.addLoja(new Loja(email, password, codigo, nome, tempo_espera, latitude, longitude, new ArrayList<>()));
                    System.out.println("REGISTO EFETUADO COM SUCESSO");
                    System.out.println("PRIMA 0 PARA VOLTAR AO MENU INICIAL");
                    break;
                case 4:
                    try {
                        clearScreen();
                        this.view.headRegistoEmpresa();
                        this.view.preenchimentoObrg();
                        this.view.email();
                        email = input.lerString();
                        this.view.password();
                        password = input.lerString();
                        this.view.nome();
                        nome = input.lerString();
                        this.view.custo_km();
                        custo = input.lerDouble();
                        this.view.latitude();
                        latitude = input.lerDouble();
                        this.view.longitude();
                        longitude = input.lerDouble();
                        this.view.local();
                        local = input.lerString();
                        this.view.raio_acao();
                        raio = input.lerDouble();
                        this.view.nif();
                        nif = input.lerInt();
                        this.view.nr_min_encomendas();
                        nrMinimo = input.lerInt();
                        this.view.encomendas_medicas();
                        medico = input.lerBoolean();;
                        codigo = "t" + random.nextInt(99);
                        while (bd.getTransportes().existeCodigo(codigo)) codigo = "t" + random.nextInt(99);
                    }
                    catch(InputMismatchException e){
                        input.lerString();
                        System.out.println("Input inválido");
                    }
                    bd.addTransporte(new EmpresaTransportes(email, password, codigo, nome, nif,custo,local,latitude,longitude,raio, new ArrayList<>(), nrMinimo, medico, 0, 0));
                    System.out.println("REGISTO EFETUADO COM SUCESSO");
                    System.out.println("PRIMA 0 PARA VOLTAR AO MENU INICIAL");
                    break;
                default:
                    System.out.println("Opcao inválida");
                    break;
            }
        }
        while (op != 0);
    }

    private void loginFlow() throws InterruptedException{
        int op;
        Input input = new Input();
        clearScreen();
        this.view.showMenuLogin();
        do {
            op = input.lerInt();
            switch (op) {
                case 0:
                    clearScreen();
                    this.view.showMenuInicial();
                    return;
                case 1:
                    userFlow();
                    break;
                case 2:
                    voluntarioFlow();
                    break;
                case 3:
                    lojaFlow();
                    break;
                case 4:
                   // empresaFlow();
                    break;
                default:
                    System.out.println("Opcao inválida");
                    break;
            }
        }
        while (op != 0);
    }

    private void voluntarioFlow(){
        int op;
        Input input = new Input();
        clearScreen();
        this.view.headLoginVoluntario();
        this.view.email();
        String email = input.lerString();
        this.view.password();
        String password = input.lerString();
        try{
            Voluntario v = bd.loginVoluntario(email, password);
            clearScreen();
            this.view.showMenuVoluntario();
            do {
                op = input.lerInt();
                switch (op) {
                    case 0:
                        clearScreen();
                        this.view.showMenuLogin();
                        return;
                    case 1:
                        Voluntario aux = this.bd.getVoluntarios().getVoluntarios().get(v.getEmail());
                        aux.setDisponibilidade(true);
                        this.bd.addVoluntarioDisponivel(aux);
                        System.out.println("Está disponível para levantar encomendas.");
                        System.out.println("Insira uma nova opção: ");
                        break;
                    case 2:
                        System.out.println("Pedidos de entrega: ");
                        int size = this.bd.getVoluntarios().getVoluntarios().get(v.getEmail()).getHistorico().size();
                        if(size == 0) System.out.println("Não tem pedidos de encomendas");
                        else {
                            System.out.println(this.bd.getVoluntarios().getVoluntarios().get(v.getEmail()).getHistorico());
                        }
                        break;
                }


            } while (op != 0);
        } catch (VoluntarioNotFoundException e){
            System.out.println("Email ou password inválidos");
        }
    }

    private void lojaFlow() {
        int op;
        Input input = new Input();
        clearScreen();
        this.view.headLoginLoja();
        this.view.email();
        String email = input.lerString();
        this.view.password();
        String password = input.lerString();
        try {
            Loja lj = bd.loginLoja(email, password);
            clearScreen();
            this.view.showMenuLoja();
            do {
                op = input.lerInt();
                switch (op) {
                    case 0:
                        clearScreen();
                        this.view.showMenuLogin();
                        return;
                    case 1:
                        System.out.println("Conjunto de voluntários disponíveis");
                        System.out.println(this.bd.getVoluntarios().printVoluntario());
                        System.out.println("Selecione o código de um voluntário");
                        String codigo = Input.lerString();
                        try {
                            String emailVol = this.bd.getVoluntarios().getEmail(codigo);
                            Voluntario v = this.bd.getVoluntarios().getVoluntarios().get(emailVol);
                            Encomenda e = lj.getEncomendas_recebidas().get(0).clone();
                            v.addEncomenda(e);
                            Loja aux = this.bd.getLojas().getLojas().get(lj.getEmail());
                            this.bd.updateLoja2(e, aux);
                            this.bd.updateVoluntario2(v);
                            System.out.println("Realizado com sucesso");

                        } catch (VoluntarioNotFoundException e){
                            System.out.println("Voluntário inválido");
                        }
                        break;
                    case 2:
                        System.out.println(this.bd.getLojas().getLojas().get(lj.getEmail()).getEncomendas_recebidas());
                        break;

                }
            } while (op != 0);

        } catch (LojaNotFoundException e) {
            System.out.println("Email ou password inválidos");
        }
    }

    private void userFlow() throws InterruptedException{
        int op;
        Input input = new Input();
        String email = "", password = "", produto = "";
        int quantidade = 0;
        boolean enc_med = false;

        clearScreen();
        this.view.headLoginUser();
        this.view.email();
        email = input.lerString();
        this.view.password();
        password = input.lerString();
        try {
            Utilizador u = bd.loginUser(email, password);
            clearScreen();
            this.view.showMenuUser();
                do {
                    op = input.lerInt();
                    switch (op) {
                        case 0:
                            clearScreen();
                            this.view.showMenuLogin();
                            return;
                        case 1:
                            this.view.headNovaEncomenda();
                            this.view.preenchimentoObrg();
                            System.out.println(bd.getProdutos().listProdutos());
                            this.view.produto();
                            produto = input.lerString();
                            try{
                                this.bd.getProdutos().existeProd(produto);
                                this.view.quantidade();
                                quantidade = input.lerInt();
                                this.view.encomendas_medicas2();
                                enc_med = input.lerBoolean();
                                clearScreen();
                                System.out.println(this.bd.getLojas().listLojasUser(u));
                                System.out.println("Selecione o código de uma loja");
                                String loja = input.lerString();
                                try {
                                    String emailLoja = this.bd.getLojas().getEmail(loja);
                                    LinhaEncomenda enc = new LinhaEncomenda(bd.getProdutos().getProdutos().get(produto).getCodigo(), produto, quantidade, bd.getProdutos().getProdutos().get(produto).getPreco() * quantidade, enc_med);
                                    Map<String, LinhaEncomenda> produtos = new TreeMap<>();
                                    produtos.put(produto, enc);
                                    Random random = new Random();
                                    String cod = "e" + random.nextInt(9999);
                                    Encomenda novaEnc = new Encomenda(cod, u.getCodigo(), loja, quantidade, u.getNome(), this.bd.getLojas().getLojas().get(emailLoja).getNome(), produtos, enc_med);
                                    u.addEncomenda(novaEnc);
                                    this.bd.getLojas().updateLoja(novaEnc, this.bd.getLojas().getLojas().get(emailLoja));
                                    System.out.println("ENCOMENDA FEITA COM SUCESSO. PRIMA 0 PARA SAIR");

                                } catch (LojaNotFoundException e){
                                    System.out.println("Loja inválida");
                                    System.out.println("Prima 0 para retroceder");
                                }

                            } catch (ProductNotFoundException e){
                                System.out.println("Produto não existe");
                                System.out.println("Prima 0 para retroceder");
                            }
                            break;
                        case 2:
                            System.out.println(u.printEncomendas());
                            System.out.println("Insira 5 para imprimir de novo o menu");
                            break;
                        case 3:
                            System.out.println(this.bd.getVoluntarios().printVoluntario());
                            System.out.println("Escolha o código do voluntário a avaliar");
                            String cod = Input.lerString();
                            try {
                                String emailVol = this.bd.getVoluntarios().getEmail(cod);
                                System.out.println("Insira a classificação de 1 a 10:");
                                Double classificacao = Input.lerDouble();
                                if(classificacao >= 0 && classificacao <= 10) {
                                    Voluntario v = this.bd.getVoluntarios().getVoluntarios().get(emailVol);
                                    this.bd.updateVoluntario(classificacao, v);
                                    System.out.println("DONE!");
                                    System.out.println("Insira uma nova opção");
                                } else System.out.println("Classificação inválida!");

                            } catch (VoluntarioNotFoundException e){
                                System.out.println("Voluntário inválido");
                                System.out.println("Prima 0 para retroceder");
                            }
                            break;
                        case 4:
                            System.out.println(this.bd.getTransportes().printTransportes());
                            System.out.println("Escolha o código da empresa de transportes a avaliar");
                            String cod2 = Input.lerString();
                            try {
                                String emailTrans = this.bd.getTransportes().getEmail(cod2);
                                System.out.println("Insira a classificação de 1 a 10:");
                                Double classificacao = Input.lerDouble();
                                if(classificacao >= 0 && classificacao <= 10) {
                                    EmpresaTransportes e = this.bd.getTransportes().getTransportes().get(emailTrans);
                                    this.bd.updateTransportes(classificacao, e);
                                    System.out.println("DONE!");
                                    System.out.println("Insira uma nova opção");
                                } else System.out.println("Classificação inválida!");

                            } catch (TransporteNotFoundException e){
                                System.out.println("Empresa inválida");
                                System.out.println("Prima 0 para retroceder");
                            }
                            break;
                        case 5:
                            clearScreen();
                            this.view.showMenuUser();
                            break;
                        default:
                            System.out.println("Opcao inválida");
                            break;
                    }
                }
                while (op != 0);

            } catch (UserNotFoundException e){
            System.out.println("Email ou password inválidos");
        }
    }


}
