package cyber.com.kamus.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

import cyber.com.kamus.R;
import cyber.com.kamus.databinding.ViewHolderContentBinding;
import cyber.com.kamus.databinding.ViewHolderSectionBinding;
import cyber.com.kamus.model.SearchResult;

public class AdapterSearch extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    ArrayList<SearchResult> searchResults = new ArrayList<>();
    ArrayList<SearchResult> searchResultsFiltered = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        RecyclerView.ViewHolder viewHolder = null;

        switch (TypeLayout.values()[i]) {
            case section:
                viewHolder = new VHSection(
                        (ViewHolderSectionBinding) DataBindingUtil.inflate(inflater,
                                R.layout.view_holder_section, viewGroup, false)
                );
                break;
            case content:
                viewHolder = new VHContent(
                        (ViewHolderContentBinding) DataBindingUtil.inflate(inflater,
                                R.layout.view_holder_content, viewGroup, false)
                );
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (TypeLayout.values()[getItemViewType(i)]) {
            case section:
                VHSection vhSection = (VHSection) viewHolder;
                vhSection.binding.title.setText(this.searchResults.get(i).getTitle());
                break;
            case content:
                VHContent vhContent = (VHContent) viewHolder;
                vhContent.binding.textup.setText(this.searchResults.get(i).getKamus().getIndonesia());
                vhContent.binding.textdown.setText(this.searchResults.get(i).getKamus().getJawa());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.searchResults.get(position).getTypeLayout().ordinal();
    }

    public void setSearchResults(ArrayList<SearchResult> searchResults) {
        this.searchResults = searchResults;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();

                if (charString.isEmpty()){
                    searchResultsFiltered = searchResults;
                }else{
                    ArrayList<SearchResult> results = new ArrayList<>();

                    for (SearchResult searchResult: searchResults) {

                    }

                }
                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }
        };
    }

    public enum TypeLayout {
        section, content
    }

    public class VHSection extends RecyclerView.ViewHolder {
        ViewHolderSectionBinding binding;

        public VHSection(ViewHolderSectionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class VHContent extends RecyclerView.ViewHolder {
        private ViewHolderContentBinding binding;

        public VHContent(ViewHolderContentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
