package lt.mindaugas.androidrestapi.user_details;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import lt.mindaugas.androidrestapi.databinding.ActivityUserDetailsBinding;
import lt.mindaugas.androidrestapi.users.ui.MainActivity;

public class UserDetailsActivity extends AppCompatActivity {
    private ActivityUserDetailsBinding binding;
    private UserDetailsViewModel viewModel = null;
    long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(UserDetailsViewModel.class);
        userId = getIntent().getLongExtra(MainActivity.MAIN_ACTIVITY_USER_ID, -1);

        setUpLiveDataObserver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.requestUserResponse((int) userId);
    }

    private void setUpLiveDataObserver() {
        viewModel.getUserResponseLiveData().observe(this, userResponse -> {
            if (userResponse != null) {
                binding.infoTextView.setText(userResponse.getUser().toString());
            }
        });
    }
}