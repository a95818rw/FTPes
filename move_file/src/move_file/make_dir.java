package move_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class make_dir {
	
	  static void make_dir(String path, String str_date) throws IOException, InterruptedException {       
          
          path = path + "/" + str_date;
          System.out.println(path);
          Path p = Paths.get(path);	//���|�]�w
          
          /*�T�{��Ƨ��O�_�s�b*/
          if (Files.exists(p)) {
              System.out.print("��Ƨ��w�s�b");
          }
          else {
              /*���s�b����,�����إ߸�Ƨ�*/
              Files.createDirectory(p);
              System.out.print("�w���\�إ߸�Ƨ�");
          }
      

	  }
	
}