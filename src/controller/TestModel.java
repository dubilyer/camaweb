package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import dao.Address;
import dao.Administrator;
import dao.Child;
import dao.EducationOrganization;
import dao.Educator;
import dao.FamilyMember;
import dao.Log;
import dao.Manager;
import dao.Message;
import dao.Parent;
import dao.StudyGroup;
import dao.User;
import daoData.AdminData;
import daoData.ChildData;
import daoData.EducatorData;
import daoData.GroupData;
import daoData.ManagerData;
import interfaces.ICama;

public class TestModel implements ICama {
	@PersistenceContext(unitName = "springHibernate")
	EntityManager em;
	private Address address = new Address("Israel", "city1", "street1", "building1");
	private String email;
	Random gen = new Random();


	@SuppressWarnings("unchecked")
	@Transactional
	public Map<String,Object> addAdministrator(String firstName, String lastName, String username) {
		Map<String,Object> result = new HashMap<String,Object>();
		Query query = em.createQuery("select a from Administrator a where username =:tel").setParameter("tel", username);
		List<Administrator> tempAdmin = (List<Administrator>)query.getResultList();
		if(!tempAdmin.isEmpty())
			return (Map<String, Object>) result.put("Error", "Administrator with this telephone number already exists");
		Administrator administrator = new Administrator(firstName, lastName, username);
		em.persist(administrator);
		return (Map<String, Object>) result.put("Success", null);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Map<String,Object> addEduOrg(String name, String type, Address address) {
		Map<String,Object> result = new HashMap<String,Object>();
		//Query query = em.createQuery("select e from EducationOrganization where address =:addr").setParameter("addr", address);
		//List<EducationOrganization> eduOrgs = query.getResultList();
		//if(!eduOrgs.isEmpty())
			//return (Map<String, Object>) result.put("Error", "Education organization at this address already exists");
		EducationOrganization eduOrg = new EducationOrganization(name, type, address);
		em.persist(eduOrg);
		return (Map<String, Object>) result.put("Success", null);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Map<String,Object> addManager(String firstName, String lastName, String username, String description, String position,
			int adminId, int eduOrgId) {
		Map<String,Object> result = new HashMap<String,Object>();
		Query query = em.createQuery("select m from Manager m where username =:tel").setParameter("tel", username);
		List<Manager> managerTemp = (List<Manager>) query.getResultList();
		EducationOrganization eduOrg = em.find(EducationOrganization.class, eduOrgId);
		if(!managerTemp.isEmpty())
			return (Map<String, Object>) result.put("Error", "Manager with this telephone number already exists");
		if(eduOrg.getManager()!=null)
			return (Map<String, Object>) result.put("Error", "This school already has manager");
		Administrator creator = em.find(Administrator.class, adminId);
		Manager manager = new Manager(firstName, lastName, username, description, creator, eduOrg, position, email);
		em.persist(manager);
		return (Map<String, Object>) result.put("Success", null);
	}

	@Transactional
	public boolean addStudyGroup(String groupName, int eduOrgId) {
		EducationOrganization eduOrg = em.find(EducationOrganization.class, eduOrgId);
		StudyGroup studyGroup = new StudyGroup(groupName, eduOrg);
		em.persist(studyGroup);
		return true;

	}

	@Transactional
	public boolean addParent(String firstName, String lastName, String username, int childId) {
		Query query = em.createQuery("select p from Parent p where username =:tel").setParameter("tel", username);
		Child child = em.find(Child.class, childId);
		@SuppressWarnings("unchecked")
		List<Parent> parents = (List<Parent>) query.getResultList();
		if(!parents.isEmpty()){
			parents.get(0).addChild(child);
		}
		 parents.add(new Parent(firstName, lastName, username));
		em.persist(parents.get(0));
		return true;
	}

	@Transactional
	public boolean addChild(String firstName, String lastName, String comment, int studyGroupId, 
			int parentId) {
		StudyGroup studyGroup = em.find(StudyGroup.class, studyGroupId);
		Child child = new Child(firstName, lastName, comment, studyGroup);
		child.setAddress(address);
		Parent parent = em.find(Parent.class, parentId);
		child.setParent(parent);
		em.persist(child);
		return true;

	}

	@Transactional
	public boolean addEducator(String firstName, String lastName, String username, String description, int managerId) {
		Manager creator = em.find(Manager.class, managerId);
		Set<StudyGroup> groups = new HashSet();
		int num = gen.nextInt(10) + 1;
		StudyGroup group1 = em.find(StudyGroup.class, num);
		groups.add(group1);
		int num2 = gen.nextInt(9) + 1;
		num2 = (num != num2) ? num2 : Math.abs(num2 + 1);
		StudyGroup group2 = em.find(StudyGroup.class, num2);
		groups.add(group2);
		Educator educator = new Educator(firstName, lastName, username, description, creator, groups);
		em.persist(educator);
		return true;
	}

	@Transactional
	public boolean addFamilyMember(String firstName, String lastName, String phone, String description, int childId) {
		Child child = em.find(Child.class, childId);
		FamilyMember familyMember = new FamilyMember(child, firstName, lastName, phone, description);
		em.persist(familyMember);
		return true;
	}

	@Transactional
	public boolean addEducatorToStudyGroup(int studyGroupId, int educatorId) {
		StudyGroup studyGroup = em.find(StudyGroup.class, studyGroupId);
		Educator educator = em.find(Educator.class, educatorId);
		educator.addGroup(studyGroup);
		return true;

	}

	@Transactional
	public boolean addMessage(int senderId, int recieverId, String message, String senderType, String receiverType)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> senderClass = Class.forName("dao." + senderType);
		Class<?> receiverClass = Class.forName("dao." + receiverType);
		User sender = (User) senderClass.newInstance();
		User receiver = (User) receiverClass.newInstance();
		sender = (User) em.find(senderClass, senderId);
		System.out.println(sender);
		receiver = (User) em.find(receiverClass, recieverId);
		System.out.println(receiver);
		Message new_message = new Message(sender, receiver, message);
		em.persist(new_message);
		return true;
	}

	@Transactional
	public boolean addLog(int creatorId, String creatorType, String text)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> creatorClass = Class.forName("dao." + creatorType);
		User creator = (User) creatorClass.newInstance();
		creator = (User) em.find(creatorClass, creatorId);
		Log log = new Log(creator, text);
		em.persist(log);
		return true;
	}

	public void printData() {
		Educator educator = em.find(Educator.class, 1);
		StudyGroup studyGroup = em.find(StudyGroup.class, 2);
		Set<Educator> educators = studyGroup.getEducators();
		Set<StudyGroup> groups = educator.getGroups();
		for (Educator e : educators) {
			System.out.println(e.toString());
		}
		for (StudyGroup gr : groups) {
			System.out.println(gr.toString());
		}
		Child child = em.find(Child.class, 3);
		Set<FamilyMember> fms = child.getFamilyMembers();
		for (FamilyMember fm : fms) {
			System.out.println(fm);
		}
	}

	@SuppressWarnings("unchecked")
	public void printCreator() {
		Message message = em.find(Message.class, (long) 1);
		System.out.println(em.find(message.getReceiverType(), message.getReceiverId()));
	}

	@Override
	@Transactional
	public boolean removeManager(int id) {
		boolean res = false;
		Manager manager = em.find(Manager.class, id);
		if (manager != null) {
			manager.getAdministrator().getManagers().remove(manager);
			manager.getEduOrg().setManager(null);
			em.remove(manager);
			res = true;
		}
		return res;
	}

	@Transactional
	@Override
	public boolean removeEducator(int id) {
		boolean res = false;
		Educator educator = em.find(Educator.class, id);
		if (educator != null) {
			if (educator.getEduOrg().getEducators() != null) {
				educator.getEduOrg().getEducators().remove(educator);
			}
			if (educator.getGroups() != null) {
				for (StudyGroup studyGroup : educator.getGroups()) {
					studyGroup.getEducators().remove(educator);
				}
			}
			em.remove(educator);
			res = true;
		}
		return res;
	}
	
	@Transactional
	@Override
	public boolean removeParent(int id) {
		boolean res = false;
		Parent parent = em.find(Parent.class, id);
		if (parent != null) {
			if (parent.getChildren() != null) {
				for (Child child : parent.getChildren()) {
					child.setParent(null);
				}
			}
			em.remove(parent);
			res = true;
		}
		return res;
	}

	@Transactional
	@Override
	public boolean removeFamilyMember(int id) {
		boolean res = false;
		FamilyMember familyMember = em.find(FamilyMember.class, id);
		if (familyMember!=null) {
			if (familyMember.getChild()!=null) {
				familyMember.getChild().getFamilyMembers().remove(familyMember);
			}
			em.remove(familyMember);
			res=true;
		}
		return false;
	}
	@Transactional
	 @Override
	 public boolean removeStudyGroup(int id){
	  boolean result = false;
	  StudyGroup group = em.find(StudyGroup.class, id);
	  if(group.getChildren() != null){
	   return false;
	  }
	  if(group != null){
	   if(group.getEducators() != null){
	    for(Educator educator:group.getEducators()){
	     educator.getGroups().remove(group);
	    }
	   }
	   if(group.getEduOrg() != null){
	    group.getEduOrg().getGroups().remove(group);
	    }
	   em.remove(group);
	    result = true;
	   }
	  return result;
	 }
	
	 @Transactional
	 @Override
	 public boolean changeGroup(int childId,int groupToId){
	  Child child = em.find(Child.class, childId);
	  StudyGroup groupFrom = child.getGroup();
	  StudyGroup groupTo = em.find(StudyGroup.class, groupToId);
	  if(child == null || groupTo == null){
	   return false;
	  }
	  child.setGroup(groupTo);
	  groupFrom.getChildren().remove(child);
	  groupTo.getChildren().add(child);
	  return true;
	 }
	 
	 @Transactional
	 @Override
	 public boolean removeEduOrg(int id){
		EducationOrganization eduOrg = em.find(EducationOrganization.class, id);
		 for(StudyGroup group:eduOrg.getGroups()){
			 if(!removeStudyGroup(group.getGroupId()))
				 return false;
		 }
		 for(Educator educator:eduOrg.getEducators()){
			 em.remove(educator);
		 }
		 em.remove(eduOrg.getManager());
		 em.remove(eduOrg);
		 return true;
	 }
	 
	 @Transactional
	 @Override
	public boolean removeChild(int id){
		 Child child = em.find(Child.class, id);
		 if(child == null)
			 return false;
		 Parent parent = child.getParent();
		 parent.getChildren().remove(child);
		 if(parent.getChildren() == null)
			 em.remove(parent);
		 for(FamilyMember famMem:child.getFamilyMembers()){
			 em.remove(famMem);
		 }
		 StudyGroup group = child.getGroup();
		 group.getChildren().remove(child);
		 em.remove(child);
		 return true;
		}
	 
	 @Transactional
	 @Override
	 public Map<String,Integer> getRolesByTel(String username){
		 Map<String,Integer> result = new HashMap<String,Integer>();
		Query queryParent = em.createQuery("select p from Parent p where username =:tel").setParameter("tel", username);
		Query queryEducator = em.createQuery("select e from Educator e where username =:tel").setParameter("tel", username);
		Query queryManager = em.createQuery("select m from Manager m where username =:tel").setParameter("tel", username);
		Query queryAdmin = em.createQuery("select a from Administrator a where username =:tel").setParameter("tel", username);
		Parent parent = (Parent) queryParent.getSingleResult();
		try {
			if(parent != null){
				result.put("Parent", parent.getId());
			}
			Educator educator = (Educator) queryEducator.getSingleResult();
			if(educator != null){
				result.put("Educator", educator.getId());
			}
			Manager manager = (Manager) queryManager.getSingleResult();
			if(manager != null){
				result.put("Manager", manager.getId());
			}
			Administrator admin = (Administrator) queryAdmin.getSingleResult();
			if(admin != null){
				result.put("Administrator", admin.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return result;
		}
	 	
	 	public Map<String, Object> getAdmin(int id){
			Map<String, Object> result = new HashMap<>();
			Administrator admin = em.find(Administrator.class, id);
			if(admin == null){
				result.put("Error", "No admin with such Id");
				return result;
			}
			Set<Manager> managers = admin.getManagers();
			Set<Integer> managersIds = new HashSet<Integer>();
			for(Manager manager:managers){
				int managerId = manager.getId();
				managersIds.add(managerId);
			}
			AdminData adminData = new AdminData(id, true, admin.getFirstName(), admin.getLastName(), 
					admin.getDescription(), admin.getEmail(),
					admin.getRegDate(), admin.getLastLoginDate(), 
					admin.getUsername(), managersIds);
			result.put("Success", adminData);
			return result ;
	 		
	 	}
	 	public Map<String, Object> getManagers(int adminId){
	 		Map<String, Object> result = new HashMap<>();
	 		Administrator administrator = em.find(Administrator.class, adminId);
	 		Set<Manager> managers = administrator.getManagers();
	 		Set<ManagerData> managerDatas = new HashSet<>();
	 		for (Manager manager : managers) {
				ManagerData managerData = new ManagerData(manager.getId(), manager.isEnabled(), manager.getFirstName(),
						manager.getLastName(), manager.getDescription(), manager.getEmail(), manager.getRegDate(), 
						manager.getLastLoginDate(), manager.getUsername(), manager.getAdministrator().getId(), 
						manager.getEduOrg().getEduOrgId(), manager.getPosition());
				managerDatas.add(managerData);
			}
	 		result.put("Success", managerDatas);
	 		return result;
	 	}
	 	
	 	public Map<String, Object> getManagersByLastName(String lastName){
	 		Map<String, Object> result = new HashMap<>();
	 		String q = "SELECT m FROM Manager m WHERE m.lastName LIKE ?1";
	 		Query query = em.createQuery(q);
	 		if (lastName==null) lastName="";
	 		query.setParameter(1, "%"+lastName+"%");
	 		@SuppressWarnings("unchecked")
			Iterable<Manager> managers = query.getResultList();
	 		Set<ManagerData> managerDatas = new HashSet<>();
	 		for (Manager manager : managers) {
				ManagerData managerData = new ManagerData(manager.getId(), manager.isEnabled(), manager.getFirstName(),
						manager.getLastName(), manager.getDescription(), manager.getEmail(), manager.getRegDate(), 
						manager.getLastLoginDate(), manager.getUsername(), manager.getAdministrator().getId(), 
						manager.getEduOrg().getEduOrgId(), manager.getPosition());
				managerDatas.add(managerData);
			}
			result.put("Success", managerDatas);
	 		return result;
	 	}

	 	public Map<String, Object> getMessagesByUser(String fromTo){
	 		Map<String, Object> result = new HashMap<>();
	 		String q = "SELECT m FROM Message m";
	 		if(!fromTo.equals("")) q = q+" WHERE m.fromTo LIKE ?1";
	 		Query query = em.createQuery(q);
	 		if(!fromTo.equals("")) query.setParameter(1, "%"+fromTo+"%");;
	 		result.put("Success", query.getResultList());
	 		return result;
	 	}
	 	
	 	public Map<String, Object> getMessagesByTime(Date date) throws ParseException{
	 		Map<String, Object> result = new HashMap<>();
	 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 		System.out.println(date);
	 		String q = "SELECT m FROM Message m WHERE m.sendingTime=?1";
	 		Query query = em.createQuery(q);
	 		query.setParameter(1, sdf.format(date));
	 		result.put("Success", query.getResultList());
	 		return result;
	 	}
	 	
	 	/**
	 	 * To check!!
	 	 * @param managerId
	 	 * @param lastName
	 	 * @return
	 	 */
	 	public Map<String, Object> getEducatorsByLastName(int managerId, String lastName){
	 		Map<String, Object> result = new HashMap<>();
	 		Manager manager = em.find(Manager.class, managerId);
	 		EducationOrganization eduOrg = manager.getEduOrg();
	 		String q = "SELECT e FROM Educator m WHERE m.lastName LIKE ?1 AND eduOrg_Id =?2";
	 		Query query = em.createQuery(q);
	 		if (lastName==null) lastName="";
	 		query.setParameter(1, "%"+lastName+"%");
	 		query.setParameter(2, eduOrg.getEduOrgId());
	 		@SuppressWarnings("unchecked")
			Iterable<Educator> educators = query.getResultList();
	 		Set<EducatorData> educatorDatas = new HashSet<>();
	 		
	 		for (Educator educ : educators) {
	 			Set<Integer> groupsId = new HashSet<>();
	 			Set<StudyGroup> groups = educ.getGroups();
	 			for (StudyGroup studyGroup : groups) {
					groupsId.add(studyGroup.getGroupId());
				}
	 			EducatorData educatorData = new EducatorData(educ.getId(), educ.isEnabled(), 
	 														educ.getFirstName(), educ.getLastName(), 
	 														educ.getDescription(), educ.getEmail(),
															educ.getRegDate(), educ.getLastLoginDate(), 
															educ.getUsername(), educ.getEduOrg().getEduOrgId(), 
															groupsId, educ.getSubject());
						educatorDatas.add(educatorData);
			}
			result.put("Success", educatorDatas);
	 		return result;
	 	}
	 	
	 	public Map<String, Object>getGroupsByManager(int managerId){
	 		Map<String, Object> result = new HashMap<>();
	 		Manager manager = em.find(Manager.class, managerId);
	 		EducationOrganization eduOrg = manager.getEduOrg();
	 		Set<StudyGroup> groups = new HashSet<>();
	 		result.put("Success", eduOrg.getGroups());
	 		return result;
	 	}
	 	public Map<String,Object> getChildrenByParent(int parentId){
	 		Map<String,Object> result = new HashMap<String,Object>();
	 		Parent parent = em.find(Parent.class, parentId);
	 		if(parent == null){
	 			result.put("Error", "Incorrect parent id");
	 			return result;
	 		}
	 		Set<Child> children = parent.getChildren();
	 		Set<ChildData> childrenData = new HashSet<ChildData>();
	 		for(Child child:children){
	 			ChildData childData = new ChildData(child.getId(),child.getFirstName(),child.getLastName(),null,0,
	 					null,0,null);
	 			childrenData.add(childData);
	 		}
	 		result.put("Success",childrenData);
			return result;
	 	}
	 	
	 	public Map<String,Object> getEducatorsByChild(int childId){
	 		Map<String,Object> result = new HashMap<String,Object>();
	 		Child child = em.find(Child.class, childId);
	 		if(child == null){
	 			result.put("Error", "Incorrect child ID");
	 			return result;
	 		}
	 		StudyGroup group = child.getGroup();
	 		Set<Educator> educators = group.getEducators();
	 		Set<EducatorData> educatorsData = new HashSet<EducatorData>();
	 		for(Educator educator:educators){
	 			Set<Integer> groupsIds = new HashSet<Integer>();
	 			Set<StudyGroup> groups = educator.getGroups();
	 			for(StudyGroup gr:groups){
	 				groupsIds.add(gr.getGroupId());
	 			}
	 			EducatorData educatorData = new EducatorData(educator.getId(), educator.isEnabled(),
	 					educator.getFirstName(), educator.getLastName(), educator.getDescription(),
	 					educator.getEmail(), educator.getRegDate(), educator.getLastLoginDate(),
	 					educator.getUsername(), educator.getEduOrg().getEduOrgId(), 
	 					groupsIds, educator.getSubject());
	 			educatorsData.add(educatorData);
	 		}
	 		result.put("Success", educatorsData);
	 		return result;
	 	}
	 	
	 	public Map<String,Object> getGroupsByEducator(int educatorId){
	 		Map<String,Object> result = new HashMap<String,Object>();
	 		Educator educator = em.find(Educator.class, educatorId);
	 		if(educator == null){
	 			result.put("Error", "Incorrect educator ID");
	 			return result;
	 		}
	 		Set<StudyGroup> groups = educator.getGroups();
	 		Set<GroupData>  groupsData = new HashSet<GroupData>();
	 		for(StudyGroup group:groups){
	 			Set<Integer> childrenIds = new HashSet<Integer>();
	 			Set<Integer> educatorsIds = new HashSet<Integer>();
	 			for(Child child:group.getChildren()){
	 				childrenIds.add(child.getId());
	 			}
	 			for(Educator ed:group.getEducators()){
	 				educatorsIds.add(ed.getId());
	 			}
	 			GroupData groupData = new GroupData(group.getGroupId(),group.getGroupName(),
	 									group.getEduOrg().getEduOrgId(),childrenIds,educatorsIds);
	 			groupsData.add(groupData);
	 		}
	 		result.put("Success", groupsData);
	 		return result;
	 	}
	 	
	 	public Map<String,Object> getChildrenByGroup(int groupId){
	 		Map<String,Object> result = new HashMap<String,Object>();
	 		StudyGroup group = em.find(StudyGroup.class, groupId);
	 		if(group == null){
	 			result.put("Error", "Incorrect group ID");
	 			return result;
	 		}
	 		Set<Child> children = group.getChildren();
	 		Set<ChildData> childrenData = new HashSet<ChildData>();
	 		for(Child child:children){
	 			ChildData childData = new ChildData(child.getId(), child.getFirstName(), child.getLastName(),
	 					child.getAddress().toString(), child.getGroup().getGroupId(), null,child.getParent().getId(),null);
	 			childrenData.add(childData);
	 		}
	 		result.put("Success", childrenData);
	 		return result;
	 	}

}
