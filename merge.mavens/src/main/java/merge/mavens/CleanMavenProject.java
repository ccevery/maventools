package merge.mavens;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class CleanMavenProject {

	public CleanMavenProject() {
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String configFilePath = args[0];
//		String configFilePath = "C:/dev/neonJEE/apitest/merge.mavens/aaa.txt";
		File file = new File(configFilePath);
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
		String firstLine = CopyToTempMaven.removeLine(lines);
		MavenProject targetProject = new MavenProject(firstLine);
		if(targetProject.getMainJavaDir().exists()){
			delete(targetProject.getMainJavaDir());
		}

		if(targetProject.getTestJavaDir().exists()){
			delete(targetProject.getTestJavaDir());
		}

		if(targetProject.getMainResourceDir().exists()){
			delete(targetProject.getMainResourceDir());
		}

		if(targetProject.getTestResourceDir().exists()){
			delete(targetProject.getTestResourceDir());
		}
	}
	private static void delete(File dir){
		File[] files = dir.listFiles();
		if(files == null){
			return;
		}
		for(File file : files){
			if(file.isFile()){
				file.delete();
			} else if(file.isDirectory()){
				deleteDir(file);
			}
		}
	}
	private static void deleteDir(File dir){
		File[] files = dir.listFiles();
		if(files == null){
			return;
		}
		for(File file : files){
			if(file.isFile()){
				file.delete();
			} else if(file.isDirectory()){
				deleteDir(file);
			}
		}
		dir.delete();
	}

}
