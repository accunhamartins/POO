public class EncomendasAceites{
    private String codigo;

    //Construtores
    public EncomendasAceites(){
      this.codigo = " ";
    }

    public EncomendasAceites(String codigo){
      this.codigo = codigo;
    }

    public EncomendasAceites(EncomendasAceites ea){
      this.codigo = ea.getCodigo();
    }

    //Getters
    public String getCodigo(){
        return this.codigo;
    }

    //Setters
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    //clone
    public EncomendasAceites clone(){
      return new EncomendasAceites(this);
    }

    //Equals
    public boolean equals(Object o){
      if (o == null || this.getClass() != o.getClass()) return false;
      if (this == o) return true;
      EncomendasAceites ea = (EncomendasAceites) o;
      return this.codigo.equals(ea.getCodigo());
    }

    public String toString(){
      StringBuilder sb = new StringBuilder();
      sb.append("Código: ").append(this.codigo + "\n");

      return sb.toString();
    }
}
