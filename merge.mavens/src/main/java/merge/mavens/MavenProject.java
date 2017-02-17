package merge.mavens;

import java.io.File;

public class MavenProject {
private String projectPath;
	public MavenProject(String projectPath) {
		this.projectPath = projectPath;
	}
	public File getMainJavaDir(){
		File dir = new File(projectPath);
		File subDir = new File(dir,"src/main/java");
		return subDir;
	}
	public File getTestJavaDir(){
		File dir = new File(projectPath);
		File subDir = new File(dir,"src/test/java");
		return subDir;
	}
	public File getMainResourceDir(){
		File dir = new File(projectPath);
		File subDir = new File(dir,"src/main/resources");
		return subDir;
	}
	public File getTestResourceDir(){
		File dir = new File(projectPath);
		File subDir = new File(dir,"src/test/resources");
		return subDir;
	}
	
	public void createDirs(){
		if(!getMainJavaDir().exists()){
			getMainJavaDir().mkdirs();
		}
		if(!getTestJavaDir().exists()){
			getTestJavaDir().mkdirs();
		}
		if(!getMainResourceDir().exists()){
			getMainResourceDir().mkdirs();
		}
		if(!getTestResourceDir().exists()){
			getTestResourceDir().mkdirs();
		}
	}

}
