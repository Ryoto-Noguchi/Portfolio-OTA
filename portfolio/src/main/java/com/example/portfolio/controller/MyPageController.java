package com.example.portfolio.controller;


import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portfolio/mypage")
public class MyPageController {

  @Autowired
  private UserService userService;

  @Autowired
  private LoginSession loginSession;

  /**
   * マイページ初期表示メソッド
   *
   * @param model
   * @return
   */
  @GetMapping("")
  public String goMyPage(Model model) {
    User user = userService.findByUserId(loginSession.getUserId());
    String image = userService.getUserImg(user);
    model.addAttribute("user", user);
    model.addAttribute("image", image);
    model.addAttribute("loginSession", loginSession);
    return "mypage";
  }
}
