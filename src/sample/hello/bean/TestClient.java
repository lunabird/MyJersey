package sample.hello.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client
 * @author Icer
 *
 */
public class TestClient {
    
    private Socket s;
    private BufferedReader br;
    //private BufferedReader line;
    private PrintWriter pw;
    private String line = "";
    
    public TestClient(String path) throws Exception {
        try{
            s = new Socket("127.0.0.1",11900);          
            pw = new PrintWriter(s.getOutputStream(),true);            
            readFileByLines(path);//��Ҫ���͵��ļ���             
            pw.close();
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }    
    
    public  void readFileByLines(String fileName) { //���ж��ļ�
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            //System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            pw.println(file.getName());//���е�һ�д��ļ����ֺͺ�׺
            int i = 1;            
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
               // System.out.println(tempString);
                pw.println(tempString); //���ļ�����д��socket���У����͵���������
                i++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
    	String filePath="C:\\Users\\repace\\Desktop\\test.bat";
        new TestClient(filePath);
    }
}