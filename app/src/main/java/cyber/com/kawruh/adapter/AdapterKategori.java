package cyber.com.kamus.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cyber.com.kamus.R;
import cyber.com.kamus.databinding.ViewHolderCategoryBinding;
import cyber.com.kamus.model.Kategori;
import cyber.com.kamus.util.listener.ListenerView;
import cyber.com.kamus.util.listener.ListenerViewHolder;

public class AdapterKategori extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Kategori> kategoris = new ArrayList<>();

    ListenerView.onClickViewHolder listenerViewHolder;

    public void setListenerViewHolder(ListenerView.onClickViewHolder listenerViewHolder) {
        this.listenerViewHolder = listenerViewHolder;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        return new VHKategory((ViewHolderCategoryBinding) DataBindingUtil.inflate(inflater,
                R.layout.view_holder_category, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        VHKategory vhKategory = (VHKategory) viewHolder;

        Glide.with(viewHolder.itemView)
                .load(vhKategory.itemView.getResources()
                        .getIdentifier(
                                this.kategoris.get(i).getDrawable().trim(), "drawable",
                                vhKategory.itemView.getContext().getPackageName()
                        )
                )
                .into(vhKategory.viewHolderCategoryBinding.icon);

        vhKategory.viewHolderCategoryBinding
                .title.setText(this.kategoris.get(i).getName());
        vhKategory.listenerViewHolder = listenerViewHolder;
    }

    @Override
    public int getItemCount() {
        return this.kategoris.size();
    }

    public void add(Kategori kategori) {
        this.kategoris.add(kategori);
        this.notifyItemInserted(this.kategoris.size() - 1);
    }

    public Kategori getItem(Integer position) {
        return this.kategoris.get(position);
    }

    class VHKategory extends RecyclerView.ViewHolder {
        private final ViewHolderCategoryBinding viewHolderCategoryBinding;
        public ListenerView.onClickViewHolder listenerViewHolder;

        public VHKategory(ViewHolderCategoryBinding viewHolderCategoryBinding) {
            super(viewHolderCategoryBinding.getRoot());
            this.viewHolderCategoryBinding = viewHolderCategoryBinding;
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerViewHolder.onClickListener(v, getAdapterPosition());
                }
            });
        }
    }
}
