package com.niit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.niit.model.Forum;

@Repository("forumDAO")
public class ForumDAOImpl implements ForumDAO
{
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	ForumDAO forumDAO;

	public ForumDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public boolean addForum(Forum forum) 
	{
		try
		{
		sessionFactory.getCurrentSession().save(forum);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	@Transactional
	public boolean updateForum(Forum forum) 
	{
		try
		{
		sessionFactory.getCurrentSession().update(forum);
		return true;
		}
		catch(Exception e)
		{
		System.out.println("Exception occured:"+e);
		return false;
		}	
	}
	
	@Transactional
	public boolean deleteForum(Forum forum) 
	{
		try
		{
		sessionFactory.getCurrentSession().delete(forum);
		return true;
		}
		catch(Exception e)
		{
		System.out.println("Exception occured:"+e);
		return false;
		}
	}

	@Transactional
	public Forum getForum(int forumId) 
	{
		Session session=sessionFactory.openSession();
		Forum forum=(Forum)session.get(Forum.class, forumId);
		session.close();
		return forum;

	}

	@Transactional
	public List<Forum> getAllForums()
	{
		Session session=sessionFactory.openSession();
		List<Forum> forumList=(List<Forum>)session.createQuery("from Forum").list();
		session.close();
		return forumList;
	}

	@Transactional
	public boolean approveForum(Forum forum) 
	{
		try{
			forum.setStatus("A");
			sessionFactory.getCurrentSession().saveOrUpdate(forum);
			return true;
			}
			catch(Exception e)
			{
			System.out.println("Exception occured:"+e);
			return false;
			}	
	}

	public boolean rejectForum(Forum forum) {
		// TODO Auto-generated method stub
		return false;
	}

}