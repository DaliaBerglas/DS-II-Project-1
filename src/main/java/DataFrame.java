import java.util.List;

public interface DataFrame {
    DataFrame filterBy(FilterCriteria filterCriteria);

    FilterCol col(String colName);

    DataFrame select(String... columnNames);

    DataFrame append(List<Object> row);

    GroupByObject groupBy(String... columnNames);

    DataFrame join(DataFrame other, List<String> selfColumns, List<String> otherColumns);

    List<List<Object>> rows();

    void print();
}