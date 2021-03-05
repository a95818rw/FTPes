package move_file;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class read_dir {
	
	String[] list1,list2;//�ɮ�/�ؼ�
	
	private String cut(String str) {
		for(int i = 0; i < 4 ; i++) str = StringUtils.substringBeforeLast(str, "-");
		return str;
	}
	
	private int getVersion(String str) {
		Scanner sc = new Scanner(str.trim());
		if(sc.hasNextInt()) {
			return Integer.parseInt(StringUtils.substringAfterLast(StringUtils.substringBeforeLast(str, "."), "-"));
		}else {
			return 0;
		}
	}
	
	private void copy(String path, String path2, String name, String target) throws IOException {
		File ftarget = new File(path2 + "/" + target);
		File file = new File(path + "/" + name);
		FileUtils.copyFile(file, ftarget);
	}
	
	private void delete(String path, String name) {
		File file = new File(path + "/" + name);
		FileUtils.deleteQuietly(file);
	}
	
	public void read(String path, String path2) {
		File folder1 = new File(path);
		File folder2 = new File(path2);
		list1 = folder1.list();
		list2 = folder2.list();
		
	}
	
	public void move(String path, String path2, String date) throws Exception {
		int i = 0, j = 0, k = 1;
		String name;
		while(i < list1.length) {
			if(StringUtils.substringAfterLast(list1[i], ".").equals("log")) {
				
				while(j < list2.length) {
					if(cut(list2[j]).equals(StringUtils.substringBeforeLast(list1[i], "."))) {
						if (k <= getVersion(list2[j])) k = getVersion(list2[j]) + 1;
						
					}
					j++;
				}
				name = StringUtils.substringBeforeLast(list1[i], ".") + "-" + date + "-" + k + ".log";
				copy(path, path2, list1[i], name);
				delete(path, list1[i]);
				k = 1;
				j = 0;
			}
			i++;
		}
	}
	
}