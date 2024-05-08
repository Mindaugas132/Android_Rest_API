package lt.mindaugas.androidrestapi.user_details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import lombok.Getter;
import lt.mindaugas.androidrestapi.entity.UserResponse;
import lt.mindaugas.androidrestapi.repository.RemoteRepository;

public class UserDetailsViewModel extends ViewModel {
    @Getter
    private MutableLiveData<UserResponse> userResponseLiveData = new MutableLiveData<>();
    private final RemoteRepository remoteRepository;

    public UserDetailsViewModel() {
        this.remoteRepository = new RemoteRepository();
//        this.userResponseLiveData = new MutableLiveData<>();
    }

    public void requestUserResponse(int userId) {
        userResponseLiveData = remoteRepository.fetchUserById(userId);
    }
}
