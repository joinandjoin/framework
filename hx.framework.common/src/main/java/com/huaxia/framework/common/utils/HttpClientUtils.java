package com.huaxia.framework.common.utils;

import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class HttpClientUtils {
	
	private final static String UTF8 = "UTF-8";
	
	private static HttpClientUtils instance = null;
	
	private CloseableHttpClient httpInst = null;
	
	private HttpClientConfig clientConfig = null;
	
	public synchronized static HttpClientUtils getInstance(){
		if (instance == null){
			instance = new HttpClientUtils();
		}
		return instance;
	}
	
	private void createClient() throws Exception {
		
		
		if (this.httpInst == null){
			if (this.clientConfig == null) this.clientConfig = new HttpClientConfig();
			
			
			this.httpInst = HttpClients.custom().setConnectionManager(this.clientConfig.getConnectionManager())
				.setDefaultRequestConfig(this.clientConfig.getRequestConfig()).build();
		}
	}

	/** 
	 * 绕过验证 
	 *   
	 * @return 
	 * @throws NoSuchAlgorithmException  
	 * @throws KeyManagementException  
	 */  
	public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {  
	    SSLContext sc = SSLContext.getInstance("SSLv3");  
	  
	    // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法  
	    X509TrustManager trustManager = new X509TrustManager() {  
	        @Override  
	        public void checkClientTrusted(  
	                java.security.cert.X509Certificate[] paramArrayOfX509Certificate,  
	                String paramString) {  
	        }  
	  
	        @Override  
	        public void checkServerTrusted(  
	                java.security.cert.X509Certificate[] paramArrayOfX509Certificate,  
	                String paramString) {  
	        }  
	  
	        @Override  
	        public java.security.cert.X509Certificate[] getAcceptedIssuers() {  
	            return null;  
	        }  
	    };  
	  
	    sc.init(null, new TrustManager[] { trustManager }, new SecureRandom());  
	    return sc;  
	}  
	
	
	
	private HttpClientUtils() {
		super();
		try {
			createClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CloseableHttpClient getHttpClient(){
		return this.httpInst;
	}

	public Object doThreadPost(String reqUrl,String content,List<NameValuePair> nvps) throws Exception {
		return doThreadPost(reqUrl, content, nvps, "application/json");
	}
	
	public Object doFormDataThreadPost(String reqUrl,List<NameValuePair> contentNvps,List<NameValuePair> nvps) throws Exception {
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(contentNvps, "UTF-8");
		return doThreadPost(reqUrl, EntityUtils.toString(entity), nvps, "application/x-www-form-urlencoded");
	}
	
	public Object doThreadPost(String reqUrl,String content,List<NameValuePair> nvps, String contentType) throws Exception {
		URIBuilder builder = new URIBuilder(reqUrl);
		if (null != nvps && nvps.size()>0){
			builder.addParameters(nvps);
		}
		URI reqUri = builder.build();
		HttpPost httppost = new HttpPost(reqUri);
		//httppost.setHeader("charset", UTF8);
		httppost.setEntity(new StringEntity(content, ContentType.create(contentType, UTF8)));
		
		
		PostThread post = new PostThread(this.getHttpClient(), httppost);
		post.start();
		post.join();
		return post.getReturnResponse();
	}
	
	public Object doThreadGet(String reqUrl,List<NameValuePair> nvps) throws Exception {
		URIBuilder builder = new URIBuilder(reqUrl);
		if (null != nvps && nvps.size()>0){
			builder.addParameters(nvps);
		}
		URI reqUri = builder.build();
		HttpGet httpget = new HttpGet(reqUri);
		GetThread get = new GetThread(this.getHttpClient(), httpget);
		get.start();
		get.join();
		return get.getReturnResponse();
	}
	

	protected class PostThread extends Thread  {
		private CloseableHttpClient httpClient = null;
		private HttpContext context = null;
		private HttpPost httppost = null;
		private ResponseHandler<?> handler = null;
		private Object returnResponse;
		
		public PostThread(final CloseableHttpClient httpClient, final HttpPost httppost,final ResponseHandler<?> handler, HttpContext context) {
            this.httpClient = httpClient;
            this.context = context;
            this.httppost = httppost;
            this.handler = handler;
        }
		
		public PostThread(final CloseableHttpClient httpClient, final HttpPost httppost) {
            this.httpClient = httpClient;
            this.context = HttpClientContext.create();
            this.httppost = httppost;
            this.handler = new BasicResponseHandler();
        }
		
		public Object getReturnResponse() {
			return returnResponse;
		}
		
		@Override
		public void run() {
			ObjectNode object = null;
			try {
				CloseableHttpResponse response = httpClient.execute(
						httppost, context);
				try {
					returnResponse = handler.handleResponse(response);
                } catch (Exception e){
                	e.printStackTrace();
                	try {
						object = JsonUtils.createNewObjectNode();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	object.put("success", false);
                	object.put("finish", false);
                	object.put("message", e.getMessage());
                	object.put("token", "");
                	object.put("websit", "");
                	returnResponse = object.toString();
                } finally {
                    response.close();
                }
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				try {
					object = JsonUtils.createNewObjectNode();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				object.put("success", false);
            	object.put("finish", false);
            	object.put("message", e.getMessage());
            	object.put("token", "");
            	object.put("websit", "");
            	returnResponse = object.toString();
			} catch (IOException e) {
				e.printStackTrace();
				try {
					object = JsonUtils.createNewObjectNode();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				object.put("success", false);
            	object.put("finish", false);
            	object.put("message", e.getMessage());
            	object.put("token", "");
            	object.put("websit", "");
            	returnResponse = object.toString();
			}
		}
	}

	protected class GetThread extends Thread {
		
		private CloseableHttpClient httpClient = null;
		private HttpContext context = null;
		private HttpGet httpget = null;
		private ResponseHandler<?> handler = null;
		private Object returnResponse;
		
		public Object getReturnResponse() {
			return returnResponse;
		}

		public GetThread(final CloseableHttpClient httpClient, final HttpGet httpget,final ResponseHandler<?> handler, HttpContext context) {
            this.httpClient = httpClient;
            this.context = context;
            this.httpget = httpget;
            this.handler = handler;
        }
		
		public GetThread(final CloseableHttpClient httpClient, final HttpGet httpget) {
            this.httpClient = httpClient;
            this.context = HttpClientContext.create();
            this.httpget = httpget;
            this.handler = new BasicResponseHandler();
        }
		
		@Override
		public void run() {
			 try {
				CloseableHttpResponse response = httpClient.execute(
				         httpget, context);
				try {
					returnResponse = handler.handleResponse(response);
                } catch (Exception e){
                	e.printStackTrace();
                } finally {
                    response.close();
                }
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	protected class HttpClientConfig {
		
		private int pool_MaxTotal = 300;
		private int pool_defaultMaxPerRout = 50;
		
		
		private int request_connectionRequestTimeout = 1000;
		private int request_connectTimeout = 5000;
		private int request_socketTimeout = 60000;
		
		/**
		 * ���췽����
		 * 
		 * @param pool_MaxTotal int ���ӳ���󲢷�������
		 * @param pool_defaultMaxPerRout int ��·�ɵ���󲢷�������
		 * @param request_connectionRequestTimeout int ��ȡ���ӵ�timeoutʱ��
		 * @param request_connectTimeout int �������ӵ�timeoutʱ��
		 * @param request_socketTimeout int ��ݴ��䴦��ʱ��
		 */
		public HttpClientConfig(int pool_MaxTotal, int pool_defaultMaxPerRout,
				int request_connectionRequestTimeout,
				int request_connectTimeout, int request_socketTimeout) {
			super();
			if (pool_MaxTotal>0){
				this.pool_MaxTotal = pool_MaxTotal;
			}
			
			if (pool_defaultMaxPerRout>0){
				this.pool_defaultMaxPerRout = pool_defaultMaxPerRout;
			}
			
			if (request_connectionRequestTimeout>0){
				this.request_connectionRequestTimeout = request_connectionRequestTimeout;
			}
			
			if (request_connectTimeout>0){
				this.request_connectTimeout = request_connectTimeout;
			}
			
			if (request_socketTimeout>0){
				this.request_socketTimeout = request_socketTimeout;
			}
			
		}
		
		public HttpClientConfig() {
			super();
		}

		public int getPool_MaxTotal() {
			return pool_MaxTotal;
		}
		
		public void setPool_MaxTotal(int pool_MaxTotal) {
			this.pool_MaxTotal = pool_MaxTotal;
		}
		
		public int getPool_defaultMaxPerRout() {
			return pool_defaultMaxPerRout;
		}
		
		public void setPool_defaultMaxPerRout(int pool_defaultMaxPerRout) {
			this.pool_defaultMaxPerRout = pool_defaultMaxPerRout;
		}
		
		public int getRequest_connectionRequestTimeout() {
			return request_connectionRequestTimeout;
		}
		
		public void setRequest_connectionRequestTimeout(
				int request_connectionRequestTimeout) {
			this.request_connectionRequestTimeout = request_connectionRequestTimeout;
		}
		
		public int getRequest_connectTimeout() {
			return request_connectTimeout;
		}
		
		public void setRequest_connectTimeout(int request_connectTimeout) {
			this.request_connectTimeout = request_connectTimeout;
		}
		
		public int getRequest_socketTimeout() {
			return request_socketTimeout;
		}
		
		public void setRequest_socketTimeout(int request_socketTimeout) {
			this.request_socketTimeout = request_socketTimeout;
		}
		
		public RequestConfig getRequestConfig(){
			RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(this.getRequest_socketTimeout())
				.setConnectTimeout(this.getRequest_connectTimeout())
				.setConnectionRequestTimeout(this.getRequest_connectionRequestTimeout()).build();
			return requestConfig;
		}
		
		public PoolingHttpClientConnectionManager getConnectionManager() throws Exception {
				SSLContext sslcontext = createIgnoreVerifySSL();  
		      
		       // 设置协议http和https对应的处理socket链接工厂的对象  
		       Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()  
		           .register("http", PlainConnectionSocketFactory.INSTANCE)  
		           .register("https", new SSLConnectionSocketFactory(sslcontext))  
		           .build();   
		       
		       
			 PoolingHttpClientConnectionManager pccm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			 pccm.setMaxTotal(this.getPool_MaxTotal());
			 pccm.setDefaultMaxPerRoute(this.getPool_defaultMaxPerRout());
			 return pccm;
		}
	}
}
