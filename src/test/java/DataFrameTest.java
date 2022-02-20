import org.junit.jupiter.api.Test;

public class DataFrameTest {
    String peoplePath = DataFrameTest.class.getResource("people.csv").getFile();
    String jobtitlePath = DataFrameTest.class.getResource("jobtitle.csv").getFile();
    String salaryPath = DataFrameTest.class.getResource("salary.csv").getFile();

    @Test
    public void test1() {
        DataFrame people = new InMemoryDataFrame(peoplePath, "ID");
        DataFrame salary = new InMemoryDataFrame(jobtitlePath, "PersonId");
        DataFrame jobTitle = new InMemoryDataFrame(salaryPath, "PersonId");

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



        DataFrame all = people.join(salary, "ID", "PersonId")
                            .join(jobTitle, "ID", "PersonId");

        DataFrame earningsByCity = all
                                    .filterBy(all.col("jobtitle").equalTo("engineer"))
                                    .filterBy(all.col("salary").greaterThan(1000))
                                    .groupBy("loc")
                                    .sum("salary");

        earningsByCity.print();
    }

    @Test
    public void test2() {
        DataFrame people = new DiskDataFrame(peoplePath);

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
        DataFrame people = new DiskDataFrame(peoplePath);

        FilterCriteria overThirty = people.col("age").greaterThan(30);

        people = people.filterBy(overThirty);

        people.print();

        people = people.filterBy(people.col("loc").equalTo("Boston"));

        people.print();
    }


    @Test
    public void test4() {
        DataFrame people = new InMemoryDataFrame(peoplePath, "ID");

        FilterCriteria personId = people.col("ID").equalTo(1)
                                    .or(people.col("ID").equalTo(2));

        people = people.filterBy(personId);

        people = people.select("name");

        people.print();
    }
}