package controller;

import java.text.ParseException;
import java.util.Date;
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
	/*public final static int  ADMINS_QUANTITY = 10;
	public final static int  EDU_ORGS_QUANTITY = 10;
	public final static int  MANAGERS_QUANTITY = 10;
	private static final int STUDY_GROUPS_QUANTITY = 10;
	private static final int PARENTS_QUANTITY = 10;
	private static final int CHILDS_QUANTITY = 20;
	private static final int EDUCATORS_QUANTITY = 10;
	private static final int FAMILY_MEMBERS_QUANTITY = 30;*/
	
	@RequestMapping(value="getAdmin"+"/{id}", method=RequestMethod.GET)
	public Map<String, Object> getAdmin(@PathVariable int id){
		return database.getAdmin(id);
	}
	
	@RequestMapping(value="getManagers", method=RequestMethod.GET)
	public Map<String, Object>getManagers(){
		return database.getManagersByLastName("");
	}
	
	@RequestMapping(value="getManagersByAdmin"+"/{adminId}", method=RequestMethod.GET)
	public Map<String, Object>getManagers(@PathVariable int adminId){
		return database.getManagers(adminId);
	}
	
	@RequestMapping(value="getManagersByLastName"+"/{pattern}", method=RequestMethod.GET)
	public Map<String, Object>getManagersByLastName(@PathVariable String pattern){
		return database.getManagersByLastName(pattern);
	}
	
	@RequestMapping(value="getMessages", method=RequestMethod.GET)
	public Map<String, Object>getMessages(){
		return database.getMessagesByUser("");
	}
	
	@RequestMapping(value="getMessagesByUser"+"/{pattern}", method=RequestMethod.GET)
	public Map<String, Object>getMessagesByUser(@PathVariable String pattern){
		return database.getMessagesByUser(pattern);
	}
	/**
	 * Problem((
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	@RequestMapping(value="getMessagesByTime"+"/{date}", method=RequestMethod.GET)
	public Map<String, Object> getMessagesByTime(@PathVariable Date date) throws ParseException{
		return database.getMessagesByTime(date);
	}
	
	//Todo
	//@RequestMapping(value="getMessagesByUserAndTime"+"/{pattern}" + "/{beginDate}" + "/{endDate}", 
	//method=RequestMethod.GET)
	 
	/**to check
	 * 
	 * @param managerId
	 * @return
	 */
	@RequestMapping(value="getGroupsByManager"+"/{managerId}", method=RequestMethod.GET)
	public Map<String, Object> getGroupsByManager(@PathVariable int managerId){
		return database.getGroupsByManager(managerId);
	}
	
	//Todo
	//@RequestMapping(value="checkAttendance"+"/{childId}", method=RequestMethod.GET)
	//Todo
	//@RequestMapping(value="checkAttendance"+"/{groupId}", method=RequestMethod.GET)
	//Todo
	//@RequestMapping(value="getHistort"+"/{beginDate}" + "/{endDate}, method=RequestMethod.GET)
	/**
	 * to check
	 * @param managerId
	 * @param lastName
	 * @return
	 */
	@RequestMapping(value="getEducatorsByLastName"+"/{managerId}"+"/{lastName}", method=RequestMethod.GET)
	public Map<String, Object> getEducatorsByLastName(@PathVariable int managerId, @PathVariable String lastName){
		return database.getEducatorsByLastName(managerId, lastName);
	}
	
	@RequestMapping(value="generateDB", method=RequestMethod.GET)
	public void generateDB() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
	database.addMessage(1, 2, "dsfg", "Administrator", "Manager");
		/*database.generateRandomAdministrators(ADMINS_QUANTITY);
	database.GenerateRandomEduOrgs(EDU_ORGS_QUANTITY);
	database.generateRandomStudyGroups(STUDY_GROUPS_QUANTITY, EDU_ORGS_QUANTITY);
	database.generateRandomManagers(MANAGERS_QUANTITY, ADMINS_QUANTITY, EDU_ORGS_QUANTITY);
	database.generateRandomParents(PARENTS_QUANTITY);
	database.generateRandomChilds(CHILDS_QUANTITY, STUDY_GROUPS_QUANTITY, PARENTS_QUANTITY);
	database.generateRandomEducators(EDUCATORS_QUANTITY, MANAGERS_QUANTITY);
	database.generateRandomFamilyMembers(FAMILY_MEMBERS_QUANTITY, CHILDS_QUANTITY);*/
	}
	@RequestMapping(value="getChildrenByParent"+"/{parentId}", method=RequestMethod.GET)
	public Map<String, Object> getChildrenByParent(@PathVariable int parentId){
		return database.getChildrenByParent(parentId);
	}
	
	@RequestMapping(value="getEducatorsByChild"+"/{childId}", method=RequestMethod.GET)
	public Map<String,Object> getEducatorByChild(@PathVariable int childId){
		return database.getEducatorsByChild(childId);
	}
}
