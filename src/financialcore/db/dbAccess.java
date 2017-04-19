package financialcore.db;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import financialcore.config.*;

public enum dbAccess {

	INSTANCE;

	private MongoClient mClient = null;
	private Datastore ds;

	private Morphia morphia = null;

	{
		mClient = new MongoClient(Config.DB_ADDR, Config.DB_PORT);
		morphia = new Morphia();
		ds = morphia.createDatastore(mClient, Config.DB_NAME);

		morphia.mapPackage("framework.model");
	}

	private dbAccess() {

	}

	public void closeDB() {
		if (mClient != null)
			mClient.close();
	}

	public Datastore getDatastore() {
		return ds;
	}

}
