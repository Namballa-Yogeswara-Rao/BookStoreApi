package com.bookStore.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.bookStore.entity.Book;
import com.bookStore.repository.BookJPA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class BookController {
    
	@Autowired
	BookJPA bookJPA;

	@GetMapping("/allBooks")
	public List<Book> getAllBooks(){
		return bookJPA.findAll();
	}
	
	@PutMapping("/update")
	public String putMethodName(@RequestBody Book book) {
		Optional<Book> existingUser = bookJPA.findById(book.getId());
		if (existingUser.isPresent()) {
			bookJPA.save(book);
			return "User Updated Successfully";
		}
		return "User Not Found";
	}
	@DeleteMapping("/delete/{id}")
    public String deleteFETUserData(@PathVariable("id") long id) {
		if(bookJPA.existsById(id)) {
        bookJPA.deleteById(id);
        return "deleted sucessfully";
		}
		return "not available";
    }
	
}
