public class ProdutoNaoPerecivel extends Produto {

    public ProdutoNaoPerecivel(String id, String descricao, double preco, double margem) {
        super(id, descricao, preco, margem);
    }

    @Override
    public double valorDeVenda() {
        return precoCusto * (1 + margemLucro);
    }

    @Override
    public String gerarDadosTexto() {
        return "1;" + identificador + ";" + descricao + ";" + precoCusto + ";" + margemLucro;
    }

    @Override
    public String toString() {
        return "ID: " + identificador + ", Descrição: " + descricao + ", Venda: R$" + String.format("%.2f", valorDeVenda());
    }
}