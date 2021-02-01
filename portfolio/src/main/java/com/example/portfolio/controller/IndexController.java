package com.example.portfolio.controller;

import java.util.List;

import com.example.portfolio.model.entity.Category;
import com.example.portfolio.model.entity.Destination;
import com.example.portfolio.model.entity.Testimonial;
import com.example.portfolio.model.entity.dto.ProductDto;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.service.CategoryService;
import com.example.portfolio.service.DestinationService;
import com.example.portfolio.service.ProductService;
import com.example.portfolio.service.TestimonialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/portfolio")
public class IndexController {

  @Autowired
  CategoryService categoryService;

  @Autowired
  DestinationService destinationService;

  @Autowired
  ProductService productService;

  @Autowired
  TestimonialService testimonialService;

  @Autowired
  private LoginSession loginSession;

  /**
   * トップページ初期表示
   * @param m Model
   * @return index.html
   */
  @GetMapping(value = "")
  public String init(Model m) {
     // ログインしてない&仮ユーザIDがない(=初めてページを開いたとき)
    if (!loginSession.isLogined() && loginSession.getTmpUserId() == 0) {
      int tempUserId = (int) (Math.random() * 100000);
      loginSession.setTmpUserId(tempUserId); // ランダムな整数を仮ユーザIDとしてログインセッションに登録
      loginSession.setLogined(false);
    }
    List<ProductDto> products = productService.findAll();
    List<Category> categories = categoryService.findAll();
    List<Destination> destinations = destinationService.findAll();
    List<Testimonial> testimonials = testimonialService.findAll();
    m.addAttribute("products", products);
    m.addAttribute("categories", categories);
    m.addAttribute("destinations", destinations);
    m.addAttribute("testimonials", testimonials);
    m.addAttribute("loginSession", loginSession);
    return "index";
  }

}

/* TODO
  - 画面設計書作成
  - READMEに載せるgifのキャプチャ動画作成
  - READMEに載せる動画をgifにして投稿
  - READMEに記載する機能をAPPを動かしながら確認し記載
  - READMEに採用担当者にどう操作してもらいたいかの手順を記載
  - READMEに使用技術の詳細を調べて記載
  */

  /* 残りの作成工程
  - 統合テスト(統合試験仕様書をページごとに簡単に作成の上、試験)
  - README作成
  - ⭐️AWSとDockerを使ってデプロイ
  - Qiita投稿(Stripe, fullCalendar)
*/

/* メンターに聞くこと
- コードレビュー
- デプロイ
- README
 */
