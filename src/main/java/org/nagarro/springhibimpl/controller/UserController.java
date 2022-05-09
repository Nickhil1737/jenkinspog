package org.nagarro.springhibimpl.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.nagarro.springhibimpl.entity.Author;
import org.nagarro.springhibimpl.entity.Book;
import org.nagarro.springhibimpl.entity.User;
import org.nagarro.springhibimpl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;
	

	@GetMapping("/bookform")
	public String bookForm(Model theModel,HttpSession session) {
		Date currentDate = new Date(); 
        String dateStr = DateFormat.getDateInstance().format(currentDate);  
        System.out.println("Date Format using getDateInstance(): "+dateStr);  
		User user = (User)session.getAttribute("user");
		System.out.println(user);
		ObjectMapper objectMapper = new ObjectMapper();
		URL url;
		Author []results = null;
		try {
			url = new URL("http://localhost:8085/author/");
			results = objectMapper.readValue(url, Author[].class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		theModel.addAttribute("products",results);
		theModel.addAttribute("date",dateStr);
		return "add-book";
	}

	@GetMapping("/books")
	public String listBooks(Model theModel,HttpSession session) {
		ObjectMapper objectMapper = new ObjectMapper();
		URL url;
		Book []results = null;
		try {
			url = new URL("http://localhost:8085/book/");
			results = objectMapper.readValue(url, Book[].class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

  
		theModel.addAttribute("products",results);
		return "list-books";
	}
	
	@GetMapping("/logout")
	public String logoutUser(@ModelAttribute("user") User theUser,HttpSession session) {
		session.setAttribute("user", null);
		return "redirect:/";
	}
	@PostMapping("/add")
	public String addBook(@ModelAttribute("user") Book book,HttpSession session) {
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = null;
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8085/book";
		HttpHeaders headers = new HttpHeaders();
        try {
			jsonStr = Obj.writeValueAsString(book);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(jsonStr,headers);
		String answer = restTemplate.postForObject(url, entity, String.class);
		System.out.println(answer);
		return "redirect:/books";
	}
	@GetMapping("/edit")
	public String editBook(Model theModel, @RequestParam("id") String bcode) {
		ObjectMapper objectMapper = new ObjectMapper();
		URL url;
		Author []results = null;
		Book book = null;
		try {
			url = new URL("http://localhost:8085/author/");
			results = objectMapper.readValue(url, Author[].class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		theModel.addAttribute("products",results);
		try {
			url = new URL("http://localhost:8085/book/"+bcode);
			book = objectMapper.readValue(url, Book.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		theModel.addAttribute("book",book);
		return "edit-book";
	}
	@PostMapping("/update")
	public String updateBook(@ModelAttribute("user") Book book,HttpSession session) {
		System.out.println(book);
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = null;
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8085/book";
		HttpHeaders headers = new HttpHeaders();
        try {
			jsonStr = Obj.writeValueAsString(book);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(jsonStr,headers);
		ResponseEntity<String> answer = restTemplate.exchange(url,HttpMethod.PUT, entity, String.class);
		System.out.println(answer);
		return "redirect:/books";
	}

	@GetMapping("/delete")
	public String deleteBook(Model theModel, @RequestParam("id") String bcode) {
		System.out.println(bcode);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8085/book/"+bcode);
		return "redirect:/books";
	}
	@PostMapping("/login")
	public String loginUser(@ModelAttribute("user") User theUser,HttpSession session) {
		if (theUser != null && userService.validate(theUser)) {
			session.setAttribute("user", theUser);
			return "redirect:/books";
		}
		else {
			return "redirect:/";
		}
	}
	
}