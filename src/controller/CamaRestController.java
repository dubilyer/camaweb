package controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CamaRestController {
	@Autowired
	TestModel database;
	
	@RequestMapping(value="getAdmin"+"/{id}", method=RequestMethod.GET)
	public Map<String, Object> getAdmin(@PathVariable int id){
		return database.getAdmin(id);
	}
}
