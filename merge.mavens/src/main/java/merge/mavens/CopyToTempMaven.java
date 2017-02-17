package merge.mavens;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CopyToTempMaven {

	public CopyToTempMaven() {
	}
	
	public static void main(String[] args) throws IOException {
		String configFilePath = args[0];
//		String configFilePath = "C:/dev/neonJEE/apitest/merge.mavens/aaa.txt";
		File file = new File(configFilePath);
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
		String firstLine = removeLine(lines);
		MavenProject targetProject = new MavenProject(firstLine);
		targetProject.createDirs();
		String line;
		
		while((line = removeLine(lines)) != null){
			MavenProject srcProject = new MavenProject(line);
			if(srcProject.getMainJavaDir().exists()){
				copy(srcProject.getMainJavaDir(), targetProject.getMainJavaDir());
			}

			if(srcProject.getTestJavaDir().exists()){
				copy(srcProject.getTestJavaDir(), targetProject.getTestJavaDir());
			}

			if(srcProject.getMainResourceDir().exists()){
				copy(srcProject.getMainResourceDir(), targetProject.getMainResourceDir());
			}

			if(srcProject.getTestResourceDir().exists()){
				copy(srcProject.getTestResourceDir(), targetProject.getTestResourceDir());
			}
			
		}
		
	}
	private static void copy(File srcDir, File destDir) throws IOException{
		File[] files = srcDir.listFiles();
		if(files == null){
			return;
		}
		for(File file : files){
			if(file.isFile()){
				File targetFile = new File(destDir, file.getName());
				Files.copy(file.toPath(), targetFile.toPath());
			} else if(file.isDirectory()){
				File targetDir = new File(destDir,file.getName());
				if(!targetDir.exists()){
					targetDir.mkdir();
				}
				copy(file, targetDir);
			}
		}
	}
	static String removeLine(List<String> lines){
		if(lines.isEmpty()){
			return null;
		}
		String line = lines.remove(0).trim();
		if(!line.isEmpty() && line.charAt(0) != '#'){
			return line;
		}
		return removeLine(lines);
	}

}
