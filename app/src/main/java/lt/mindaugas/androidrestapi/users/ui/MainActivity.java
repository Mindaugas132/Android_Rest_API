package lt.mindaugas.androidrestapi.users.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import lt.mindaugas.androidrestapi.R;
import lt.mindaugas.androidrestapi.databinding.ActivityMainBinding;
import lt.mindaugas.androidrestapi.entity.User;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel = null;
    private ActivityMainBinding binding;
    private ArrayAdapter<User> arrayAdapter;
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setUpLiveDataObserver();
    }

    private void setUpLiveDataObserver() {
        mainViewModel.getUsersResponseLiveData().observe(this, usersResponse -> {
            if (usersResponse != null) {
                users.addAll(usersResponse.getData());
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setUpRecyclerView() {
        binding.usersRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }
}
