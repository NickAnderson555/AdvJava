package java112.employee;

import java.io.*;
import java.util.*;

public class Search {

private String searchType;
private String searchTerm;
private List<Employee> results;
private boolean test;

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setResults(List<Employee> results) {
        this.results = results;
    }

    public List<Employee> getResults() {
        return results;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public boolean getTest() {
        return test;
    }

    public void addFoundEmployee(Employee employee) {
        results.add(employee);
    }

    public Search(String searchType, String searchTerm) {
        results = new ArrayList<Employee>();
        test = false;
        this.searchType = searchType;
        this.searchTerm = searchTerm;
    }

    public Search() {

    }

}
