package move_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class make_dir {
	
	  static void make_dir(String path, String str_date) throws IOException, InterruptedException {       
          
          path = path + "/" + str_date;
          System.out.println(path);
          Path p = Paths.get(path);	//路徑設定
          
          /*確認資料夾是否存在*/
          if (Files.exists(p)) {
              System.out.print("資料夾已存在");
          }
          else {
              /*不存在的話,直接建立資料夾*/
              Files.createDirectory(p);
              System.out.print("已成功建立資料夾");
          }
      

	  }
	
}