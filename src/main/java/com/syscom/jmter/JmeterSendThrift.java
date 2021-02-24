package com.syscom.jmter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import com.syscom.client.sendDataClient;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class JmeterSendThrift extends AbstractJavaSamplerClient {
	private SampleResult results;

	private int count1 = 0;
	private int count2 = 0;
//	private int timestep = 0 ; 
	public void setupTest(JavaSamplerContext jsc) {
		results = new SampleResult();
	}

	public Arguments getDefaultParameters() {
		Arguments params = new Arguments();

		params.addArgument("msg", "");
	//	params.addArgument("parNum", "");
	//	params.addArgument("topicName", "");
	//	params.addArgument("BROKER_LIST", "");
		params.addArgument("NOWDATE", "");
	//	params.addArgument("SD_TRACE_NBR", "");
		return params;
	}

	public SampleResult runTest(JavaSamplerContext arg0) {
		// boolean flag = false;
		String msg = arg0.getParameter("msg");
//		String parNum = arg0.getParameter("parNum");
//		String topicName = arg0.getParameter("topicName");
		String nowdate = arg0.getParameter("NOWDATE");
//		String sd_trace_nbr = arg0.getParameter("SD_TRACE_NBR");
		// System.out.println(msg);

		try {
			results.sampleStart();
			// count1++;
//			timestep = timestep+33;
			Random r = new Random();
			//Integer i = r.nextInt(Integer.valueOf(parNum));

//			StringBuilder stringBuilder = new StringBuilder(msg);
			//2.0 add start
			byte[] byteArrMsgGBK = msg.getBytes("GBK");
			StringBuffer msgs = new StringBuffer();
			String dateString = "";
			if (nowdate.equals("Y") || nowdate.equals("y")) {
				 dateString = new SimpleDateFormat("yyyyMMddHHmmssSSS")
						.format(new Date(System.currentTimeMillis())).substring(0, 16);
			}
//			for(int a  = 49, b = 0; a<65 ;a++, b++){
//				byteArrMsgGBK[a] = dateString.getBytes("GBK")[b];
//			}
			
//			for(int a = 838 , b = 0; a < 844 ; a++ , b++){
//				byteArrMsgGBK[a] = sd_trace_nbr.getBytes("GBK")[b];
//			}
			for(int a  = 0; a<byteArrMsgGBK.length;a++){
				msgs.append((char)byteArrMsgGBK[a]);
			}
			//producer.send(new ProducerRecord<String, String>(topicName, i, i.toString(),msgs.toString() ));

			//2.0 add end
			
//			stringBuilder.replace(838, 844, sd_trace_nbr);

//			if (nowdate.equals("Y") || nowdate.equals("y")) {
//				String dateString = new SimpleDateFormat("yyyyMMddHHmmssSSS")
//						.format(new Date(System.currentTimeMillis()+timestep)).substring(0, 16);
//				stringBuilder.replace(49, 65, dateString);
//			}
//			producer.send(new ProducerRecord<String, String>(topicName, i, i.toString(), stringBuilder.toString()));
			String str = msgs.toString();

			sendDataClient dataClient = new sendDataClient() ;
			String rtn = dataClient.sendData(str);
			System.out.println(rtn);
//			results.setDataEncoding("UTF-8");
//			results.setResponseCode("Kafka+count1" + count1 + "count2" + count2);

			// results.setResponseData("Kafka+count1+"count2"+count2);
			results.setSuccessful(true);
		} catch (Exception e) {
			// TODO: handle exception
			results.setSuccessful(false);
			e.printStackTrace();
		} finally {
			results.sampleEnd();
		}
		return results;
	}

	public void teardownTest(JavaSamplerContext arg0) {
		System.out.println(count1);
		System.out.println(count2);
		//producer.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Arguments param=new Arguments();
//
//		param.addArgument("msg","F:\\tmp\\test.csv");
//		param.addArgument("NOWDATE","Y");
//
//		JavaSamplerContext arg0=new JavaSamplerContext(param);
//
//		JmeterSendThrift test=new JmeterSendThrift();
//
//		test.setupTest(arg0);
//		test.runTest(arg0);
//		test.teardownTest(arg0);



	}

}
