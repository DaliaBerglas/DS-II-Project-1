import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DataFrameTest {

    @Test
    public void test1() {
        DataFrame people = new InMemoryDataFrame("C:\\Users\\...", Arrays.asList("ID"));
        DataFrame salary = new InMemoryDataFrame("C:\\Users\\...", Arrays.asList("PersonId"));
        DataFrame jobTitle = new InMemoryDataFrame("C:\\Users\\...", Arrays.asList("PersonId"));

        FilterCriteria inBoston = people.col("loc").equalTo("boston");
        FilterCriteria overThirty = people.col("age").greaterThan(30);
        FilterCriteria tall = people.col("height").greaterThan(6);


        DataFrame tallPeople = people.filterBy(tall)
                                    .select("name", "loc");
        tallPeople.print();



        DataFrame countByAge = people.filterBy(inBoston.and(overThirty))
                            .groupBy("age")
                            .count("name");

        countByAge.print();



        DataFrame all = people.join(salary, Arrays.asList("ID"), Arrays.asList("PersonId"))
                            .join(jobTitle, Arrays.asList("ID"), Arrays.asList("PersonId"));

        DataFrame earningsByCity = all
                                    .filterBy(all.col("jobtitle").equalTo("engineer"))
                                    .filterBy(all.col("salary").greaterThan(1000))
                                    .groupBy("loc")
                                    .sum("salary");

        earningsByCity.print();
    }

    @Test
    public void test2() {
        DataFrame people = new DiskDataFrame("C:\\Users\\...");

        FilterCriteria inBoston = people.col("loc").equalTo("boston");
        FilterCriteria overThirty = people.col("age").greaterThan(30);
        FilterCriteria tall = people.col("height").greaterThan(6);



        people = people.filterBy(inBoston);

        people = people.filterBy(overThirty);

        people = people.filterBy(tall);

        people = people.select("name");

        people.print();
    }


    @Test
    public void test3() {
        DataFrame people = new DiskDataFrame("C:\\Users\\...");

        FilterCriteria overThirty = people.col("age").greaterThan(30);

        people = people.filterBy(overThirty);

        people.print();

        people = people.filterBy(people.col("loc").equalTo("Boston"));

        people.print();
    }


    @Test
    public void test4() {
        DataFrame people = new InMemoryDataFrame("C:\\Users\\...", Arrays.asList("ID"));

        FilterCriteria personId = people.col("ID").equalTo(1234)
                                    .or(people.col("ID").equalTo(5678));

        people = people.filterBy(personId);

        people = people.select("name");

        people.print();
    }
}