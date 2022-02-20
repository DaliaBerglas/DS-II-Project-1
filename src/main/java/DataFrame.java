import java.util.List;

public interface DataFrame {
    DataFrame filterBy(FilterCriteria filterCriteria);

    FilterCol col(String colName);

    DataFrame select(String... columnNames);

    DataFrame append(List<Object> row);

    GroupByObject groupBy(String... columnNames);

    DataFrame join(DataFrame other, String selfColumn, String otherColumn);

    List<List<Object>> rows();

    void print();
}