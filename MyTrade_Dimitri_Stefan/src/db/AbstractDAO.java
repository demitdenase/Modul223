package db;

import javax.naming.NoPermissionException;
import db.connectionPooling.ConnectionPoolingImplementation;

public abstract class AbstractDAO {
	static{
		try {
			ConnectionPoolingImplementation.getInstance(1,5);
		} catch (NoPermissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
