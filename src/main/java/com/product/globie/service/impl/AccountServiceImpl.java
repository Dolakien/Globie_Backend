package com.product.globie.service.impl;

import com.product.globie.entity.User;
import com.product.globie.payload.DTO.AccountDTO;
import com.product.globie.payload.request.CreateAccountRequest;
import com.product.globie.payload.response.MyAccountResponse;
import com.product.globie.repository.RoleRepository;
import com.product.globie.repository.UserRepository;
import com.product.globie.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public AccountDTO createAccount(CreateAccountRequest accountDTO) {
        if (userRepository.existsByUserName(accountDTO.getUserName())) {
            throw new RuntimeException("User already exists with username: " + accountDTO.getUserName());
        }

        User newUser = modelMapper.map(accountDTO, User.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String pass = "12345678";
        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        String encodedPassword = passwordEncoder.encode(pass);
        newUser.setPassword(encodedPassword);
        newUser.setRole(roleRepository.findRoleByRoleId(accountDTO.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + accountDTO.getRoleId())));

        User savedUser = userRepository.save(newUser);

        return modelMapper.map(savedUser, AccountDTO.class);
    }


    @Override
    public User updateAccount(User user) {
        return null;
    }

    @Override
    public void deleteAccount(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + username));
        userRepository.delete(user);
    }

    @Override
    public AccountDTO getAccount(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + username));
        return modelMapper.map(user, AccountDTO.class);
    }

    @Override
    public List<AccountDTO> getAllAccount() {
        List<User> users = userRepository.findAll();

        // Ánh xạ từng phần tử trong List<User> sang List<AccountDTO>
        return users.stream()
                .map(user -> modelMapper.map(user, AccountDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MyAccountResponse myAccount() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUserName(name)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + name));
        return modelMapper.map(user, MyAccountResponse.class);
    }
}
