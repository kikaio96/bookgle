package kr.or.son.bookgle.controller;

import java.util.ArrayList;
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

import kr.or.son.bookgle.dto.Book;
import kr.or.son.bookgle.dto.BookState;
import kr.or.son.bookgle.dto.Lend;
import kr.or.son.bookgle.dto.User;
import kr.or.son.bookgle.service.BookService;
import kr.or.son.bookgle.service.BookStateService;
import kr.or.son.bookgle.service.LendService;
import kr.or.son.bookgle.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	BookService bookService;
	@Autowired
	BookStateService bookStateService;
	@Autowired
	UserService userService;
	@Autowired
	LendService lendService;
	
	@GetMapping(path="/users")
	public String userList(@RequestParam(name="start", required=false, defaultValue="0") int start, ModelMap model) {
		int listCount = 5;
		List<User> userList = userService.getUserList(start, listCount);
		
		int count = userService.userCount();
		int pageCount = count/listCount;
		if(count % listCount > 0)
			pageCount++;
		
		List<Integer> pageStartList = new ArrayList<>();
		for(int i = 0; i < pageCount; i++) {
			pageStartList.add(i * listCount);
		}
		
		model.addAttribute("userList", userList);
		model.addAttribute("count", count);
		model.addAttribute("pageStartList", pageStartList);
		
		return "admin/userlist";
	}
	
	@GetMapping(path="/user")
	public String userRegist(ModelMap model) {
		return "admin/userregist";
	}
	
	@GetMapping(path="/users/{user_id}")
	public String userInfo(@PathVariable int user_id, ModelMap model) {
		User user = userService.getUser(user_id);
		
		model.addAttribute("userInfo", user);
		
		return "admin/userinfo";
	}
	
	@PostMapping(path="/user")
	public String userRegist(@ModelAttribute User user) {
		user.setPassword("1111");
		userService.addUser(user);
		return "redirect:/admin/users";
	}
	
	@PutMapping(path="/users/{user_id}")
	public String userModify(@ModelAttribute User user) {
		userService.updateUserByAdmin(user);
		
		return "redirect:/admin/users/" + user.getUser_id();
	}
	
	@DeleteMapping(path="/users/{user_id}")
	public String userdelete(@RequestParam(name="user_id") int user_id) {
		userService.deleteUser(user_id);
		return "redirect:/admin/users";
	}
	
	
	@GetMapping(path = "/books")
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
		
		return "admin/booklist";
	}
	
	@GetMapping(path = "/books/{bookId}")
	public String bookInfo(@PathVariable int bookId, ModelMap model) {
		int book_id = bookId;
		Book book = bookService.getBook(book_id);
		
		model.addAttribute("book", book);
		
		return "admin/bookinfo";
	}
	
	@GetMapping(path = "/book")
	public String bookRegist(ModelMap model) {
		return "admin/bookregist";
	}
	
	@PutMapping(path = "/books/{bookId}")
	public String bookModify(@ModelAttribute Book book, @ModelAttribute BookState bookState, @RequestParam(name="before_total_count") int before_total_count) {
		bookService.updateBook(book);
		int remain = bookState.getTotal_count() - before_total_count; 
		bookStateService.updateTotalCount(bookState, remain);
		return "redirect:/admin/books/" + book.getBook_id();
	}
	
	@PostMapping(path = "/book")
	public String regist(@ModelAttribute Book book, @ModelAttribute BookState bookState) {
		Book insertedBook = bookService.addBook(book);
		bookState.setBook_id(insertedBook.getBook_id());
		bookState.setRemain(bookState.getTotal_count());
		bookStateService.addBookState(bookState);
		return "redirect:/admin/books/";
	}
	
	@DeleteMapping(path = "/books/{bookId}")
	public String delete(@PathVariable long bookId, @RequestParam(name="book_state_id") int book_state_id) {
		bookStateService.deleteBookState(book_state_id);
		bookService.deleteBook(bookId);
		return "redirect:/admin/books";
	}
	
	@GetMapping(path="/lends")
	public String lendList(@RequestParam(name="start", required=false, defaultValue="0") int start, HttpSession session, ModelMap model) {		
		int listCount = 5;

		List<Lend> lendList = lendService.getLendList(start, listCount);
		
		// 전체 페이지수 구하기
		int count = lendService.lendCount();
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
		
		return "admin/lendlist";
	}
}
