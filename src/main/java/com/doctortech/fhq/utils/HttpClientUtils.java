package com.doctortech.fhq.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

	/**
	 * 发送post请求
	 * 
	 * @author jiaxm
	 * @date 2018年9月14日
	 * @param url
	 * @param files
	 *            文件列表
	 * @param originName
	 *            对应的原始文件名
	 * @param params
	 *            参数
	 * @param timeout
	 *            超时时间
	 * @return statusCode 响应状态码， data 数据
	 */
	@SuppressWarnings("deprecation")
	public static Map<String, String> httpPost(String url, List<File> files, List<String> originName,
			Map<String, Object> params, int timeout) {
		Map<String, String> resultMap = new HashMap<String, String>();
		RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(15000)
				.build();
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
		String result = "";
		int count = 0;
		try {
			HttpPost httpPost = new HttpPost(url);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
			builder.setCharset(Charset.forName("UTF-8"));
			if (files != null) {
				for (File file : files) {
					builder.addBinaryBody(originName.get(count), file);
					count++;
				}
			}
			ContentType strContent = ContentType.create("text/plain", Charset.forName("UTF-8"));
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (entry.getValue() == null)
					continue;
				builder.addPart(entry.getKey(), new StringBody(entry.getValue().toString(), strContent));
			}
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);// 执行提交

			// 设置连接超时时间
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout)
					.setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
			httpPost.setConfig(requestConfig);

			HttpEntity responseEntity = response.getEntity();
			resultMap.put("statusCode", String.valueOf(response.getStatusLine().getStatusCode()));
			resultMap.put("data", "");
			if (responseEntity != null) {
				// 将响应内容转换为字符串
				result = EntityUtils.toString(responseEntity, java.nio.charset.Charset.forName("UTF-8"));
				resultMap.put("data", result);
			}
		} catch (Exception e) {
			resultMap.put("statusCode", "error");
			resultMap.put("data", "HTTP请求出现异常: " + e.getMessage());

			Writer w = new StringWriter();
			e.printStackTrace(new PrintWriter(w));
			System.out.println("HTTP请求出现异常: " + w.toString());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultMap;
	}
}
