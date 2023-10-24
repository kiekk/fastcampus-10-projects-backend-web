package com.fastcampus.pass.contoller.pass;

import com.fastcampus.pass.service.pass.Pass;
import com.fastcampus.pass.service.pass.PassService;
import com.fastcampus.pass.service.user.User;
import com.fastcampus.pass.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/passes")
@RequiredArgsConstructor
public class PassViewController {
    private final UserService userService;
    private final PassService passService;

    @GetMapping
    public String getPasses(@RequestParam("userId") String userId, Model model) {
        final List<Pass> passes = passService.getPasses(userId);
        final User user = userService.getUser(userId);

        model.addAttribute("passes", passes);
        model.addAttribute("user", user);

        return "pass/index";
    }

}
