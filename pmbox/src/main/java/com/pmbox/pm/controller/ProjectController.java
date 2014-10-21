package com.pmbox.pm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmbox.pm.dao.DAO;
import com.pmbox.pm.dao.ProjectDAO;
import com.pmbox.pm.dao.ProjectUserDAO;
import com.pmbox.pm.dao.TaskDAO;
import com.pmbox.pm.dao.TodoDAO;
import com.pmbox.pm.dao.UserDAO;
import com.pmbox.pm.entity.Project;
import com.pmbox.pm.entity.ProjectUser;
import com.pmbox.pm.entity.Task;
import com.pmbox.pm.entity.Todo;
import com.pmbox.pm.entity.User;
import com.pmbox.pm.exception.AdException;
import com.pmbox.pm.service.Authentication;
import com.pmbox.pm.service.DateOperation;
import com.pmbox.pm.service.TaskTodo;

@Controller
public class ProjectController {
		@RequestMapping(value="project.htm", method=RequestMethod.GET)
		public String projectHome(@RequestParam("projectID") int projectID, @RequestParam("message") String message, Model model, HttpSession session) {
			if(!Authentication.islogin(session)) return "index";
			if(!Authentication.isUser((User)(session.getAttribute("user")))){
				model.addAttribute("errorMessage","Not authenticated to do this operation");				
				return "error";
			}
			if(Authentication.isProjectAdmin(session, projectID)){
				//get project
				Project project = null;
				for(Object o: (List)(session.getAttribute("involvedProjects"))){
					Project temp = (Project) o;
					if(temp.getProjectID() == projectID)
						project = temp;
				}
				//get task and todo list
				try
			    {
			        TaskDAO taskDAO = new TaskDAO();
			        TodoDAO todoDAO = new TodoDAO();
			        List<Task> testresult = taskDAO.getTaskByProjectID(projectID);
			        List<TaskTodo> tasktodoList = new ArrayList<TaskTodo>();
			        if(testresult.size() > 0){
			        	for(Task task: testresult){
				        	TaskTodo taskTodo = new TaskTodo(task);
				        	tasktodoList.add(taskTodo);
				        	// get todo for each task
				        	List<Todo> todosresultList = todoDAO.getByTaskID(task.getTaskID());
				        	if(todosresultList.size() > 0){
				        		for(Todo todo: todosresultList){
				        			int assigneeID = todo.getAssigneeID();
				        			User assignee = null;
				        			if(assigneeID != 0){
				        				UserDAO userDAO = new UserDAO();
				        				assignee = userDAO.get(assigneeID);
				        			}
				        			taskTodo.addAssignee(assignee);
				        			taskTodo.addTodo(todo);
				        		}
				        	}
				        }
			        }	
			        model.addAttribute("tasktodoList", tasktodoList);
			        DAO.close();
			    }
			    catch (AdException e)
			    {
			        System.out.println("Exception: " + e.getMessage());
			    }
				//get task and todo list end
				
				model.addAttribute("project", project);
				model.addAttribute("message", message);
				return "adminprojectHome";
			}else if(Authentication.isProjectMember(session, projectID) ||
					Authentication.isProjectClient(session, projectID)){
				//get project
				Project project = null;
				for(Object o: (List)(session.getAttribute("involvedProjects"))){
					Project temp = (Project) o;
					if(temp.getProjectID() == projectID)
						project = temp;
				}
				//get task and todo list
				try
			    {
			        TaskDAO taskDAO = new TaskDAO();
			        TodoDAO todoDAO = new TodoDAO();
			        List<Task> testresult = taskDAO.getTaskByProjectID(projectID);
			        List<TaskTodo> tasktodoList = new ArrayList<TaskTodo>();
			        if(testresult.size() > 0){
			        	for(Task task: testresult){
				        	TaskTodo taskTodo = new TaskTodo(task);
				        	tasktodoList.add(taskTodo);
				        	// get todo for each task
				        	List<Todo> todosresultList = todoDAO.getByTaskID(task.getTaskID());
				        	if(todosresultList.size() > 0){
				        		for(Todo todo: todosresultList){
				        			int assigneeID = todo.getAssigneeID();
				        			User assignee = null;
				        			if(assigneeID != 0){
				        				UserDAO userDAO = new UserDAO();
				        				assignee = userDAO.get(assigneeID);
				        			}
				        			taskTodo.addAssignee(assignee);
				        			taskTodo.addTodo(todo);
				        		}
				        	}
				        }
			        }	
			        model.addAttribute("tasktodoList", tasktodoList);
			        DAO.close();
			    }
			    catch (AdException e)
			    {
			        System.out.println("Exception: " + e.getMessage());
			    }
				//get task and todo list end
				model.addAttribute("message", message);
				model.addAttribute("project", project);
				return "memberprojectHome";
			} else {
				model.addAttribute("errorMessage","Unexpected error occurs!");
				return "error";
			}
		}
		
		@RequestMapping(value="addProject.htm", method=RequestMethod.POST)
		public String submitProject(@RequestParam("name") String name, Model model, HttpSession session) {
			if(!Authentication.islogin(session)) return "index";
			if(!Authentication.isUser((User)(session.getAttribute("user")))){
				model.addAttribute("errorMessage","Not authenticated to do this operation");
				return "error";
			}
			
			//how to Check if the passed values is valid? Intercepter?			
			try
		    {
		        ProjectDAO projectDao = new ProjectDAO();		        
		        Project newproject = projectDao.create(name, DateOperation.getCurrentTime());
		        ProjectUserDAO puserDao = new ProjectUserDAO();
		        puserDao.create(newproject.getProjectID(), 
		        		((User)session.getAttribute("user")).getUserID(), "ProjectAdmin");		        
		        DAO.close();
		        model.addAttribute("message","Project "+name+" has been created successfully");
		        return "redirect:/home.htm";
		    }
		    catch (AdException e)
		    {
		        System.out.println("Exception: " + e.getMessage());
		    }
			model.addAttribute("message","Error occur, please create again!");
			return "userHome";
		}
		
		
		@RequestMapping(value="deleteProject.htm", method=RequestMethod.POST)
		public String deleteProject(@RequestParam("projectID") int projectID, Model model, HttpSession session) {
			if(!Authentication.islogin(session)) return "index";
			if(!Authentication.isUser((User)(session.getAttribute("user")))){
				model.addAttribute("errorMessage","Not authenticated to do this operation");
				return "error";
			}
			if(!Authentication.isProjectAdmin(session, projectID)){
				model.addAttribute("errorMessage","Not authenticated to do this operation");
				return "error";
			}
			
			//add member and client project homepage
			
			//how to Check if the passed values is valid? Intercepter?			
			try
		    {
		        ProjectDAO projectDao = new ProjectDAO();
		        Project project = projectDao.get(projectID);
		        String name = project.getName();
		        projectDao.delete(project);
		        ProjectUserDAO puserDao = new ProjectUserDAO();
		        List<ProjectUser> pus = puserDao.getUsersByProjectID(projectID);
		        for(int i = 0; i < pus.size(); i++){
		        	ProjectUser puser = pus.get(i);
		        	puserDao.delete(puser);
		        }
		        //delete project
		        //delete all projectuser records
		        model.addAttribute("message","Project "+name+" has been deleted successfully");
		        DAO.close();
		        return "redirect:/home.htm";
		    }
		    catch (AdException e)
		    {
		        System.out.println("Exception: " + e.getMessage());
		    }
			model.addAttribute("message","Error occur, please delete again!");
			return "redirect:/home.htm";
		}
}
