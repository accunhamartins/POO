import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TrazAqui {

   public void showMenuInicial(){
       System.out.println("«««««««««««««««««««««TRAZ-AQUI»»»»»»»»»»»»»»»»»»»»»»»»");
       System.out.println("1.Registar");
       System.out.println("2.Login");
       System.out.println("0.Sair da aplicação");
   }
   public void showMenuRegisto(){
       System.out.println("Tipo de utilizador: ");
       System.out.println("1. Utilizador doméstico");
       System.out.println("2. Voluntário");
       System.out.println("3. Loja");
       System.out.println("4. Empresa de Transportes");
   }

   public void insereUser(BDGeral bd){
       Random random = new Random();
       Scanner input = new Scanner(System.in);
       String password = "", nome = "", email = "";
       Double latitude = 0.0, longitude = 0.0;
       String codigo = "";
       try {
           System.out.println("Os seguintes campos são de preenchimento obrigatório: ");
           System.out.println("Insira o seu email: ");
           email = input.next();
           System.out.println("Insira a sua password: ");
           password = input.next();
           System.out.println("Insira o seu nome: ");
           nome = input.next();
           System.out.println("Insira a sua latitude: ");
           latitude = input.nextDouble();
           System.out.println("Insira a sua longitude: ");
           longitude = input.nextDouble();
           codigo = "u" + random.nextInt(99);
           while (bd.getUtilizadores().existeCodigo(codigo)) codigo = "u" + random.nextInt(99);
       }
       catch(InputMismatchException e){
           input.next();
           System.out.println("Input inválido");
       }
       bd.addUser(new Utilizador(email, password, codigo, nome, latitude, longitude));
   }

    public void insereVoluntario(BDGeral bd){
        Random random = new Random();
        Scanner input = new Scanner(System.in);
        String password = "", nome = "", email = "";
        Double latitude = 0.0, longitude = 0.0, raio = 0.0;
        String codigo = "";
        try {
            System.out.println("Os seguintes campos são de preenchimento obrigatório: ");
            System.out.println("Insira o seu email: ");
            email = input.next();
            System.out.println("Insira a sua password: ");
            password = input.next();
            System.out.println("Insira o seu nome: ");
            nome = input.next();
            System.out.println("Insira a sua latitude: ");
            latitude = input.nextDouble();
            System.out.println("Insira a sua longitude: ");
            longitude = input.nextDouble();
            System.out.println("Insira o seu raio de ação: ");
            raio = input.nextDouble();
            codigo = "v" + random.nextInt(99);
            while (bd.getVoluntarios().existeCodigo(codigo)) codigo = "v" + random.nextInt(99);
        }
        catch(InputMismatchException e){
            input.next();
            System.out.println("Input inválido");
        }
        bd.addVoluntario(new Voluntario(email, password, nome, codigo, true, latitude, longitude, LocalDate.now(), raio, new ArrayList<>()));
    }


    public void insereLoja(BDGeral bd){
        Random random = new Random();
        Scanner input = new Scanner(System.in);
        String password = "", nome = "", email = "";
        Double latitude = 0.0, longitude = 0.0, tempo_espera = 0.0;
        String codigo = "";
        try {
            System.out.println("Os seguintes campos são de preenchimento obrigatório: ");
            System.out.println("Insira o seu email: ");
            email = input.next();
            System.out.println("Insira a sua password: ");
            password = input.next();
            System.out.println("Insira o seu nome: ");
            nome = input.next();
            System.out.println("Insira a sua latitude: ");
            latitude = input.nextDouble();
            System.out.println("Insira a sua longitude: ");
            longitude = input.nextDouble();
            System.out.println("Insira o tempo de espera em fila: ");
            tempo_espera = input.nextDouble();
            codigo = "l" + random.nextInt(99);
            while (bd.getLojas().existeCodigo(codigo)) codigo = "l" + random.nextInt(99);
        }
        catch(InputMismatchException e){
            input.next();
            System.out.println("Input inválido");
        }
        bd.addLoja(new Loja(email, password, codigo, nome, tempo_espera, latitude, longitude, new ArrayList<>()));
    }

    public void insereTransporte(BDGeral bd){
        Random random = new Random();
        Scanner input = new Scanner(System.in);
        String password = "", nome = "", email = "", local = "";
        Double latitude = 0.0, longitude = 0.0, raio = 0.0, custo = 0.0;
        int nif = 0, nrMinimo = 0;
        boolean medico = false;
        String codigo = "";
        try {
            System.out.println("Os seguintes campos são de preenchimento obrigatório: ");
            System.out.println("Insira o seu email: ");
            email = input.next();
            System.out.println("Insira a sua password: ");
            password = input.next();
            System.out.println("Insira o seu nome: ");
            nome = input.next();
            System.out.println("Insira o custo por km: ");
            custo = input.nextDouble();
            System.out.println("Insira a sua latitude: ");
            latitude = input.nextDouble();
            System.out.println("Insira a sua longitude: ");
            longitude = input.nextDouble();
            System.out.println("Insira o local onde se situa a empresa: ");
            local = input.next();
            System.out.println("Insira o seu raio de ação: ");
            raio = input.nextDouble();
            System.out.println("Insira o seu NIF: ");
            nif = input.nextInt();
            System.out.println("Insira o número mínimo de encomendas: ");
            nrMinimo = input.nextInt();
            System.out.println("Aceita transportar encomendas médicas? (True/False) ");
            medico = input.nextBoolean();
            codigo = "t" + random.nextInt(99);
            while (bd.getTransportes().existeCodigo(codigo)) codigo = "t" + random.nextInt(99);
        }
        catch(InputMismatchException e){
            input.next();
            System.out.println("Input inválido");
        }
        bd.addTransporte(new EmpresaTransportes(email, password, codigo, nome, nif,custo,local,latitude,longitude,raio, new ArrayList<>(), nrMinimo, medico));
    }

}
