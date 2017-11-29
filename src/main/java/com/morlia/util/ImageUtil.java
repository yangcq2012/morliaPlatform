package com.morlia.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
/*
 * 生成验证码
 */
public class ImageUtil {
	private static final char[] CHARS = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	};
	//字符数量
	private static final int SIZE = 4;
	//干扰线数量
	private static final int LINES = 6;
	//验证码图片宽，高
	private static final int WIDTH = 80;
	private static final int HEIGHT = 40;
	//验证码图片字体大小
	private static final int FONT_SIZE = 30;
	
	//随机取色
	public static Color getColor(){
		Random rand = new Random();
		Color color = new Color(rand.nextInt(256),
				rand.nextInt(256),rand.nextInt(256));
		
		return color;
	}
	
	public static Object[] createImage(){
		StringBuffer sb = new StringBuffer();
		BufferedImage image = new BufferedImage(
				WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//画随机字符
		Random rand = new Random();
		for(int i=0;i<SIZE;i++){
			int index = rand.nextInt(CHARS.length);
			g.setColor(getColor());
			g.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
			g.drawString(CHARS[index]+"", i*WIDTH/SIZE, 3*HEIGHT/4);
			sb.append(CHARS[index]);
		}
		//画干扰线
		for(int i=0;i<LINES;i++){
			g.setColor(getColor());
			g.drawLine(rand.nextInt(WIDTH), rand.nextInt(HEIGHT),
					rand.nextInt(WIDTH), rand.nextInt(HEIGHT));			
		}
		return new Object[]{sb.toString(),image};
	}
	/*
	 * main方法测试
	 */
	public static void main(String[] args) throws Exception {
		Object[] obj = createImage();
		BufferedImage image = (BufferedImage) obj[1];
		OutputStream os = new FileOutputStream("f:/1.png");
		ImageIO.write(image, "png", os);
		os.close();
	}

}

