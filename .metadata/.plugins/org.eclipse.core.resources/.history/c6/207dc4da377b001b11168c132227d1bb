package ftps;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
 
/**
 * A program demonstrates how to upload files from local computer to a remote
 * FTP server using Apache Commons Net API.
 * @author www.codejava.net
 */
public class main {
 
	public static void main(String[] args) {
		String ip = "10.253.46.102";
		int port = 990;
		String user = "client1";
		String pw = "c1234567890";
		String target = "/test";
		String downloadpath = "D:/temp";
		
		FTPSClient ftpsClient = new FTPSClient("TLS", true);

		try {
			ftpsClient.connect(ip, port);
			ftpsClient.execPBSZ(0);
			ftpsClient.execPROT("P");
			ftpsClient.login(user, pw);
			ftpsClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			ftpsClient.changeWorkingDirectory(target);//改變當前路徑
			
			FTPFile[] files = ftpsClient.listFiles();
			
			for (FTPFile ftpFile : files) {
				File downloadFile = new File(downloadpath + "/" + ftpFile.getName());
				ftpFile.setName(target + "/" + ftpFile.getName());
				System.out.println(ftpFile.getName());
				String remoteFile1 = ftpFile.getName();
				
				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
				ftpsClient.retrieveFile(remoteFile1, outputStream);
				outputStream.close();
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ftpsClient.isConnected()) {
					ftpsClient.logout();
					ftpsClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}