package ftps;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
 
/**
 * A program demonstrates how to upload files from local computer to a remote
 * FTP server using Apache Commons Net API.
 * @author www.codejava.net
 */
public class ftps {
 
	public static void main(String[] args) {
		String ip;
		int port;
		String user;
		String pw;
		String target;
		String downloadpath;
		
		Options options = new Options();

		options.addOption("ip", true, "ip");
		options.addOption("port", true, "port");
		options.addOption("user", true, "user");
		options.addOption("pw", true, "pw");
		options.addOption("target", true, "target");
		options.addOption("downloadpath", true, "path");

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd;
		
		FTPSClient ftpsClient = new FTPSClient("TLS", true);

		try {
			cmd = parser.parse( options, args);
			ip = cmd.getOptionValue("ip");
			port = Integer.parseInt(cmd.getOptionValue("port"));
			user = cmd.getOptionValue("user");
			pw = cmd.getOptionValue("pw");
			target = cmd.getOptionValue("target");
			downloadpath = cmd.getOptionValue("downloadpath");
			
			Path p = Paths.get(downloadpath);	//���|�]�w
			 
			  /*�T�{��Ƨ��O�_�s�b*/
			  if (Files.exists(p)) {
				  System.out.print("��Ƨ��w�s�b");
			  }
			  else {
				  /*���s�b����,�����إ߸�Ƨ�*/
				  Files.createDirectory(p);
				  System.out.print("�w���\�إ߸�Ƨ�");
			}
			
			ftpsClient.connect(ip, port);
			ftpsClient.execPBSZ(0);
			ftpsClient.execPROT("P");
			ftpsClient.login(user, pw);
			ftpsClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			ftpsClient.changeWorkingDirectory(target);//���ܷ�e���|
			
			FTPFile[] files = ftpsClient.listFiles();
			ArrayList<String> files_name = new ArrayList<String>();
			boolean x = true;
			for (FTPFile ftpFile : files) {
				for(String file_name : files_name) {
					if(StringUtils.substringBeforeLast(ftpFile.getName(), ".").equals(StringUtils.substringBeforeLast(file_name, "."))) {
						x = false;
						break;
					}
				}
				if(x == true) {
					File downloadFile = new File(downloadpath + "/" + ftpFile.getName());
					files_name.add(ftpFile.getName());
					ftpFile.setName(target + "/" + ftpFile.getName());
					String remoteFile1 = ftpFile.getName();
					OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
					ftpsClient.retrieveFile(remoteFile1, outputStream);
					outputStream.close();
				}
				x = true;
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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