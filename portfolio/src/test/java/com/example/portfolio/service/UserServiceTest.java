package com.example.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.Optional;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.UserRepository;
import com.example.portfolio.model.entity.User;
import com.example.portfolio.model.session.LoginSession;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UserServiceTest {

  @Mock
  private UserRepository userRepos;

  @Mock
  private LoginSession loginSession;

  @Mock
  private ProductService productService;

  @InjectMocks
  private UserService userSerive;

  @Captor
  ArgumentCaptor<User> argCaptor;

  @Test
  public void testUpdateUserImageSuccess() {
    when(loginSession.getUserId()).thenReturn(1);
    MockMultipartFile kmlfile = new MockMultipartFile("data", "filename.kml", "text/plain", "some kml".getBytes());
    when(userRepos.updateUserImage(any(), anyInt())).thenReturn(1);
    int actual = userSerive.updateUserImage(kmlfile);
    assertEquals(1, actual);
  }

  @Test
  public void testSetLoginSession() {
    User user = new User();
    user.setUserId(1);
    user.setUserName("testAdmin");
    user.setEmail("example@gmail.com");
    user.setPassword("password");
    doNothing().when(loginSession).setTmpUserId(null);
    doNothing().when(loginSession).setLogined(true);
    doNothing().when(loginSession).setUserId(user.getUserId());
    doNothing().when(loginSession).setUserName(user.getUserName());
    doNothing().when(loginSession).setPassword(user.getPassword());
    doNothing().when(loginSession).setEmail(user.getEmail());
    userSerive.setLoginSession(user);
  }

  @Test
  public void testFindPaginatedList() {
    Optional<Integer> page = Optional.of(Integer.valueOf(0));
    when(productService.getCurrentPage(any())).thenReturn(1);
    Pageable pageable = mock(Pageable.class);
    @SuppressWarnings("unchecked")
    Page<User> expected = mock(Page.class);
    when(userRepos.findAll(pageable)).thenReturn(expected);
    Page<User> actual = userSerive.findPaginatedList(page);
    assertNull(actual);

  }




}
