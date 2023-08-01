package com.cnzh.csjl.common;
import com.cnzh.csjl.common.Image;

public class IBMImageTest {
	public static void main(String[] args) {
		Image image = new Image();
		String url = image.getPicture();
		String reString= IBM.getResult(url);
		System.out.println(reString);
	}
}
