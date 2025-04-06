import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoPerecivel extends Produto {
    private LocalDate dataValidade;

    public ProdutoPerecivel(String id, String descricao, double preco, double margem, LocalDate validade) {
        super(id, descricao, preco, margem);
        this.dataValidade = validade;
    }

    @Override
    public double valorDeVenda() {
        if (dataValidade.isBefore(LocalDate.now().plusDays(30)))
            return precoCusto * (1 + margemLucro / 2);
        return precoCusto * (1 + margemLucro);
    }

    @Override
    public String gerarDadosTexto() {
        return "2;" + identificador + ";" + descricao + ";" + precoCusto + ";" + margemLucro + ";" +
                dataValidade.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        String data = dataValidade.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return "ID: " + identificador + ", Descrição: " + descricao + ", Venda: R$" + String.format("%.2f", valorDeVenda()) + ", Validade: " + data;
    }
}