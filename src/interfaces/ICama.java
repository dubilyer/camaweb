package interfaces;

import java.util.*;

import dao.*;

public interface ICama {
	
	// adders
	public Map<String, Object> addAdministrator(String firstName, String lastName, String username);
	public Map<String, Object> addEduOrg(String name, String type, Address address);
	public Map<String, Object> addManager(String firstName, String lastName, String username, String description, String position,
			int adminId, int eduOrgId);
	public boolean addStudyGroup(String groupName, int eduOrgId);
	public boolean addParent(String firstName, String lastName, String username, int childId);
	public boolean addChild(String firstName, String lastName, String comment, int studyGroupId, 
			int parentId);
	public boolean addEducator(String firstName, String lastName, String username, String description, int managerId);
	public boolean addFamilyMember(String firstName, String lastName, String phone, String description, int childId);
	public boolean addEducatorToStudyGroup(int studyGroupId, int educatorId);
	public boolean addMessage(int senderId, int recieverId, String message, 
			String senderType, String receiverType, int childId) throws ClassNotFoundException, 
			InstantiationException, IllegalAccessException;
	public boolean addLog(int creatorId, String creatorType, String text) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
	// Removers
	public boolean removeManager(int id);
	public boolean removeEducator(int id);
	public boolean removeParent(int id);
	public boolean removeFamilyMember(int id);
	boolean changeGroup(int childId, int groupToId);
	boolean removeStudyGroup(int id);
	boolean removeEduOrg(int id);
	boolean removeChild(int id);
	//Requests
	public Map<String,Integer> getRolesByTel(String username); // will be used during login to check role, if there are more than one
	public Map<String,Object> getEducatorsByChild(int childId);
	public Map<String, Object> getManagersByAdmin(int adminId);
	public Map<String, Object>getGroupsByManager(int managerId);
	public Map<String,Object> getChildrenByParent(int parentId);
	public Map<String,Object> getGroupsByEducator(int educatorId);
	public Map<String,Object> getChildrenByGroup(int groupId);
	public Map<String,Object> getFamilyMembersByChild(int childId);
	public Map<String,Object> getAttStatsByChild(int childId,Date dateFrom,Date dateTo);
}
