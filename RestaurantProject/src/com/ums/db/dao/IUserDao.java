package com.ums.db.dao;

import java.util.List;

import com.ums.db.UMSDBException;


public interface IUserDao<T> {
	/**
	 * @param entity
	 * @throws DAOException
	 */
	public void create (T entity) throws UMSDBException;
	
	/**
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public T read (int id) throws UMSDBException;
	
	/**
	 * @return
	 * @throws DAOException
	 */
	public List<T> list () throws UMSDBException;
	
	/**
	 * @param entity
	 * @throws DAOException
	 */
	public void update (T entity) throws UMSDBException;
	
	/**
	 * @param id
	 * @throws DAOException
	 */
	public void delete (Integer id) throws UMSDBException;
}
