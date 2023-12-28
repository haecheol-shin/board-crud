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

        User existingUser = userRepository.findById(user.getId()).get(0);

        if (existingUser == null) {
            throw new IllegalStateException("아이디가 일치하지 않습니다.");
        } else if (!(existingUser.getPassword().equals(user.getPassword()))) {
            throw new IllegalStateException("패스워드가 일치하지 않습니다.");
        } else {
            httpSession.setAttribute("loggedInUser", existingUser); // 왜 existingUser는 되고 user는 안되지
            return user.getName();
        }
    }

    public User getLoggedInUser() {
        return (User) httpSession.getAttribute("loggedInUser");
    }

    private void validateDuplicateMember(User user) {

        if (userRepository.countUsers() != 0) {
            List<User> findUser = userRepository.findById(user.getId());
            if (!findUser.isEmpty()) {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            }
        }

    }


}
