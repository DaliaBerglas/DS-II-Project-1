public interface FilterCriteria {
    FilterCriteria and(FilterCriteria filterCriteria);

    FilterCriteria or(FilterCriteria filterCriteria);
}
