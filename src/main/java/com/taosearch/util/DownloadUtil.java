package com.taosearch.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadUtil {

	/**
	 * @param filePath
	 *            要下载的文件路径
	 * @param returnName
	 *            返回的文件名
	 * @param response
	 *            HttpServletResponse
	 * @param delFlag
	 *            是否删除文件
	 */
	protected void download(String filePath, String returnName,
			HttpServletResponse response, boolean delFlag) {
		this.prototypeDownload(new File(filePath), returnName, response,
				delFlag);
	}

	/**
	 * @param file
	 *            要下载的文件
	 * @param returnName
	 *            返回的文件名
	 * @param response
	 *            HttpServletResponse
	 * @param delFlag
	 *            是否删除文件
	 */
	protected void download(File file, String returnName,
			HttpServletResponse response, boolean delFlag) {
		this.prototypeDownload(file, returnName, response, delFlag);
	}

	/**
	 * @param file
	 *            要下载的文件
	 * @param returnName
	 *            返回的文件名
	 * @param response
	 *            HttpServletResponse
	 * @param delFlag
	 *            是否删除文件
	 */
	public void prototypeDownload(File file, String returnName,
			HttpServletResponse response, boolean delFlag) {
		// 下载文件
		FileInputStream inputStream = null;
		ServletOutputStream outputStream = null;
		try {
			if (!file.exists())
				return;
			response.reset();
			// 设置响应类型 PDF文件为"application/pdf"，WORD文件为："application/msword"，
			// EXCEL文件为："application/vnd.ms-excel"。
			response.setContentType("application/octet-stream;charset=utf-8");

			// 设置响应的文件名称,并转换成中文编码
			// returnName = URLEncoder.encode(returnName,"UTF-8");
			returnName = response.encodeURL(new String(returnName.getBytes(),
					"iso8859-1")); // 保存的文件名,必须和页面编码一致,否则乱码

			// attachment作为附件下载；inline客户端机器有安装匹配程序，则直接打开；注意改变配置，清除缓存，否则可能不能看到效果
			response.addHeader("Content-Disposition", "attachment;filename="
					+ returnName);

			// 将文件读入响应流
			inputStream = new FileInputStream(file);
			outputStream = response.getOutputStream();
			int length = 1024;
			int readLength = 0;
			byte buf[] = new byte[1024];
			readLength = inputStream.read(buf, 0, length);
			while (readLength != -1) {
				outputStream.write(buf, 0, readLength);
				readLength = inputStream.read(buf, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 删除原文件

			if (delFlag) {
				file.delete();
			}
		}
	}

	/**
	 * by 2013-10-17
	 * 
	 * @param byteArrayOutputStream
	 *            将文件内容写入ByteArrayOutputStream
	 * @param response
	 *            HttpServletResponse 写入response
	 * @param returnName
	 *            返回的文件名
	 */
	public void download(ByteArrayOutputStream byteArrayOutputStream,
			HttpServletRequest request, HttpServletResponse response,
			String returnName) throws IOException {
		response.setContentType("application/octet-stream;charset=utf-8");
		// returnName = response.encodeURL(new
		// String(returnName.getBytes(),"iso8859-1")); //保存的文件名,必须和页面编码一致,否则乱码
		response.addHeader("Content-Disposition",
				"attachment;" + this.encodeFileName(request, returnName));
		response.setContentLength(byteArrayOutputStream.size());

		ServletOutputStream outputstream = response.getOutputStream(); // 取得输出流
		byteArrayOutputStream.writeTo(outputstream); // 写到输出流
		byteArrayOutputStream.close(); // 关闭
		outputstream.flush(); // 刷数据
	}

	public static String encodeFileName(HttpServletRequest request,
			String fileName) {
		String userAgent = request.getHeader("User-Agent");
		String rtn = "";
		try {
			String new_filename = URLEncoder.encode(fileName, "UTF8");
			// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
			rtn = "filename=\"" + new_filename + "\"";
			if (userAgent != null) {
				userAgent = userAgent.toLowerCase(); // IE浏览器，只能采用URLEncoder编码
				if (userAgent.indexOf("msie") != -1) {
					rtn = "filename=\"" + new_filename + "\"";
				} else if (userAgent.indexOf("opera") != -1) { // Opera浏览器只能采用filename*
					rtn = "filename*=UTF-8''" + new_filename;
				} else if (userAgent.indexOf("safari") != -1) { // Safari浏览器，只能采用ISO编码的中文输出
					rtn = "filename=\""
							+ new String(fileName.getBytes("UTF-8"),
									"ISO8859-1") + "\"";
				} else if (userAgent.indexOf("applewebkit") != -1) { // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
					// new_filename = MimeUtility.encodeText(fileName, "UTF8",
					// "B");
					rtn = "filename=\"" + new_filename + "\"";
				} else if (userAgent.indexOf("mozilla") != -1) { // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
					rtn = "filename*=UTF-8''" + new_filename;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return rtn;
	}
}
