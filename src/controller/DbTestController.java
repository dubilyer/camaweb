package controller;

import java.util.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class DbTestController {

	public final static int  ADMINS_QUANTITY = 10;
	public final static int  EDU_ORGS_QUANTITY = 10;
	public final static int  MANAGERS_QUANTITY = 10;
	private static final int STUDY_GROUPS_QUANTITY = 10;
	private static final int PARENTS_QUANTITY = 10;
	private static final int CHILDS_QUANTITY = 20;
	private static final int EDUCATORS_QUANTITY = 10;
	private static final int FAMILY_MEMBERS_QUANTITY = 30;
	private static final int MESSAGE_QUANTITY = 30;
	private static final int LOGS_QUANTITY = 30;
	private static HashMap<String, Integer> typesQuant = new HashMap<>();
	static{
		typesQuant.put("Administrator", ADMINS_QUANTITY);
		typesQuant.put("Manager", MANAGERS_QUANTITY);
		typesQuant.put("Educator", EDUCATORS_QUANTITY);
		typesQuant.put("Parent", PARENTS_QUANTITY);
	}
	
	
	
	public static void main(String[] args) {
	
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");
		TestModel tm = (TestModel)ctx.getBean("database");
	/*	tm.generateRandomAdministrators(ADMINS_QUANTITY);
		tm.GenerateRandomEduOrgs(EDU_ORGS_QUANTITY);
		tm.generateRandomStudyGroups(STUDY_GROUPS_QUANTITY, EDU_ORGS_QUANTITY);
		tm.generateRandomManagers(MANAGERS_QUANTITY, ADMINS_QUANTITY, EDU_ORGS_QUANTITY);
		tm.generateRandomParents(PARENTS_QUANTITY);
		tm.generateRandomChilds(CHILDS_QUANTITY, STUDY_GROUPS_QUANTITY, PARENTS_QUANTITY);
		tm.generateRandomEducators(EDUCATORS_QUANTITY, MANAGERS_QUANTITY);
		tm.generateRandomFamilyMembers(FAMILY_MEMBERS_QUANTITY, CHILDS_QUANTITY);
		try {
			tm.generateRandomMessages(MESSAGE_QUANTITY, typesQuant);
			tm.generateRandomLogs(LOGS_QUANTITY, typesQuant);	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}*/
//		tm.printData();
		
		tm.printCreator();
		tm.removeEducator(2);
		tm.removeManager(3);
		tm.removeParent(4);
		tm.removeFamilyMember(1);
		tm.removeStudyGroup(3);
		tm.changeGroup(2, 3);
		ctx.close();
		}

}
