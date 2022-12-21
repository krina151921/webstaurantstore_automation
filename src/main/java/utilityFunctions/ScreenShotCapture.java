package utilityFunctions;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenShotCapture extends InitializeDriver{
	
	public static void capture(String testCaseName) throws IOException{
		String strPath="Screenshot/";
		TakesScreenshot ts=(TakesScreenshot)driver;
		File screenShotFile=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShotFile,new File(strPath+testCaseName+".png"));
		
	 }
	
	public static void alertCapture(String testCaseName) throws IOException, AWTException{
		String strPath="Screenshot/";
		Robot robot=new Robot();
		Rectangle rect=new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage image = robot.createScreenCapture(rect);
        ImageIO.write(image, "png", new File(strPath+testCaseName+".png"));
	}
	 
	
	

}