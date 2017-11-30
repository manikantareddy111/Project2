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
import com.social.dao.BlogDAO;
import com.social.model.Blog;

@ComponentScan("com.social")
public class Blogtest 
{
	 @Autowired
	 private static BlogDAO blogDAO;
	
	 	@SuppressWarnings("resource")
		@BeforeClass
		public static void initialize()
		{
			AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
			context.register(Dbconfig.class);
			context.scan("com.social.*");
			context.refresh();
			
			blogDAO=(BlogDAO)context.getBean("blogDAO");
		}
	 	
	 	@Ignore
	 	@Test
	 	public void addBlogTest()
	 	{
			Blog blog=new Blog();
			//blog.setBlogId(1001);
			blog.setBlogName("Advanced java");
			blog.setBlogContent("Java advance concepts");
			blog.setUsername("snigda");
			blog.setStatus("A");
			blog.setLikes(5);
			blog.setCreateDate(new java.util.Date());
			
			assertTrue("Problem in Inserting Blog",blogDAO.addBlog(blog));
	 	}
	 	
	 	@Ignore
	 	@Test
	 	public void getBlogTest()
	 	{
	 		Blog blog=(Blog)blogDAO.getBlog(1);
			System.out.println("BlogName:" + blog.getBlogName());
			System.out.println("BlogContent:" +blog.getBlogContent());
			assertNotNull("blog not found", blog);
	 	}
	 	
	 	@Ignore
	 	@Test
	 	public void updateTest()
	 	{
	 			Blog blog=(Blog)blogDAO.getBlog(4);
	 			blog.setBlogName("Core Java");
	 			blog.setBlogContent("OOPS concept");
	 			assertTrue("Problem in updating Blog",blogDAO.updateBlog(blog));
	 	}
	 	
	 	@Ignore
	 	@Test
		public void deleteBlogTest()
	 	{
			Blog blog=(Blog)blogDAO.getBlog(5);
			assertTrue("Problem in deletion",blogDAO.deleteBlog(blog));
		}
	 	
	 	@Ignore
	 	@Test
		public void approveBlogTest()
	 	{
			Blog blog=(Blog)blogDAO.getBlog(2);
			assertTrue("Problem in approving",blogDAO.approveBlog(blog));
		}
	 	
	 	@Test
		public void getAllBlogsTest()
	 	{
			List<Blog> blogList=(List<Blog>)blogDAO.getAllBlogs();
			assertNotNull("Blog list not found ",blogList.get(0));
			for(Blog blog:blogList)
			{
				System.out.println("BlogID:"+blog.getBlogId() + "BlogName:"+blog.getBlogName());
			}
		}
	 	
}