package fmat.arquitectura.DBAccess.modelo;

public class DBConfigUpdater extends Thread{
	private DataBaseConfigInfo DBConfigInfo = DataBaseConfigInfo.getDataBaseConfigInfo();
	//private static DBConfigUpdater dbConfigUpdater;
	
	
	public void run(){
		monitorChanges();
	}
	
	/*
	public static DBConfigUpdater getDBConfigUpdater(){
		if(dbConfigUpdater == null)
			 dbConfigUpdater = new DBConfigUpdater();
		return dbConfigUpdater;
	}//*/
	
	public DBConfigUpdater(){
		this.start();
	}
	
	private void monitorChanges(){
		DBConfigFileMonitor dBConfigFileMonitor = new DBConfigFileMonitor();
		while(true){
			if(dBConfigFileMonitor.hasConfigFileChanged()){
				DBConfigInfo.update();
			}
			try {
				final int INTERVAL = 60000;
				Thread.sleep(INTERVAL);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
}
