public class LinhaEncomenda{
    private String descricao;
    private double preco;
    private int quantidade;
    private boolean produtoMedico;

    public LinhaEncomenda(){
        this.descricao = "";
        this.preco = 0;
        this.quantidade = 0;
        this.produtoMedico = false;
    }

    public LinhaEncomenda(String descricao, double preco, int  quantidade, boolean produtoMedico){
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.produtoMedico = produtoMedico;
    }

    public LinhaEncomenda(LinhaEncomenda a){
        this.descricao = a.getDescricao();
        this.preco = a.getPreco();
        this.quantidade = a.getQuantidade();
        this.produtoMedico = a.isProdutoMedico();
    }

    public boolean isProdutoMedico() {
        return this.produtoMedico;
    }

    public double getPreco() {
        return this.preco;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setProdutoMedico(boolean produtoMedico) {
        this.produtoMedico = produtoMedico;
    }

    public LinhaEncomenda clone(){

        return (new LinhaEncomenda(this));
    }


    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        LinhaEncomenda o = (LinhaEncomenda) obj;

        return this.descricao.equals(o.getDescricao()) &&
                this.preco == o.getPreco() &&
                this.quantidade == o.getQuantidade() &&
                this.produtoMedico == o.isProdutoMedico();
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Produto: ");
        sb.append(this.descricao + "\n");
        sb.append("Preço: ");
        sb.append(this.preco + "\n");
        sb.append("Quantidade: ");
        sb.append(this.quantidade+"\n");
        sb.append("É um produto médico: ");
        sb.append(this.produtoMedico +"\n");

        return sb.toString();
    }


}