public interface GroupByObject {
    DataFrame count(String columnName);

    DataFrame sum(String columnName);
}
