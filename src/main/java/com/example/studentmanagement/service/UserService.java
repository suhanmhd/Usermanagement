package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.UserInfo;
import com.example.studentmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


//    @Override
//    public User createUser(User user) {
//
//
//        return userRepo.save(user);
//    }

        public String addUser(UserInfo userInfo, Model model){
            Optional<UserInfo>existingUser = userRepository.findByUsername(userInfo.getUsername());
            Optional<UserInfo>existingEmail = userRepository.findByEmail(userInfo.getEmail());
            if(existingUser.isPresent()){
                model.addAttribute("errorMessage", "User already exists");
                return "signup";
            } else if (existingEmail.isPresent()) {
                model.addAttribute("errorMessage", "Email already exists");
                return "signup";

            } else {

                userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
                userRepository.save(userInfo);
                return "login";
            }
    }

    public List<UserInfo> findAll() {
           return userRepository.findByUsernameNot("admin");
    }


    public Optional<UserInfo> getUserdetails(Long id){
            return userRepository.findById(id);
    }

    public UserInfo save(UserInfo user) {
            return  userRepository.save(user);
    }

    public void delete(Long id) {
            userRepository.deleteById(id);
    }

    public List<UserInfo> loadAllUsers() {
            return  userRepository.findAll();
    }

    public List<UserInfo> searchUsers(String keyword) {
        return userRepository.findByKeyword(keyword);
    }

    public Optional<UserInfo> getUserdata(String username) {
            return  userRepository.findByUsername(username);
    }

    public void deleteEmail(Long id) {
           // userRepository.deleteEmail(id);
    }

    public UserInfo findById(Long id) {
            return userRepository.findById(id).orElse(null);
    }

//    public Optional<UserInfo> getUserById(Long id) {
//            return userRepository.findById(id);
//    }
}
