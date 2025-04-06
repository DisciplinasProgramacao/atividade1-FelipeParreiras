import java.util.Comparator;

public interface IOrdenador<T> {
    T[] ordenar(T[] dados, Comparator<T> criterio);
    long getComparacoes();
    long getMovimentacoes();
    double getTempoOrdenacao();
}