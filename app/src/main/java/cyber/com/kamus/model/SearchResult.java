package cyber.com.kamus.model;

import java.util.logging.Filter;

import cyber.com.kamus.adapter.AdapterSearch;

public class SearchResult {
    String title;
    AdapterSearch.TypeLayout typeLayout;
    Kamus kamus;

    public SearchResult(String title) {
        this.title = title;
        typeLayout = AdapterSearch.TypeLayout.section;
    }

    public SearchResult(Kamus kamus) {
        this.kamus = kamus;
        typeLayout = AdapterSearch.TypeLayout.content;
    }

    public SearchResult(String title, AdapterSearch.TypeLayout typeLayout, Kamus kamus) {
        this.title = title;
        this.typeLayout = typeLayout;
        this.kamus = kamus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AdapterSearch.TypeLayout getTypeLayout() {
        return typeLayout;
    }

    public void setTypeLayout(AdapterSearch.TypeLayout typeLayout) {
        this.typeLayout = typeLayout;
    }

    public Kamus getKamus() {
        return kamus;
    }

    public void setKamus(Kamus kamus) {
        this.kamus = kamus;
    }

}
