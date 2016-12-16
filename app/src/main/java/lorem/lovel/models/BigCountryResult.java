package lorem.lovel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lorem.lovel.models.CardModel;


@JsonIgnoreProperties(ignoreUnknown = true)
public class BigCountryResult {
    private int currentPage;
    private int numberOfPages;
    private int totalResults;

    private CardModel[] data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public CardModel[] getData() {
        return data;
    }

    public void setData(CardModel[] data) {
        this.data = data;
    }
}
