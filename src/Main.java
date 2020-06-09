import java.io.IOException;

public class Main{

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException{
        TrazAquiView view = new TrazAquiView();
        TrazAquiController controller = new TrazAquiController();
        controller.setView(view);
        BDGeral base = controller.readFlow();
        controller.setBd(base);
        controller.mainFlow();
        System.out.println("Gravando último estado da aplicação...");
        base.gravarFicheiro("update");
        System.out.println("Saindo do programa....");
        
    }
}
