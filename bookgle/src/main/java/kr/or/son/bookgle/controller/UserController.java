package kr.or.son.bookgle.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.son.bookgle.dto.Book;
import kr.or.son.bookgle.dto.Lend;
import kr.or.son.bookgle.dto.User;
import kr.or.son.bookgle.service.BookService;
import kr.or.son.bookgle.service.LendService;
import kr.or.son.bookgle.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	BookService bookService;
	@Autowired
	LendService lendService;
	
	@GetMapping(path="/login")
	public String login(ModelMap model) {
		return "user/fragments/login";
	}
	
	@PostMapping(path="/login")
	public String login(@RequestParam(name="email") String email, @RequestParam(name="password") String password, 
						HttpSession session, RedirectAttributes redirectAttr) {
		User user = userService.login(email, password);
		
		if(user == null) {
			redirectAttr.addFlashAttribute("errorResult", -1);
			return "redirect:/user/login";
		} else {
			session.setAttribute("userInfo", user);
			return "redirect:/user/books";
		}
	}
	
	@PostMapping(path="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userInfo");
		return "redirect:/user/login";
	}
	
	@GetMapping(path="/userinfo")
	public String userInfo(HttpSession session, ModelMap model) {
		User user = (User)session.getAttribute("userInfo");
		
		try {
			model.addAttribute("userInfo", user);
		} catch(Exception e) {
			return "user/fragments/login";
		}
		
		return "user/userinfo";
	}
	
	@PutMapping(path="/users/{userId}")
	public String userModify(@ModelAttribute User user, @RequestParam(name="password_confirm") String pwdConfirm, 
							HttpSession session, RedirectAttributes redirectAttr) {
		User userInfo = (User)session.getAttribute("userInfo");
		
		try {
			userInfo.setPassword(user.getPassword());
			userInfo.setPhone(user.getPhone());
		} catch(Exception e) {
			return "user/fragments/login";
		}
		
		if(userInfo.getPassword() == "") {
			redirectAttr.addFlashAttribute("errorResult", -2);
			return "redirect:/user/userinfo";
		}
		
		if(!user.getPassword().equals(pwdConfirm)) {
			redirectAttr.addFlashAttribute("errorResult", -1);
			return "redirect:/user/userinfo";
		}
		
		userService.updateUser(userInfo);
		session.setAttribute("userInfo", userInfo);
		return "redirect:/user/userinfo";
	}
	
	@GetMapping(path="/books")
	public String bookList(@RequestParam(name="start", required=false, defaultValue="0") int start, ModelMap model) {
		int listCount = 5;
		// 책 목록 select
		List<Book> bookList = bookService.getBookList(start, listCount);
		
		// 전체 페이지수 구하기
		int count = bookService.bookCount();
		int pageCount = count / listCount;
		if(count % listCount > 0)
			pageCount++;
		
		List<Integer> pageStartList = new ArrayList<>();
		for(int i = 0; i < pageCount; i++) {
			pageStartList.add(i * listCount);
		}
		
		model.addAttribute("bookList", bookList);
		model.addAttribute("count", count);
		model.addAttribute("pageStartList", pageStartList);
		
		return "user/booklist";
	}
	
	@GetMapping(path = "/books/{bookId}")
	public String bookInfo(@PathVariable int bookId, ModelMap model) {
		int book_id = bookId;
		Book book = bookService.getBook(book_id);
		
		model.addAttribute("book", book);
		
		return "user/bookinfo";
	}
	
	@PostMapping(path="/lend")
	public String bookLend(@RequestParam(name="book_id") int bookId, HttpSession session, RedirectAttributes redirectAttr) {
		User user = (User)session.getAttribute("userInfo");
		
		// userInfo가 비어있는 경우 null exception
		// 로그인 화면으로 redirect
		long user_id;
		try {
			user_id = user.getUser_id();
		} catch(Exception e) {
			return "redirect:/user/login";
		}
		
		
		Calendar cal = Calendar.getInstance();
		Date today = new Date();
		cal.setTime(today);
		
		Lend lend = new Lend();
		lend.setUser_id(user_id);
		lend.setBook_id(bookId);
		lend.setLend_date(today);
		cal.add(Calendar.DATE, 14);
		lend.setReturn_date(cal.getTime());
		
		// 대여 전에 도서 수량이 부족해진 경우
		try {
			lend = lendService.addLend(lend);
		} catch (Exception e) {
			redirectAttr.addFlashAttribute("errorCode", 500);
			redirectAttr.addFlashAttribute("errorMessage", "도서 수량이 부족합니다.");
			return "redirect:/user/books/" + bookId;
		}
		
		return "redirect:/user/lends";
	}
	
	@GetMapping(path="/lends")
	public String lendList(@RequestParam(name="start", required=false, defaultValue="0") int start, HttpSession session, ModelMap model) {
		User user = (User)session.getAttribute("userInfo");
		
		long user_id;
		try {
			user_id = user.getUser_id();
		} catch(Exception e) {
			return "user/fragments/login";
		}
		
		int listCount = 5;

		List<Lend> lendList = lendService.getLendList(start, listCount, user_id);
		
		// 전체 페이지수 구하기
		int count = lendService.lendCount(user_id);
		int pageCount = count / listCount;
		if(count % listCount > 0)
			pageCount++;
		
		List<Integer> pageStartList = new ArrayList<>();
		for(int i = 0; i < pageCount; i++) {
			pageStartList.add(i * listCount);
		}
		
		model.addAttribute("lendList", lendList);
		model.addAttribute("count", count);
		model.addAttribute("pageStartList", pageStartList);
		
		return "user/lendlist";
	}
	
	@DeleteMapping(path="/return")
	public String bookReturn(@RequestParam(name="lend_id") int lendId, @RequestParam(name="book_id") int bookId, HttpSession session, ModelMap model) {
		lendService.deleteLend(lendId, bookId);
		return "redirect:/user/lends";
	}
}
