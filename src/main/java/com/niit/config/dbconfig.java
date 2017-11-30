package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import com.niit.dao.BlogDAO;
import com.niit.dao.BlogDAOImpl;
import com.niit.dao.ForumDAO;
import com.niit.dao.ForumDAOImpl;
import com.niit.model.Blog;
import com.niit.model.Forum;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.social")
@Component
public class dbconfig 
{	
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource()
	{
		DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		driverManagerDataSource.setUsername("niit1");
		driverManagerDataSource.setPassword("niit1");
		return driverManagerDataSource;
	}
	public Properties getHibernateProperties()
	{
		Properties properties=new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect","org.hibernate.dialect.OracleDialect");
		return properties;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory()
	{
		LocalSessionFactoryBuilder localSessionFactoryBuilder=new LocalSessionFactoryBuilder(getOracleDataSource());
		localSessionFactoryBuilder.addProperties(getHibernateProperties());
		localSessionFactoryBuilder.addAnnotatedClass(Blog.class);
		localSessionFactoryBuilder.addAnnotatedClass(Forum.class);
		localSessionFactoryBuilder.scanPackages("com.social");
		System.out.println("SessionFactory Bean Created");
		return localSessionFactoryBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) 
	{
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		System.out.println("Transaction");
		return transactionManager;
	}
	
	@Autowired
	@Bean(name = "blogDAO")
	public BlogDAO getBlogDAO(SessionFactory sessionFactory)
	{
		System.out.println("Blog DAO object Created");
		return new BlogDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name = "forumDAO")
	public ForumDAO getForumDAO(SessionFactory sessionFactory)
	{
		System.out.println("Forum object Created");
		return new ForumDAOImpl(sessionFactory);
	}
}
