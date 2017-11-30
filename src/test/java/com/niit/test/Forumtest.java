package com.niit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.social.config.Dbconfig;
import com.social.dao.ForumDAO;

import com.social.model.Forum;

@ComponentScan("com.social")
public class ForumTest 
{
	@Autowired
	private static ForumDAO  forumDAO;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.register(Dbconfig.class);
		context.scan("com.social.*");
		context.refresh();
		
		forumDAO=(ForumDAO)context.getBean("forumDAO");
	}
	
	@Ignore
	@Test
	public void addForumTest()
	{
		Forum forum=new Forum();
		//forum.setForumId(104);
		forum.setForumName("StackOverflow");
		forum.setForumContent("Discussion about everything");
		forum.setUserId(7);
		forum.setStatus("A");
		forum.setCreateDate(new java.util.Date());
		
		assertTrue("Problem in Inserting forum", forumDAO.addForum(forum));
	}
	
	@Ignore
	@Test
 	public void getForumTest()
 	{
 		Forum forum=(Forum)forumDAO.getForum(22);
		System.out.println("ForumName:" + forum.getForumName());
		System.out.println("ForumContent:" + forum.getForumContent());
		assertNotNull("forum not found", forum);
 	}
	
	@Ignore
	@Test
 	public void updateForumTest()
 	{
		Forum forum=(Forum)forumDAO.getForum(22);
		forum.setForumName("Geeksforgeeks");
 		forum.setForumContent("OOPS concept");
 		assertTrue("Problem in updating Forum",forumDAO.updateForum(forum));
 	}
	
	@Ignore
	@Test
	public void deleteForumTest()
 	{
		Forum forum=(Forum)forumDAO.getForum(22);
		assertTrue("Problem in deletion",forumDAO.deleteForum(forum));
	}
	
	@Ignore
	@Test
	public void approveForumTest()
	{
		Forum forum=(Forum)forumDAO.getForum(25);
		assertTrue("Problem in approving",forumDAO.approveForum(forum));
	}
	
	@Test
	public void getAllForumsTest()
	{
		List<Forum> forumList=(List<Forum>)forumDAO.getAllForums();
		assertNotNull("Forum list not found ",forumList.get(0));
		for(Forum forum:forumList)
		{
			System.out.println("ForumID:"+forum.getForumId() + "ForumName:"+forum.getForumName());
		}
	}
}