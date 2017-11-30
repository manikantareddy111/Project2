package com.niit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Blog;

@Repository("BlogDAO")
public class BlogDAOImpl implements BlogDAO
{	
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	BlogDAO blogDAO;
	
	public BlogDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public boolean addBlog(Blog blog) 
	{
		try
		{
		sessionFactory.getCurrentSession().save(blog);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	@Transactional
	public boolean updateBlog(Blog blog) 
	{
		try
		{
		sessionFactory.getCurrentSession().update(blog);
		return true;
		}
		catch(Exception e)
		{
		System.out.println("Exception occured:"+e);
		return false;
		}	
	}

	@Transactional
	public boolean deleteBlog(Blog blog) 
	{
		try
		{
		sessionFactory.getCurrentSession().delete(blog);
		return true;
		}
		catch(Exception e)
		{
		System.out.println("Exception occured:"+e);
		return false;
		}	
	}

	@Transactional
	public Blog getBlog(int blogId) 
	{
		Session session=sessionFactory.openSession();
		Blog blog=(Blog)session.get(Blog.class, blogId);
		session.close();
		return blog;
	}
	
	@Transactional
	public List<Blog> getAllBlogs() 
	{
		Session session=sessionFactory.openSession();
		List<Blog> blogList=(List<Blog>)session.createQuery("from Blog").list();
		session.close();
		return blogList;
	}

	@Transactional
	public boolean approveBlog(Blog blog) 
	{
		try{
			blog.setStatus("A");
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			return true;
			}
			catch(Exception e)
			{
			System.out.println("Exception occured:"+e);
			return false;
			}	
	}

	public boolean rejectBlog(Blog blog) {
		// TODO Auto-generated method stub
		return false;
	}

}