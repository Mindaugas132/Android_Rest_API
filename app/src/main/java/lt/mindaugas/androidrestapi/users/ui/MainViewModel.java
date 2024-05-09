package lt.mindaugas.androidrestapi.users.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.Getter;
import lt.mindaugas.androidrestapi.entity.UsersResponse;
import lt.mindaugas.androidrestapi.repository.RemoteRepository;

public class MainViewModel extends ViewModel {
    @Getter
    private final MutableLiveData<UsersResponse> usersResponseLiveData;
    private final RemoteRepository remoteRepository;

    public MainViewModel() {
        this.remoteRepository = new RemoteRepository();
        this.usersResponseLiveData = new MutableLiveData<>();
    }

    public void requestUsersResponse() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                UsersResponse usersResponse = remoteRepository.fetchAllUsers().get();
                usersResponseLiveData.postValue(usersResponse);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

}
