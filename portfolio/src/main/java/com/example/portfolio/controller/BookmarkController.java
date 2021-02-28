package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.dto.BookmarkDto;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.BookmarkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/portfolio/bookmark")
public class BookmarkController {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private BookmarkService bookmarkService;

  /**
   * お気に入り一覧表示
   * @param m Model
   * @return bookmark.html
   */
  @GetMapping(value = "")
  public String init(Model m) {
    List<BookmarkDto> bookmarks = bookmarkService.getBookmarkList(loginSession.getUserId()); // ユーザーIDを条件にbookmarksテーブルから全てのレコードを取得する
    m.addAttribute("bookmarks", bookmarks);
    m.addAttribute("loginSession", loginSession);
    return "bookmark";
  }

  /**
   * お気に入り追加
   * @param productId 商品ID
   * @return 商品追加成功/失敗
   */
  @PostMapping(value = "/add")
  @ResponseBody
  public boolean add(@RequestBody int productId) {
    boolean bool = false;
    BookmarkDto bookmark = bookmarkService.findBookmark(productId, loginSession.getUserId()); // 商品IDとユーザーIDを条件にしてbookmarksテーブルからユニークなレコードを取得する
    if (bookmark == null) { // ブックマークの重複がなかった場合
      int result = bookmarkService.add(productId); // bookmarksテーブルにレコードを追加する
      if (result > 0) {
        bool = true;
      }
    }
    return bool;
  }

  /**
   * お気に入り削除
   * @param productId 商品ID
   * @return お気に入り削除成功/失敗
   * @throws Exception
   */
  @PostMapping(value = "/delete")
  @ResponseBody
  public boolean delete(@RequestBody int productId) {
    boolean bool = false;
    int result = bookmarkService.delete(productId);
    if (result > 0) {
      bool = true;
    }
    return bool;
  }

  /**
   * ブックマーク済みかどうかをチェック
   * @param productId
   * @return bool ブックマーク済み:true 未ブックマーク:false
   */
  @PostMapping(value = "/check")
  @ResponseBody
  public boolean check(@RequestBody int productId) {
    boolean bool = false;
    BookmarkDto bookmark = bookmarkService.findBookmark(productId, loginSession.getUserId());
    if (bookmark != null) { // まだbookmarksテーブルに登録されていなかったら
      bool = true; // 登録可能状態に変更する
    }
    return bool;
  }

}
