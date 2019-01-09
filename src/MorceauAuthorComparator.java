import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class MorceauAuthorComparator implements Comparator<Morceau>
{
    @Override
    public Comparator<Morceau> reversed() {
        return null;
    }

    @Override
    public Comparator<Morceau> thenComparing(Comparator<? super Morceau> other) {
        return null;
    }

    @Override
    public <U> Comparator<Morceau> thenComparing(Function<? super Morceau, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Morceau> thenComparing(Function<? super Morceau, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Morceau> thenComparingInt(ToIntFunction<? super Morceau> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Morceau> thenComparingLong(ToLongFunction<? super Morceau> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Morceau> thenComparingDouble(ToDoubleFunction<? super Morceau> keyExtractor) {
        return null;
    }

    @Override
    public int compare(Morceau o1, Morceau o2) {
        return o1.auteur.compareTo(o2.auteur);
    }
}
