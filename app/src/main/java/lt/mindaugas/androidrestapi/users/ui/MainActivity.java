package lt.mindaugas.androidrestapi.users.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import lt.mindaugas.androidrestapi.R;
import lt.mindaugas.androidrestapi.databinding.ActivityMainBinding;
import lt.mindaugas.androidrestapi.entity.User;
import lt.mindaugas.androidrestapi.user_details.UserDetailsActivity;

public class MainActivity extends AppCompatActivity {
    public static final String MAIN_ACTIVITY_USER_ID = "user_ID";
    private MainViewModel mainViewModel = null;
    private ActivityMainBinding binding;
    private RecycleAdapter recycleAdapter;
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setUpLiveDataObserver();

        setUpRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainViewModel.requestUsersResponse();
    }

    private void setUpLiveDataObserver() {
        mainViewModel.getUsersResponseLiveData().observe(this, usersResponse -> {
            if (usersResponse != null) {
                users.addAll(usersResponse.getData());
                recycleAdapter.addToList(users);
            }
        });
    }

    private void setUpRecyclerView() {
        binding.usersRecycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleAdapter = new RecycleAdapter(
                this, users, this::onItemClick, this::onLongItemClick
        );
        binding.usersRecycleView.setAdapter(recycleAdapter);

    }

    private void onItemClick(int position) {
        User user = users.get(position);
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, user.getId());
        startActivity(intent);
    }

    private void onLongItemClick(int position) {
        User user = users.get(position);
        Snackbar
                .make(binding.getRoot(), "Long Click: " + user.getFirstName(), Snackbar.LENGTH_LONG)
                .show();
    }
}
