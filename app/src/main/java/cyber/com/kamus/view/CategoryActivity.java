package cyber.com.kamus.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import cyber.com.kamus.R;
import cyber.com.kamus.adapter.AdapterSearch;
import cyber.com.kamus.database.database.Database;
import cyber.com.kamus.databinding.ActivityCategoryBinding;
import cyber.com.kamus.util.Helper;

public class CategoryActivity extends AppCompatActivity {
    ActivityCategoryBinding binding;

    public static final String CATEGORY = "CATEGORY";
    private AdapterSearch adapter;
    private Database database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Helper.chooseTheme(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category);
        this.adapter = new AdapterSearch();
        this.database = new Database(this);

        Intent intent = getIntent();

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (intent != null) {
            String category = intent.getStringExtra(CATEGORY);

            setTitle(category);

            binding.recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            binding.recycler.setAdapter(this.adapter);
            this.adapter.setSearchResults(database.getTableKamus().selectAllToHas(AdapterSearch.SearchFrom.fromIndonesia, category));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
