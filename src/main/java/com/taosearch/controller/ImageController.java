package com.taosearch.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taosearch.util.ConfigUtils;

/**
 * 
 * @ClassName: ImageController
 * @Description: 用于图片获取
 * @author jiajia
 * @date 2016年11月17日 上午1:55:33
 *
 */
@RequestMapping("/img")
@Controller
public class ImageController {
	@RequestMapping("/download")
	public void download(HttpServletRequest request,
			HttpServletResponse response) {
		String res_path = ConfigUtils.getProp("res_path");

		// 来源网站
		String dir = request.getParameter("dir");
		// 来源位置
		String img = request.getParameter("img");

		File pic = null;
		if (img.indexOf(".gif") != -1 || img.indexOf(".jpg") != -1
				|| img.indexOf(".png") != -1) {
			pic = new File(res_path + dir + File.separator + img);
		} else {
			pic = new File(res_path + dir + File.separator + img + ".gif");
			if (!pic.exists()) {
				pic = new File(res_path + dir + File.separator + img + ".jpg");
				if (!pic.exists()) {
					pic = new File(res_path + dir + File.separator + img
							+ ".png");
				}
			}
		}

		if (pic.exists()) {
			FileInputStream fis = null;
			OutputStream os = null;
			try {
				fis = new FileInputStream(pic);
				os = response.getOutputStream();
				int count = 0;
				byte[] buffer = new byte[1024 * 1024];
				while ((count = fis.read(buffer)) != -1)
					os.write(buffer, 0, count);
				os.flush();
			} catch (IOException e) {
				System.out.println("=====================================网络异常==========================================");
				e.printStackTrace();
			} finally {
				try {
					if (os != null)
						os.close();
					if (fis != null)
						fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
