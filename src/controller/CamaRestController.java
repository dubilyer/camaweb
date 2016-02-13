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
	public final static int  ADMINS_QUANTITY = 10;
	public final static int  EDU_ORGS_QUANTITY = 10;
	public final static int  MANAGERS_QUANTITY = 10;
	private static final int STUDY_GROUPS_QUANTITY = 10;
	private static final int PARENTS_QUANTITY = 10;
	private static final int CHILDS_QUANTITY = 20;
	private static final int EDUCATORS_QUANTITY = 10;
	private static final int FAMILY_MEMBERS_QUANTITY = 30;
	
	@RequestMapping(value="getAdmin"+"/{id}", method=RequestMethod.GET)
	public Map<String, Object> getAdmin(@PathVariable int id){
		return database.getAdmin(id);
	}
	
	@RequestMapping(value="generateDB", method=RequestMethod.GET)
	public void generateDB(){
	database.generateRandomAdministrators(ADMINS_QUANTITY);
	database.GenerateRandomEduOrgs(EDU_ORGS_QUANTITY);
	database.generateRandomStudyGroups(STUDY_GROUPS_QUANTITY, EDU_ORGS_QUANTITY);
	database.generateRandomManagers(MANAGERS_QUANTITY, ADMINS_QUANTITY, EDU_ORGS_QUANTITY);
	database.generateRandomParents(PARENTS_QUANTITY);
	database.generateRandomChilds(CHILDS_QUANTITY, STUDY_GROUPS_QUANTITY, PARENTS_QUANTITY);
	database.generateRandomEducators(EDUCATORS_QUANTITY, MANAGERS_QUANTITY);
	database.generateRandomFamilyMembers(FAMILY_MEMBERS_QUANTITY, CHILDS_QUANTITY);
		
	}
}
