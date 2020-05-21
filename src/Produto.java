public class Produto {
    private String codigo;
    private String descricao;
    private double preco;
    private boolean produtoMedico;

    public Produto(){
        this.codigo = " ";
        this.descricao = " ";
        this.preco = 0;
        this.produtoMedico = false;
    }

    public Produto(String codigo, String descricao, double preco, boolean produtoMedico){
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.produtoMedico = produtoMedico;
    }

    public Produto(Produto a){
        this.codigo = a.getCodigo();
        this.descricao = a.getDescricao();
        this.preco = a.getPreco();
        this.produtoMedico = a.isProdutoMedico();
    }

    public String getCodigo(){
        return this.codigo;
    }

    public boolean isProdutoMedico() {
        return this.produtoMedico;
    }

    public double getPreco() {
        return this.preco;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setProdutoMedico(boolean produtoMedico) {
        this.produtoMedico = produtoMedico;
    }

    public Produto clone(){

        return (new Produto(this));
    }


    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Produto o = (Produto) obj;

        return  this.codigo.equals(o.getCodigo()) &&
                this.descricao.equals(o.getDescricao()) &&
                this.preco == o.getPreco() &&
                this.produtoMedico == o.isProdutoMedico();
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Código de produto: ");
        sb.append(this.codigo + "\n");
        sb.append("Produto: ");
        sb.append(this.descricao + "\n");
        sb.append("Preço: ");
        sb.append(this.preco + "\n");
        sb.append("É um produto médico: ");
        sb.append(this.produtoMedico +"\n");

        return sb.toString();
    }
}
