package com.example.portfolio.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.CalendarReservationDtoRepository;
import com.example.portfolio.model.dao.ReservationDtoRepository;
import com.example.portfolio.model.dao.ReservationRepository;
import com.example.portfolio.model.entity.Reservation;
import com.example.portfolio.model.entity.dto.CalendarReservationDto;
import com.example.portfolio.model.entity.dto.ReservationDto;
import com.example.portfolio.model.form.ReservationForm;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ReservationService {

  @Autowired
  private LoginSession loginSession;

  @Autowired
  private ReservationRepository reservationRepos;

  @Autowired
  private ReservationDtoRepository reservationDtoRepos;

  @Autowired
  private CalendarReservationDtoRepository calendarReservRepos;

  @Autowired
  private UserService userService;

  @Autowired
  private Utils utils;

  /**
   * 予約の挿入
   *
   * @param f ReservationForm
   * @return Reservation
   */
  public Reservation reserve(ReservationForm f) {
    int userId = loginSession.getUserId(); // loginセッションからユーザIDを取得
    int productId = f.getProductId(); // formから商品IDを取得
    int count = f.getCount(); // formから個数を取得
    String title = userService.findUserNameById(userId) + " : " + count + "PAX"; // 予約タイトルの文字列をつくる
    String start = f.getDate(); // formから開始日を取得
    String end = utils.getEndDate(start); // 終了日を開始日の1日後に設定する
    Reservation reservation = new Reservation(userId, productId, count, start, end, title); // reservationインスタンスを上記の引数で作成
    return reservationRepos.save(reservation); // reservationsテーブルに新規レコードを登録
  }

  /**
   * 予約リスト取得
   *
   * @param userId ユーザID
   * @return 予約リスト
   */
  public List<ReservationDto> getReservationList(int userId) {
    return reservationDtoRepos.getReservationList(userId);
  }

  /**
   * 予約削除
   *
   * @param reservationId 予約ID
   * @return 削除件数
   */
  public int cancel(int reservationId) {
    try {
      return reservationRepos.deleteByReservationId(reservationId);
    } catch (IllegalArgumentException e) {
      throw new RuntimeException();
    }
  }

  /**
   * ページネーションされた予約リスト取得
   *
   * @param page リクエストされたページ番号
   * @return ページネーションされた予約リスト
   */
  public Page<ReservationDto> findPaginatedList(Optional<Integer> page) {
    Pageable pageable = utils.getPageable(page); 
    int pageSize = pageable.getPageSize();
    int currentPage = pageable.getPageNumber();
    int startItem = currentPage * pageSize;
    List<ReservationDto> list;
    List<ReservationDto> products = reservationDtoRepos.findAllReservation();
    if (products.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, products.size());
			list = products.subList(startItem, toIndex);
		}
		Page<ReservationDto> productPage = new PageImpl<>(list, pageable, products.size());
		return productPage;
  }

  /**
   * 予約有効フラグの更新
   *
   * @param reservationId 予約ID
   */
  public void updateValidFlag(int reservationId) {
    reservationRepos.updateValidFlag(reservationId);
  }

  /**
   * 予約リストの全取得
   *
   * @return 全予約リスト
   */
  public List<Reservation> findAllForCalendar() {
    List<CalendarReservationDto> results = calendarReservRepos.findAllByValidFlagTrue();
    List<Reservation> reservations = new ArrayList<>();
    for (CalendarReservationDto r : results) {
      reservations.add(new Reservation(r.getId(), r.getStart(), r.getEnd(), r.getTitle()));
    }
    return reservations;
  }

}
