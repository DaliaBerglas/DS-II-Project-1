public interface FilterCol {
    FilterCriteria greaterThan(Object val);

    FilterCriteria lessThan(Object val);

    FilterCriteria equalTo(Object val);
}
