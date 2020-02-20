package ua.training.utility;

import java.util.List;

public class Page<E> {

    private int totalElements;
    private int currentPage;
    private List<E> elements;
    private int elementsOnPage;
    private int totalPage;

    public Page() {
    }

    public Page(int totalElements, int currentPage,
                List<E> elements, int elementsOnPage) {
        this.totalElements = totalElements;
        this.currentPage = currentPage;
        this.elements = elements;
        this.elementsOnPage = elementsOnPage;
        totalPage = totalPage();
    }

    private int totalPage() {
        int result = 1;
        if (totalElements > 0) {
            result = (int) Math.ceil((double) totalElements / elementsOnPage);
        }
        return result;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<E> getElements() {
        return elements;
    }

    public void setElements(List<E> elements) {
        this.elements = elements;
    }

    public int getElementsOnPage() {
        return elementsOnPage;
    }

    public void setElementsOnPage(int elementsOnPage) {
        this.elementsOnPage = elementsOnPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalElements=" + totalElements +
                ", currentPage=" + currentPage +
                ", elements=" + elements +
                ", elementsOnPage=" + elementsOnPage +
                ", totalPage=" + totalPage +
                '}';
    }
}
