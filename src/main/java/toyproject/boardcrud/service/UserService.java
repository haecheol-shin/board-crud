package toyproject.boardcrud.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.boardcrud.domain.User;
import toyproject.boardcrud.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Transactional
    public String signup(User user) {

        validateDuplicateMember(user);
        userRepository.save(user);
        return user.getId();
    }

    @Transactional
    public String signin(User user) {

        if (userRepository.findById(user.getId()) == null) {
            throw new IllegalStateException("아이디가 일치하지 않습니다.");
        } else if (userRepository.findById(user.getId()).getPassword() != user.getPassword()) {
            throw new IllegalStateException("패스워드가 일치하지 않습니다.");
        } else {
            httpSession.setAttribute("loggedInUser", user);
            return user.getName();
        }
    }

    public User getLoggedInUser() {
        return (User) httpSession.getAttribute("loggedInUser");
    }

    private void validateDuplicateMember(User user) {

        User findUser = userRepository.findById(user.getId());
        if (!(findUser == null)) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }
    }


}
