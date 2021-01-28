package com.example.portfolio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.CalendarReservationDtoRepository;
import com.example.portfolio.model.dao.ReservationDtoRepository;
// import com.example.portfolio.model.dao.ReservationDtoRepository;
import com.example.portfolio.model.dao.ReservationRepository;
import com.example.portfolio.model.entity.Reservation;
import com.example.portfolio.model.entity.dto.CalendarReservationDto;
import com.example.portfolio.model.entity.dto.ReservationDto;
import com.example.portfolio.model.form.ReservationForm;
import com.example.portfolio.model.session.LoginSession;
import com.example.portfolio.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
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
    int userId = loginSession.getUserId();
    int productId = f.getProductId();
    int count = f.getCount();
    String title = userService.findUserNameById(userId) + " : " + count + "PAX";
    String start = f.getDate();
    String end = utils.getEndDate(start);
    Reservation reservation = new Reservation(userId, productId, count, start, end, title);
    return reservationRepos.save(reservation);
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
  public Page<Reservation> findPaginatedList(Optional<Integer> page) {
    Reservation probe = new Reservation();
    probe.setValidFlag(true);
    ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "userId", "productId", "title", "start",
        "end", "count");
    Example<Reservation> example = Example.of(probe, matcher);
    Pageable pageable = utils.getPageable(page);
    return reservationRepos.findAll(example, pageable);
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
