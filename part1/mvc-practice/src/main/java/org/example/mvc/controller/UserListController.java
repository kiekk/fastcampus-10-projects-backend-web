package org.example.mvc.controller;

import org.example.mvc.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("users", List.of(
                new User("userId-1", "test1"),
                new User("userId-2", "test2")
        ));
        return "/user/list.jsp";
    }
}
