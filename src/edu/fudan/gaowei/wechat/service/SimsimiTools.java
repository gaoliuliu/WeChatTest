package edu.fudan.gaowei.wechat.service;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * С�Ƽ�������
 */
public class SimsimiTools {

    public static void main(String[] args) {
        try {
            System.out.println(getSimsimiContentByNiuren("���"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * С�Ƽ�������
     * 
     * @author 
     */
    public static String getSimsimiContentByNiuren(String params) {
        StringBuffer bufferRes = new StringBuffer();
        try {
            URL realUrl = new URL(
                    "http://www.niurenqushi.com/app/simsimi/ajax.aspx");
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            // ���ӳ�ʱ
            conn.setConnectTimeout(25000);
            // ��ȡ��ʱ --��������Ӧ�Ƚ���������ʱ��
            conn.setReadTimeout(25000);

            HttpURLConnection.setFollowRedirects(true);
            // ����ʽ
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Referer",
                    "http://www.niurenqushi.com/app/simsimi/");
            conn.connect();
            // ��ȡURLConnection�����Ӧ�������
            OutputStreamWriter out = new OutputStreamWriter(
                    conn.getOutputStream());
            // �����������
            out.write("txt=" + URLEncoder.encode(params, "UTF-8"));
            out.flush();
            out.close();

            InputStream in = conn.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in,
                    "UTF-8"));
            String valueString = null;
            while ((valueString = read.readLine()) != null) {
                bufferRes.append(valueString);
            }
            in.close();
            if (conn != null) {
                // �ر�����
                conn.disconnect();
            }
        } catch (Exception e) {
            System.out.println("С�Ƽ��ӿڵ��ó���" + e.getMessage());
            //return XiaoDouMachine.getXiaoDouMsg(params);
        }

        if (bufferRes.toString().equals("����")) {
            //return XiaoDouMachine.getXiaoDouMsg(params);
        }

        String finalRes = removeNews(bufferRes.toString());
        System.out.println("С�Ƽ������˻ظ�:" + finalRes);
        return finalRes;
    }

    /**
     * ���ε�ǰ�ӿ��еĹ��
     * 
     * @return
     */
    public static String removeNews(String sendMsgs) {
        // ȥ�����
        if (sendMsgs.indexOf("simsimi2") != -1) {
            sendMsgs = "ż��ëС¿��Ů����ľ�����ѣ���ӭ�ϻ�ѧ�ӵ�Ϸ   O(��_��)O";
        } else if (sendMsgs.indexOf("Database") != -1
                || sendMsgs.indexOf("Failed") != -1) {
            int random = (int) (Math.random() * 5);
            switch (random) {
            case 1:
                sendMsgs = "��";
                break;
            case 2:
                sendMsgs = "���������İ�";
                break;
            case 3:
                sendMsgs = "�ź�";
                break;
            case 4:
                sendMsgs = "��ѽ";
                break;
            case 5:
                sendMsgs = "��";
                break;
            default:
                sendMsgs = "��";
                break;
            }
        } else if (sendMsgs.indexOf("Unauthorized access") != -1) {
            sendMsgs = "����ô��������˵��ʲô��˼ѽ[���]�������ܻ���������";
        } else if (sendMsgs.indexOf("����Խ��һش�") != -1) {
            sendMsgs = "�ð�";
        }
        // �滻��������
        sendMsgs = sendMsgs.replaceAll("ɵ��", "sb");
        sendMsgs = sendMsgs.replaceAll("С��", "ëС¿");
        // sendMsgs = sendMsgs.replaceAll("С��", "С����");
        sendMsgs = sendMsgs
                .replaceAll(
                        "С����������ҳ���ַ��http://xiao.douqq.com QQ������http://www.xiugexing.com",
                        "�׼Ҳ������˵Ļ�����~");
        sendMsgs = sendMsgs.replaceAll("С��", "ëС¿");
        sendMsgs = sendMsgs.replaceAll("�˼�", "�׼�");
        sendMsgs = sendMsgs.replaceAll("�ֳ�����QQ������http://www.xiugexing.com",
                "�׼Ҳ������˵Ļ�����~");
        return sendMsgs;
    }
}
