package lt.mindaugas.androidrestapi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import lt.mindaugas.androidrestapi.repository.RemoteRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RemoteRepository remoteRepository = new RemoteRepository();
        remoteRepository.fetchAllUsers();
        remoteRepository.fetchUserById(1);
    }
}