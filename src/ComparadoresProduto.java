import java.util.Comparator;

public class ComparadoresProduto {
    public static Comparator<Produto> porId() {
        return Comparator.comparing(Produto::getIdentificador);
    }

    public static Comparator<Produto> porDescricao() {
        return Comparator.comparing(Produto::getDescricao, String.CASE_INSENSITIVE_ORDER);
    }
}