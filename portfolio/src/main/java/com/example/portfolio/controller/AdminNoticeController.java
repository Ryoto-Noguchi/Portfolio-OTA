package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.Notice;
import com.example.portfolio.service.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/portfolio/admin/notice")
public class AdminNoticeController {

  @Autowired
  private NoticeService noticeService;

  /**
   * 管理者/お知らせ画面一覧表示
   *
   * @param m Model
   * @return admin/notice.html
   */
  @GetMapping(value = "")
  public String init(Model m) {
    List<Notice> notices = noticeService.findAll(); // お知らせの全取得
    m.addAttribute("notices", notices);
    return "admin/notice";
  }

  /**
   * お知らせ詳細画面表示
   *
   * @param id お知らせID
   * @param m  Model
   * @return admin/notice_detail,html
   */
  @GetMapping(value = "/{id}")
  public String showDetail(@PathVariable("id") int id, Model m) {
    Notice notice = noticeService.findById(id); // お知らせIDを条件にお知らせを取得する
    m.addAttribute("notice", notice);
    return "admin/notice_detail";
  }

  /**
   * お知らせ修正
   *
   * @param notice お知らせ
   * @return お知らせ修正成功/失敗
   */
  @PostMapping(value = "/modify")
  @ResponseBody
  public boolean update(@RequestBody Notice notice) {
    boolean bool = false;
    int result = noticeService.update(notice); // お知らせ更新
    if (result > 0) { // 更新されたレコードがあった場合
      bool = true; // 更新成功に変更
    }
    return bool;
  }

  /**
   * お知らせ作成
   * @param notice
   * @return 作成成功/失敗
   */
  @PostMapping("/create")
  @ResponseBody
  public boolean insert(@RequestBody Notice notice) {
    boolean bool = false;
    Notice insertedNotice = noticeService.insert(notice); // 入力された内容をもとにお知らせを作成
    if (insertedNotice != null) { // お知らせが問題なく作成された場合
      bool = true; // 作成成功に変更する
    }
    return bool;
  }

}
