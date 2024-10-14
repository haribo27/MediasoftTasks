import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.mediasoft.CollectionSearchAndSort;
import ru.practicum.mediasoft.Human;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class CollectionSearchAndSortTest {

    private List<Human> humans;
    private CollectionSearchAndSort collectionSearchAndSort;

    @Before
    public void setUpData() {
        collectionSearchAndSort = new CollectionSearchAndSort();
        humans = List.of(
                new Human("John", LocalDate.of(2024, 10, 12), "male"),
                new Human("Kate", LocalDate.of(2024, 10, 13), "female"),
                new Human("Alex", LocalDate.of(2023, 10, 6), "female"),
                new Human("Regina", LocalDate.of(2023, 9, 30), "female"),
                new Human("John2", LocalDate.of(2020, 10, 13), "male"),
                new Human("Mike", LocalDate.of(2004, 10, 12), "male"),
                new Human("Sophia", LocalDate.of(2024, 10, 17), "female"),
                new Human("Emma", LocalDate.of(2009, 10, 1), "female"),
                new Human("James", LocalDate.of(2024, 10, 22), "male"),
                new Human("Olivia", LocalDate.of(1994, 10, 12), "female")
        );
    }

    @Test
    public void findIfParamsAreNull() {
        List<Human> findResults = collectionSearchAndSort.findHumans(humans, null);
        Assert.assertEquals(findResults, humans);
    }

    @Test
    public void sortCollectionByBirthday() {
        List<Human> findResult = collectionSearchAndSort.findHumans(humans, Map.of(), "birthday");
        Assert.assertEquals(findResult.size(), 10);
        Assert.assertEquals(findResult.getLast().getName(), "Olivia");
    }

    @Test
    public void findJohnAndSortByAge() {
        List<Human> findResult = collectionSearchAndSort.findHumans(humans, Map.of("name", "John"),
                "birthday");

        Assert.assertEquals(findResult.size(),2);
        Assert.assertEquals(findResult.getFirst().getName(),"John");
    }

    @Test
    public void sortCollectionByName() {
        List<Human> findResult = collectionSearchAndSort.findHumans(humans, Map.of(), "name");
        Assert.assertEquals(findResult.size(), 10);
        Assert.assertEquals(findResult.getFirst().getName(), "Alex");
    }

    @Test
    public void invalidParamForSearchMustSearchByName() {
        List<Human> findResult = collectionSearchAndSort.findHumans(humans, Map.of(), "other");
        Assert.assertEquals(findResult.size(), 10);
        Assert.assertEquals(findResult.getFirst().getName(), "Alex");
    }

    @Test
    public void findOnlyOneJohn() {
        Map<String, Object> params = Map.of(
                "name", "John",
                "gender", "male",
                "birthday", LocalDate.of(2024, 10, 12)
        );
        List<Human> findResult = collectionSearchAndSort.findHumans(humans, params);
        Assert.assertEquals(findResult.size(), 1);
        Assert.assertEquals(findResult.getFirst().getName(), "John");
    }

    @Test
    public void findAllFemales() {
        Map<String, Object> params = Map.of(
                "gender", "female"
        );
        List<Human> findResult = collectionSearchAndSort.findHumans(humans, params);
        Assert.assertEquals(findResult.size(), 6);
        Assert.assertEquals(findResult.getLast().getName(), "Olivia");
    }

    @Test
    public void findNoResults() {
        Map<String, Object> params = Map.of(
                "name", "Nikolay"
        );
        List<Human> findResults = collectionSearchAndSort.findHumans(humans, params);
        Assert.assertEquals(findResults.size(), 0);
    }

    @Test
    public void findAllMales() {
        Map<String, Object> params = Map.of(
                "gender", "male"
        );
        List<Human> findResults = collectionSearchAndSort.findHumans(humans, params);
        Assert.assertEquals(findResults.size(), 4);
        Assert.assertEquals(findResults.getFirst().getName(), "John");
    }

    @Test
    public void findByWordsInName() {
        Map<String, Object> params = Map.of(
                "name", "m"
        );
        List<Human> findResults = collectionSearchAndSort.findHumans(humans, params);
        Assert.assertEquals(findResults.size(), 3);
        Assert.assertEquals(findResults.getLast().getName(), "James");
    }

    @Test
    public void findHumansAgeLess5Years() {
        Map<String, Object> params = Map.of(
                "ageLess", "5"
        );
        List<Human> findResults = collectionSearchAndSort.findHumans(humans, params);
        Assert.assertEquals(findResults.size(), 7);
    }

    @Test
    public void findHumansAgeMore15Years() {
        Map<String, Object> params = Map.of(
                "ageMore", "15"
        );
        List<Human> findResults = collectionSearchAndSort.findHumans(humans, params);
        Assert.assertEquals(findResults.size(), 2);
    }
}
