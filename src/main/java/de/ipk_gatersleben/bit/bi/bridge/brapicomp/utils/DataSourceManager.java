package de.ipk_gatersleben.bit.bi.bridge.brapicomp.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


/**
 * Manages Daos, tables and connections.
 */
public class DataSourceManager {
	private static DataSourceManager manager = new DataSourceManager( );
	private static HashMap<String, Dao> daoMap = new HashMap<String, Dao>();
	private static ConnectionSource source;
	
	/* A private Constructor prevents any other 
	* class from instantiating.
	*/
	private DataSourceManager(){ }
	   
	/* Static 'instance' method */
	public static DataSourceManager getInstance( ) {
		return manager;
	}
	
	/**
	 * Add class to Dao Hashmap
	 * @param daoClass Dao to be added
	 * @throws SQLException .
	 */
	public static void addDao(Class daoClass) throws SQLException{
		daoMap.put(daoClass.getName(), DaoManager.createDao(source, daoClass));
	}
	
	/**
	 * Get Dao from hashmap
	 * @param c Class to get dao for
	 * @return Dao
	 */
	public static Dao getDao(Class c){
		return daoMap.get(c.getName());
	}
	
	public static void createTable(Class tableClass) throws SQLException {
		TableUtils.createTableIfNotExists(source, tableClass);
	}
	
	public static void setConnectionSource(ConnectionSource s){
		source = s;
	}
	
	public static void closeConnectionSource() throws IOException{
		source.close();
	}
}
