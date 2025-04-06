import java.util.Arrays;
import java.util.Comparator;

public class Mergesort<T> implements IOrdenador<T> {
    private long comparacoes = 0;
    private long movimentacoes = 0;
    private double tempo = 0;

    public T[] ordenar(T[] dados, Comparator<T> criterio) {
        comparacoes = movimentacoes = 0;
        long inicio = System.nanoTime();
        T[] resultado = mergeSort(dados, 0, dados.length - 1, criterio);
        tempo = (System.nanoTime() - inicio) / 1_000_000.0;
        return resultado;
    }

    private T[] mergeSort(T[] dados, int ini, int fim, Comparator<T> criterio) {
        if (ini >= fim) return Arrays.copyOfRange(dados, ini, fim + 1);

        int meio = (ini + fim) / 2;
        T[] esquerda = mergeSort(dados, ini, meio, criterio);
        T[] direita = mergeSort(dados, meio + 1, fim, criterio);
        return merge(esquerda, direita, criterio);
    }

    private T[] merge(T[] esq, T[] dir, Comparator<T> criterio) {
        T[] resultado = Arrays.copyOf(esq, esq.length + dir.length);
        int i = 0, j = 0, k = 0;
        while (i < esq.length && j < dir.length) {
            comparacoes++;
            if (criterio.compare(esq[i], dir[j]) <= 0) {
                resultado[k++] = esq[i++];
            } else {
                resultado[k++] = dir[j++];
            }
            movimentacoes++;
        }
        while (i < esq.length) resultado[k++] = esq[i++];
        while (j < dir.length) resultado[k++] = dir[j++];
        movimentacoes += (esq.length - i) + (dir.length - j);
        return resultado;
    }

    public long getComparacoes() { return comparacoes; }
    public long getMovimentacoes() { return movimentacoes; }
    public double getTempoOrdenacao() { return tempo; }
}