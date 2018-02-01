package com.javens.http;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * HttpUtils 处理http请求
 * 
 * https://hc.apache.org/httpcomponents-client-ga/tutorial/html/connmgmt.html
 * 
 * @author liujing
 * 
 */
public class HttpClientUtils {

    private static final Logger LOG = LoggerFactory.getLogger(HttpClientUtils.class);

    private static CloseableHttpClient httpClient;

    private static final int SOCKET_TIMEOUT = 1000 * 30;
    private static final int CONNECT_TIMEOUT = 1000 * 30;

    private static final List<BasicHeader> DEFAULT_HEADER= Arrays.asList(new BasicHeader(HttpHeaders.CONTENT_ENCODING, "utf-8"));

    static {

    	 SSLContextBuilder builder = new SSLContextBuilder();
         try {
        	//TODO 可信的keystore上线之后再设置 
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf= new SSLConnectionSocketFactory(builder.build());
				Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create
						().register(
								"http", PlainConnectionSocketFactory.getSocketFactory()).register(
										"https", sslsf).build();
			// 配置同时支持 HTTP 和 HTPPS
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			// Increase max total connection to 200
			cm.setMaxTotal(2000);
			// Increase default max connection per route to 20
			cm.setDefaultMaxPerRoute(50);
			// Increase max connections for localhost:80 to 50
			HttpHost localhost = new HttpHost("locahost", 8080);
			cm.setMaxPerRoute(new HttpRoute(localhost), 50);
			
			RequestConfig config = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
			
			httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(config).build();
			//接口未幂等，暂时不重试
//			httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(config).setRetryHandler(getRetryHandler()).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException | KeyStoreException e) {
			e.printStackTrace();
		}
        // .setKeepAliveStrategy(keepAliveStrategy)

    }
    
   
    public static String post(String url, List<Header> headers, String post) throws IOException,
            ClientProtocolException {

        return request(new HttpPost(url), headers, null, post);
    }

    public static String post(String url, List<Header> headers, Map<String,String> params) throws IOException{
        List<NameValuePair> list=new ArrayList<>();
        for(String key:params.keySet())
            list.add(new BasicNameValuePair(key, params.get(key)));
        return post(url,headers,list);
    }

    public static String post(String url, Map<String,String> params) throws IOException{
        List<NameValuePair> list=new ArrayList<>();
        for(String key:params.keySet())
            list.add(new BasicNameValuePair(key, params.get(key)));
        return post(url,null,list);
    }

    public static String postO(String url, Map<String,Object> params) throws IOException{
        List<NameValuePair> list=new ArrayList<>();
        for(String key:params.keySet())
            list.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
        return post(url,null,list);
    }

    public static String post(String url, List<Header> headers, List<NameValuePair> nvps) throws IOException,
            ClientProtocolException {
        return request(new HttpPost(url), headers, nvps, null);
    }

    public static String post(String url, String post) throws IOException, ClientProtocolException {

        return request(new HttpPost(url), null, null, post);
    }

    public static String get(String url, Map<String, String> params) throws IOException, ClientProtocolException {
        StringBuilder sb=new StringBuilder(url);
        if(params!=null){
            boolean flag=url.indexOf("?")==-1;
            for(String key:params.keySet()){
                (flag?sb.append("?"):sb.append("&"))
                        .append(key).append("=")
                        .append(params.get(key));
                flag=false;
            }
        }

        return request(new HttpGet(sb.toString()), null, null, null);
    }

    public static String get(String url, List<Header> headers, Map<String, String> params) throws IOException,
            ClientProtocolException {
        StringBuilder sb=new StringBuilder(url);
        boolean flag=url.indexOf("?")==-1;
        for(String key:params.keySet()){
            (flag?sb.append("?"):sb.append("&"))
                    .append(key).append("=")
                    .append(params.get(key));
            flag=false;
        }

        return request(new HttpGet(sb.toString()), headers, null, null);
    }

    public static String postJson(String uri,String json) throws  IOException{
        List<Header> headers=Arrays.asList(new BasicHeader(HttpHeaders.CONTENT_ENCODING, "utf-8"), new BasicHeader(
            HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType()));
        return request(new HttpPost(uri), headers, null, json);
    }


    private static String request(HttpRequestBase httpRequest, List<Header> headers, List<NameValuePair> nvps, String postData) throws IOException,
            ClientProtocolException {
        Date start=new Date();
        String result = "";
        CloseableHttpResponse response = null;

        try {

            if (headers != null && !headers.isEmpty()) {
                headers.forEach(header->{httpRequest.addHeader(header);});
            }
            else{
                DEFAULT_HEADER.forEach(header ->httpRequest.addHeader(header));
            }

            if (nvps != null && !nvps.isEmpty() && httpRequest instanceof HttpPost) {
                ((HttpPost) httpRequest).setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            }

            if (postData != null && httpRequest instanceof HttpPost) {
                ((HttpPost) httpRequest).setEntity(new StringEntity(postData, "UTF-8"));
            }

            response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity);

            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity);

        } catch (ClientProtocolException e) {
            LOG.error(e.getMessage(), e);
            throw e;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw e;
        } finally {
            closeQuietly(response);
        }
        LOG.warn("http request spend time:"+(new Date().getTime()-start.getTime()));
        return result;
    }

    private static void closeQuietly(Closeable c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

    public static void main(String[] args) throws Exception {

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Content-Type", "application/json"));

        System.out.println(post("http://127.0.0.1:8080/parameter/get", headers, "{\"param\":{\"id\":\"x\"}}"));
    }

    public static class ParamMap implements Map<String,String>{
        private HashMap<String,String> params=new HashMap<>();
        @Override
        public int size() {
            return params.size();
        }

        @Override
        public boolean isEmpty() {
            return params.isEmpty();
        }

        @Override
        public boolean containsKey(Object key) {
            return params.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return params.containsKey(value);
        }

        @Override
        public String get(Object key) {
            return params.get(key);
        }

        @Override
        public String put(String key, String value) {
            return params.put(key,value);
        }
        public ParamMap putParam(String key,String value){
            put(key,value);
            return this;
        }

        @Override
        public String remove(Object key) {
            return params.remove(key);
        }

        @Override
        public void putAll(Map<? extends String, ? extends String> m) {
            params.putAll(m);
        }

        @Override
        public void clear() {
            params.clear();
        }

        @Override
        public Set<String> keySet() {
            return params.keySet();
        }

        @Override
        public Collection<String> values() {
            return params.values();
        }

        @Override
        public Set<Entry<String, String>> entrySet() {
            return params.entrySet();
        }
        
    }
    private static HttpRequestRetryHandler getRetryHandler() {
    	return new HttpRequestRetryHandler() {
    		public boolean retryRequest(IOException exception,int executionCount,HttpContext context) {
    			if (executionCount >= 3) {
    				return false;
    			}
    			if (exception instanceof InterruptedIOException) {
    				return false;
    			}
    			if (exception instanceof UnknownHostException) {
    				return false;
    			}
    			if (exception instanceof ConnectTimeoutException) {
    				return false;
    			}
    			if (exception instanceof SSLException) {
    				return false;
    			}
    			HttpClientContext clientContext = HttpClientContext.adapt(context);
    			HttpRequest request = clientContext.getRequest();
    			boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
    			if (idempotent) {
    				// 考虑幂等
    				return true;
    			}
    			return false;
    		}
    	};
    }
    
}
