package p4v;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import com.perforce.p4java.core.file.FileSpecBuilder;
import com.perforce.p4java.core.file.FileSpecOpStatus;
import com.perforce.p4java.core.file.IExtendedFileSpec;
import com.perforce.p4java.core.file.IFileSpec;
import com.perforce.p4java.exception.AccessException;
import com.perforce.p4java.exception.ConfigException;
import com.perforce.p4java.exception.ConnectionException;
import com.perforce.p4java.exception.NoSuchObjectException;
import com.perforce.p4java.exception.P4JavaException;
import com.perforce.p4java.exception.RequestException;
import com.perforce.p4java.exception.ResourceException;
import com.perforce.p4java.option.server.GetDepotFilesOptions;
import com.perforce.p4java.server.IOptionsServer;
import com.perforce.p4java.server.ServerFactory;

public class Stuff {


	public void doStuff() {
		String p4vServerURI = "p4java://public.perforce.com:1666";
		int thresholdTime = 10000;
		try {
			IOptionsServer server = ServerFactory.getOptionsServer(p4vServerURI, null);
			if (server != null) {
				try {
					server.connect();
				} catch (AccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RequestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			server.setUserName("fnofgs");
			server.login("calle45balle");
			
			
			
			System.out.println("Depot files on Perforce server at URI '" + p4vServerURI + "':");
			/*List<IFileSpec> fileList = server.getDepotFiles(
					FileSpecBuilder.makeFileSpecList("//public/..."), new GetDepotFilesOptions());
			if (fileList != null) {
				for (IFileSpec fileSpec : fileList) {
					if (fileSpec != null) {
						
						if (fileSpec.getOpStatus() == FileSpecOpStatus.VALID) {
							System.out.println("Time: " + fileSpec);
							System.out.println("TIme2: " + System.currentTimeMillis());
							long time = System.currentTimeMillis() - fileSpec.getDate().getTime();
							System.out.println("Time been here:" + time);
							System.out.println(formatFileSpec(fileSpec));
							
							if (time < thresholdTime) {
								System.out.println("Boorn baby");
							}
							

						} else {
							System.err.println(fileSpec.getStatusMessage());
						}
					}
				}
			}*/
			List<IExtendedFileSpec> depotFiles = null;
			depotFiles = server.getExtendedFiles(FileSpecBuilder.makeFileSpecList("//guest/aaron_bockelie/p4powershell/..."),null);
			if ((depotFiles != null) && (depotFiles.get(0) != null) && (depotFiles.get(0).getHeadAction() != null)) {
				
				for (IExtendedFileSpec f: depotFiles) {
				//IExtendedFileSpec f = depotFiles.get(0);
				
				System.out.println("Time: " + f.getHeadTime().getTime() + "Name: " + f.getFileSize());
				
				if ((System.currentTimeMillis() - f.getHeadTime().getTime()) < thresholdTime) {
					System.out.println("Do stuff");
				}
				}
			}
			//server.setCurrentClient(server.getClient("s"));

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (P4JavaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected static String formatFileSpec(IFileSpec fileSpec) {
		return fileSpec.getDepotPathString();
	}
	public static void main(String[]args) {

		Stuff stuff = new Stuff();
		stuff.doStuff();
	}
}
