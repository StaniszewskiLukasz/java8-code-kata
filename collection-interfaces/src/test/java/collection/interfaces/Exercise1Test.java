package collection.interfaces;

import common.test.tool.annotation.Easy;
import common.test.tool.dataset.ClassicOnlineStore;
import common.test.tool.entity.Customer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class Exercise1Test extends ClassicOnlineStore {

    @Easy
    @Test
    public void iterateByForEach() {
        List<Customer> customerIterable = this.mall.getCustomerList();
        List<String> nameList = new ArrayList<>();

        /**
         * Create a {@link Consumer} which represents an operation to add customer's name to {@link nameList} list.
         * Iterate {@link customerIterable} with {@link Iterable#forEach} and use the {@link Consumer}
         * to finish creating the name list.
         */
        Consumer<Customer> consumer = customer -> nameList.add(customer.getName());
        //po co tworzymy tego consumera???
        //tworzymy Consumera który przyjmuje obiekt Customer tworzymy go poprzez dodanie do listy imion, imienia customerów
        customerIterable.forEach(consumer);
        //nie do końca rozumiem!!! //ToDo

        assertThat(nameList.toString(), is("[Joe, Steven, Patrick, Diana, Chris, Kathy, Alice, Andrew, Martin, Amy]"));
    }

    @Easy
    @Test
    public void whoHaveNoEInYourName() {
        Collection<String> nameCollection =
                new ArrayList<>(Arrays.asList("Joe", "Steven", "Patrick", "Chris"));

        /**
         * Create a {@link Predicate} which predicates if the input string contains "e".
         * Remove elements from {@link nameCollection} which contains "e" using {@link Collection#removeIf}.
         */
        Predicate<String> predicate = o -> o.contains("e");
        //tworzymy interfejs funkcyjny by poprzez lambdę sprawdzić czy w stringach jest e
        nameCollection.removeIf(predicate);
        //jeśli predicate jest true to wyrzucamy go z listy

        assertThat(nameCollection.toString(), is("[Patrick, Chris]"));
        //DONE
    }

    @Easy
    @Test
    public void replaceTheElements() {
        List<String> nameList =
                new ArrayList<>(Arrays.asList("Joe", "Steven", "Patrick", "Chris"));

        /**
         * Create a {@link UnaryOperator} which returns given string wrapped with "()".
         * Replace the elements in {@link nameList} with string wrapped with "()" using {@link List#replaceAll} .
         */
        UnaryOperator<String> unaryOperator = o->"("+o+")";
        //tworzymy interfejs funkcyjny by poprzez lambdę do każdego stringa dodać nawiasy
        nameList.replaceAll(unaryOperator);
        //zamieniamy zawartość listy na unaryOperator który także jest Stringiem

        assertThat(nameList.toString(), is("[(Joe), (Steven), (Patrick), (Chris)]"));
        //DONE without problems
    }

    @Easy
    @Test
    public void sortByName() {
        List<String> nameList =
                new ArrayList<>(Arrays.asList("Joe", "Steven", "Patrick", "Chris"));

        /**
         * Create a {@link Comparator} to sort the name list by their name's length in ascending order.
         */
        Comparator<String > comparator = Comparator.comparing(String::length);
        nameList.sort(comparator);

        assertThat(nameList.toString(), is("[Joe, Chris, Steven, Patrick]"));
        //DONE without problems
    }

    @Easy
    @Test
    public void createStream() {
        Collection<String> nameList =
                new ArrayList<>(Arrays.asList("Joe", "Steven", "Patrick", "Chris"));

        /**
         * Create a serial {@link Stream} using {@link Collection#stream}
         * You can learn about {@link Stream} APIs at stream-api module.
         */
        Stream<String > stringStream = nameList.stream();

        assertThat(stringStream.count(), is(4L));
        assertThat(stringStream.isParallel(), is(false));
        //DONE without problems
    }

    @Easy
    @Test
    public void createParallelStream() {
        Collection<String> nameList =
                new ArrayList<>(Arrays.asList("Joe", "Steven", "Patrick", "Chris"));
        Stream<String> parallelStream = nameList.parallelStream();

        /**
         * Create a parallel {@link Stream} using {@link Collection#parallelStream} or {@link Stream#parallel}
         */
        Stream<Object> nameParallelStream = null;

        assertThat(parallelStream.count(), is(4L));
        assertThat(parallelStream.isParallel(), is(true));
        //DONE without problems
    }
}
