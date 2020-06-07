import java.io.IOException;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TrazAquiController implements Serializable {
    private BDGeral bd;
    private TrazAquiView view;

    /**
     * Método que define a view que o controlador irá conhecer
     * @param view
     */

    public void setView(TrazAquiView view){this.view = view;}

    /**
     * Método que define o model, que , neste caso, é a BDGeral, que contém todos os users do sistema.
     * @param bd
     */

    public void setBd(BDGeral bd){
        this.bd = bd;
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Método que define se é para carregar os ficheiros a partir de um ficheiro txt ou de um ficheiro binário
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */

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
                    System.out.println("Insira o ficheiro a ler");
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

    /**
     * Método que controla a execucção de cada uma das opções do menu inicial
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     */

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

    /**
     * Método utilizado para realizar os registos de novos users do sistema
     */

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
                    bd.addUser(new Utilizador(email, password, codigo, nome, latitude, longitude, new ArrayList<>()).clone());
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
                        this.view.encomendas_medicas();
                        medico = input.lerBoolean();
                        while (bd.getVoluntarios().existeCodigo(codigo)) codigo = "v" + random.nextInt(99);
                    } catch (InputMismatchException e) {
                        input.lerString();
                        System.out.println("Input inválido");
                    }
                    bd.addVoluntario(new Voluntario(email, password, nome, codigo, true, latitude, longitude, LocalDate.now(), raio, new ArrayList<>(), 0, 0, medico).clone());
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
                    bd.addLoja(new Loja(email, password, codigo, nome, tempo_espera, latitude, longitude, new ArrayList<>()).clone());
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
                    bd.addTransporte(new EmpresaTransportes(email, password, codigo, nome, nif,custo,local,latitude,longitude,raio, new ArrayList<>(), nrMinimo, medico, 0, 0, false).clone());
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

    /**
     * Método que controla todas as ações do user, tendo este já efetuado o login no sistema
     * @throws InterruptedException
     */

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
                    empresaFlow();
                    break;
                default:
                    System.out.println("Opcao inválida");
                    break;
            }
        }
        while (op != 0);
    }

    /**
     * Método que controla as opções de um utilizador do tipo Empresa de Trnasportes
     */

    private void empresaFlow(){
        int op;
        Input input = new Input();
        clearScreen();
        this.view.headLoginEmpresa();
        this.view.email();
        String email = input.lerString();
        this.view.password();
        String password = input.lerString();
        try{
            EmpresaTransportes et = bd.loginEmpresa(email, password).clone();
            clearScreen();
            this.view.showMenuTransportes();
            do{
                op = input.lerInt();
                switch (op){
                    case 1:
                        EmpresaTransportes aux = this.bd.getTransportes().getTransportes().get(et.getEmail()).clone();
                        aux.setDisponivel(true);
                        this.bd.addEmpresaDisponivel(aux);
                        System.out.println("Está disponível para levantar encomendas.");
                        System.out.println("Prima 7 para voltar ao menu");
                        break;
                    case 2:
                        EmpresaTransportes aux2 = this.bd.getTransportes().getTransportes().get(et.getEmail()).clone();
                        aux2.setDisponivel(false);
                        this.bd.addEmpresaDisponivel(aux2);
                        System.out.println("Está indisponível para levantar encomendas.");
                        System.out.println("Prima 7 para voltar ao menu");
                        break;
                    case 5:
                        int size3 = this.bd.getTransportes().getTransportes().get(et.getEmail()).getRegistos().size();
                        if (size3 == 0) System.out.println("Não tem pedidos de encomendas");
                        else {
                            System.out.println("Histórico de encomendas: ");
                            System.out.println(this.bd.getTransportes().getTransportes().get(et.getEmail()).getRegistos());
                        }
                        System.out.println("Prima 7 para voltar ao menu");
                        break;
                    case 6:
                        break;
                    case 7:
                        clearScreen();
                        this.view.showMenuTransportes();
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }

            } while (op != 0);
        } catch (TransporteNotFoundException e){
            clearScreen();
            System.out.println("Email ou password inválidos\n\n");
            System.out.println("Selecione nova opção de login\n");
            this.view.showMenuLogin();
        }
    }

    /**
     * Método que controla as opções de um utilizador do tipo Voluntário
     */

    private void voluntarioFlow() {
        int op;
        Input input = new Input();
        clearScreen();
        this.view.headLoginVoluntario();
        this.view.email();
        String email = input.lerString();
        this.view.password();
        String password = input.lerString();
        try {
            Voluntario v = bd.loginVoluntario(email, password).clone();
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
                        Voluntario aux = this.bd.getVoluntarios().getVoluntarios().get(v.getEmail()).clone();
                        aux.setDisponibilidade(true);
                        this.bd.addVoluntarioDisponivel(aux);
                        System.out.println("Está disponível para levantar encomendas.");
                        System.out.println("Prima 6 para voltar ao menu");
                        break;
                    case 2:
                        Voluntario aux3 = this.bd.getVoluntarios().getVoluntarios().get(v.getEmail()).clone();
                        aux3.setDisponibilidade(false);
                        this.bd.addVoluntarioDisponivel(aux3);
                        System.out.println("Está indisponível para levantar encomendas.");
                        System.out.println("Prima 6 para voltar ao menu");
                        break;
                    case 3:
                        int size3 = this.bd.getVoluntarios().getVoluntarios().get(v.getEmail()).getHistorico().size();
                        if (size3 == 0) System.out.println("Não tem pedidos de encomendas");
                        else {
                           System.out.println("Histórico de encomendas: ");
                           System.out.println(this.bd.getVoluntarios().getVoluntarios().get(v.getEmail()).getHistorico());
                        }
                        System.out.println("Prima 6 para voltar ao menu");
                        break;
                    case 4:
                        Voluntario aux2 = this.bd.getVoluntarios().getVoluntarios().get(v.getEmail()).clone();
                        aux2.setDisponibilidade(false);
                        this.bd.addVoluntarioDisponivel(aux2);
                        String preparadas = this.bd.getVoluntarios().getVoluntarios().get(v.getEmail()).getPreparadas();
                        if(preparadas.equals("0")) System.out.println("Ainda não existem encomendas preparadas");
                        else {
                            System.out.println(preparadas);
                            System.out.println("Indique o código da encomenda que levantou");
                            String cod = Input.lerString();
                            Encomenda encomenda = this.bd.getVoluntarios().getVoluntarios().get(v.getEmail()).getEncomenda(cod);
                            try {
                                Loja lj = this.bd.getLojas().getLojas().get(this.bd.getLojas().getEmail(encomenda.getCodigo_loja()));
                                Encomenda e = lj.getEnc(cod);
                                this.bd.updateLoja2(e, lj);
                                System.out.println("Realizado com sucesso");

                            } catch (LojaNotFoundException e) {
                                System.out.println("Loja inválida");
                            } catch (EncomendaNotFoundException e) {
                                System.out.println("Encomenda Inválida");
                            }
                            v.updateEncomendaLoja(encomenda);
                            this.bd.updateVoluntario2(v);
                        }
                        System.out.println("Insira 6 para voltar a imprimir o menu");
                        break;
                    case 5:
                        String naoEntregue = this.bd.getVoluntarios().getVoluntarios().get(v.getEmail()).getNaoEntregue();
                        if(naoEntregue.equals("0")){System.out.println("Não existem encomendas por entregar");}
                        else {
                            System.out.println(naoEntregue);
                            System.out.println("Indique a encomenda que acabou de ser entregue");
                            String cod2 = Input.lerString();
                            Encomenda encomenda2 = this.bd.getVoluntarios().getVoluntarios().get(v.getEmail()).getEncomenda(cod2).clone();
                            LocalDateTime entrega = LocalDateTime.now();
                            LocalDateTime emissao = encomenda2.getData();
                            Duration duration = Duration.between(entrega, emissao);
                            long diff = Math.abs(duration.toMinutes());
                            v.updateEncomenda(encomenda2);
                            String codUser = encomenda2.getCodigo_user();
                            try {
                                String emailUser = this.bd.getUtilizadores().getEmail(codUser);
                                Utilizador u = this.bd.getUtilizadores().getUsers().get(emailUser).clone().clone();
                                u.updateEncomenda(encomenda2);
                                this.bd.updateUser2(u);
                                this.bd.updateVoluntario2(v);
                                System.out.println("A encomenda " + encomenda2.getCodigo() + " foi entregue ao utilizador " + encomenda2.getCodigo_user());
                                System.out.println("Demorou " + diff + " minutos a ser entregue");
                                System.out.println("Insira 6 para voltar a imprimir o menu");
                            } catch (UserNotFoundException e){
                                System.out.println("Utilizador não foi encontrado");
                            }
                        }
                        break;
                    case 6:
                        clearScreen();
                        this.view.showMenuVoluntario();
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }


            } while (op != 0);

        } catch (VoluntarioNotFoundException e) {
            clearScreen();
            System.out.println("Email ou password inválidos\n\n");
            System.out.println("Selecione nova opção de login\n");
            this.view.showMenuLogin();
        }
    }

    /**
     * Método que controla as opções de um utilizador do tipo Loja
     */

    private void lojaFlow(){
        int op;
        Input input = new Input();
        clearScreen();
        this.view.headLoginLoja();
        this.view.email();
        String email = input.lerString();
        this.view.password();
        String password = input.lerString();
        try {
            Loja lj = bd.loginLoja(email, password).clone();
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
                        String notReady = this.bd.getLojas().getLojas().get(lj.getEmail()).getEncNotReady();
                        if(notReady.equals("0")){
                            System.out.println("Não existem encomendas por preparar");
                        }
                        else {
                            System.out.println("Lista de encomendas por preparar: ");
                            System.out.println(notReady);
                            System.out.println("Indique o código da encomenda que está pronta a ser levantada");
                            String cod = input.lerString();
                            try {
                                Encomenda enc = this.bd.getLojas().getLojas().get(lj.getEmail()).getEnc(cod).clone();
                                lj.updateEncomenda(enc);
                                this.bd.updateEncomendaVoluntario(enc);
                                this.bd.updateLoja3(lj);
                                this.bd.updateAceites(enc.getCodigo());
                            } catch (EncomendaNotFoundException e) {
                                System.out.println("Código de encomenda inválido");
                            }
                        }
                        System.out.println("Prima 4 para imprimir o menu");
                        break;
                    case 2:
                        System.out.println(this.bd.getLojas().getLojas().get(lj.getEmail()).getEncomendas_recebidas());
                        break;
                    case 3:
                        break;
                    case 4:
                        clearScreen();
                        this.view.showMenuLoja();
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } while (op != 0);

        } catch (LojaNotFoundException e) {
            clearScreen();
            System.out.println("Email ou password inválidos\n\n");
            System.out.println("Selecione nova opção de login\n");
            this.view.showMenuLogin();
        }
    }

    /**
     * Método que controla as opções de um utilizador do Utilizador doméstico
     */

    private void userFlow() throws InterruptedException{
        int op;
        Input input = new Input();
        String email, password, produto = "";

        clearScreen();
        this.view.headLoginUser();
        this.view.email();
        email = input.lerString();
        this.view.password();
        password = input.lerString();
        try {
            Utilizador u = bd.loginUser(email, password).clone();
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
                            int quantidade = 0;
                            double quantidadeTot = 0.0;
                            double custoTotal = 0.0;
                            String loja;
                            List<String> produtosSel = new ArrayList<>();
                            Map<String, LinhaEncomenda> produtos = new TreeMap<>();
                            this.view.headNovaEncomenda();
                            this.view.preenchimentoObrg();
                            System.out.println(this.bd.getLojas().listLojasUser(u));
                            System.out.println("Selecione o código de uma loja");
                            loja = input.lerString();
                            try {
                                System.out.println(bd.getProdutos().listProdutosNormais());
                                String emailLoja = this.bd.getLojas().getEmail(loja);
                                while (!produto.equals("0")) {
                                    this.view.produto();
                                    System.out.println("Insira 0 para concluir a seleção de produtos");
                                    produto = input.lerString();
                                    produtosSel.add(produto);
                                    System.out.println("\n");
                                }
                                produtosSel.remove("0");
                                try {
                                    for (String s : produtosSel) {
                                        this.bd.getProdutos().existeProd(s);
                                        clearScreen();
                                        System.out.println("Insira a quantidade de " + s + " || Preço por unidade = " + bd.getProdutos().getProdutos().get(s).getPreco());
                                        quantidade = input.lerInt();
                                        LinhaEncomenda enc = new LinhaEncomenda(bd.getProdutos().getProdutos().get(s).getCodigo(), s, quantidade, bd.getProdutos().getProdutos().get(s).getPreco()).clone();
                                        produtos.put(s, enc);
                                        quantidadeTot += quantidade;
                                        custoTotal += bd.getProdutos().getProdutos().get(s).getPreco() * quantidade;
                                    }
                                    System.out.println("Custo total da encomenda: " + custoTotal);
                                    Loja j = this.bd.getLojas().getLojas().get(emailLoja).clone();
                                    Random random = new Random();
                                    String cod = "e" + random.nextInt(9999);
                                    while(this.bd.getEncomendasAceites().getAceites().contains(cod)) cod = "e" + random.nextInt(9999);
                                    Encomenda novaEnc = new Encomenda(cod, u.getCodigo(), loja, quantidadeTot, u.getNome(), this.bd.getLojas().getLojas().get(emailLoja).getNome(), produtos, false, LocalDateTime.now(), false, false, false);
                                    List<Voluntario> disponiveis = this.bd.getVoluntarios().voluntariosDisponíveis(j);

                                    if(disponiveis.size() == 0){
                                        System.out.println("Não existem voluntários disponíveis perto da loja selecionada.");
                                        System.out.println("Será necessário selecionar uma empresa transportadora, pagando pelos seus serviços.");
                                        System.out.println(this.bd.getTransportes().printEmpresas(u, j, novaEnc.getPeso()));
                                        System.out.println("Selecione o código de uma empresa ou 0 para cancelar a encomenda");
                                        String opEmpresa = input.lerString();
                                        if(opEmpresa.equals("0")) op = 0;
                                        else {
                                            try {
                                                u.addEncomenda(novaEnc);
                                                String emailEmpresa = this.bd.getTransportes().getEmail(opEmpresa);
                                                EmpresaTransportes et = this.bd.getTransportes().getTransportes().get(emailEmpresa).clone();
                                                et.addEncomenda(novaEnc);
                                                this.bd.updateTransportes2(et);
                                                this.bd.updateLoja(novaEnc, this.bd.getLojas().getLojas().get(emailLoja));
                                                System.out.println("ENCOMENDA FEITA COM SUCESSO. PRIMA 6 PARA SAIR");
                                            } catch (TransporteNotFoundException e){
                                                System.out.println("Empresa transportadora inválida");
                                            }
                                        }
                                    }
                                    else if(disponiveis.size() == 1){
                                        u.addEncomenda(novaEnc);
                                        Voluntario v = disponiveis.get(0).clone();
                                        System.out.println("A encomenda será entregue pelo voluntário --> " + v.getNome() + " --> " + v.getCodigo());
                                        v.addEncomenda(novaEnc);
                                        this.bd.updateVoluntario2(v);
                                        this.bd.updateLoja(novaEnc, this.bd.getLojas().getLojas().get(emailLoja));
                                        System.out.println("ENCOMENDA FEITA COM SUCESSO. PRIMA 6 PARA SAIR");
                                    }
                                    else {
                                        u.addEncomenda(novaEnc);
                                        int choice = random.nextInt(disponiveis.size() - 1);
                                        Voluntario v = disponiveis.get(choice).clone();
                                        System.out.println("A encomenda será entregue pelo voluntário --> " + v.getNome() + " --> " + v.getCodigo());
                                        v.addEncomenda(novaEnc);
                                        this.bd.updateVoluntario2(v);
                                        this.bd.updateLoja(novaEnc, this.bd.getLojas().getLojas().get(emailLoja));
                                        System.out.println("ENCOMENDA FEITA COM SUCESSO. PRIMA 6 PARA SAIR");
                                    }

                                } catch (ProductNotFoundException e) {
                                    System.out.println("Produto não existe");
                                    System.out.println("Prima 0 para retroceder");
                                }
                            } catch (LojaNotFoundException e){
                                System.out.println("Loja inválida");
                                System.out.println("Prima 0 para retroceder");
                            }
                            break;

                        case 2:
                            int quantidade2 = 0;
                            double quantidadeTot2 = 0.0;
                            double custoTotal2 = 0.0;
                            String loja2;
                            List<String> produtosSel2 = new ArrayList<>();
                            Map<String, LinhaEncomenda> produtos2 = new TreeMap<>();
                            String produto2 = "";
                            this.view.headNovaEncomenda();
                            this.view.preenchimentoObrg();
                            System.out.println(this.bd.getLojas().listLojasUser(u));
                            System.out.println("Selecione o código de uma loja");
                            loja2 = input.lerString();
                            try {
                                System.out.println(bd.getProdutos().listProdutosMedicos());
                                String emailLoja = this.bd.getLojas().getEmail(loja2);
                                while (!produto2.equals("0")) {
                                    this.view.produto();
                                    System.out.println("Insira 0 para concluir a seleção de produtos");
                                    produto2 = input.lerString();
                                    produtosSel2.add(produto2);
                                    System.out.println("\n");
                                }
                                produtosSel2.remove("0");
                                try {
                                    for (String s : produtosSel2) {
                                        this.bd.getProdutos().existeProd(s);
                                        clearScreen();
                                        System.out.println("Insira a quantidade de " + s + " || Preço por unidade = " + bd.getProdutos().getProdutos().get(s).getPreco());
                                        quantidade2 = input.lerInt();
                                        LinhaEncomenda enc = new LinhaEncomenda(bd.getProdutos().getProdutos().get(s).getCodigo(), s, quantidade2, bd.getProdutos().getProdutos().get(s).getPreco());
                                        produtos2.put(s, enc);
                                        quantidadeTot2 += quantidade2;
                                        custoTotal2 += bd.getProdutos().getProdutos().get(s).getPreco() * quantidade2;
                                    }
                                    System.out.println("Custo total da encomenda: " + custoTotal2);
                                    Loja j = this.bd.getLojas().getLojas().get(emailLoja).clone();
                                    Random random = new Random();
                                    String cod = "e" + random.nextInt(9999);
                                    while(this.bd.getEncomendasAceites().getAceites().contains(cod)) cod = "e" + random.nextInt(9999);
                                    Encomenda novaEnc = new Encomenda(cod, u.getCodigo(), loja2, quantidadeTot2, u.getNome(), this.bd.getLojas().getLojas().get(emailLoja).getNome(), produtos2, true, LocalDateTime.now(), false, false, false);
                                    List<Voluntario> disponiveis = this.bd.getVoluntarios().voluntariosDisponíveisMed(j);

                                    if(disponiveis.size() == 0){
                                        System.out.println("Não existem voluntários disponíveis perto da loja selecionada.");
                                        System.out.println("Será necessário selecionar uma empresa transportadora, pagando pelos seus serviços.");
                                        System.out.println(this.bd.getTransportes().printEmpresasMed(u, j, novaEnc.getPeso()));
                                        System.out.println("Selecione o código de uma empresa ou 0 para cancelar a encomenda");
                                        String opEmpresa = input.lerString();
                                        if(opEmpresa.equals("0")) op = 0;
                                        else {
                                            try {
                                                u.addEncomenda(novaEnc);
                                                String emailEmpresa = this.bd.getTransportes().getEmail(opEmpresa);
                                                EmpresaTransportes et = this.bd.getTransportes().getTransportes().get(emailEmpresa).clone();
                                                et.addEncomenda(novaEnc);
                                                this.bd.updateTransportes2(et);
                                                this.bd.updateLoja(novaEnc, this.bd.getLojas().getLojas().get(emailLoja));
                                                System.out.println("ENCOMENDA FEITA COM SUCESSO. PRIMA 6 PARA SAIR");
                                            } catch (TransporteNotFoundException e){
                                                System.out.println("Empresa transportadora inválida");
                                            }
                                        }
                                    }
                                    else if(disponiveis.size() == 1){
                                        u.addEncomenda(novaEnc);
                                        Voluntario v = disponiveis.get(0).clone();
                                        System.out.println("A encomenda será entregue pelo voluntário --> " + v.getNome() + " --> " + v.getCodigo());
                                        v.addEncomenda(novaEnc);
                                        this.bd.updateVoluntario2(v);
                                        this.bd.updateLoja(novaEnc, this.bd.getLojas().getLojas().get(emailLoja));
                                        System.out.println("ENCOMENDA FEITA COM SUCESSO. PRIMA 6 PARA SAIR");
                                    }
                                    else {
                                        u.addEncomenda(novaEnc);
                                        int choice = random.nextInt(disponiveis.size() - 1);
                                        Voluntario v = disponiveis.get(choice).clone();
                                        System.out.println("A encomenda será entregue pelo voluntário --> " + v.getNome() + " --> " + v.getCodigo());
                                        v.addEncomenda(novaEnc);
                                        this.bd.updateVoluntario2(v);
                                        this.bd.updateLoja(novaEnc, this.bd.getLojas().getLojas().get(emailLoja));
                                        System.out.println("ENCOMENDA FEITA COM SUCESSO. PRIMA 6 PARA SAIR");
                                    }

                                } catch (ProductNotFoundException e) {
                                    System.out.println("Produto não existe");
                                    System.out.println("Prima 0 para retroceder");
                                }
                            } catch (LojaNotFoundException e){
                                System.out.println("Loja inválida");
                                System.out.println("Prima 0 para retroceder");
                            }
                            break;

                        case 3:
                            System.out.println(u.printEncomendasRecebidas());
                            System.out.println("Insira 7 para imprimir de novo o menu");
                            break;
                        case 4:
                            System.out.println(u.printEncomendasPorEntregar());
                            System.out.println("Insira 7 para imprimir de novo o menu");
                            break;
                        case 5:
                            System.out.println(this.bd.getVoluntarios().printVoluntario());
                            System.out.println("Escolha o código do voluntário a avaliar");
                            String cod = Input.lerString();
                            try {
                                String emailVol = this.bd.getVoluntarios().getEmail(cod);
                                System.out.println("Insira a classificação de 1 a 10:");
                                Double classificacao = Input.lerDouble();
                                if(classificacao >= 0 && classificacao <= 10) {
                                    Voluntario v = this.bd.getVoluntarios().getVoluntarios().get(emailVol).clone();
                                    this.bd.updateVoluntario(classificacao, v);
                                    System.out.println("DONE!");
                                    System.out.println("Insira uma nova opção");
                                } else System.out.println("Classificação inválida!");

                            } catch (VoluntarioNotFoundException e){
                                System.out.println("Voluntário inválido");
                                System.out.println("Prima 0 para retroceder");
                            }
                            break;
                        case 6:
                            System.out.println(this.bd.getTransportes().printTransportes());
                            System.out.println("Escolha o código da empresa de transportes a avaliar");
                            String cod2 = Input.lerString();
                            try {
                                String emailTrans = this.bd.getTransportes().getEmail(cod2);
                                System.out.println("Insira a classificação de 1 a 10:");
                                Double classificacao = Input.lerDouble();
                                if(classificacao >= 0 && classificacao <= 10) {
                                    EmpresaTransportes e = this.bd.getTransportes().getTransportes().get(emailTrans).clone();
                                    this.bd.updateTransportes(classificacao, e);
                                    System.out.println("DONE!");
                                    System.out.println("Insira uma nova opção");
                                } else System.out.println("Classificação inválida!");

                            } catch (TransporteNotFoundException e){
                                System.out.println("Empresa inválida");
                                System.out.println("Prima 0 para retroceder");
                            }
                            break;
                        case 7:
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
            clearScreen();
            System.out.println("Email ou password inválidos\n\n");
            System.out.println("Selecione nova opção de login\n");
            this.view.showMenuLogin();

        }
    }


}
